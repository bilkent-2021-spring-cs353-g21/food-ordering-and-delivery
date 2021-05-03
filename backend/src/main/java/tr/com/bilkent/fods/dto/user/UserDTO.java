package tr.com.bilkent.fods.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDTO extends UserWithoutPasswordDTO {
    @NotEmpty
    @Size(min = 6, max = 63)
    private String password;
}
