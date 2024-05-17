package com.infinity_learn.system;

import org.bson.types.ObjectId;

public class User
{
    
    public User(String name, String rg, String cpf, String email, String login, String senha, int age) 
    {
        this.name = name;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.age = age;
    }
    

    private ObjectId id;
    private String name;
    private String rg;
    private String cpf;
    private String email;
    private String login;
    private String senha;
    private int age;
}
