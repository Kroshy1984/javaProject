package ru.sfedu.javaProject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Response<T> {
    private int statusCode;
    private Set<String> errors;
    private T response;
}
