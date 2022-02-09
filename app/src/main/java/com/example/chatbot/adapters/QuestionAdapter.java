package com.example.chatbot.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.Question;
import com.example.chatbot.R;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private OnQuestionClickListener onClickListener;
    private final Context context;
    private ArrayList<Question> questions = new ArrayList<>();


    public QuestionAdapter(Context context, ArrayList<Question> questions, OnQuestionClickListener onClickListener) {
        this.context = context;
        this.questions = questions;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_question_list, parent, false);

        QuestionHolder holder = new QuestionHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(position);

        Question question = questions.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onStateClick(question);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public interface OnQuestionClickListener{
        void onStateClick(Question question);
    }

    class QuestionHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_question);
        }

        void bind(int listIndex){
            tvQuestion.setText(questions.get(listIndex).question);
        }
    }
}

