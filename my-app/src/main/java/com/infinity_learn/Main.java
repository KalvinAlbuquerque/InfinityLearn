package com.infinity_learn;

import java.util.ArrayList;

import com.infinity_learn.DAO.UserDAO;
import com.infinity_learn.database.ConnectionController;
import com.infinity_learn.system.*; 


public class Main 
{
    public static void main(String[] args) throws Exception
    {
        
        var database = ConnectionController.getDatabase();

        UserDAO userDAO = new UserDAO(database);

        User user = new User("Amanda", "Amanda@", "Amanda123", "Amanda12345", "719");
        userDAO.insert(user);
        
        ArrayList<User> users = userDAO.getAllUsers();
        User.print(users);
        

    }
}