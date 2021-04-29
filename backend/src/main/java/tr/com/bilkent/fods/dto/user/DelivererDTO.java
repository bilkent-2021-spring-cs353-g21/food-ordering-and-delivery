package tr.com.bilkent.fods.dto.user;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
public class DelivererDTO extends UserDTO {
    @NotNull
    @Size(max = 15)
    private String phoneNumber;
}
