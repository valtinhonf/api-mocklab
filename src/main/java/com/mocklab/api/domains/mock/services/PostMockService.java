package com.mocklab.api.domains.mock.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocklab.api.shared.exceptions.SchemaPostValidationException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PostMockService {

    public boolean validate(Object object, String __schema) {
        try {
            // Converte o objeto para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String objectJson = objectMapper.writeValueAsString(object);

            // Carrega o Schema e valida
            JSONObject jsonSchema = new JSONObject(__schema);
            JSONObject jsonObject = new JSONObject(objectJson);
            Schema schema = SchemaLoader.load(jsonSchema);

            schema.validate(jsonObject); // Lança exceção se inválido

            return true; // Objeto válido
        } catch (Exception e) {
            System.err.println("Erro na validação: " + e.getMessage());
            throw new SchemaPostValidationException(e.getLocalizedMessage());
        }
    }

}
