package com.example.springdatajpa.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Set;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Converter(autoApply = true)
@Component
@RequiredArgsConstructor
public class ReceiverConverter implements AttributeConverter<Set<Long>, String> {

  private final ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Set<Long> attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      log.error("{}을 json으로 변환하는 중 오류가 발생했습니다.", attribute.toString(), e);
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Set<Long> convertToEntityAttribute(String dbData) {
    try {
      JavaType type = objectMapper.getTypeFactory().
          constructCollectionType(Set.class, Long.class);

      return objectMapper.readValue(dbData, type);
    } catch (JsonProcessingException e) {
      log.error("{}을 {}로 변환하는 중 오류가 발생했습니다.", dbData, this.getClass().getSimpleName(), e);
      return Collections.emptySet();
    }
  }
}
