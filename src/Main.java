package io.openruntimes.java.src;

import io.openruntimes.java.RuntimeContext;
import io.openruntimes.java.RuntimeOutput;
import java.util.HashMap;
import java.util.Map;
import io.appwrite.Client;

import Utils;

public class Main {

    public RuntimeOutput main(RuntimeContext context) throws Exception {
        if (context.getReq().getMethod().equals("GET")) {
            return context.getRes().send(Utils.getStaticFile("index.html"));
        }
    }
}
