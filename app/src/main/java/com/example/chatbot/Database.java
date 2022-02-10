package com.example.chatbot;

import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Database {
    public static ArrayList<Category> categories = new ArrayList<>();
    public static Category oftenQuestions = new Category("частые вопросы", null);
    public static ArrayList<String> messages = new ArrayList<>();

}
