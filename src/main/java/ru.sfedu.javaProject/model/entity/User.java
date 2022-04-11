package ru.sfedu.javaProject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull(message="name is mandatory")
    @Size(min = 2, max = 20, message = "length of name must be in 2..20")
    private String name;

    @Column(nullable = false)
    @Min(value = 16, message = "age must be in 16..120")
    @Max(value = 120, message = "age must be in 16..120")
    @NotNull(message = "age is mandatory")
    private Integer age;

    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "gender is mandatory")
    private Gender gender;

}
