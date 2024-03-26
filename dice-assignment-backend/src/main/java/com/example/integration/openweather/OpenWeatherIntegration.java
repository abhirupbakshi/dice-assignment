package com.example.integration.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class OpenWeatherIntegration {

  private final ObjectMapper mapper;
  private final String openWeatherKey;

  @Autowired
  public OpenWeatherIntegration(
      ObjectMapper mapper, @Value("${spring.application.open-weather-key}") String openWeatherKey) {
    this.openWeatherKey = openWeatherKey;
    this.mapper = mapper;
  }

  public ThreeHourForecastDataResponse getThreeHourIntervalForecast(String location)
      throws JsonProcessingException {

    String uri =
        "https://api.openweathermap.org/data/2.5/forecast?q="
            + location
            + "&appid="
            + openWeatherKey;

    try {
      ResponseEntity<String> response =
          WebClient.create(uri).get().retrieve().toEntity(String.class).block();

      return mapper.readValue(response.getBody(), ThreeHourForecastDataResponse.class);
    } catch (WebClientResponseException e) {
      throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
