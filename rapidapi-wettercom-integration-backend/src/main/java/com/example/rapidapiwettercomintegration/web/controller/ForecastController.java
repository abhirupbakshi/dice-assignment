package com.example.rapidapiwettercomintegration.web.controller;

import com.example.rapidapiwettercomintegration.integration.openweather.OpenWeatherIntegration;
import com.example.rapidapiwettercomintegration.integration.openweather.ThreeHourForecastDataResponse;
import com.example.rapidapiwettercomintegration.integration.wetter.SummaryByLocationNameResponse;
import com.example.rapidapiwettercomintegration.integration.wetter.WettercomIntegration;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = "*")
public class ForecastController {

  private final WettercomIntegration wettercom;
  private final OpenWeatherIntegration openWeather;

  @Autowired
  public ForecastController(WettercomIntegration wettercom, OpenWeatherIntegration openWeather) {
    this.wettercom = wettercom;
    this.openWeather = openWeather;
  }

  @GetMapping("/summary/{location}")
  public ResponseEntity<SummaryByLocationNameResponse> summaryByLocation(
      @PathVariable("location") String location) throws IOException {

    return ResponseEntity.ok().body(wettercom.getForecastSummaryByLocationName(location));
  }

  @GetMapping("/3h/{lat}/{lon}")
  public ResponseEntity<ThreeHourForecastDataResponse> forecastIn3HourInterval(
      @PathVariable("lat") BigDecimal lat, @PathVariable("lon") BigDecimal lon) throws IOException {

    return ResponseEntity.ok().body(openWeather.getThreeHourIntervalForecast(lat, lon));
  }
}
