package com.sena.inventarioback.utils.response;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ObjectResponse<T> extends DefaultResponse<T> {

    private T object;

    public ObjectResponse(T object, HttpStatus status, List<Message> message, List<Error> error, MESSAGETYPES messageType) {
        super(Collections.singletonList(object), status, message, error, messageType);
        this.object = object;
        this.dataType = DATATYPE.OBJECT;
    }


    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }


}