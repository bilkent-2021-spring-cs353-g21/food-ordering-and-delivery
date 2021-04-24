package tr.com.bilkent.fods.entity.friendship;

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
public class FriendshipKey implements Serializable {
    @Column(name = "customer_username")
    private String customerUsername;

    @Column(name = "friend_username")
    private String friendUsername;
}
