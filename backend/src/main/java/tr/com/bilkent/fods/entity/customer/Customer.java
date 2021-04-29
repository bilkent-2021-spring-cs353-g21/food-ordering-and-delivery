package tr.com.bilkent.fods.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.User;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "customer")
public class Customer extends User {
    @NotNull
    private Double credit = 0.0;

    @NotNull
    private Integer score = 0;
}
