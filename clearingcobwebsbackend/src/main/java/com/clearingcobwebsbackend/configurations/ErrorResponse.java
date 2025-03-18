package com.clearingcobwebsbackend.configurations;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorResponse {
  private LocalDateTime timestamp;
  private int statusCode;
  private String error;
  private String message;

  public ErrorResponse(LocalDateTime timestamp, int statusCode, String error, String message) {
    this.timestamp = timestamp;
    this.statusCode = statusCode;
    this.error = error;
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
