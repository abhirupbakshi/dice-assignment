package com.example.web;

import java.net.URI;

import com.example.exception.UserAbsentException;
import com.example.exception.UserExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@CrossOrigin(originPatterns = "*")
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleException(Exception e) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    pd.setType(URI.create("internal-server-error"));

    log.error("error: ", e);

    return pd;
  }

  @ExceptionHandler(UserExistsException.class)
  public ProblemDetail handleUserExistsException(UserExistsException e) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pd.setType(URI.create("user-exists"));
    pd.setTitle("User with username " + e.getUsername() + " already exist");

    return pd;
  }

  @ExceptionHandler(UserAbsentException.class)
  public ProblemDetail handleUserAbsentException(UserAbsentException e) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pd.setType(URI.create("user-absent"));
    pd.setTitle("User with username " + e.getUsername() + " does not exist");

    return pd;
  }
}
