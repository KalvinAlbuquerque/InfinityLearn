package com.infinity_learn.system;

import java.util.ArrayList;

import org.bson.types.ObjectId;

public class User
{
    private ObjectId id;
    private String name;
    private String email;
    private String login;
    private String password;
    private String phoneNumber;


    
    public User(String name, String email, String login, String password, String phoneNumber) 
    {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }    

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public ObjectId getId() 
    {
        return id;
    }

    public void setId(ObjectId id) 
    {
        this.id = id;
    }

    public String getName() 
    {

        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public static void print(ArrayList<User> users)
    {
        System.out.println("\n\n**************USERS************\n\n");
        for(User user : users)
        {
            System.out.print("\nName: " + user.getName());
            System.out.println("\nID: " + user.getId());
            System.out.println("Login: " + user.getLogin());
            System.out.println("Password: " + user.getPassword());
            System.out.println("E-mail: " + user.getEmail());
            System.out.println("Phone Number: " + user.getPhoneNumber());
        }

    }

    public static void print(User user)
    {
        System.out.println("\n\n**************USER " + user.getName() + "************\n\n");
        
        System.out.print("\nName: " + user.getName());
        System.out.println("\nID: " + user.getId());
        System.out.println("Login: " + user.getLogin());
        System.out.println("Password: " + user.getPassword());
        System.out.println("E-mail: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());

    }
}
