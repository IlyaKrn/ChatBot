package com.example.chatbot.ui;

import android.os.Bundle;
import android.util.Log;
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

import com.example.chatbot.R;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DialogAddCategory extends Dialog {

    private Button addChat, cancel;
    private EditText etCategory;

    public DialogAddCategory(AppCompatActivity activity) {
        super(activity);
    }
    public DialogAddCategory(Fragment fragment) {
        super(fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_add_chat, container, false);
        addChat = rootView.findViewById(R.id.bt_create);
        cancel = rootView.findViewById(R.id.bt_cancel);
        etCategory = rootView.findViewById(R.id.et_category);

        addChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // получение введенных данных
                String cat = etCategory.getText().toString();
                ArrayList<Question> arr = new ArrayList<>();
                arr.add(new Question("ans", "quest"));
                Category category = new Category(cat, arr);
                // скрытие предупреждений о некорректных данных

                // если поля ввода не пустые
                if (category.name.length() > 0) {
                        FirebaseDatabase.getInstance().getReference(  "ghfgj").child(" jjjj").setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                destroy();
                            }
                        });

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destroy();
            }
        });
       return super.onCreateView(inflater, container, savedInstanceState);
    }
}
