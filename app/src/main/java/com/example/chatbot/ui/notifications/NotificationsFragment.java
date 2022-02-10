package com.example.chatbot.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.AnswerActivity;
import com.example.chatbot.AskQuestionActivity;
import com.example.chatbot.Database;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;
import com.example.chatbot.adapters.QuestionAdapter;
import com.example.chatbot.databinding.FragmentNotificationsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ArrayList<Question> questions = new ArrayList<>();
    private RecyclerView rvQuestions;
    private QuestionAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                Intent intent = new Intent(getActivity(), AnswerActivity.class);
                intent.putExtra(Question.INTENT_QUESTION, (Serializable) question);
                startActivity(intent);
            }
        });
        rvQuestions.setAdapter(adapter);

        questions.clear();
        questions.addAll(Database.oftenQuestions.questions);
        adapter.notifyDataSetChanged();

    }


}