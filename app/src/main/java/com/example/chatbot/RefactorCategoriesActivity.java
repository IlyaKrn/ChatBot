package com.example.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.chatbot.adapters.CategoryAdapter;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.ui.DialogAddCategory;
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
    private ValueEventListener categoryListener;


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
            }
        });
    }

    private void init(){
        btBack = findViewById(R.id.bt_back);
        fab = findViewById(R.id.fab_add_category);
        rvCategories = findViewById(R.id.rv_categories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this, categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onStateClick(Category category) {

            }
        });
        rvCategories.setAdapter(adapter);

        categoryListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categories.clear();
                for (DataSnapshot s : snapshot.getChildren()){
                    Category c = s.getValue(Category.class);
                    assert c != null;
                    categories.add(c);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        Category.getDatabase().addValueEventListener(categoryListener);
    }

    @Override
    public void onDestroy() {
        Category.getDatabase().removeEventListener(categoryListener);
        super.onDestroy();
    }
}