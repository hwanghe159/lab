package com.example.springdatajpa.component;

import com.example.springdatajpa.domain.NotificationInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Converter(autoApply = true)
@Component
@RequiredArgsConstructor
public class NotificationInfoConverter implements AttributeConverter<NotificationInfo, String> {

  private final ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(NotificationInfo attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      log.error("{}을 json으로 변환하는 중 오류가 발생했습니다.", attribute.toString(), e);
      throw new IllegalArgumentException();
    }
  }

  @Override
  public NotificationInfo convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, NotificationInfo.class);
    } catch (JsonProcessingException e) {
      log.error("{}을 {}로 변환하는 중 오류가 발생했습니다.", dbData, this.getClass().getSimpleName(), e);
      return null;
    }
  }
}
