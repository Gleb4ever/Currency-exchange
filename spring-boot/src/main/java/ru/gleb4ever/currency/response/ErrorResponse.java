package ru.gleb4ever.currency.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorResponse {

  boolean result;
  ValidationErrors errors;
  String message;

  @Getter
  @Builder
  public static class ValidationErrors {

    Map<String, List<String>> errorMap;

    @JsonAnyGetter
    public Map<String, List<String>> getErrorMap() {
      return errorMap;
    }
  }
}