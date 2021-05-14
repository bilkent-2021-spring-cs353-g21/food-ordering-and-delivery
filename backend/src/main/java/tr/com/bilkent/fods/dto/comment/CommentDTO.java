package tr.com.bilkent.fods.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bilkent.fods.dto.order.OrderWithoutContentDTO;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private OrderWithoutContentDTO order;

    private Integer restaurantScore;
    private Integer deliveryScore;
    private Timestamp timestamp;
    private String text;

    private Timestamp responseTimestamp;
    private String response;
}
