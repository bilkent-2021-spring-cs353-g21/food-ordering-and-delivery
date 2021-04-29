package tr.com.bilkent.fods.entity.enumconverter;

import lombok.AllArgsConstructor;
import tr.com.bilkent.fods.entity.deliverer.DelivererStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@AllArgsConstructor
public class DelivererStatusConverter implements AttributeConverter<DelivererStatus, String> {
    @Override
    public String convertToDatabaseColumn(DelivererStatus delivererStatus) {
        return (delivererStatus == null) ? null : delivererStatus.value();
    }

    @Override
    public DelivererStatus convertToEntityAttribute(String status) {
        return DelivererStatus.get(status);
    }
}