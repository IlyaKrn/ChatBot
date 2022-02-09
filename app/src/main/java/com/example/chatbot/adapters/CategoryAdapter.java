package com.example.chatbot.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.Category;
import com.example.chatbot.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private OnCategoryClickListener onClickListener;
    private final Context context;
    private ArrayList<Category> categories;


    public CategoryAdapter(Context context, ArrayList<Category> categories, OnCategoryClickListener onClickListener) {
        this.context = context;
        this.categories = categories;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_category_list, parent, false);

        CategoryHolder holder = new CategoryHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(position);

        Category category = categories.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onStateClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface OnCategoryClickListener {
        void onStateClick(Category category);
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        TextView tvCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }

        void bind(int listIndex){
            tvCategory.setText(categories.get(listIndex).name);
        }
    }
}

