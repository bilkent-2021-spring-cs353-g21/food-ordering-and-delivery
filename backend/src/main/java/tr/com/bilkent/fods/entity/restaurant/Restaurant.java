package tr.com.bilkent.fods.entity.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.entity.schedule.Schedule;
import tr.com.bilkent.fods.entity.serves.Serves;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.discount.Discount;
import tr.com.bilkent.fods.entity.district.District;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "restaurant")
public class Restaurant {
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

    @OneToMany(mappedBy = "restaurant")
    private List<Serves> serves;

    @ElementCollection
    @CollectionTable(name = "restaurant_tags", joinColumns = @JoinColumn(name = "rid"))
    @Column(name = "tag")
    private List<String> tags;

    @ManyToOne
    private RestaurantManager manager;

    @OneToMany(mappedBy = "restaurant")
    private List<Discount> discounts;

    @OneToMany(mappedBy = "restaurant")
    private List<Schedule> schedules;

    @ManyToMany(mappedBy = "worksWith")
    private List<Deliverer> deliverers;
}
