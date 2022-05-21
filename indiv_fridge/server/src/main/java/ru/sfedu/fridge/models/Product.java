package ru.sfedu.fridge.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "shelfLife is required")
    private Date shelfLife;

    @NotEmpty(message = "name is required")
    private String name;
}
