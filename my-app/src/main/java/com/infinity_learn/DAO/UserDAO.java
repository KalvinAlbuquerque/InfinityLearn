package com.infinity_learn.DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.infinity_learn.system.User;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.infinity_learn.database.ConnectionController;

public class UserDAO implements DAO<User>
{
    private MongoCollection<User> users;  
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
    public void delete(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Document getDocument() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void update(User user) {
        // TODO Auto-generated method stub
        
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
