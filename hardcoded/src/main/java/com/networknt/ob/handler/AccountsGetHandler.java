package com.networknt.ob.handler;

import com.networknt.handler.LightHttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;

public class AccountsGetHandler implements LightHttpHandler {
    
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // get the userId from the JWT claims. It is already in the AuditInfo attachment.
        String userId = "stevehu";
        String responseBody = null;
        if("stevehu".equals(userId)) {
            responseBody = "{\"Data\":{\"Account\":[{\"AccountId\":\"22289\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2019-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Steve's Saving\"},{\"AccountId\":\"31820\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2018-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Steve's Checking\"}]},\"Links\":{\"Self\":\"https://api.alphabank.com/open-banking/v3.1/aisp/accounts/\"},\"Meta\":{\"TotalPages\":1}}";
        } else if ("ericbroda".equals(userId)) {
            responseBody = "{\"Data\":{\"Account\":[{\"AccountId\":\"42281\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2019-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Eric's Saving\"},{\"AccountId\":\"41221\",\"Status\":\"Enabled\",\"StatusUpdateDateTime\":\"2018-01-01T06:06:06+00:00\",\"Currency\":\"GBP\",\"AccountType\":\"Personal\",\"AccountSubType\":\"CurrentAccount\",\"Nickname\":\"Eric's Checking\"}]},\"Links\":{\"Self\":\"https://api.alphabank.com/open-banking/v3.1/aisp/accounts/\"},\"Meta\":{\"TotalPages\":1}}";
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
