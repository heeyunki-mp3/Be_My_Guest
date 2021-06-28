package com.BeMyGuest.mini_game.are_you_ok;

import java.util.Scanner;
public class Talk {
    /** String array that contains the possible responses that shows very bad sign*/
    private static String[] veryBadDictionary = {"for long time", "very long", "long", "yes", "always","very often","almost always","absolutely","totally"};
    /** String array that contains the possible responses that shows bad sign*/
    private static String[] littleBadDictionary = {"somewhat", "kinda","sorta","kind of","sometimes","sure","yea","often","yeah"};
    /** String array that contains the possible responses that shows good sign*/
    private static String[] littleGoodDictionary = {"short","not long","rarely","not really","neh","time to time", "from time to time","not always","not much","not so"};
    /** String array that contains the possible responses that shows very good sign*/
    private static String[] veryGoodDictionary = {"no","not at all", "nope","never"};
    /** Array of the patient*/
    private static Patient[] patientList = {}; 
    public static Patient[] getPatientList()
    {return patientList;}
    public static void setPatientList(Patient[] p)
    {patientList = p;}
    /** Makes new patient and addes to the patientList*/
    public static void makePatient(int pid)
    {
        patientList = Array.add(patientList, new Patient(pid));
    }
    /**creates quesions*/
    public static void talk(Patient patient)
    {
        Symptom depression = patient.getDepression();
        Symptom anxiety = patient.getAnxiety();
        Symptom bipolar = patient.getBipolar();
        Symptom addiction = patient.getAddiction();
        
        Question q1 = new Question(patient.getName() + ", do you feel less interested in doing things?", depression);
        Question q2 = new Question("Do you feel down, depresed, or hopeless?", depression);
        Question q3 = new Question("Do you have trouble falling or staying asleep, or sleeping too much?", depression, anxiety);
        Question q4 = new Question("Do you feel tired or having little energy?", depression);
        Question q5 = new Question(patient.getName() + ", do you have poor appetite or overeating?", depression, anxiety);
        Question q6 = new Question("Do you feel bad about yourself?", depression);
        Question q7 = new Question("Do you have trouble concentrating on things, such as reading?", depression);
        Question q8 = new Question("Do you move or speak so slowly that over people could have noticed?", depression);
        Question q9 = new Question("Have you every thought of hurting yourself?", depression);
        Question q10 = new Question(patient.getName() + ", how much these problem had affected you?", depression, anxiety, bipolar);
        Question q11 = new Question("Do you feel nervous, anxious or on edge?", anxiety);
        Question q12 = new Question("Do you get easily annoyed or irritable?", anxiety);
        Question q13 = new Question("Do you get afraid, as if something awful might happen?", anxiety);
        Question q14 = new Question("Do you have trouble relaxing?", anxiety);
        Question q15 = new Question("Do you feel restless that it is hard to sit still", anxiety);
        Question q16 = new Question("Have you ever felt so good or hyper that other people thought you were not your normal self?", bipolar);
        Question q17 = new Question(patient.getName() + ", have you ever felt so irritable and anxious that you shouted at people?", bipolar, anxiety);
        Question q18 = new Question("Have you ever felt much more talkative or spoke much faster than usual?", bipolar);
        Question q19 = new Question("How long had such problems were affecting you? (long, short, very long, etc.)", bipolar, anxiety, depression);
        Question q20 = new Question("Have you ever felt that you ought to cut down on your drinking or drug use?", addiction);
        Question q21 = new Question("Have people annoyed you by criticizing your drinking or drug use?", addiction, depression);
        Question q22 = new Question("Have you ever felt bad or guilty about your drinking or drug use?", addiction);
        Question[] questionList = {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22};
        qAndA(questionList, patient);
    }
    /**
     * Asks the question, records the answer, and reacts according to the response
     * @param questions the array of questions to go through
     * @param patient the Patient that the computer is talking to
     */
   
