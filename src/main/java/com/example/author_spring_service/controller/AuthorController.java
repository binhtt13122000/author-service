package com.example.author_spring_service.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.example.author_spring_service.common.Common;
import com.example.author_spring_service.common.ErrorAPI;
import com.example.author_spring_service.dtos.CreateAuthorDTO;
import com.example.author_spring_service.dtos.UpdateAuthorDTO;
import com.example.author_spring_service.entity.Author;
import com.example.author_spring_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping()
    public ResponseEntity post(@Valid @RequestBody CreateAuthorDTO body) {
        try {
            Author author = Author.mapper(body);
            Author createdAuthor = authorService.add(author);
            return new ResponseEntity(createdAuthor, HttpStatus.CREATED);
        } catch (DynamoDBMappingException ex) {
            return generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(Common.PATH_GET_ONE)
    public ResponseEntity<Author> getOne(@PathVariable String id) {
        try {
            Author author = authorService.getById(id);
            return new ResponseEntity(author, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return generateResponse(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
        } catch (DynamoDBMappingException ex) {
            return generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Author> update(@Valid @RequestBody UpdateAuthorDTO body) {
        try {
            Author author = Author.mapper(body);
            Author updateAuthor = authorService.update(author);
            return new ResponseEntity(updateAuthor, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return generateResponse(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
        } catch (DynamoDBMappingException ex) {
            return generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(Common.PATH_GET_ONE)
    public ResponseEntity<Author> delete(@PathVariable String id) {
        try {
            Author author = authorService.delete(id);
            return new ResponseEntity(author, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return generateResponse(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
        } catch (DynamoDBMappingException ex) {
            return generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity generateResponse(String message, HttpStatus status) {
        return new ResponseEntity(new ErrorAPI(status, message), status);
    }
}




//    @GetMapping()
//    public ResponseEntity getList(
//            @RequestParam(name = Common.FIRST_NAME_PARAM) Optional<String> firstName,
//            @RequestParam(name = Common.LAST_NAME_PARAM) Optional<String> lastName,
//            @RequestParam(name = Common.EMAIL_PARAM) Optional<String> email,
//            @RequestParam Optional<Integer> offset,
//            @RequestParam Optional<Integer> limit
//    ) {
//        Map<String, String> filters = new HashMap<>();
//        filters.put(Common.FIRST_NAME_FIELD, firstName.orElse(Common.DEFAULT_STRING));
//        filters.put(Common.LAST_NAME_FIELD, lastName.orElse(Common.DEFAULT_STRING));
//        filters.put(Common.EMAIL_FIELD, email.orElse(Common.DEFAULT_STRING));
//        QueryResultPage<Author> authorScanResultPage = this.authorService.getData(
//                limit.orElse(Common.DEFAULT_LIMIT),
//                offset.orElse(Common.DEFAULT_OFFSET), filters);
//        return new ResponseEntity(authorScanResultPage, HttpStatus.OK);
//    }