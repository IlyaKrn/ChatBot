package com.example.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private Button btEnter;
    private ImageButton btBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        init();
        Objects.requireNonNull(getSupportActionBar()).hide();

        etLogin.setText("admin@gmail.com");
        etPassword.setText("111111");

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etLogin.getText())){
                    if (!TextUtils.isEmpty(etPassword.getText())){
                        final String login = etLogin.getText().toString();
                        final String password = etPassword.getText().toString();
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(login, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(AdminLoginActivity.this, RefactorCategoriesActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init(){
        etLogin = findViewById(R.id.et_email_in);
        etPassword = findViewById(R.id.et_password_in);
        btEnter = findViewById(R.id.bt_sign_in_in);
        btBack = findViewById(R.id.bt_back);
    }
}