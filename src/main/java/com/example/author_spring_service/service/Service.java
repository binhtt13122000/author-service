package com.example.author_spring_service.service;

public interface Service<T> {
    T getById(String id);

    T add(T t);

    T update(T t);

    T delete(String uuid);
}




//    QueryResultPage<T> getData(int limit, int offset, Map<String, String> filters);

