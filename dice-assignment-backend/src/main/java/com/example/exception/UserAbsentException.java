package com.example.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAbsentException extends RuntimeException {

  private final String username;

  public UserAbsentException(String username) {
    super();
    this.username = username;
  }

  public UserAbsentException(String username, String message) {
    super(message);
    this.username = username;
  }

  public UserAbsentException(String username, String message, Throwable cause) {
    super(message, cause);
    this.username = username;
  }

  public UserAbsentException(String username, Throwable cause) {
    super(cause);
    this.username = username;
  }

  protected UserAbsentException(
      String username,
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.username = username;
  }
}
