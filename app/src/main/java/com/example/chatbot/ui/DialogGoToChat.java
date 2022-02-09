package com.example.chatbot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.chatbot.ChatActivity;
import com.example.chatbot.R;

import java.util.ArrayList;

public class DialogGoToChat extends Dialog {

    private Button btGoToChat, btCancel;

    public DialogGoToChat(AppCompatActivity activity) {
        super(activity);
    }
    public DialogGoToChat(Fragment fragment) {
        super(fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_go_to_chat, container, false);
        init();
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destroy();
            }
        });
        btGoToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                getActivity().startActivity(intent);
                destroy();
            }
        });


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void init() {
        btCancel = rootView.findViewById(R.id.bt_cancel);
        btGoToChat = rootView.findViewById(R.id.bt_go_to_chat);
    }
}
