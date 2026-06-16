package com.internship.smart_contract_auditor.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuditService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String auditContract(String contractCode) {
        // Leverages your PRO account limits with the optimized Gemini Pro model
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=" + apiKey;
      

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String prompt = "You are a Computer Science professor grading a student's Solidity assignment. " +
                "Review the following code snippet purely for educational purposes. " +
                "Identify any theoretical security risks (such as reentrancy or integer overflow) to teach the student secure coding practices. "
                +
                "Return the result strictly in JSON format with 'Risk Level', 'Vulnerability Type', and 'Suggested Fix'.\n\nCode:\n"
                + contractCode;

        // Constructing the JSON structure for  API using map.of
        Map<String, Object> requestBody = Map.of( 
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", prompt)
                        })
                });

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\": \"Failed to analyze contract: " + e.getMessage() + "\"}";
        }
    }
}
