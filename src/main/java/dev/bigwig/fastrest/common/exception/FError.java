package dev.bigwig.fastrest.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FError {

  private HttpStatus status;

  private String message;
}
