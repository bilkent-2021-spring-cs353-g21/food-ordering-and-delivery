package tr.com.bilkent.fods.entity.delivererassignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.order.Order;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "deliverer_assignment")
public class DelivererAssignment {
    @EmbeddedId
    private DeliveryAssignmentKey deliveryAssignmentKey;

    @ManyToOne
    @MapsId("oid")
    @JoinColumn(name = "oid")
    private Order order;

    @ManyToOne
    @MapsId("delivererUsername")
    @JoinColumn(name = "deliverer_username")
    private Deliverer deliverer;

    @CreationTimestamp
    @Column(name = "assignment_time")
    private Timestamp assignmentTime;

    @Column(name = "deliver_time")
    private Timestamp deliverTime;

    @Column(name = "is_rejected")
    private Boolean isRejected;
}
