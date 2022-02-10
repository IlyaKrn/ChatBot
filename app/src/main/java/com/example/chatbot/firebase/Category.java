package com.example.chatbot.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    public static final String INTENT_CATEGORY = "intent_category";
    public static final String CATEGORIES = "categories";

    public String name;
    public ArrayList<Question> questions = new ArrayList<>();

    public Category() {
    }

    public Category(String name, ArrayList<Question> questions) {
        this.name = name;
        this.questions = questions;
        if (this.questions == null){
            this.questions = new ArrayList<>();
        }
    }

    public static DatabaseReference getDatabase(){
        return FirebaseDatabase.getInstance().getReference(CATEGORIES);
    }
    public DatabaseReference getDatabaseQuestions(){
        return FirebaseDatabase.getInstance().getReference(CATEGORIES).child(this.name);
    }
}
