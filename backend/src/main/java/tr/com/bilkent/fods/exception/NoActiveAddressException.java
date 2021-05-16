package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NoActiveAddressException extends RuntimeException {
}