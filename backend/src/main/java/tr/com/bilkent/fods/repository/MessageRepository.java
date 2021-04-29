package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.friendship.Friendship;
import tr.com.bilkent.fods.entity.message.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM message WHERE " +
            "customer_username = :#{#friendship.customer} AND friend_username = :#{#friendship.friend} " +
            "ORDER BY timestamp", nativeQuery = true)
    List<Message> getMessagesOfFriendship(Friendship friendship);
}
