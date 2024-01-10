package com.example.ipscollege.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ipscollege.MainActivity;
import com.example.ipscollege.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


//    private VideoView videoView;
    private EditText loginEmail, loginPassword;
    private MaterialButton loginButton;
    private String email, password;
    TextView openRegister, openForgetPassword;
    private FirebaseAuth auth;
    private ProgressDialog pgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        videoView = findViewById(R.id.videoView);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPass);
        openRegister = findViewById(R.id.openRegister);
        loginButton = findViewById(R.id.loginButton);
        openForgetPassword = findViewById(R.id.openForgetPassword);
        pgbar = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        //video background
//        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.background);
//        videoview.setVideoURI(uri);
//        videoview.start();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        openForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));

            }
        });

    }


    private void validateData() {
        email = loginEmail.getText().toString();
        password = loginPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please provide all fields.", Toast.LENGTH_SHORT).show();
        }else {
            loginUser();
        }

    }

    private void loginUser() {
        pgbar.setTitle("Please wait");
        pgbar.setMessage(" Login...");
        pgbar.show();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            pgbar.dismiss();
                            openMain();
                        }else {
                            pgbar.dismiss();
                            Toast.makeText(LoginActivity.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pgbar.dismiss();
                        Toast.makeText(LoginActivity.this, "error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openMain() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
        
    }

    private void openRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }


}