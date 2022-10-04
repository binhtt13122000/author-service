package com.example.author_spring_service.repository;

public interface Repository<T> {
    T getById(String id);
    T put(T t);

    T remove(T t);
}



//    QueryResultPage<T> getList(int limit, int offset, Map<String, String> filters);

