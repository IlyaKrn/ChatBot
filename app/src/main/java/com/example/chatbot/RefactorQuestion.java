package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chatbot.adapters.QuestionAdapter;
import com.example.chatbot.firebase.Question;

public class RefactorQuestion extends AppCompatActivity {

    private EditText etQuestion;
    private EditText etAnswer;
    private ImageButton btBack;
    private Button btRefactor;
    private Button btCancel;
    private Question question;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refactor_question);
        init();

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btRefactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *
                 *
                 *
                 * */
            }
        });

        etQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etQuestion.getText().toString().equals(question.question))
                    hideButtons();
                else {
                    showButtons();
                }
            }
        });
        etAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etAnswer.getText().toString().equals(question.answer))
                    hideButtons();
                else {
                    showButtons();
                }
            }
        });
    }

    private void init(){
        btBack = findViewById(R.id.bt_back);
        btRefactor = findViewById(R.id.bt_refactor);
        btCancel = findViewById(R.id.bt_cancel);
        etAnswer = findViewById(R.id.et_answer);
        etQuestion = findViewById(R.id.et_question);
        question = (Question) getIntent().getSerializableExtra(Question.INTENT_QUESTION);
        etQuestion.setText(question.question);
        etAnswer.setText(question.answer);
    }
    private void showButtons(){
        findViewById(R.id.buttons).setVisibility(View.VISIBLE);
    }
    private void hideButtons(){
        findViewById(R.id.buttons).setVisibility(View.GONE);
    }
}