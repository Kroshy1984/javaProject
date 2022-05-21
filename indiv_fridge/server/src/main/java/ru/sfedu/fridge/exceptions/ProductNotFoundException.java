package ru.sfedu.fridge.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    private String message;

    public ProductNotFoundException(int productId) {
        this.message = String.format("Product with id=%d not found", productId);
    }
}
