package com.mocklab.api.shared.exceptions;

public class SchemaPostValidationException extends RuntimeException{

    public SchemaPostValidationException(String messageError){
        super(messageError);
    }

}
