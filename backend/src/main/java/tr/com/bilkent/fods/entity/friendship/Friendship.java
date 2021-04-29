package tr.com.bilkent.fods.entity.friendship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tr.com.bilkent.fods.entity.customer.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

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
    private Customer customer;

    @ManyToOne
    @MapsId("friendUsername")
    @JoinColumn(name = "friend_username")
    private Customer friend;

    @CreationTimestamp
    @Column(name = "from_date")
    private Date fromDate;

    @NotNull
    @Column(name = "is_accepted")
    private Boolean isAccepted = false;
}
