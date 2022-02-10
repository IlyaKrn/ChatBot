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

import com.example.chatbot.Database;
import com.example.chatbot.R;
import com.example.chatbot.firebase.Category;
import com.example.chatbot.firebase.Question;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.xml.namespace.QName;

public class DialogAddQuestion extends Dialog {

    private Button addChat, cancel;
    private EditText etCategory;
    private Category category;

    public DialogAddQuestion(AppCompatActivity activity, Category category) {
        super(activity);
        this.category = category;
    }
    public DialogAddQuestion(Fragment fragment) {
        super(fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_add_question, container, false);
        addChat = rootView.findViewById(R.id.bt_create);
        cancel = rootView.findViewById(R.id.bt_cancel);
        etCategory = rootView.findViewById(R.id.et_category);

        addChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // получение введенных данных
                String cat = etCategory.getText().toString();
                ArrayList<Question> arr = new ArrayList<>();
                Question question = new Question(cat, null);
                // скрытие предупреждений о некорректных данных

                // если поля ввода не пустые
                if (category.name.length() > 0) {
                    for (int i = 0; i < Database.categories.size(); i++) {
                        if (Database.categories.get(i).name.equals(category.name)){
                            Database.categories.get(i).questions.add(question);
                        }
                    }
                }
                destroy();
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
