package com.example.chatbot.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Question implements Serializable {
    public static final String INTENT_QUESTION = "intent_question";
    public String question;
    public String answer;
    public static final String OFTEN_QUESTIONS = "often_questions";

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public static DatabaseReference getOftenQuestions(){
        return FirebaseDatabase.getInstance().getReference(OFTEN_QUESTIONS);
    }
}
