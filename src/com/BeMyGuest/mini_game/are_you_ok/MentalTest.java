package com.BeMyGuest.mini_game.are_you_ok;

import java.awt.event.*; 
import java.awt.Color;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.swing.border.*;

import javax.imageio.ImageIO;
public class MentalTest {
    private static JTextArea fromComputer;
    private static JTextField toComputer;
    private Question [] questionList;
    
    public MentalTest(){
        
        Patient you = new Patient(0,"dummyName");
        this.initialize(you);

        JFrame f=new JFrame("Let's Talk!");  
        JLabel title = new JLabel("Have a Chat!");
        fromComputer=new JTextArea();
        toComputer = new JTextField();

        fromComputer.setEditable(false);
        fromComputer.setLineWrap(true);
        fromComputer.setText("Hello, I am Dr. Helper. If you are having a hard time, please visit me! \nI might be able to give you some advice and find out any problems! \n \nShall we start? How can I call you?");  

        fromComputer.setBounds(50,50, 300,150);  

        Border border = BorderFactory.createLineBorder(Color.BLUE);
        fromComputer.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    
        toComputer.setBounds(50,300, 150,30);

        JButton b=new JButton("Send"); 
        try{
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/mini_game/are_you_ok/talkBackground.png")))));
        }catch(IOException e){
            e.printStackTrace();
        }
        b.setBounds(250,300,95,30);  
        b.addActionListener(new ActionListener(){ 
        int questionIndex = -1; 
        public void actionPerformed(ActionEvent e){
                if (questionIndex == -1){
                    you.setName(toComputer.getText());
                    fromComputer.setText("Great! " + you.getName() + "! Do you feel less interested in doing things?");
                }else{
                    talk(questionIndex, you);
                }
                toComputer.setText(""); 
                questionIndex ++;
                 
            }  
        });
        
        f.add(b);
        f.add(toComputer);
        f.add(fromComputer);  
        f.add(title);  
        f.setSize(400,500);  
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);  
        f.setVisible(true);   
    }
    public void initialize(Patient patient){
        Symptom depression = patient.getDepression();
        Symptom anxiety = patient.getAnxiety();
        Symptom bipolar = patient.getBipolar();
        Symptom addiction = patient.getAddiction();
        
        Question q1 = new Question(patient.getName() + ", do you feel less interested in doing things?", depression);
        Question q2 = new Question("Do you feel down, depresed, or hopeless?", depression);
        Question q3 = new Question("Do you have trouble falling or staying asleep, or sleeping too much?", depression, anxiety);
        Question q4 = new Question("Do you feel tired or having little energy?", depression);
        Question q5 = new Question("Do you have poor appetite or overeating?", depression, anxiety);
        Question q6 = new Question("Do you feel bad about yourself?", depression);
        Question q7 = new Question("Do you have trouble concentrating on things, such as reading?", depression);
        Question q8 = new Question("Do you move or speak so slowly that over people could have noticed?", depression);
        Question q9 = new Question("Have you every thought of hurting yourself?", depression);
        Question q10 = new Question("How much these problem had affected you?", depression, anxiety, bipolar);
        Question q11 = new Question("Do you feel nervous, anxious or on edge?", anxiety);
        Question q12 = new Question("Do you get easily annoyed or irritable?", anxiety);
        Question q13 = new Question("Do you get afraid, as if something awful might happen?", anxiety);
        Question q14 = new Question("Do you have trouble relaxing?", anxiety);
        Question q15 = new Question("Do you feel restless that it is hard to sit still", anxiety);
        Question q16 = new Question("Have you ever felt so good or hyper that other people thought you were not your normal self?", bipolar);
        Question q17 = new Question("Have you ever felt so irritable and anxious that you shouted at people?", bipolar, anxiety);
        Question q18 = new Question("Have you ever felt much more talkative or spoke much faster than usual?", bipolar);
        Question q19 = new Question("How long had such problems were affecting you? (long, short, very long, etc.)", bipolar, anxiety, depression);
        Question q20 = new Question("Have you ever felt that you ought to cut down on your drinking or drug use?", addiction);
        Question q21 = new Question("Have people annoyed you by criticizing your drinking or drug use?", addiction, depression);
        Question q22 = new Question("Have you ever felt bad or guilty about your drinking or drug use?", addiction);
        Question[] questionL= {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22};
        this.questionList = questionL;
    }
   
    public void talk(int i, Patient patient){
        String response = toComputer.getText(); 

        int rate = Talk.rate(response);
        Symptom[] relatedSymptomlist = questionList[i].getRelatedSymptom();
        for (int j=0; j<relatedSymptomlist.length; j++)
        {
            relatedSymptomlist[j].setRate(relatedSymptomlist[j].getRate()+rate);
        }
        if(i >= questionList.length-2){
            patient.normalize();
            fromComputer.setText("Well done! These are the result! \n" + patient.stat());
        }
        else{
            fromComputer.setText(react(rate) + "\n" + questionList[i+1].getQuestion());
        }
    } 
    public String react(int r) 
    {
        if (r>8)
        {return ("Aw... I am sorry to hear that...");}
        if (r>5 && r<=8)
        {return("Oh... I see. ");}
        if (3<r && r<=5)
        {return("Hmm... Okay.");}
        if (r <= 3)
        {return("Awesome!");}
        return "";
    } 
}  
