package tr.com.bilkent.fods.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import javax.persistence.*;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "schedule")
public class Schedule {
    @EmbeddedId
    private ScheduleKey scheduleKey;

    @ManyToOne
    @MapsId("rid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rid")
    private Restaurant restaurant;

    @Column(name = "open_from")
    private Time openFrom;

    @Column(name = "open_until")
    private Time openUntil;
}
