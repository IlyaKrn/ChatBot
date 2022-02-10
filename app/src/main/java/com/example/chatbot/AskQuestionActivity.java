package com.example.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chatbot.adapters.QuestionAdapter;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;
import com.example.chatbot.ui.DialogGoToChat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
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
    private ArrayList<Question> searchQuestions = new ArrayList<>();

    private ValueEventListener questionsListener;

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
                tvMaybe.setVisibility(View.VISIBLE);
                searchQuestions.clear();
                searchQuestions.addAll(category.questions);

                for (Question q : searchQuestions){
                    Log.e(q.answer, q.question);
                }

                adapter.notifyDataSetChanged();
                /*
                DialogGoToChat dialogGoToChat = new DialogGoToChat(AskQuestionActivity.this);
                dialogGoToChat.create(R.id.fragmentContainerView);

                 */
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
        adapter = new QuestionAdapter(this, searchQuestions, new QuestionAdapter.OnQuestionClickListener() {
            @Override
            public void onStateClick(Question question) {
                Intent intent = new Intent(AskQuestionActivity.this, AnswerActivity.class);
                intent.putExtra(Question.INTENT_QUESTION, (Serializable) question);
                Log.e("fgdf", question.answer + " " + question.question);
                startActivity(intent);
            }
        });
        rvAnswer.setAdapter(adapter);
    }
}