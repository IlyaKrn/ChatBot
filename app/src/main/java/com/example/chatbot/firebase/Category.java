package com.example.chatbot.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Category implements Serializable {
    public static final String INTENT_CATEGORY = "intent_category";
    private static final String CATEGORIES = "categories";
    public String name;

    public static DatabaseReference getDatabase(){
        return FirebaseDatabase.getInstance().getReference(CATEGORIES);
    }
}
