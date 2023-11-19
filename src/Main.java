package io.openruntimes.java.src;

import io.openruntimes.java.RuntimeContext;
import io.openruntimes.java.RuntimeOutput;
import java.util.HashMap;
import java.util.Map;
import io.appwrite.Client;

public class Main {

    public RuntimeOutput main(RuntimeContext context) throws Exception {
        if (context.getReq().getMethod().equals("GET")) {
            return context.getRes().send("Got GET Request");
        }
        return context.getRes().send("Not GET Request: "+context.getReq().getMethod());
    }
}
