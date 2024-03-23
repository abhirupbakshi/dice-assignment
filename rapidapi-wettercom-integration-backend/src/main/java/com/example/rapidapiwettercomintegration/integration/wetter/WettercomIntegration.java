package com.example.rapidapiwettercomintegration.integration.wetter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class WettercomIntegration {

  private final ObjectMapper mapper;
  private final String rapidApiKey;

  @Autowired
  public WettercomIntegration(
      ObjectMapper mapper, @Value("${spring.application.rapid-api-key}") String rapidApiKey) {
    this.mapper = mapper;
    this.rapidApiKey = rapidApiKey;
  }

  public SummaryByLocationNameResponse getForecastSummaryByLocationName(String location)
      throws JsonProcessingException {
    String uri = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + location + "/summary/";

    try {
      ResponseEntity<String> response =
          WebClient.create(uri)
              .get()
              .header("X-RapidAPI-Key", rapidApiKey)
              .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
              .retrieve()
              .toEntity(String.class)
              .block();

      return mapper.readValue(response.getBody(), SummaryByLocationNameResponse.class);

    } catch (WebClientResponseException e) {
      HttpStatus status = Objects.requireNonNull(HttpStatus.resolve(e.getStatusCode().value()));
      if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
        throw new RuntimeException("Received status code: " + status);
      }

      Map body = mapper.readValue(e.getResponseBodyAs(String.class), Map.class);
      ProblemDetail pd = ProblemDetail.forStatus(status);

      if (body.get("error") instanceof Map<?, ?> error) {
        pd.setProperty("details", error.get("details"));
      }

      log.warn("Response error: " + e.getResponseBodyAs(String.class));
      throw new ErrorResponseException(status, pd, null);
    }
  }
}
