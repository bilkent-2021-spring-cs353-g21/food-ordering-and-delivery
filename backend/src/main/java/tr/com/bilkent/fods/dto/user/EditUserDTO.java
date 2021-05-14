package tr.com.bilkent.fods.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
public class EditUserDTO {
    @Size(max = 40)
    private String fullName;

    private Date birthdate;

    @Size(max = 15)
    private String phoneNumber;

    @Size(min = 6, max = 63)
    private String password;
}
