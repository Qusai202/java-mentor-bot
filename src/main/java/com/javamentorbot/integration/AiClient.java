package com.javamentorbot.integration;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AiClient {

    
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-3.5-turbo"; 

    private final HttpClient client;

    public AiClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public String getResponse(String userQuestion) {
        
       
        if (API_KEY.contains("PUT_YOUR_REAL_API_KEY_HERE")) {
            return "Error: You need to paste your real API Key inside AiClient.java line 13!";
        }

        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", MODEL);
            requestBody.put("max_tokens", 150);

            JSONArray messages = new JSONArray();

            String systemPrompt = "You are a patient and expert Java Tutor for university students. "
                    + "Your goal is to explain Java concepts clearly using simple language. "
                    + "Provide step-by-step explanations. "
                    + "Do NOT write full code solutions for homework assignments; instead, guide the student. "
                    + "Keep the answer under 3 sentences.";

            messages.put(new JSONObject().put("role", "system").put("content", systemPrompt));
            messages.put(new JSONObject().put("role", "user").put("content", userQuestion));

            requestBody.put("messages", messages);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

         
            if (response.statusCode() != 200) {
                 return "API Error: " + response.body();
            }

            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray choices = jsonResponse.getJSONArray("choices");
            String aiContent = choices.getJSONObject(0)
                                     .getJSONObject("message")
                                     .getString("content");

            return aiContent.trim();

        } catch (Exception e) {
            return "Error connecting to AI: " + e.getMessage();
        }
    }
}