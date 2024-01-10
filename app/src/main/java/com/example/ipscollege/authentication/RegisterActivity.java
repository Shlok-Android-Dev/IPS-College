package com.example.ipscollege.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipscollege.MainActivity;
import com.example.ipscollege.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName, regEmail, regPassword, mobileNo;
    private MaterialButton registerButton;
    private String name;
    private String email;
    private String pass;
    private String phoneNo;
    private FirebaseAuth auth;
    private DatabaseReference reference, dbRef;
    private TextView openLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        regName = findViewById(R.id.registerName);
        regEmail = findViewById(R.id.registerEmail);
        mobileNo = findViewById(R.id.registerMobile);
        regPassword = findViewById(R.id.registerPass);
        openLogin = findViewById(R.id.openLogin);
        registerButton = findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    private void openLogin() {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMain();
        }
    }


    private void openMain() {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void validateData() {

        name = regName.getText().toString();
        email = regEmail.getText().toString();
        pass = regPassword.getText().toString();
        phoneNo = mobileNo.getText().toString();


        if (name.isEmpty()){
            regName.setError("Required");
            regName.requestFocus();
        } else if (email.isEmpty()) {
            regEmail.setError("Required");
            regEmail.requestFocus();
        } else if (pass.isEmpty()) {
            regPassword.setError("Required");
            regPassword.requestFocus();
        } else if (phoneNo.isEmpty()) {
            regPassword.setError("Required");
            regPassword.requestFocus();
        }else {
            createUser();
        }

    }

    private void createUser() {

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            uploadData();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData() {

        dbRef = reference.child("users");
        String key = dbRef.push().getKey();

        HashMap<String, String> user = new HashMap<>();
        user.put("key",key);
        user.put("name",name);
        user.put("email",email);
        user.put("mobileNo",phoneNo);
        user.put("status", "no");

        dbRef.child(name+key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            openMain();
                        }else {

                            Toast.makeText(RegisterActivity.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(RegisterActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}