package tr.com.bilkent.fods.exception;

import lombok.Getter;

@Getter
public class CommentAlreadyRepliedException extends RuntimeException {
    private final Long oid;

    public CommentAlreadyRepliedException(final Long oid) {
        this.oid = oid;
    }
}