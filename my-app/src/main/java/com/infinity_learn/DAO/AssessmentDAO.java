package com.infinity_learn.DAO;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.infinity_learn.system.Assessment;
import com.infinity_learn.system.Question;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

public class AssessmentDAO implements DAO<Assessment> 
{
    private static final String COLLECTIONNAME = "Assessments";
    private static MongoCollection<Document> collection;

    public AssessmentDAO(MongoDatabase database)
    {
        AssessmentDAO.collection = database.getCollection(COLLECTIONNAME);
    }

    public AssessmentDAO(){};
    
    @Override
    public void insert(Assessment assessment) 
    {   
        assessment.setQuestions(Question.createTag(assessment.getQuestions()));

        try
        {
            Document assessmentDocument = new Document(Keys.TITLE.getKeyName(), assessment.getTitle())
                .append(Keys.DESCRIPTION.getKeyName(), assessment.getDescription())
                .append(Keys.TYPE.getKeyName(), assessment.getType())
                .append(Keys.START_TIME.getKeyName(), assessment.getStartTime())
                .append(Keys.END_TIME.getKeyName(), assessment.getEndTime());
                

            List<Document> questionsDocument = assessment.getQuestions().stream()
                .map(question -> new Document(QuestionDAO.Keys.TAG.getKeyName(), question.getTag())
                    .append(QuestionDAO.Keys.DESCRIPTION.getKeyName(), question.getDescription())
                    .append(QuestionDAO.Keys.ANSWER.getKeyName(), question.getAnswer()))
                .collect(Collectors.toList());

            assessmentDocument.append(Keys.QUESTIONS.getKeyName(), questionsDocument);

            AssessmentDAO.collection.insertOne(assessmentDocument);
        }
        catch(Exception e)
        {
            System.out.println("Error in inserting a question document " + e.getMessage());
        }
    }

    public static ArrayList<Question> getQuestions(String assessmentTitle)
    {
        ArrayList<Question> questions = new ArrayList<>();
        
        Document assessmentDocument = AssessmentDAO.collection.find(Filters.eq(Keys.TITLE.getKeyName(), assessmentTitle)).first();
        if (assessmentDocument != null) 
        {
            List<Document> questionDocuments = assessmentDocument.getList(Keys.QUESTIONS.getKeyName(), Document.class);

            if (questionDocuments != null) 
            {
                for (Document questionDoc : questionDocuments) 
                {
                    questions.add(new Question(questionDoc));
                }
            }
        }

        return questions;
    }

    public static ArrayList<Question> getQuestions(Document document)
    {
        ArrayList<Question> questions = new ArrayList<>();
        
        List<Document> questionDocuments = document.getList(Keys.QUESTIONS.getKeyName(), Document.class);

        if (questionDocuments != null) 
        {
            for (Document questionDoc : questionDocuments) 
            {
                questions.add(new Question(questionDoc));
            }
        }

        return questions;
    }
    

    public static Question getQuestion(String tag)
    {
        ArrayList<Question> questions = new ArrayList<>();
        Question searchedQuestion = null;
        for(Question question : questions)
        {
            if(question.getTag().equals(tag))
            {
                searchedQuestion = question;
                break;
            }
        }

        return searchedQuestion;

    }

    public static Assessment getAssessment(String title)
    {
        Document document = AssessmentDAO.collection.find(Filters.eq(Keys.TITLE.getKeyName(), title)).first();
        System.out.println(title);
        Assessment assessment = null;
        if (document != null && !document.isEmpty())
        {
            String description = document.getString(Keys.DESCRIPTION.getKeyName());
            String type = document.getString(Keys.TYPE.getKeyName());
            LocalDateTime startTime =  Assessment.convertInLocalDateTime(document.getDate(Keys.START_TIME.getKeyName()));
            LocalDateTime endTime =  Assessment.convertInLocalDateTime(document.getDate(Keys.END_TIME.getKeyName()));
            ArrayList<Question> questions = getQuestions(document);
            ObjectId id = document.getObjectId(Keys.ID.getKeyName());

            assessment = new Assessment(title, questions, description, type, startTime, endTime);
            assessment.setId(id);
        }

        return assessment;
    }

    @Override
    public Boolean delete(Assessment assessment) {
       
        DeleteResult deleteResult = AssessmentDAO.collection.deleteOne(Filters.eq(Keys.ID.getKeyName(), assessment.getId()));

        long count = deleteResult.getDeletedCount();

        return count == 1;
    }

    @Override
    public MongoCollection<Document> getCollection()
    {
        return AssessmentDAO.collection;
    }


    @Override
    public void update(Assessment assessment) 
    {
        assessment.setQuestions(Question.createTag(assessment.getQuestions()));

        List<Document> questions = assessment.getQuestions().stream()
        .map(question -> new Document(QuestionDAO.Keys.TAG.getKeyName(), question.getTag())
            .append(QuestionDAO.Keys.DESCRIPTION.getKeyName(), question.getDescription())
            .append(QuestionDAO.Keys.ANSWER.getKeyName(), question.getAnswer()))
        .collect(Collectors.toList());

        
        AssessmentDAO.collection.updateOne
        (
            Filters.eq(Keys.ID.getKeyName(), assessment.getId()),
            Updates.combine
            (
                Updates.set(Keys.TITLE.getKeyName(),assessment.getTitle()),
                Updates.set(Keys.DESCRIPTION.getKeyName(),assessment.getDescription()),
                Updates.set(Keys.START_TIME.getKeyName(), assessment.getStartTime()),
                Updates.set(Keys.END_TIME.getKeyName(),assessment.getEndTime()),
                Updates.set(Keys.TYPE.getKeyName(), assessment.getType()),
                Updates.set(Keys.QUESTIONS.getKeyName(),questions)
            )
        );
    }

    private enum Keys
    {
        TITLE("title"),
        QUESTIONS("questions"),
        TYPE("type"),
        DESCRIPTION("description"),
        START_TIME("start_time"),
        END_TIME("end_time"),
        ID("_id");
        
        private final String keyName;

        Keys(String keyName)
        {
            this.keyName = keyName;
        }

        public String getKeyName()
        {
            return this.keyName;
        }
    }

    
}
