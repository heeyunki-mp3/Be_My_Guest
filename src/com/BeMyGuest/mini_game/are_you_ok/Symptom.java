package com.BeMyGuest.mini_game.are_you_ok;

/**
 * This class generates Symptom that the patient might have
 * @author Heeyun Kim
 */
public class Symptom
{
    /**The name of the symptom */
    private String type;
    /**To which extent this symptom is showing 
     * precondition: 0<=rate and rate<=10, 0 being least serious, 10 being the most serious
     */
    private int rate;
    /** Number of the question that relates to this symptom*/
    private int numRelatedQuestion;
    /**
     * Constructs the Symptom type
     * @param type is the name of the Symptom
     */
    public Symptom(String type)
    {
        this.type = type;
        rate = 0;
        numRelatedQuestion = 0;
    }
    /**
     * Consturcts the Symptom type
     * @param type is the name of the Symptom
     * @param rate is to which extent the Symptom is serious
     */
    public Symptom(String type, int rate)
    {
        this.type = type;
        this.rate = rate;
        numRelatedQuestion = 0;
    }
    public void setNumRelatedQuestion(int n) {numRelatedQuestion = n;}
    public void incrementNumRelatedQuestion(){numRelatedQuestion++;}
    public int getNumRelatedQuestion(){return numRelatedQuestion;}
    public String getType(){return type;}
    public int getRate(){return rate;}
    public void setType(String type){this.type = type;}
    public void setRate(int rate){this.rate = rate;}

}