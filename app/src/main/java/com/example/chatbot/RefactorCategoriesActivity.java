package com.example.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.chatbot.adapters.CategoryAdapter;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.ui.DialogAddCategory;
import com.example.chatbot.ui.OnDestroyListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RefactorCategoriesActivity extends AppCompatActivity {

    private ImageButton btBack;
    private FloatingActionButton fab;
    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private ArrayList<Category> categories = new ArrayList<>();

    private Button btOften;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refactor_categories);
        init();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddCategory dialog = new DialogAddCategory(RefactorCategoriesActivity.this);
                dialog.create(R.id.fragmentContainerView);
                dialog.setOnDestroyListener(new OnDestroyListener() {
                    @Override
                    public void onDestroy() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        btOften.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RefactorCategoriesActivity.this, RefactorSelectCategory.class);
                intent.putExtra(Category.INTENT_CATEGORY, new Category("often_questions", Database.oftenQuestions));
                startActivity(intent);
            }
        });
    }

    private void init(){
        btOften = findViewById(R.id.bt_often);
        btBack = findViewById(R.id.bt_back);
        fab = findViewById(R.id.fab_add_category);
        rvCategories = findViewById(R.id.rv_categories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this, Database.categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onStateClick(Category category) {
                Intent intent = new Intent(RefactorCategoriesActivity.this, RefactorSelectCategory.class);
                intent.putExtra(Category.INTENT_CATEGORY, category);
                startActivity(intent);
            }
        });
        rvCategories.setAdapter(adapter);
    }

}