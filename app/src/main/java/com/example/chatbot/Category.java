package com.example.chatbot;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Category {
    private static final String CATEGORIES = "categories";
    public String name;

    public static DatabaseReference getDatabase(){
        return FirebaseDatabase.getInstance().getReference(CATEGORIES);
    }
}
