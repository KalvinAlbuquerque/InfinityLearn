package com.infinity_learn;


import java.util.ArrayList;

import com.infinity_learn.DAO.AssessmentDAO;
import com.infinity_learn.DAO.UserDAO;
import com.infinity_learn.database.ConnectionController;
import com.infinity_learn.system.*; 


public class Main 
{
    public static void main(String[] args) throws Exception
    {
        
      var database = ConnectionController.getDatabase();


      User user = new User("Kalvin", "kalvin@", "kalvin123", "123456", "71984", "admin");
      UserDAO ud = new UserDAO(database);
      ud.insert(user);



    }
}