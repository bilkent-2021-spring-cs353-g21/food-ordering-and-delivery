package tr.com.bilkent.fods.entity.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tr.com.bilkent.fods.entity.order.Order;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "comment")
public class Comment {
    @Id
    private Long oid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "oid")
    private Order order;

    @NotNull
    @Column(name = "restaurant_score")
    private Integer restaurantScore;

    @NotNull
    @Column(name = "delivery_score")
    private Integer deliveryScore;

    @CreationTimestamp
    private Timestamp timestamp;

    @NotNull
    @Column(length = 1024)
    private String text;

    @Column(name = "response_ts")
    private Timestamp responseTimestamp;

    @Column(length = 1024)
    private String response;
}
