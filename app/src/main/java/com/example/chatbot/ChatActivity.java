package com.example.chatbot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.Message;
import com.example.chatbot.R;
import com.example.chatbot.adapters.MessageListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvMessages;
    private EditText etSend;
    private ArrayList<Message> messageList  = new ArrayList<>();;
    private MessageListAdapter adapter;
    public final String NULL_MESSAGE = "";
    private static ArrayList<Message> selectedIds  = new ArrayList<>();;

    private ImageButton btSend;
    private ImageButton btAddImage;
    private ImageButton btChatSettings;
    private ImageButton btClose;


    private ValueEventListener chatDataListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
        Objects.requireNonNull(getSupportActionBar()).hide();

        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messages.add(new Message("ds" + i, false));
        }
        adapter.notifyDataSetChanged();
    }

    // инициализация
    void init(){
        etSend = (EditText)findViewById(R.id.et_send);
        rvMessages = findViewById(R.id.rv_messages);
        btSend = findViewById(R.id.bt_send);
        btClose = findViewById(R.id.bt_back);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageListAdapter(this, Database.messages, true, new MessageListAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(int messagePosition) {

            }
        });
        rvMessages.setAdapter(adapter);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSend.getText().toString().length() > 0) {
                    Database.messages.add(etSend.getText().toString());
                    adapter.notifyDataSetChanged();
                    etSend.setText(NULL_MESSAGE);
                }
            }
        });

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        Database.messages.clear();
        super.onDestroy();
    }

    private void scrollMessages(){
        rvMessages.scrollToPosition(messageList.size()-1);
    }

}