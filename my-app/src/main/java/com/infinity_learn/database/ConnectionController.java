package com.infinity_learn.database;

import static org.bson.codecs.configuration.CodecRegistries.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase; 


public class ConnectionController 
{
    private static MongoClient connection;
    private static final String DATABASE_NAME = "InfinityLearn";
    private static String uri = "mongodb+srv://admin:anc131315@cluster0.wjbsbfc.mongodb.net/";

    public static MongoDatabase getDatabase()
    {
        try
        {
            if(connection == null)
            {
                /* Para criar um servidor local, basta retirar a URI e chamar o método create() sem parâmetros */
                connection = MongoClients.create(uri);
            }

            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), 
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

            MongoDatabase dataBase = connection.getDatabase(ConnectionController.DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
            
            return dataBase;
        }
        catch(Exception e)
        {
            System.out.println("Error in connecting database: " + e.getMessage());
            return null;
        }
    }

    public static void createCollection(String colectionName)
    {
        try
        {
            var mongoDatabase = getDatabase();
            mongoDatabase.createCollection(colectionName);
            System.out.println("Collection created sucessfully");
        }
        catch(Exception e)
        {
            System.out.println("Error in create collection: " + e.getMessage());
        }
    }

    
}
