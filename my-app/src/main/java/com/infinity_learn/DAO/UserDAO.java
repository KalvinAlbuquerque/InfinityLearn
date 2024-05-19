package com.infinity_learn.DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.infinity_learn.system.User;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import org.bson.types.ObjectId;


public class UserDAO implements DAO<User>
{
    private static final String COLLECTIONNAME = "Users";
    private MongoCollection<Document> collection;



    public UserDAO(MongoDatabase database)
    {
        this.collection = database.getCollection(COLLECTIONNAME);
    }

    @Override
    public void insert(User user) 
    {
        try
        {

            Document userDocument = new Document(Keys.NAME.getKeyName(), user.getName())
                .append(Keys.LOGIN.getKeyName(), user.getLogin())
                .append(Keys.PASSWORD.getKeyName(), user.getPassword())
                .append(Keys.EMAIL.getKeyName(), user.getEmail())
                .append(Keys.PHONE_NUMBER.getKeyName(), user.getPhoneNumber());

            this.collection.insertOne(userDocument);

        }
        catch(Exception e)
        {
            System.out.println("Error in inserting a user document " + e.getMessage());
        }



        
    }

    @Override
    public MongoCollection<Document> getCollection() 
    {
        return this.collection;
    }

    @Override
    public Boolean delete(User user) 
    {
        DeleteResult deleteResult = this.collection.deleteOne(Filters.eq(Keys.ID.getKeyName(), user.getId()));

        long count = deleteResult.getDeletedCount();

        return count == 1;
    }

    @Override
    public void update(User user) 
    {
        this.collection.updateOne
        (
            Filters.eq(Keys.ID.getKeyName(), user.getId()), 
            Updates.combine
            (
                Updates.set(Keys.NAME.getKeyName(), user.getName()),
                Updates.set(Keys.LOGIN.getKeyName(), user.getLogin()),
                Updates.set(Keys.PASSWORD.getKeyName(), user.getPassword()),
                Updates.set(Keys.EMAIL.getKeyName(), user.getEmail()),
                Updates.set(Keys.PHONE_NUMBER.getKeyName(), user.getPhoneNumber())
            )
        );
    }

    public ArrayList<User> getAllUsers()
    {
        FindIterable<Document> findIterable = this.collection.find();
        ArrayList<User> users = new ArrayList<>();

        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext())
        {
            Document document = iterator.next();

            String name = document.getString(Keys.NAME.getKeyName());
            String login = document.getString(Keys.LOGIN.getKeyName());
            String password = document.getString(Keys.PASSWORD.getKeyName());
            String email = document.getString(Keys.EMAIL.getKeyName());
            String phone_number = document.getString(Keys.PHONE_NUMBER.getKeyName());
            ObjectId id = document.getObjectId(Keys.ID.getKeyName());

            User user = new User(name, email, login, password, phone_number);
            user.setId(id);

            users.add(user);
        }
        
        return users;
    }
    
    public User getUser(String login)
    {
        Document document = this.collection.find(Filters.eq(Keys.LOGIN.getKeyName(), login)).first();
        User newUser = null;
        if(!document.isEmpty())
        {

            String name = document.getString(Keys.NAME.getKeyName());
            String password = document.getString(Keys.PASSWORD.getKeyName());
            String email = document.getString(Keys.EMAIL.getKeyName());
            String phone_number = document.getString(Keys.PHONE_NUMBER.getKeyName());
            ObjectId id = document.getObjectId(Keys.ID.getKeyName());

            newUser = new User(name, email, login, password, phone_number);
            newUser.setId(id);

        }

        return newUser;


    }

     //public User getUser(String login)
      
    
    public enum Keys
    {
        NAME("userName"),
        LOGIN("login"),
        PASSWORD("password"),
        EMAIL("email"),
        PHONE_NUMBER("phone_number"),
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
