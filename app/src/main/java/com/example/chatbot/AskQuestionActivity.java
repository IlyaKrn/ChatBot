package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatbot.adapters.QuestionAdapter;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;

import java.util.ArrayList;
import java.util.Objects;

public class AskQuestionActivity extends AppCompatActivity {

    private Category category;
    private ImageButton btBack;
    private Button btSearchAnswer;
    private EditText etQuestion;
    private RecyclerView rvAnswer;
    private TextView tvMaybe;
    private QuestionAdapter adapter;
    private ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        Objects.requireNonNull(getSupportActionBar()).hide();
        init();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSearchAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT).show();
                tvMaybe.setVisibility(View.VISIBLE);
                questions.add(new Question("111", "1111"));
                questions.add(new Question("222", "2222"));
                questions.add(new Question("333", "3333"));
                questions.add(new Question("444", "4444"));
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void init() {
        category = (Category) getIntent().getSerializableExtra(Category.INTENT_CATEGORY);
        btBack = findViewById(R.id.bt_back);
        btSearchAnswer = findViewById(R.id.bt_search_answer);
        etQuestion = findViewById(R.id.et_question);
        tvMaybe = findViewById(R.id.tv_maybe);
        rvAnswer = findViewById(R.id.rv_answer);
        rvAnswer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionAdapter(this, questions, new QuestionAdapter.OnQuestionClickListener() {
            @Override
            public void onStateClick(Question question) {
                Intent intent = new Intent(AskQuestionActivity.this, AnswerActivity.class);
                intent.putExtra(Question.INTENT_QUESTION, question);
                startActivity(intent);
            }
        });
        rvAnswer.setAdapter(adapter);
    }
}