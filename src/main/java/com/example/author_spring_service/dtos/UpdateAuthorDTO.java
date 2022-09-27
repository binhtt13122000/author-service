package com.example.author_spring_service.dtos;

import com.example.author_spring_service.common.ErrorMessages;
import com.example.author_spring_service.entity.Author;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateAuthorDTO extends CreateAuthorDTO {
    @NotBlank(message = ErrorMessages.NOT_NULL_UUID_ERROR_MESSAGE)
    private String uuid;
}
