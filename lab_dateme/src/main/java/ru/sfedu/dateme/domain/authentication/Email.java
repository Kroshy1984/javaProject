package ru.sfedu.dateme.domain.authentication;

import org.apache.commons.validator.routines.EmailValidator;
import ru.sfedu.dateme.domain.exceptions.IncorrectEmailAddressException;

public class Email {
    private String address;

    public Email(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public static Email create(String address) {
        if (!EmailValidator.getInstance().isValid(address))
            throw new IncorrectEmailAddressException();

        return new Email(address);
    }
}
