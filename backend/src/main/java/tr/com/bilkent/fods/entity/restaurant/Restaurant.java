package tr.com.bilkent.fods.entity.restaurant;

import lombok.*;
import tr.com.bilkent.fods.entity.EntityBase;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "restaurant")
public class Restaurant extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @NotNull
    private String name;

    @Column(length = 512)
    private String description;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @ManyToOne
    private District address;

    @ElementCollection
    @CollectionTable(name = "restaurant_tags", joinColumns = @JoinColumn(name = "rid"))
    @Column(name = "tag")
    private List<String> tags;

    @ManyToOne
    private RestaurantManager manager;
}
