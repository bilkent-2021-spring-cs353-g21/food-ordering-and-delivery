package tr.com.bilkent.fods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class User extends EntityBase {
    @Id
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(unique = true)
    private String email;

    private Date birthdate;
}
