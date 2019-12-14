package com.networknt.ob.handler;

import com.networknt.handler.LightHttpHandler;
import com.networknt.httpstring.AttachmentConstants;
import com.networknt.utility.Constants;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;

public class AccountsAccountIdGetHandler implements LightHttpHandler {
    
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Map<String, Object> auditInfo = exchange.getAttachment(AttachmentConstants.AUDIT_INFO);
        String userId = (String)auditInfo.get(Constants.USER_ID_STRING);
        String accountId = exchange.getQueryParameters().get("AccountId").getFirst();
        String responseBody = null;
        if("stevehu".equals(userId)) {
            switch(accountId) {
                case "22289":
                    responseBody = "{\"Data\":{\"Account\":[{\"AccountId\":\"22289\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2019-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Steve's Saving\"}]},\"Links\":{\"Self\":\"https://api.alphabank.com/open-banking/v3.1/aisp/accounts/22289\"},\"Meta\":{\"TotalPages\":1}}";
                    break;
                case "31820":
                    responseBody = "{\"Data\":{\"Account\":[{\"AccountId\":\"31820\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2019-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Steve's Checking\"}]},\"Links\":{\"Self\":\"https://api.alphabank.com/open-banking/v3.1/aisp/accounts/31820\"},\"Meta\":{\"TotalPages\":1}}";
                    break;
            }
        } else if ("ericbroda".equals(userId)) {
            switch(accountId) {
                case "42281":
                    responseBody = "{\"Data\":{\"Account\":[{\"AccountId\":\"42281\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2019-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Eric's Saving\"}]},\"Links\":{\"Self\":\"https://api.alphabank.com/open-banking/v3.1/aisp/accounts/42281\"},\"Meta\":{\"TotalPages\":1}}";
                    break;
                case "41221":
                    responseBody = "{\"Data\":{\"Account\":[{\"AccountId\":\"41221\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2019-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Eric's Checking\"}]},\"Links\":{\"Self\":\"https://api.alphabank.com/open-banking/v3.1/aisp/accounts/41221\"},\"Meta\":{\"TotalPages\":1}}";
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
