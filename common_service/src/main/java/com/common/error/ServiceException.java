package com.common.error;

public class ServiceException extends RuntimeException {
  public ServiceException(String message) {
    super(message);
  }
}
