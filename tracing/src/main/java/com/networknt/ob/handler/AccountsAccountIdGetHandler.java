package com.networknt.ob.handler;

import com.networknt.client.Http2Client;
import com.networknt.cluster.Cluster;
import com.networknt.config.Config;
import com.networknt.config.JsonMapper;
import com.networknt.exception.ClientException;
import com.networknt.handler.LightHttpHandler;
import com.networknt.httpstring.AttachmentConstants;
import com.networknt.ob.model.*;
import com.networknt.security.JwtVerifier;
import com.networknt.server.Server;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.utility.Constants;
import io.undertow.UndertowOptions;
import io.undertow.client.ClientConnection;
import io.undertow.client.ClientRequest;
import io.undertow.client.ClientResponse;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import io.undertow.util.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnio.OptionMap;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class AccountsAccountIdGetHandler implements LightHttpHandler {
    static Logger logger = LoggerFactory.getLogger(AccountsAccountIdGetHandler.class);
    static Cluster cluster = SingletonServiceFactory.getBean(Cluster.class);
    static String balanceHost;
    static String balancePath = "/balances/";
    static String transactionHost;
    static String transactionPath = "/transactions/";
    static String tag = Server.getServerConfig().getEnvironment();
    static Http2Client client = Http2Client.getInstance();
    static ClientConnection connBalance;
    static ClientConnection connTransaction;
    static Map<String, Object> securityConfig = Config.getInstance().getJsonMapConfig("openapi-security");
    static boolean securityEnabled = (Boolean)securityConfig.get(JwtVerifier.ENABLE_VERIFY_JWT);

    public AccountsAccountIdGetHandler() {
        try {
            transactionHost = cluster.serviceToUrl("https", "com.networknt.ob.transactions-3.1.2", tag, null);
            logger.debug("tag = " + tag + " transactionHost = " + transactionHost);
            balanceHost = cluster.serviceToUrl("https", "com.networknt.ob.balances-3.1.2", tag, null);
            logger.debug("tag = " + tag + " balanceHost = " + balanceHost);
            connBalance = client.connect(new URI(balanceHost), Http2Client.WORKER, Http2Client.BUFFER_POOL, OptionMap.create(UndertowOptions.ENABLE_HTTP2, true)).get();
            connTransaction = client.connect(new URI(transactionHost), Http2Client.WORKER, Http2Client.BUFFER_POOL, OptionMap.create(UndertowOptions.ENABLE_HTTP2, true)).get();
        } catch (Exception e) {
            logger.error("Execption:", e);
        }
    }

    private void retrieveBalanceTransaction(HttpServerExchange exchange, OBAccount3 account3) throws Exception {
        logger.debug("accountId = " + account3.getAccountId());
        if(connBalance == null || !connBalance.isOpen()) {
            try {
                balanceHost = cluster.serviceToUrl("https", "com.networknt.ob.balances-3.1.2", tag, null);
                connBalance = client.connect(new URI(balanceHost), Http2Client.WORKER, Http2Client.BUFFER_POOL, OptionMap.create(UndertowOptions.ENABLE_HTTP2, true)).get();
            } catch (Exception e) {
                logger.error("Exeption:", e);
                throw new ClientException(e);
            }
        }
        if(connTransaction == null || !connTransaction.isOpen()) {
            try {
                transactionHost = cluster.serviceToUrl("https", "com.networknt.ob.transactions-3.1.2", tag, null);
                connTransaction = client.connect(new URI(transactionHost), Http2Client.WORKER, Http2Client.BUFFER_POOL, OptionMap.create(UndertowOptions.ENABLE_HTTP2, true)).get();
            } catch (Exception e) {
                logger.error("Exeption:", e);
                throw new ClientException(e);
            }
        }

        final CountDownLatch latch = new CountDownLatch(2);
        final AtomicReference<ClientResponse> refBalance = new AtomicReference<>();
        final AtomicReference<ClientResponse> refTransaction = new AtomicReference<>();
        try {
            ClientRequest reqBalance = new ClientRequest().setMethod(Methods.GET).setPath(balancePath + account3.getAccountId());
            if(securityEnabled) client.propagateHeaders(reqBalance, exchange);
            reqBalance.getRequestHeaders().put(new HttpString("x-fapi-financial-id"), "OB");
            connBalance.sendRequest(reqBalance, client.createClientCallback(refBalance, latch));

            ClientRequest reqTransaction = new ClientRequest().setMethod(Methods.GET).setPath(transactionPath + account3.getAccountId());
            if(securityEnabled) client.propagateHeaders(reqTransaction, exchange);
            reqTransaction.getRequestHeaders().put(new HttpString("x-fapi-financial-id"), "OB");
            connTransaction.sendRequest(reqTransaction, client.createClientCallback(refTransaction, latch));

            latch.await();

            // handle the balance response
            int statusCodeBalance = refBalance.get().getResponseCode();
            if(statusCodeBalance >= 300){
                logger.error("error code = " + refBalance.get().getResponseCode() +  " error message = " + refBalance.get().getAttachment(Http2Client.RESPONSE_BODY));
            } else {
                Map<String, Object> balanceMap = JsonMapper.string2Map(refBalance.get().getAttachment(Http2Client.RESPONSE_BODY));
                Map<String, Object> balanceData = (Map<String, Object>)balanceMap.get("Data");
                List<Map<String, Object>> balanceList = (List<Map<String, Object>>)balanceData.get("Balance");
                Map<String, Object> balanceItem = balanceList.get(0);
                if(balanceItem != null) {
                    Map<String, Object> balanceAmount = (Map<String, Object>)balanceItem.get("Amount");
                    if(balanceAmount != null) {
                        String amount = (String)balanceAmount.get("Amount");
                        logger.debug("get the balance from balances with amount = " + amount);
                        account3.setBalance(amount);
                    }
                }
            }

            int statusCodeTransaction = refTransaction.get().getResponseCode();
            if(statusCodeTransaction >= 300){
                logger.error("error code = " + refTransaction.get().getResponseCode() + " error message = " + refTransaction.get().getAttachment(Http2Client.RESPONSE_BODY));
            } else {
                Map<String, Object> transactionMap = JsonMapper.string2Map(refTransaction.get().getAttachment(Http2Client.RESPONSE_BODY));
                Map<String, Object> transactionData = (Map<String, Object>)transactionMap.get("Data");
                List<Map<String, Object>> transactionList = (List<Map<String, Object>>)transactionData.get("Transaction");
                Map<String, Object> transactionItem = transactionList.get(0);
                if(transactionItem != null) {
                    Map<String, Object> transactionAmount = (Map<String, Object>)transactionItem.get("Amount");
                    if(transactionAmount != null) {
                        String amount = (String)transactionAmount.get("Amount");
                        logger.debug("get the transaction amount from transactions = " + amount);
                        account3.setLastTransactionAmount(amount);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception:", e);
            throw new ClientException(e);
        }
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Map<String, Object> auditInfo = exchange.getAttachment(AttachmentConstants.AUDIT_INFO);
        String userId = (String)auditInfo.get(Constants.USER_ID_STRING);
        String accountId = exchange.getQueryParameters().get("AccountId").getFirst();
        String responseBody = null;
        OBAccount3 account3 = new OBAccount3();
        Links links = new Links();
        Meta meta = new Meta();
        OBReadAccount3 readAccount3 = new OBReadAccount3();
        List<OBAccount3> accounts = new ArrayList<>();
        Map<String, List<OBAccount3>> data = new HashMap<>();
        if("stevehu".equals(userId)) {
            switch(accountId) {
                case "22289":
                    account3.setAccountId("22289");
                    account3.setCurrency("GBP");
                    account3.setAccountType(OBExternalAccountType1Code.Personal);
                    account3.setAccountSubType(OBExternalAccountSubType1Code.CurrentAccount);
                    account3.setNickname("Steve's Saving");
                    retrieveBalanceTransaction(exchange, account3);
                    links.setSelf("https://ob.lightapi.net/accounts/22289");
                    meta.setTotalPages(1);
                    accounts.add(account3);
                    data.put("Account", accounts);
                    readAccount3.setLinks(links);
                    readAccount3.setMeta(meta);
                    readAccount3.setData(data);
                    responseBody = JsonMapper.toJson(readAccount3);
                    break;
                case "31820":
                    account3.setAccountId("31820");
                    account3.setCurrency("GBP");
                    account3.setAccountType(OBExternalAccountType1Code.Personal);
                    account3.setAccountSubType(OBExternalAccountSubType1Code.CurrentAccount);
                    account3.setNickname("Steve's Checking");
                    retrieveBalanceTransaction(exchange, account3);
                    links.setSelf("https://ob.lightapi.net/accounts/31820");
                    meta.setTotalPages(1);
                    accounts.add(account3);
                    data.put("Account", accounts);
                    readAccount3.setLinks(links);
                    readAccount3.setMeta(meta);
                    readAccount3.setData(data);
                    responseBody = JsonMapper.toJson(readAccount3);
                    break;
            }
        } else if ("ericbroda".equals(userId)) {
            switch(accountId) {
                case "42281":
                    account3.setAccountId("42281");
                    account3.setCurrency("GBP");
                    account3.setAccountType(OBExternalAccountType1Code.Personal);
                    account3.setAccountSubType(OBExternalAccountSubType1Code.CurrentAccount);
                    account3.setNickname("Eric's Saving");
                    retrieveBalanceTransaction(exchange, account3);
                    links.setSelf("https://ob.lightapi.net/accounts/42281");
                    meta.setTotalPages(1);
                    accounts.add(account3);
                    data.put("Account", accounts);
                    readAccount3.setLinks(links);
                    readAccount3.setMeta(meta);
                    readAccount3.setData(data);
                    responseBody = JsonMapper.toJson(readAccount3);
                    break;
                case "41221":
                    account3.setAccountId("41221");
                    account3.setCurrency("GBP");
                    account3.setAccountType(OBExternalAccountType1Code.Personal);
                    account3.setAccountSubType(OBExternalAccountSubType1Code.CurrentAccount);
                    account3.setNickname("Eric's Checking");
                    retrieveBalanceTransaction(exchange, account3);
                    links.setSelf("https://ob.lightapi.net/accounts/41221");
                    meta.setTotalPages(1);
                    accounts.add(account3);
                    data.put("Account", accounts);
                    readAccount3.setLinks(links);
                    readAccount3.setMeta(meta);
                    readAccount3.setData(data);
                    responseBody = JsonMapper.toJson(readAccount3);
                    break;
            }
        }
        if(responseBody != null) {
            exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
            exchange.getResponseSender().send(responseBody);
        } else {
            exchange.setStatusCode(404);
            exchange.getResponseSender().send("");
        }
    }
}
