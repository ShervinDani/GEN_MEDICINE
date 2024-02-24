package com.example.gen_medicine.COMMON;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gen_medicine.USER.Home;
import com.example.gen_medicine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button btn1;
    EditText email,password;
    TextView signup;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString();
                String password1=password.getText().toString();
                if(email1.equals(""))
                {
                    email.setError("Enter Name");
                    email.requestFocus();
                }
                else if(password1.equals(""))
                {
                    password.setError("Enter Password");
                    password.requestFocus();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(LoginActivity.this, Home.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Login Unsuccessfull",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent =new Intent(LoginActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}