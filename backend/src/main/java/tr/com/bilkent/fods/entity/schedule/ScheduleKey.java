package tr.com.bilkent.fods.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ScheduleKey implements Serializable {
    private Long rid;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
}
