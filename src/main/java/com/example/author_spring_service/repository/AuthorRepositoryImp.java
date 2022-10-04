package com.example.author_spring_service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.example.author_spring_service.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AuthorRepositoryImp implements AuthorRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Author getById(String id) throws DynamoDBMappingException {
        return dynamoDBMapper.load(Author.class, id);
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




//    @Override
//    public QueryResultPage getList(int limit, int offset, Map<String, String> filters) {
//        Map<String,String> expressionAttributesNames = new HashMap<>();
//        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
//        final ArrayList<String> filterScan = new ArrayList<>();
//        filters.forEach((key, value) -> {
//            String variable = String.format(Common.SCAN_FILTER_VARIABLE, key);
//            String data = String.format(Common.SCAN_FILTER_DATA, key);
//            expressionAttributesNames.put(variable, key);
//            expressionAttributeValues.put(data, new AttributeValue().withS(value));
//            filterScan.add(String.format(Common.SCAN_ATTRIBUTE_FORMAT, variable, data));
//        });
//        Author author = new Author();
//        author.setUuid("1");
//        DynamoDBQueryExpression<Author> queryExpression = new DynamoDBQueryExpression<Author>()
//                .withHashKeyValues(author)
//                .withFilterExpression(String.join(Common.DELIMITER, filterScan))
//                .withExpressionAttributeNames(expressionAttributesNames)
//                .withExpressionAttributeValues(expressionAttributeValues)
//                .withLimit(limit)
//                .withConsistentRead(false);
//        return dynamoDBMapper.queryPage(Author.class, queryExpression);
//    }
