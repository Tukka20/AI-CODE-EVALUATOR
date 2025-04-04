package com.example.smartcodeevaluator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeepSeekService {
    @Value("${deepseek.api.key}")
    private String apiKey;

    @Value("${deepseek.api.url.plagiarism}")
    private String plagiarismUrl;

    @Value("${deepseek.api.url.feedback}")
    private String feedbackUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String checkPlagiarism(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"code\": \"" + code + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            String response = restTemplate.exchange(plagiarismUrl, HttpMethod.POST, entity, String.class).getBody();
            return response; // Example: "Plagiarism score: 10% (No significant matches)"
        } catch (Exception e) {
            return "Plagiarism check failed: " + e.getMessage();
        }
    }

    public String getAiFeedback(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"code\": \"" + code + "\", \"prompt\": \"Provide feedback on code quality and efficiency\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            String response = restTemplate.exchange(feedbackUrl, HttpMethod.POST, entity, String.class).getBody();
            return response; // Example: "Feedback: Good use of loops, but consider using a hash map."
        } catch (Exception e) {
            return "Feedback generation failed: " + e.getMessage();
        }
    }
}