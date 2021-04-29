package tr.com.bilkent.fods.entity.enumconverter;

import lombok.AllArgsConstructor;
import tr.com.bilkent.fods.entity.message.MessageStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@AllArgsConstructor
public class MessageStatusConverter implements AttributeConverter<MessageStatus, String> {
    @Override
    public String convertToDatabaseColumn(MessageStatus messageStatus) {
        return (messageStatus == null) ? null : messageStatus.value();
    }

    @Override
    public MessageStatus convertToEntityAttribute(String status) {
        return MessageStatus.get(status);
    }
}