package dev.bigwig.fastrest.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

@Configuration
public class WebConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    mapper.registerModule(new Hibernate5Module());

    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDateTime.class,
      new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    javaTimeModule.addSerializer(LocalDate.class,
      new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    javaTimeModule.addSerializer(LocalTime.class,
      new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    mapper.registerModule(javaTimeModule);

    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);

    return mapper;
  }

  @Bean
  public Formatter<LocalDate> localDateFormatter() {
    return new Formatter<>() {
      @Override
      public @NotNull String print(@NotNull LocalDate object, @NotNull Locale locale) {
        return object.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      }

      @Override
      public @NotNull LocalDate parse(@NotNull String text, @NotNull Locale locale) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      }
    };
  }

  @Bean
  public Formatter<LocalTime> localTimeFormatter() {
    return new Formatter<>() {
      @Override
      public @NotNull String print(@NotNull LocalTime object, @NotNull Locale locale) {
        return object.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
      }

      @Override
      public @NotNull LocalTime parse(@NotNull String text, @NotNull Locale locale) {
        return LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm:ss"));
      }
    };
  }

  @Bean
  public Formatter<LocalDateTime> localDateTimeFormatter() {
    return new Formatter<>() {
      @Override
      public @NotNull String print(@NotNull LocalDateTime object, @NotNull Locale locale) {
        return object.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      }

      @Override
      public @NotNull LocalDateTime parse(@NotNull String text, @NotNull Locale locale) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      }
    };
  }
}
