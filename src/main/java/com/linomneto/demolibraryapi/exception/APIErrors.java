package com.linomneto.demolibraryapi.exception;

import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIErrors {

    private List<String> errors;

    public APIErrors(BusinessException be) {
        this.errors = Arrays.asList(be.getMessage());
    }

    public APIErrors(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(e -> this.errors.add(e.getDefaultMessage()));
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
