package com.example.chatbot.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.Question;
import com.example.chatbot.adapters.QuestionAdapter;
import com.example.chatbot.databinding.FragmentNotificationsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private ArrayList<Question> questions = new ArrayList<>();
    private RecyclerView rvQuestions;
    private QuestionAdapter adapter;
    private ValueEventListener oftenQuestionsListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        init();

        return root;
    }

    private void init() {
        rvQuestions = binding.rvOftenQuestions;
        rvQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QuestionAdapter(getContext(), questions, new QuestionAdapter.OnQuestionClickListener() {
            @Override
            public void onStateClick(Question question) {

            }
        });
        rvQuestions.setAdapter(adapter);
        oftenQuestionsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questions.clear();
                for (DataSnapshot s : snapshot.getChildren()){
                    Question q = s.getValue(Question.class);
                    assert q != null;
                    questions.add(q);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        Question.getOftenQuestions().addValueEventListener(oftenQuestionsListener);
    }

    @Override
    public void onDestroyView() {
        Question.getOftenQuestions().removeEventListener(oftenQuestionsListener);
        super.onDestroyView();
        binding = null;
    }
}