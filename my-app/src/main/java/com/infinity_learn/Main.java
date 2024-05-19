package com.infinity_learn;


import com.infinity_learn.DAO.UserDAO;
import com.infinity_learn.database.ConnectionController;
import com.infinity_learn.system.*; 


public class Main 
{
    public static void main(String[] args) throws Exception
    {
        
        var database = ConnectionController.getDatabase();

        UserDAO userDAO = new UserDAO(database);
        
        User Amanda = userDAO.getUser("Amanda123");

        userDAO.delete(Amanda);
    }
}