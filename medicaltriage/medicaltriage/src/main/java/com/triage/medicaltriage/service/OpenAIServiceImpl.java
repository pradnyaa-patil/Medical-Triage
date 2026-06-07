package com.triage.medicaltriage.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String prompt) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(apiKey);

        Map<String, Object> request = new HashMap<>();

        request.put( "model", "gpt-4.1-mini");

        List<Map<String, String>> messages = new ArrayList<>();

        messages.add(Map.of("role", "user","content", prompt));

        request.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        List<Map<String,Object>> choices = (List<Map<String,Object>>) response.getBody().get("choices");

        Map<String,Object> firstChoice = choices.get(0);

        Map<String,Object> message = (Map<String,Object>) firstChoice.get("message");

        return message.get("content").toString();
    }
}
