package com.infinity_learn.DAO;

import com.mongodb.client.MongoCollection;
import com.infinity_learn.system.User;
import com.infinity_learn.database.ConnectionController;

public class UserDAO 
{
    private MongoCollection<User> users;  

    public UserDAO()
    {
        try
        {
            this.users = ConnectionController.getDatabase().getCollection("User", User.class);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void InsertUser(User user)
    {
        var result = this.users.insertOne(user);

        if(result.getInsertedId() != null)
        {
            System.out.println("User inserted succesfully!");
        }
        else
        {
            System.out.println("User inserting failed");
        }
    }
}
