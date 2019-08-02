package com.example.spring.catalog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JsonCollectionToStringConverter<T extends Object>
        implements AttributeConverter<Collection<T>, String> {

    private final ObjectMapper objectMapper;

    public JsonCollectionToStringConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(Collection<T> attribute) {
        String jsonString = null;
        try {
            // convert collection of POJO to json
            jsonString = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    @Override
    public Collection<T> convertToEntityAttribute(String dbData) {
        Collection<T> objectArray = new ArrayList<>();
        try {
            // convert json to collection of POJO
            objectArray = objectMapper.readValue(dbData, new TypeReference<T>() {});
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return objectArray;
    }
}
