package com.infinity_learn.DAO;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface DAO<T>
{
    public void insert(T entity);
    
    public Boolean delete(T entity);

    public void update(T entity);

    public MongoCollection<Document> getCollection();
    
}
