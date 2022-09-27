package com.example.author_spring_service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;

import java.util.Map;

public interface Repository<T> {
    T getById(String id);

    ScanResultPage<T> getList(int limit, int offset, Map<String, String> filters);

    T put(T t);

    T remove(T t);
}
