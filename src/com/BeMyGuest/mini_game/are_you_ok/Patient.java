package com.BeMyGuest.mini_game.are_you_ok;

/**
 * This class generates Patient object
 * @author Heeyun Kim
 */
public class Patient{
   /** Represents the id of the patient, starts at 0 */
   private int id;
   /** Represents the name of the patient */
   private String name;
   /** Represents the symptom depression */
   private Symptom depression;
   /** Represents the symptom anxiety */
   private Symptom anxiety;
   /** Represents the symptom bipolar */
   private Symptom bipolar;
   /** Represents the symptom addiction */
   private Symptom addiction;
   /** Represents the array of symptoms */
   private Symptom[] symptomList = new Symptom[4];
   /** Represents the array of the rate of the symptoms */
   private int[] symptomRateList = new int[4];
   /**
    * Constructs the Patient object
    * @param id is the id of the patient
    * @param name is the name of the patient
    */
   public Patient(){
      this.id = 0;
   }
   
   public Patient(int id, String name)
   {
      this.id = id;
      this.name = name;
      depression = new Symptom("depression");
      anxiety = new Symptom("anxiety");
      bipolar = new Symptom("bipolar");
      addiction = new Symptom("addiction");
      updateList();
   }
   /**
    * Constructs the Patient object
    * @param id represents the id of the patient
    */
   public Patient(int id)
   {
      this.id = id;
      depression = new Symptom("depression");
      anxiety = new Symptom("anxiety");
      bipolar = new Symptom("bipolar");
      addiction = new Symptom("addiction");
      updateList();
   }
   /**
    * Updates the symptomList and symtomRateList whenever a patient is constructed or the rate of the symptom changed
    */
   private void updateList()
   {
      symptomList[0] = depression;
      symptomList[1] = anxiety;
      symptomList[2] = bipolar;
      symptomList[3] = addiction;
      symptomRateList[0] = depression.getRate();
      symptomRateList[1] = anxiety.getRate();
      symptomRateList[2] = bipolar.getRate();
      symptomRateList[3] = addiction.getRate();
   }
   public void setName(String name)
   {this.name = name;}
   public String getName()
   {return name;}
   public Symptom getDepression()
   {return depression;}
   public Symptom getAnxiety()
   {return anxiety;}
   public Symptom getBipolar()
   {return bipolar;}
   public Symptom getAddiction()
   {return addiction;}
   public int getId()
   {return id;}
   /** sets the rate of the symptoms, not the symptom itself */
   public void setDepression(int r)
   {depression.setRate(r);updateList();}
   /** sets the rate of the symptoms, not the symptom itself */
   public void setAnxiety(int r)
   {anxiety.setRate(r);updateList();}
   /** sets the rate of the symptoms, not the symptom itself */
   public void setBipolar(int r)
   {bipolar.setRate(r);updateList();}
   /** sets the rate of the symptoms, not the symptom itself */
   public void setAddiction(int r)
   {addiction.setRate(r);updateList();}
   /** normalizes the rate since there are uneven number of questions that is related to each symptom */
   public void normalize()
   {
      depression.setRate(depression.getRate()/depression.getNumRelatedQuestion()); 
      anxiety.setRate(anxiety.getRate()/anxiety.getNumRelatedQuestion()); 
      bipolar.setRate(bipolar.getRate()/bipolar.getNumRelatedQuestion()); 
      addiction.setRate(addiction.getRate()/addiction.getNumRelatedQuestion()); 
      updateList();
   }
   /** find out the most serious symptom according to the ratings of each symptom*/
   private Symptom mostSeriousOne()
   {
      return symptomList[Array.maxIndex(symptomRateList)];
   }
   /** find out the least serious symptom according ot the rating of each symptom */
   private Symptom leastSeriousOne()
   {
      return symptomList[Array.minIndex(symptomRateList)];
   }
   /**
    * Evaluates the result
    * @return The string that summarizes the patient's symptom
    */
   public String evaluateResult()
   {
      String result = "";
      for (int i=0; i<symptomList.length; i++){
         result += "Your " + symptomList[i].getType() + " rate is " + symptomList[i].getRate() + ". ";
      }
      if (mostSeriousOne().equals(leastSeriousOne()))
         result += "There isn't a serious problem that sticks out.";
      else
         result += "\n The most serious symptom is " + mostSeriousOne().getType() +", and the least serious symptom is " + leastSeriousOne().getType();
      if (Array.allOverEight(symptomRateList))
         result +="\n Since all of your sympyom has very high rating, I please talk to someone you trust about this issue.";
      else if (Array.anyOverEight(symptomRateList))
      {
         result += "\n Since you have at least one symptom with high rate, I recommend you talk to someone you trust about this issue";
      }
      
      return result;
   }
   public String toString()
   {

      String result = "";
      for (int i=0; i<symptomList.length; i++){
         result += name+"\'s " + symptomList[i].getType() + " rate is " + symptomList[i].getRate() + ". ";
      }
      return result;
   }
   /**
    * remove this patient from main's static variable
    */
   public void deletePatient()
   {
      Patient[] original = Talk.getPatientList();
      if (original.length == 1)
      {
         Talk.setPatientList(new Patient[0]);
         return;
      }
      Patient[] removed = {};
      for (int i=0; i<original.length; i++)
      {
         if (this.id==original[i].getId())
         {
            i++;
         }
         if (!(i==original.length))
            removed = Array.add(removed, original[i]);
      }
      Talk.setPatientList(removed);
     
   }
   /**
    * Gets the mode, mean, and sum of the ratings of the symptoms of this patient
    * @return String that summarizes the statistics of the ratings of the symptoms
    */
   public String stat()
   {
      int mode = Array.mode(symptomRateList);
      int mean = Array.mean(symptomRateList);
      int sum = Array.sum(Array.reverse(Array.reverse(symptomRateList)))/Array.intAt(100000,0);
      return name + "'s statistic: \n average of the rating of the symptoms: " + mean + "\n mode of the rating of the symptoms: "+ mode +"\n sum of the rating of the symptoms: "+ sum;
   }
}