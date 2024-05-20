package com.infinity_learn.system;

import java.util.ArrayList;

import org.bson.types.ObjectId;

public class Course 
{
    private ObjectId id;
    private String name;
    private String description;
    private ArrayList<Assessment> assessments;
    private ArrayList<Content> contents;

    

    public Course(String name, String description, ArrayList<Assessment> assessments, ArrayList<Content> contents) 
    {
        this.name = name;
        this.description = description;
        this.assessments = assessments;
        this.contents = contents;
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

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public ArrayList<Assessment> getAssessments() 
    {
        return assessments;
    }

    public void setAssessments(ArrayList<Assessment> assessments) 
    {
        this.assessments = assessments;
    }
    
    public ArrayList<Content> getContents() 
    {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) 
    {
        this.contents = contents;
    }

    
}
