package com.boso.personapi.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

  private Date date;
  private String message;
  private String details;

  public ExceptionResponse(final Date date, final String message, final String details) {
    super();
    this.date = date;
    this.message = message;
    this.details = details;
  }

  public Date getDate() {
    return date;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

  public void setDate(final Date date) {
    this.date = date;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public void setDetails(final String details) {
    this.details = details;
  }
}
