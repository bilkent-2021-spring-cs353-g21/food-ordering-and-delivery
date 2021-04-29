package tr.com.bilkent.fods.dto.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@ToString
public class CustomHTTPResponse {
    private Object message;
    private String error;
    private Timestamp timestamp;

    public CustomHTTPResponse() {
        this.timestamp = Timestamp.from(Instant.now());
    }

    public CustomHTTPResponse(Object message) {
        this();
        this.message = message;
    }

    public CustomHTTPResponse(Object message, String error) {
        this(message);
        this.error = error;
    }
}
