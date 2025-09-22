package org.example.serializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.example.models.UserInfoDto;

import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // intentionally left blank
    }

    @Override
    public byte[] serialize(String topic, UserInfoDto data){
        byte[] byteArray = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byteArray = objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return byteArray;
    }
}
