package com.example.author_spring_service.dtos;

import com.example.author_spring_service.common.ErrorMessages;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateAuthorDTO {
    @NotBlank(message = ErrorMessages.NOT_NULL_FIRSTNAME_ERROR_MESSAGE)
    private String firstName;
    @NotBlank(message = ErrorMessages.NOT_NULL_LASTNAME_ERROR_MESSAGE)
    private String lastName;
    @NotBlank(message = ErrorMessages.NOT_NULL_EMAIL_ERROR_MESSAGE)
    @Email(message = ErrorMessages.PATTERN_EMAIL_ERROR_MESSAGE)
    private String email;
}
