package ru.pranch.cinema.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
  @Bean
  public ObjectMapper getObjectMapper() {
    return JsonMapper.builder()
      .addModule(new JavaTimeModule())
      .addModule(new Jdk8Module())
      .addModule(new ParameterNamesModule()).build();
  }
}
