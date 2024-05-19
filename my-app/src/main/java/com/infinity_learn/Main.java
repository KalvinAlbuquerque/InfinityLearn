package com.infinity_learn;


import java.time.LocalDateTime;
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


        Question q1 = new Question("Questao 3", "Letra A");
        Question q2 = new Question("QUestao 4", "Letra B");

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);

        AssessmentDAO ad = new AssessmentDAO(database);

      /*   Assessment assessment = new Assessment("teste", questions, "Exame de teste", Assessment.Type.EXAM.getType(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        ad.insert(assessment); */
        
        Assessment assessment = ad.getAssessment("Terceiro Exame"); 
        ad.delete(assessment);
        
        //ad.delete(a)


    }
}