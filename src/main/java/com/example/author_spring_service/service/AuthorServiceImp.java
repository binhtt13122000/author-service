package com.example.author_spring_service.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.example.author_spring_service.common.ErrorMessages;
import com.example.author_spring_service.entity.Author;
import com.example.author_spring_service.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getById(String id) throws DynamoDBMappingException {
        Author author = authorRepository.getById(id);
        if(author == null){
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND);
        }
        return author;
    }

    @Override
    public Author add(Author author) throws DynamoDBMappingException {
        return this.authorRepository.put(author);
    }

    @Override
    public Author update(Author author) throws DynamoDBMappingException, ResourceNotFoundException {
        this.getById(author.getUuid());
        this.authorRepository.put(author);
        return author;
    }

    @Override
    public Author delete(String uuid) throws ResourceNotFoundException, DynamoDBMappingException {
        Author author = this.getById(uuid);
        this.authorRepository.remove(author);
        return author;
    }
}



//    @Override
//    public QueryResultPage<Author> getData(int limit, int offset, Map<String, String> filters) {
//        return this.authorRepository.getList(limit, offset, filters);
//    }
