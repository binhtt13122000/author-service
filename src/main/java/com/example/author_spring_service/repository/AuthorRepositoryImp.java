package com.example.author_spring_service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.author_spring_service.common.Common;
import com.example.author_spring_service.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorRepositoryImp implements AuthorRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Author getById(String id) throws DynamoDBMappingException {
        return dynamoDBMapper.load(Author.class, id);
    }

    @Override
    public ScanResultPage<Author> getList(int limit, int offset, Map<String, String> filters) {
        Map<String,String> expressionAttributesNames = new HashMap<>();
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        final ArrayList<String> filterScan = new ArrayList<>();
        filters.forEach((key, value) -> {
            String variable = String.format(Common.SCAN_FILTER_VARIABLE, key);
            String data = String.format(Common.SCAN_FILTER_DATA, key);
            expressionAttributesNames.put(variable, key);
            expressionAttributeValues.put(data, new AttributeValue().withS(value));
            filterScan.add(String.format(Common.SCAN_ATTRIBUTE_FORMAT, variable, data));
        });
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression(String.join(Common.DELIMITER, filterScan))
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues)
                .withLimit(limit)
                .withConsistentRead(false);
        return dynamoDBMapper.scanPage(Author.class, scanExpression);
    }

    @Override
    public Author put(Author author) throws DynamoDBMappingException {
        dynamoDBMapper.save(author);
        return author;
    }

    @Override
    public Author remove(Author author) throws DynamoDBMappingException {
        dynamoDBMapper.delete(author);
        return author;
    }

}
