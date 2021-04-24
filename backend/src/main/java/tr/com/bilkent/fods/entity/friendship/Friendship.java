package tr.com.bilkent.fods.entity.friendship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tr.com.bilkent.fods.entity.message.Message;
import tr.com.bilkent.fods.entity.customer.Customer;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "friend")
public class Friendship {
    @EmbeddedId
    private FriendshipKey friendshipKey;

    @ManyToOne
    @MapsId("customerUsername")
    @JoinColumn(name = "customer_username")
    private Customer customerUsername;

    @ManyToOne
    @MapsId("friendUsername")
    @JoinColumn(name = "friend_username")
    private Customer friendUsername;

    @CreationTimestamp
    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "is_accepted")
    private Boolean isAccepted;

    @OneToMany(mappedBy = "friendship")
    private Set<Message> messages;
}
