package tr.com.bilkent.fods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityBase implements Serializable {
    @NotNull
    private boolean deleted = false;
}
