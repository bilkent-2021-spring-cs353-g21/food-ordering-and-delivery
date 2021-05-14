package tr.com.bilkent.fods.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.comment.Comment;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT C.* FROM comment C INNER JOIN orders O ON C.oid = O.oid " +
            "WHERE O.from_restaurant = ?1 ORDER BY C.timestamp DESC", nativeQuery = true)
    Page<Comment> getCommentsOfRestaurant(Restaurant restaurant, Pageable pageable);
}
