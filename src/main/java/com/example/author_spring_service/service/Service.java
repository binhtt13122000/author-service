package com.example.author_spring_service.service;

import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;

import java.util.Map;

public interface Service<T> {
    T getById(String id);

    T add(T t);

    T update(T t);

    T delete(String uuid);

    ScanResultPage<T> getData(int limit, int offset, Map<String, String> filters);
}
