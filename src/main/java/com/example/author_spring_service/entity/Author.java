package com.example.author_spring_service.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.example.author_spring_service.common.Common;
import com.example.author_spring_service.dtos.CreateAuthorDTO;
import com.example.author_spring_service.dtos.UpdateAuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = Common.AUTHOR_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    public Author(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @DynamoDBAutoGeneratedKey
    @DynamoDBHashKey
    private String uuid;
    @DynamoDBAttribute
    private String firstName;
    @DynamoDBAttribute
    private String lastName;
    @DynamoDBAttribute
    private String email;
    public static Author mapper(UpdateAuthorDTO dto){
        return new Author(dto.getUuid(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }
    public static Author mapper(CreateAuthorDTO dto) {
        return new Author(dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }
}
