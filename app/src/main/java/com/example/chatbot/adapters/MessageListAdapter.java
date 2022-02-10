package com.example.chatbot.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chatbot.Message;
import com.example.chatbot.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageHolder> {

    private final ArrayList<String> messages;
    private final Context context;
    private final boolean isUser;

    public MessageListAdapter(Context context, ArrayList<String> messages, boolean isUser, OnStateClickListener onClickListener) {
        this.context = context;
        this.messages = messages;
        this.isUser = isUser;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_message_list, parent, false);

        MessageHolder holder = new MessageHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(position);

    }
    @Override
    public int getItemCount() {
        return messages.size();
    }

    public interface OnStateClickListener{
        void onStateClick(int messagePosition);
    }

    class MessageHolder extends RecyclerView.ViewHolder{

        TextView notMy_tvMessage;
        View notMy_itemBody;

        View my_itemBody;
        TextView my_tvMessage;

        Message m;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            notMy_tvMessage = this.itemView.findViewById(R.id.not_my_tv_message);
            notMy_itemBody = this.itemView.findViewById(R.id.not_my_item_body);

            my_itemBody = this.itemView.findViewById(R.id.my_item_body);
        }

        public void bind(int listIndex){
            notMy_tvMessage.setText(messages.get(listIndex));
        }
        private void showNotMyMessage(){
            notMy_itemBody.setVisibility(View.VISIBLE);
            my_itemBody.setVisibility(View.GONE);
        }
        private void showMyMessage(){
            notMy_itemBody.setVisibility(View.GONE);
            my_itemBody.setVisibility(View.VISIBLE);
        }
        private void showSystemMessage() {
            notMy_itemBody.setVisibility(View.GONE);
            my_itemBody.setVisibility(View.GONE);
        }

    }
}
