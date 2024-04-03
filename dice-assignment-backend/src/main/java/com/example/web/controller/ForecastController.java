package com.example.web.controller;

import com.example.configuration.Constants;
import com.example.model.OpenWeatherThreeHourForecastDataResponse;
import com.example.model.WetterSummaryByLocationNameResponse;
import com.example.service.integration.OpenWeatherIntegration;
import com.example.service.integration.WettercomIntegration;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_URI_PREFIX + "forecast")
public class ForecastController {

  private final WettercomIntegration wettercom;
  private final OpenWeatherIntegration openWeather;

  @Autowired
  public ForecastController(WettercomIntegration wettercom, OpenWeatherIntegration openWeather) {
    this.wettercom = wettercom;
    this.openWeather = openWeather;
  }

  @GetMapping("/summary/{location}")
  public ResponseEntity<WetterSummaryByLocationNameResponse> summaryByLocation(
      @PathVariable("location") String location) throws IOException {

    return ResponseEntity.ok().body(wettercom.getForecastSummaryByLocationName(location));
  }

  @GetMapping("/3h/{location}")
  public ResponseEntity<OpenWeatherThreeHourForecastDataResponse> forecastIn3HourInterval(
      @PathVariable("location") String location) throws IOException {

    return ResponseEntity.ok().body(openWeather.getThreeHourIntervalForecast(location));
  }
}
