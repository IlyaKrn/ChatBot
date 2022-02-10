package com.example.chatbot.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.AskQuestionActivity;
import com.example.chatbot.Database;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.adapters.CategoryAdapter;
import com.example.chatbot.databinding.FragmentDashboardBinding;
import com.example.chatbot.firebase.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private ArrayList<Category> categories = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        init();






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void init(){
        rvCategories = binding.rvCategories;
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CategoryAdapter(getContext(), categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onStateClick(Category category) {
                Intent intent = new Intent(getActivity(), AskQuestionActivity.class);
                intent.putExtra(Category.INTENT_CATEGORY, (Serializable) category);
                startActivity(intent);
            }
        });
        rvCategories.setAdapter(adapter);

        categories.clear();
        categories.addAll(Database.categories);
        adapter.notifyDataSetChanged();
    }


}