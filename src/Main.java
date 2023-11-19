package io.openruntimes.java.src;

import io.openruntimes.java.RuntimeContext;
import io.openruntimes.java.RuntimeOutput;

public class Main {

    public RuntimeOutput main(RuntimeContext context) throws Exception {
        String reqVariables[] = {
            "VONAGE_API_KEY",
            "VONAGE_API_SECRET",
            "VONAGE_API_SIGNATURE_SECRET",
            "VONAGE_WHATSAPP_NUMBER"
        };
        Utils.throw_if_missing(System.getenv(), reqVariables);

        if (context.getReq().getMethod().equals("GET")) {
            return context.getRes().send("Got GET Request");
        }
        return context.getRes().send("Not GET Request: "+context.getReq().getMethod());
    }
}
