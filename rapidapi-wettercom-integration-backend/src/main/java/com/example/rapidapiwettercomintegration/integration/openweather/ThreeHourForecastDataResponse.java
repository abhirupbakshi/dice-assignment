package com.example.rapidapiwettercomintegration.integration.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.Main;
import java.time.Instant;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor(force = true)
@Getter
@Setter
@Accessors(chain = true)
public class ThreeHourForecastDataResponse {

  private Integer cnt;
  private City city;
  private List<Summary> list;

  @NoArgsConstructor(force = true)
  @Getter
  @Setter
  @Accessors(chain = true)
  public static class City {

    private Integer id;
    private String name;
    private Coordinate coord;
    private String country;
    private Long population;
    private Integer timezone;
    private Integer sunrise;
    private Integer sunset;

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Coordinate {

      private Double lat;
      private Double lon;
    }
  }

  @NoArgsConstructor(force = true)
  @Getter
  @Setter
  @Accessors(chain = true)
  public static class Summary {

    private Instant dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Integer visibility;
    private Double pop;
    private Rain rain;
    private Sys sys;

    @JsonProperty("dt_txt")
    private String dtTxt;

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Main {

      private Double temp;
      private Double feels_like;
      private Double temp_min;
      private Double temp_max;
      private Double pressure;
      private Double sea_level;
      private Double grnd_level;
      private Double humidity;
    }

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Weather {

      private Integer id;
      private String main;
      private String description;
      private String icon;
    }

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Clouds {

      private Double all;
    }

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Wind {

      private Double speed;
      private Double deg;
      private Double gust;
    }

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Rain {

      @JsonProperty("3h")
      private Double threeH;
    }

    @NoArgsConstructor(force = true)
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Sys {

      private String pod;
    }
  }
}
