package dev.bigwig.fastrest.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class FException extends RuntimeException {

  private String message;

  private HttpStatus httpStatus;

  public FException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
