package io.openruntimes.java.src;

import io.openruntimes.java.RuntimeContext;
import io.openruntimes.java.RuntimeOutput;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import java.io.IOException;

import java.util.Base64;

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
        
        try{
			Gson gson = new Gson();
			Map<String, String> data = new HashMap<String, String>();
			data.put("from", System.getenv("VONAGE_WHATSAPP_NUMBER"));
			data.put("to", System.getenv("TO_NUMBER"));
			data.put("message_type", "text");
			data.put("text", "Hi, this is body");
			data.put("channel", "whatsapp");
	
			String body = gson.toJson(data);
	
			String basicAuth= System.getenv("VONAGE_API_KEY") + ":" + System.getenv("VONAGE_API_SECRET");
			String basicAuthToken = "Basic " + Base64.getEncoder().encodeToString(basicAuth.getBytes());
			
			HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https://messages-sandbox.nexmo.com/v1/messages"))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("Authorization", basicAuthToken)
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();

			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Status Code: " + response.statusCode());
		}catch(URISyntaxException | IOException | InterruptedException e){
			e.printStackTrace();
		}

        return context.getRes().send("{\"ok\": true}");
    }
}
