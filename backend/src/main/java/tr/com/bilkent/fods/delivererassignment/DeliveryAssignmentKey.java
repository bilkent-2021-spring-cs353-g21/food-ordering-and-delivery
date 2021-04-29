package tr.com.bilkent.fods.delivererassignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DeliveryAssignmentKey implements Serializable {
    private Long oid;

    @Column(name = "deliverer_username")
    private String delivererUsername;
}
