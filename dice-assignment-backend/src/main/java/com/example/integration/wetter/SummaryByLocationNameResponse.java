package com.example.integration.wetter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor(force = true)
@Getter
@Setter
@Accessors(chain = true)
public class SummaryByLocationNameResponse {

  private Location location;
  private Forecast forecast;

  @NoArgsConstructor(force = true)
  @Getter
  @Setter
  @Accessors(chain = true)
  public static class Location {

    private String code;
    private String name;
    private String timezone;
    private Coordinates coordinates;

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Coordinates {

      private Double latitude;
      private Double longitude;
    }
  }

  @NoArgsConstructor(force = true)
  @Getter
  @Setter
  @Accessors(chain = true)
  public static class Forecast {

    private Instant forecastDate;
    private Instant nextUpdate;
    private String source;
    private Point point;
    private List<Summary> items;

    @Getter
    public enum Point {
      GLOBAL("global"),
      LOWEST_POINT("lowestPoint"),
      HIGHEST_POINT("highestPoint");

      @JsonValue private final String value;

      Point(String value) {
        this.value = value;
      }
    }

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Summary {

      private LocalDate date;
      private Instant dateWithTimezone;
      private Double freshSnow;
      private Double snowHeight;
      private Weather weather;
      private Precipitation prec;
      private Double sunHours;
      private Double rainHours;
      private Temperature temperature;
      private Wind wind;
      private Windchill windchill;
      private SnowfallLine snowLine;
      private Astronomy astronomy;

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class Weather {

        private Integer state;
        private String text;
        private String icon;
      }

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class Precipitation {

        private Double sum;
        private Double probability;
        private Double sumAsRain;

        @JsonProperty("class")
        private Integer clazz;
      }

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class Temperature {

        private Double min;
        private Double max;
        private Double avg;
      }

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class Wind {

        private String unit;
        private String direction;
        private String text;
        private Double min;
        private Double max;
        private Double avg;
        private Boolean significationWind;
        private Gust gusts;

        @NoArgsConstructor(force = true)
        @Getter
        @Setter
        @Accessors(chain = true)
        public static class Gust {

          private Double value;
          private String text;
        }
      }

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class Windchill {

        private Double min;
        private Double max;
        private Double avg;
      }

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class SnowfallLine {

        private Double min;
        private Double max;
        private Double avg;
        private String unit;
      }

      @NoArgsConstructor(force = true)
      @Getter
      @Setter
      @Accessors(chain = true)
      public static class Astronomy {

        private String dawn;
        private String sunrise;
        private String suntransit;
        private String sunset;
        private String dusk;
        private String moonrise;
        private String moontransit;
        private String moonset;
        private Number moonphase;
        private Integer moonzodiac;
      }
    }
  }
}