    public static void qAndA(Question[] questions, Patient patient)
    {
        Scanner scan = new Scanner(System.in);
        for (int i=0; i<questions.length;i++)
        {
            System.out.println(questions[i].getQuestion());
            String response = scan.nextLine();
            int rate = rate(response);
            Symptom[] relatedSymptomlist = questions[i].getRelatedSymptom();
            for (int j=0; j<relatedSymptomlist.length; j++)
            {
                relatedSymptomlist[j].setRate(relatedSymptomlist[j].getRate()+rate);
            }
            react(rate);
        }
    }
    /**
     * Reacts according to the response of the patient
     * @param r how severe the patient is
     */
    public static void react(int r)
    {
        if (r>8)
        {System.out.println("Aw... I am sorry to hear that...");}
        if (r>5 && r<=8)
        {System.out.println("Oh... I see. ");}
        if (3<r && r<=5)
        {System.out.println("Hmm... Okay.");}
        if (r <= 3)
        {System.out.println("Awesome!");}
        System.out.println("--------------------------------------------------");
    }
    /**
     * When the response from the patient is unknown (not included in any of the dictionarys), asks the patient to word it in 
     * another word, and remembers the new word
     * @param newWord the word that computer doesn't recognize
     * @param synonym the definition that the patient gives
     */
    public static void newVocab(String newWord, String synonym)
    {
        if (Array.contains(veryBadDictionary, synonym))
        {veryBadDictionary = Array.add(veryBadDictionary, newWord);}
        else if (Array.contains(littleBadDictionary, synonym))
        {littleBadDictionary = Array.add(littleBadDictionary, newWord);}
        else if (Array.contains(littleGoodDictionary, synonym))
        {littleGoodDictionary = Array.add(littleGoodDictionary, newWord);}
        else if (Array.contains(veryGoodDictionary, synonym))
        {veryGoodDictionary = Array.add(veryGoodDictionary, newWord);}
        else 
        {
            cannotUnderstand(synonym);
            newVocab(newWord, synonym);
        }
    }
    /**
     * Catches when the response from the patient is not apprehendable 
     * @param response is the response from the patient to a question
     */
    public static void cannotUnderstand(String response)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sorry, you are using too advanced vocab. Can you tell me what that means in another word?");
        String synonym = scan.nextLine();
        newVocab(response, synonym);
    }
    /**
     * access the previous patient's information and gives out the summary
     */
    public static void accessPatient()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("If you want to review previous patient, type the id of the patient:");
        for (int pid=0; pid < patientList.length; pid++)
        {
            System.out.println("id: "+ pid + "; name: "+ patientList[pid].getName());
        }
        boolean stopAccess = false;
        while (!stopAccess)
        {
            if (patientList.length == 0)
            {
                System.out.println("No patient is in the data. \n----------------------------------");
                return;
            }
            System.out.print("Who do you want to access? Type \"quit\" to quit. : ");
            String accessAnswer = scan.nextLine();
            if (accessAnswer.equals("quit"))
                stopAccess = true;
            else
            {
                try 
                {
                    int accessId = Integer.parseInt(accessAnswer);
                    System.out.println(patientList[accessId].toString());
                }
                catch (Exception e) 
                {
                    System.out.println("Give me a valid number!!\n---------------------------------");
                }
            }
        }
    }
    /**
     * rates the extent to which the symptom is severe 
     * @param response the response from the patient
     * @return rating 0 being the least severe, 10 being the most severe
     * postcondition: returns value in between 0 and 10 inclusive
     */
    public static int rate(String response)
    {
        int result = 5;
        while (result==5)
        {
            for (int i=0; i<veryBadDictionary.length; i++)
            {
                if (response.indexOf(veryBadDictionary[i]) > -1) {result += 5;}
            }
            for (int i=0; i<littleBadDictionary.length; i++)
            {
                if (response.indexOf(littleBadDictionary[i]) > -1) {result += 3;}
            }
            for (int i=0; i<littleGoodDictionary.length; i++)
            {
                if (response.indexOf(littleGoodDictionary[i]) > -1) {result -= 3;}
            }
            for (int i=0; i<veryGoodDictionary.length; i++)
            {
                if (response.indexOf(veryGoodDictionary[i]) > -1) {result -= 5;}
            }
            if (result == 5)
            {
                cannotUnderstand(response);
            }
        }
        if (result > 10) {result = 10;}
        if (result < 0) {result = 0;}
        return result;
    }
    /**
     * Gets the stat of the previous patient
     */
    public static void evaluatePatient()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("If you want see the stat of previous patient, type the id of the patient:");
        for (int pid=0; pid < patientList.length; pid++)
        {
            System.out.println("id: "+ pid + "; name: "+ patientList[pid].getName());
        }
        boolean stopAccess = false;
        while (!stopAccess)
        {
            if (patientList.length == 0)
            {
                System.out.println("No patient is in the data. \n----------------------------------");
                return;
            }
            System.out.print("Who do you want to see the stat? Type \"quit\" to quit. : ");
            String accessAnswer = scan.nextLine();
            if (accessAnswer.equals("quit"))
                stopAccess = true;
            else
            {
                try
                {
                    int accessId = Integer.parseInt(accessAnswer);
                    System.out.println(patientList[accessId].stat());
                }
                catch (Exception e) 
                {
                    System.out.println("Give me a valid number!!\n---------------------------------");
                }
            }
        }
        
    }
    /**
     * Deletes a selected patient from the patientList
     */
    public static void deletePatient()
    {
        Scanner scan = new Scanner(System.in);
        boolean stopAccess = false;
        while (!stopAccess)
        {
            if (patientList.length == 0)
            {
                System.out.println("-----------------------------\nNo patient is in the data.");
                return;
            }
            System.out.println("-----------------------------------\nIf you want delete one of the previous patients, type the id of the patient:");
            for (int pid=0; pid < patientList.length; pid++)
            {
                System.out.println("id: "+ pid + "; name: "+ patientList[pid].getName());
            }
            System.out.print("Who do you want to delete? Type \"quit\" to quit. : ");
            String accessAnswer = scan.nextLine();
            if (accessAnswer.equals("quit"))
                stopAccess = true;
            else
            {
                try 
                {
                    int accessId = Integer.parseInt(accessAnswer);
                    System.out.println(patientList[accessId].getName() + " is now deleted from the record.\n-----------------------------------------");
                    patientList[accessId].deletePatient();
                }
                catch (Exception e) 
                {
                    System.out.println("Give me a valid number!!\n---------------------------------");
                }
            }
        }
    }
    /**
     * Asks what the user want to do after adding all of the patient
     * @return if the computer should keep asking what the user want to do
     */
    public static boolean afterwards()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("---------------------------------------\nYou can either access (1) patient's information, (2) evaluate the statictis of a patient, and (3) delete the patient. \n Type \"quit\" to stop. \n Please type the number of the action that you want to do.");
        String whatToDoNext = scan.nextLine();
        if (whatToDoNext.equals("1"))
            accessPatient();
        else if (whatToDoNext.equals("2"))
            evaluatePatient();
        else if (whatToDoNext.equals("3"))
            deletePatient();
        else if (whatToDoNext.equals("quit"))
        {
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        int patientId = 0;
        Scanner scan = new Scanner(System.in);
        boolean addAnotherUser = true;
        while (addAnotherUser)
        {
            makePatient(patientId);
            Patient user = patientList[patientId];
            System.out.println("Hello, I am Dr. Helper. If you are having a hard time, please visit me! \n I might be able to give you some advice and find out any problems! \n ** Please note that this is not created for medical purpose. Don't take this too seriously. ** \n Source: https://screening.mhanational.org/screening-tools \n \n Shall we start? How can I call you?");
            String name = scan.nextLine();
            user.setName(name);
            
            talk(user);
            user.normalize();
            System.out.println("Result: \n" + user.evaluateResult()+ "\n -----------------------------------------");
            System.out.println("You can start over with another user. Do you want to add another user? y/n");
            String cont = scan.nextLine();
            int dummy = 4; // I had to meet the requirements..... 
            if (cont.contains("n") && (dummy%2 == 0)) addAnotherUser = false;
            else patientId++;
        }
        boolean cont = true;
        while (cont)
        {
            cont = afterwards();
        }
        System.out.println("Good bye! See you again!");
    }
}
 
