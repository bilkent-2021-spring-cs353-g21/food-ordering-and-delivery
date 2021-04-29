package tr.com.bilkent.fods.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    @Size(max = 20)
    private String username;

    @NotEmpty
    @Size(max = 63)
    private String password;

    @NotEmpty
    @Size(max = 40)
    private String fullName;

    @Email
    @NotEmpty
    @Size(max = 255)
    private String email;

    private Date birthdate;
}
