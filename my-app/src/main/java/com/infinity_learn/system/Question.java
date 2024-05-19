package com.infinity_learn.system;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.infinity_learn.DAO.QuestionDAO;

public class Question 
{
    private ObjectId id;
    private String description;
    private String answer;
    private String tag;

    
    public Question(String description, String answer) 
    {
        this.description = description;
        this.answer = answer;
    }

    public Question(Document document) 
    {
        this.description = document.getString(QuestionDAO.Keys.DESCRIPTION.getKeyName());
        this.answer = document.getString(QuestionDAO.Keys.ANSWER.getKeyName());
        this.tag = document.getString(QuestionDAO.Keys.TAG.getKeyName());
    }

    public ObjectId getId() 
    {
        return id;
    }

    public void setId(ObjectId id) 
    {
        this.id = id;
    }
    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getAnswer() 
    {
        return answer;
    }

    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }
    

    
    public String getTag() 
    {
        return tag;
    }
    
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public static void print(Question question)
    {
        System.out.println("\n\n**************QUESTION " + question.getTag() + "************\n\n");
        
        System.out.print("\nTag: " + question.getTag());
        System.out.println("\nID: " + question.getId());
        System.out.println("Answer: " + question.getAnswer());
    }

    public static void print(ArrayList<Question> questions)
    {
        System.out.println("\n\n**************QUESTIONS************");
        for(Question question : questions)
        {
            System.out.print("\nTag: " + question.getTag());
            System.out.println("\nID: " + question.getId());
            System.out.println("Answer: " + question.getAnswer());
        }
    }

    public static ArrayList<Question> createTag(ArrayList<Question> questions)
    {
        int tag = 0;
        for(Question question : questions)
        {
            question.setTag("Q00" + Integer.toString(tag));
            tag++;
        }

        return questions;
    }
    
    
}
