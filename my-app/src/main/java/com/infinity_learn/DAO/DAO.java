package com.infinity_learn.DAO;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface DAO<T>
{
    public void insert(T entity);
    
    public void delete(T entity);

    public void update(T entity);

    public Document getDocument();

    public MongoCollection<Document> getCollection();
    
}
