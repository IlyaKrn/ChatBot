package com.example.chatbot.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    public static final String INTENT_CATEGORY = "intent_category";
    private static final String CATEGORIES = "categories";

    public String name;
    public ArrayList<Question> questions;

    public Category() {
    }

    public static DatabaseReference getDatabase(){
        return FirebaseDatabase.getInstance().getReference(CATEGORIES);
    }
    public DatabaseReference getDatabaseQuestions(){
        return FirebaseDatabase.getInstance().getReference(CATEGORIES).child(this.name);
    }
}
