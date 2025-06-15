package com.mercadolibre.be_java_hisp_w31_g07.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDate;

public final class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    private static final ObjectWriter writer = mapper
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();

    static {
        mapper.configOverride(LocalDate.class)
                .setFormat(JsonFormat.Value.forPattern("dd-MM-yyyy"));
    }

    public static String generateFromDto(Object dto) throws JsonProcessingException {
        return writer.writeValueAsString(dto);
    }

    public static <T> T fromJsonToDto(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

}