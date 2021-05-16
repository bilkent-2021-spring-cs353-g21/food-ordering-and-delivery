package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import tr.com.bilkent.fods.entity.district.DistrictKey;

@Getter
@AllArgsConstructor
@ToString
public class DistrictNotServedException extends RuntimeException {
    private final Long rid;
    private final DistrictKey districtKey;
}