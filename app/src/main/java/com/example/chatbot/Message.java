package com.example.chatbot;

public class Message {
    public String message;
    public boolean isAdmin;

    public Message(String message, boolean isAdmin) {
        this.message = message;
        this.isAdmin = isAdmin;
    }

    public Message() {
    }
}
