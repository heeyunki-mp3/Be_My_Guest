package com.BeMyGuest.mini_game.are_you_ok;

/**
 * The class generates the Question object to be asked to the user
 * @author Heeyun Kim
 */
public class Question
{
    /** Represents the question to be asked*/
    private String question;
    /** Represents the mental symptom that is associated with the question */
    private Symptom[] relatedSymptom = {};
    /**
     * Constructs the Question
     * @param q Question
     * @param s0 Symptom 1
     * @param s1 Symptom 2
     * @param s2 Symptom 3
     * @param s3 Symptom 4
     */
    public Question(String q, Symptom s0, Symptom s1, Symptom s2, Symptom s3)
    {
        question = q;
        relatedSymptom = Array.add(relatedSymptom, s0);
        relatedSymptom = Array.add(relatedSymptom, s1);
        relatedSymptom = Array.add(relatedSymptom, s2);
        relatedSymptom = Array.add(relatedSymptom, s3);
        updateNumRelatedQuestion();
    }
    /**
     * Constructs the Question
     * @param q Question
     * @param s0 Symptom 1
     * @param s1 Symptom 2
     * @param s2 Symptom 3
     */
    public Question(String q, Symptom s0, Symptom s1, Symptom s2)
    {
        question = q;
        relatedSymptom = Array.add(relatedSymptom, s0);
        relatedSymptom = Array.add(relatedSymptom, s1);
        relatedSymptom = Array.add(relatedSymptom, s2);
        updateNumRelatedQuestion();
    }
    /**
     * Constructs the Question
     * @param q Question
     * @param s0 Symptom 1
     * @param s1 Symptom 2
     */
    public Question(String q, Symptom s0, Symptom s1)
    {
        question = q;
        relatedSymptom = Array.add(relatedSymptom, s0);
        relatedSymptom = Array.add(relatedSymptom, s1);
        updateNumRelatedQuestion();
    }
    /**
     * Constructs the Question
     * @param q Question
     * @param s0 Symptom 1
     */
    public Question(String q, Symptom s0)
    {
        question = q;
        relatedSymptom = Array.add(relatedSymptom, s0);
        updateNumRelatedQuestion();
    }
    /**
     * updates the number of related question for each related symptom
     */
    public void updateNumRelatedQuestion()
    {
        for (int i=0; i<relatedSymptom.length; i++)
        {
            relatedSymptom[i].incrementNumRelatedQuestion();
        }
    }
    public Symptom[] getRelatedSymptom()
    {
        return relatedSymptom;
    }
    public String getQuestion()
    {
        return question;
    }
    public void setRelatedSymptom(Symptom[] s)
    {relatedSymptom = s;}
    public void setQuestion(String q)
    {question = q;}

}