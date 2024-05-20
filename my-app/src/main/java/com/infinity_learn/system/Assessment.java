package com.infinity_learn.system;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;


import org.bson.types.ObjectId;

public class Assessment 
{
    private ObjectId id;
    private String title;
    private ArrayList<Question> questions;
    private String description;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    

    public Assessment(String title, ArrayList<Question> questions, String description, String type, LocalDateTime startTime, LocalDateTime endTime) 
    {
        this.title = title;
        this.type = type;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questions = questions;
    }

    
    public ObjectId getId() 
    {
        return id;
    }

    public void setId(ObjectId id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    public ArrayList<Question> getQuestions() 
    {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) 
    {
        this.questions = questions;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public LocalDateTime getStartTime() 
    {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) 
    {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() 
    {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) 
    {
        this.endTime = endTime;
    }

    public String getType() 
    {
        return type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }


    public enum Type
    {
        EXAM("exam"),
        QUIZZ("quizz");
        
        private final String type;

        Type(String type)
        {
            this.type = type;
        }

        public String getType()
        {
            return this.type;
        }
    }

    public static LocalDateTime convertInLocalDateTime(Date date)
    {
        Instant instant = date.toInstant();
        
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static void print(Assessment assessment)
    {
        System.out.println("\n\n**************ASSESMENT " + assessment.getTitle() + "************\n\n");
        
        System.out.println("\nID: " + assessment.getId());
        System.out.print("\nType: " + assessment.getType());
        System.out.println("\nDescription: " + assessment.getDescription());
        System.out.println("Start Time: " + assessment.getStartTime());
        System.out.println("End Time: " + assessment.getEndTime());
        Question.print(assessment.getQuestions());
    }
    
}
