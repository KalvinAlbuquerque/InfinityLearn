package com.infinity_learn.DAO;

import org.bson.Document;

import com.infinity_learn.system.Question;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

public class QuestionDAO implements DAO<Question>
{
    private static final String COLLECTIONNAME = "Assessments";
    private MongoCollection<Document> collection;



    public QuestionDAO(MongoDatabase database)
    {
        this.collection = database.getCollection(COLLECTIONNAME);
    }

    @Override
    public void insert(Question question) 
    {
        try
        {
            Document questionDocument = new Document(Keys.DESCRIPTION.getKeyName(), question.getDescription())
            .append(Keys.ANSWER.getKeyName(), question.getAnswer())
            .append(Keys.TAG.getKeyName(), question.getTag());
            
            this.collection.insertOne(questionDocument);
        }
        catch(Exception e)
        {
            System.out.println("Error in inserting a question document " + e.getMessage());
        }
    }

    @Override
    public Boolean delete(Question question) 
    {   
        DeleteResult deleteResult = this.collection.deleteOne(Filters.eq(Keys.ID.getKeyName(), question.getId()));

        long count = deleteResult.getDeletedCount();

        return count == 1;
    }
    

    @Override
    public MongoCollection<Document> getCollection() 
    {
        return this.collection;
    }


    @Override
    public void update(Question question) 
    {
        this.collection.updateOne(Filters.eq(Keys.ID.getKeyName(), question.getId()),
            Updates.combine
            (
                Updates.set(Keys.ANSWER.getKeyName(), question.getAnswer()),
                Updates.set(Keys.TAG.getKeyName(), question.getTag()),
                Updates.set(Keys.DESCRIPTION.getKeyName(), question.getDescription())
            )
        
        );
        
    }
    
    public static enum Keys
    {
        DESCRIPTION("description"),
        ANSWER("answer"),
        TAG("tag"),
        ID("_id");
        
        private final String keyName;

        Keys(String keyName)
        {
            this.keyName = keyName;
        }

        public String getKeyName()
        {
            return this.keyName;
        }
    }

}

