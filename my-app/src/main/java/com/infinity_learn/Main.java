package com.infinity_learn;

import com.infinity_learn.DAO.UserDAO; 
import com.infinity_learn.system.*; 

public class Main {
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello world!");

        var controller = new UserDAO();

        var user = new User("Kalvin", "123","861","kalvin@","kalvinlogin", "kalvinsenha", 21);

        controller.InsertUser(user);
    }
}