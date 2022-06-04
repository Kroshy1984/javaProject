package ru.sfedu.javaProject.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PairRequest {

    @NotNull(message = "must be not null")
    @JsonAlias("first_user_id")
    private Long firstUserId;

    @NotNull(message = "must be not null")
    @JsonAlias("second_user_id")
    private Long secondUserId;

    @AssertTrue(message = "first_user_id and second_user_id must be different")
    public boolean isValid() {
        return !firstUserId.equals(secondUserId);
    }
}
