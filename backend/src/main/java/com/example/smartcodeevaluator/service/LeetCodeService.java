package com.example.smartcodeevaluator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LeetCodeService {
    @Value("${leetcode.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchProblem(String problemSlug) {
        String query = "{ \"query\": \"query { problem(slug: \\\"" + problemSlug + "\\\") { title description } }\" }";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        try {
            String response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class).getBody();
            return response; // Example: "{ \"data\": { \"problem\": { \"title\": \"Two Sum\", \"description\": \"Given an array...\" } } }"
        } catch (Exception e) {
            return "Failed to fetch problem: " + e.getMessage();
        }
    }
}