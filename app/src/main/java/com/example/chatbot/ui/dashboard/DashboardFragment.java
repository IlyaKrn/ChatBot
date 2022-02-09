package com.example.chatbot.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.Category;
import com.example.chatbot.adapters.CategoryAdapter;
import com.example.chatbot.databinding.FragmentDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private ArrayList<Category> categories = new ArrayList<>();

    private ValueEventListener categoryListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
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
            public void onStateClick(Category chatId) {

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