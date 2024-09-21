package com.room.api.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AccountNumberMaskSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String accountNumber, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (accountNumber != null) {
            String maskedAccountNumber = "XXXXXXX" + accountNumber.substring(accountNumber.length() - 4);
            jsonGenerator.writeString(maskedAccountNumber);
        } else {
            jsonGenerator.writeString(accountNumber);
        }
    }
}
