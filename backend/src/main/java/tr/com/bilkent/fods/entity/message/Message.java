package tr.com.bilkent.fods.entity.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.enumconverter.MessageStatusConverter;
import tr.com.bilkent.fods.entity.friendship.Friendship;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @ManyToOne
    @JoinColumn(name = "customer_username")
    @JoinColumn(name = "friend_username")
    private Friendship friendship;

    @NotNull
    @Column(length = 1024)
    private String text;

    @CreationTimestamp
    private Timestamp timestamp;

    @Convert(converter = MessageStatusConverter.class)
    private MessageStatus status = MessageStatus.SENT;

    /**
     * Shows whether this message is sent from the customer to friend, or received from the friend.
     */
    private Boolean direction;

    @ManyToOne
    @JoinColumn(name = "customer_username", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "friend_username", insertable = false, updatable = false)
    private Customer friend;
}
