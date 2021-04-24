package tr.com.bilkent.fods.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.friendship.Friendship;
import tr.com.bilkent.fods.entity.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "customer")
public class Customer extends User {
    private Double credit;

    private Integer score;

    @OneToMany(mappedBy = "customerUsername")
    private Set<Friendship> friends;

    @OneToMany(mappedBy = "friendUsername")
    private Set<Friendship> friendOf;
}
