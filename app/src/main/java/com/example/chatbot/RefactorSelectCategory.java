package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.chatbot.adapters.QuestionAdapter;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RefactorSelectCategory extends AppCompatActivity {

    private Category category;
    private FloatingActionButton btAddQuestion;
    private RecyclerView rvQuestions;
    private QuestionAdapter adapter;
    private ImageButton btBack;
    private ArrayList<Question> questions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refactor_select_category);
        init();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *
                 *
                 * */
            }
        });
    }



    private void init(){
        btAddQuestion = findViewById(R.id.fab_add_question);
        btBack = findViewById(R.id.bt_back);
        rvQuestions = findViewById(R.id.rv_questions);
        category = (Category) getIntent().getSerializableExtra(Category.INTENT_CATEGORY);
        questions.addAll(category.questions);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionAdapter(this, questions, new QuestionAdapter.OnQuestionClickListener() {
            @Override
            public void onStateClick(Question question) {
                Intent intent = new Intent(RefactorSelectCategory.this, RefactorQuestion.class);
                intent.putExtra(Question.INTENT_QUESTION, question);
                startActivity(intent);
            }
        });
        rvQuestions.setAdapter(adapter);
    }
}