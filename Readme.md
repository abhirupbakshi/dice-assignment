# API:

| Endpoint             | Method | Description                                                       | Request Path Variable  |
|----------------------|--------|-------------------------------------------------------------------|------------------------|
| `/summary/:location` | GET    | Get weather summary by location name                              | Location name          |
| `/3h/:lat/:lon`      | GET    | Get weather forecast in 3 hour interval by latitude and longitude | Latitude and Longitude |

## Response Schema Example

### Route: /summary/:location

#### Status 200:

```json
{
  "location": {
    "code": "DE0001020",
    "name": "Berlin",
    "timezone": "Europe/Berlin",
    "coordinates": {
      "latitude": 52.5244,
      "longitude": 13.4105
    }
  },
  "forecast": {
    "forecastDate": "2024-03-23T09:20:54Z",
    "nextUpdate": "2024-03-23T17:30:00Z",
    "source": "w3Data",
    "point": "global",
    "items": [
      {
        "date": "2024-03-23",
        "dateWithTimezone": "2024-03-22T23:00:00Z",
        "freshSnow": 0.0,
        "snowHeight": null,
        "weather": {
          "state": 61,
          "text": "leichter Regen",
          "icon": "d_61.svg"
        },
        "prec": {
          "sum": 6.34,
          "probability": 90.0,
          "sumAsRain": null,
          "class": 2
        },
        "sunHours": 2.0,
        "rainHours": null,
        "temperature": {
          "min": 5.0,
          "max": 9.0,
          "avg": null
        },
        "wind": {
          "unit": "km/h",
          "direction": "Westwind",
          "text": "W",
          "min": 3.0,
          "max": 16.0,
          "avg": null,
          "significationWind": true,
          "gusts": {
            "value": 41.0,
            "text": null
          }
        },
        "windchill": {
          "min": 3.0,
          "max": 7.0,
          "avg": null
        },
        "snowLine": {
          "min": null,
          "max": null,
          "avg": null,
          "unit": "m"
        },
        "astronomy": {
          "dawn": "2024-03-23T05:26:53+01:00",
          "sunrise": "2024-03-23T06:01:03+01:00",
          "suntransit": "2024-03-23T12:12:47+01:00",
          "sunset": "2024-03-23T18:25:35+01:00",
          "dusk": "2024-03-23T18:59:54+01:00",
          "moonrise": "2024-03-23T16:29:00+01:00",
          "moontransit": "2024-03-23T23:22:24+01:00",
          "moonset": "2024-03-23T05:49:02+01:00",
          "moonphase": 4,
          "moonzodiac": 6
        }
      }
    ]
  }
}
```

#### Status 304, 500:

Problem details response

#### Status 400:

Problem details response with a custom field "details" of the following type:

```json
{
  "details": {
    "type": "array",
    "items": {
      "type": "object",
      "properties": {
        "field": {
          "type": [
            "string",
            "null"
          ]
        },
        "message": {
          "type": "string"
        }
      }
    }
  }
}
```

#### Other Status:

Problem details response with a custom field "details" of the string type.
<hr>

### Route: /summary/:location

#### Status 200:

```json
{
  "cnt": 40,
  "list": [
    {
      "dt": 1711206000,
      "main": {
        "temp": 283.61,
        "feels_like": 282.2,
        "temp_min": 283.44,
        "temp_max": 283.61,
        "pressure": 1015,
        "sea_level": 1015,
        "grnd_level": 1010,
        "humidity": 57
      },
      "weather": [
        {
          "id": 500,
          "main": "Rain",
          "description": "light rain",
          "icon": "10d"
        }
      ],
      "clouds": {
        "all": 83
      },
      "wind": {
        "speed": 6.74,
        "deg": 272,
        "gust": 10.88
      },
      "visibility": 10000,
      "pop": 1,
      "rain": {
        "3h": 0.67
      },
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2024-03-23 15:00:00"
    }
  ],
  "city": {
    "id": 6455259,
    "name": "Paris",
    "coord": {
      "lat": 48.8551,
      "lon": 2.355
    },
    "country": "FR",
    "population": 0,
    "timezone": 3600,
    "sunrise": 1711172789,
    "sunset": 1711217251
  }
}
```
# Deployments
- The backend is build into a docker container which can be pulled from <a href="https://hub.docker.com/r/abhirupbakshi/dice-assignment-backend">this</a> docker hub repository.
- A running instance of the image is already deployed on a OCI (Oracle Cloud Infrastructure) instance.
- The frontend is deployed on the vercel.