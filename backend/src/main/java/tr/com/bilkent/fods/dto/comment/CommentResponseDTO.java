package tr.com.bilkent.fods.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private long oid;

    private Timestamp responseTimestamp;
    private String response;
}
