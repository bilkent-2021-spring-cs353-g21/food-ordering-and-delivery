package tr.com.bilkent.fods.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentListDTO {
    private Long totalComments;

    private Integer totalPages;

    private List<CommentDTO> comments;
}
