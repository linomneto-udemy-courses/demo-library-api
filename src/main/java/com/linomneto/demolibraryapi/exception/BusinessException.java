package com.linomneto.demolibraryapi.exception;

public class BusinessException extends RuntimeException {

    public enum Type {
        IsbnAlreadyExists("Isbn already exists");

        String val;
        Type(String s) {
            val = s;
        }
        public String msg() {
            return val;
        }
    }

    private Type type;

    public BusinessException(Type type) {
        super(type.msg());
        this.type = type;
    }

}
