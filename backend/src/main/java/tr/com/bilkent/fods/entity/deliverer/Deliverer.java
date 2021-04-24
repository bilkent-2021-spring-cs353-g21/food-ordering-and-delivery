package tr.com.bilkent.fods.entity.deliverer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.entity.User;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "deliverer")
public class Deliverer extends User {
    @Column(name = "phone_number")
    private String phoneNumber;

    private DelivererStatus status = DelivererStatus.AVAILABLE;

    @ManyToMany
    @JoinTable(name = "works_with",
            joinColumns = @JoinColumn(name = "deliverer_username"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private List<Restaurant> worksWith;
}
