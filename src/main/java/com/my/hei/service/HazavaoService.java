package com.my.hei.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class HazavaoService {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${API_KEY}")
  private String apikey;

  public String getDefinition(String teny) {
    String apiUrl = "https://api.openai.com/v1/chat/completions";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(apikey);

    Map<String, Object> message = Map.of(
            "role", "user",
            "content", "Hazavao amin'ny teny malagasy ilay tenay: " +teny
    );

    Map<String, Object> body = Map.of(
            "model", "gpt-3.5-turbo",
            "messages", List.of(message),
            "temperature", 0.7
    );

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

    ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);

    // Ici c'est l'extraction du texte
    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
    Map<String, Object> firstChoice = choices.get(0);
    Map<String, String> messageContent = (Map<String, String>) firstChoice.get("message");
    return messageContent.get("content");
  }
}
