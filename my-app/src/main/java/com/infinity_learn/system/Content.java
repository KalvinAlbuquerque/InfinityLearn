package com.infinity_learn.system;

import java.net.URL;

import org.bson.types.ObjectId;

public class Content 
{
    private ObjectId id;
    private String name;
    private String description;
    private String type;
    private URL url;

    

    public Content(String name, String description, String type, URL url) 
    {
        this.name = name;
        this.description = description;
        this.type = type;
        this.url = url;
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

    public String getType() 
    {
        return type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public URL getUrl() 
    {
        return url;
    }

    public void setUrl(URL url) 
    {
        this.url = url;
    }

    
        
}
