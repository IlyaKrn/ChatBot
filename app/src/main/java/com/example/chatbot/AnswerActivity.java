package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chatbot.firebase.Question;

public class AnswerActivity extends AppCompatActivity {

    private ImageButton btBack;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        init();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvAnswer.setText(question.answer);
        tvQuestion.setText(question.question);


    }

    private void init(){
        question = (Question) getIntent().getSerializableExtra(Question.INTENT_QUESTION);
        btBack = findViewById(R.id.bt_back);
        tvAnswer = findViewById(R.id.tv_answer);
        tvQuestion = findViewById(R.id.tv_question);
    }
}