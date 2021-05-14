package tr.com.bilkent.fods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.comment.CommentDTO;
import tr.com.bilkent.fods.entity.comment.Comment;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = OrderMapper.class)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    List<CommentDTO> commentDtosFromComment(List<Comment> comments);
}
