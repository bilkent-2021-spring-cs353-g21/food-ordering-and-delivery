package tr.com.bilkent.fods.exception;

import lombok.Getter;

@Getter
public class NonExistsCommentException extends RuntimeException {
    private final Long oid;

    public NonExistsCommentException(final Long oid) {
        this.oid = oid;
    }
}