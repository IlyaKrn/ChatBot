package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatbot.firebase.Question;

import java.util.Objects;

public class AnswerActivity extends AppCompatActivity {

    private ImageButton btBack;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private Question question;
    private ImageButton btMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        init();
        Objects.requireNonNull(getSupportActionBar()).hide();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvAnswer.setText(question.answer);
        tvQuestion.setText(question.question);


        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(AnswerActivity.this, view);
                popup.inflate(R.menu.popup_menu_main);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.news:
                                Toast.makeText(getApplicationContext(), "news", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.i_is_admin:
                                Intent intent = new Intent(AnswerActivity.this, AdminLoginActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

    }

    private void init(){
        btMenu = findViewById(R.id.bt_menu);
        question = (Question) getIntent().getSerializableExtra(Question.INTENT_QUESTION);
        btBack = findViewById(R.id.bt_back);
        tvAnswer = findViewById(R.id.tv_answer);
        tvQuestion = findViewById(R.id.tv_question);
    }
}