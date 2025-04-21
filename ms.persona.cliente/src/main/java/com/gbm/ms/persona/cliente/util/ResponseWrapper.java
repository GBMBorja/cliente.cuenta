package com.gbm.ms.persona.cliente.util;
public class ResponseWrapper<T> {
    private T data;
    private String message;
    private boolean success;

    // Constructor para respuesta exitosa
    public ResponseWrapper(T data) {
        this.data = data;
        this.success = true;
    }

    // Constructor para mensaje de error
    public ResponseWrapper(String message) {
        this.message = message;
        this.success = false;
    }

    // Getters y Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}