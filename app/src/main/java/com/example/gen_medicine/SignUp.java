package com.example.gen_medicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    EditText name,pass,cpass,email;
    Button signup;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.password);
        cpass=findViewById(R.id.cpassword);
        email=findViewById(R.id.email);
        signup=findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        pb=new ProgressDialog(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=name.getText().toString();
                String email1=email.getText().toString();
                String pass1=pass.getText().toString();
                String cpass1=cpass.getText().toString();
                if(name1.equals(""))
                {
                    name.setError("Enter your name");
                    name.requestFocus();
                }
                if(email1.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
                {
                    email.setError("Enter Valid Email");
                    email.requestFocus();
                }
                if(pass1.equals("") || pass1.length()<8)
                {
                    pass.setError("Above 8 characters");
                }
                if(!pass1.equals(cpass1))
                {
                    cpass.setError("Password dont match");
                    cpass.requestFocus();
                }
                else
                {
                    pb.setTitle("Registration");
                    pb.setMessage("Please Wait..!");
                    pb.setCanceledOnTouchOutside(false);
                    pb.show();
                    auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                toNextActivity();
                            }
                            else
                            {
                                pb.dismiss();
                                Toast.makeText(SignUp.this,"Unsuccessfull",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public void toNextActivity()
    {
        pb.dismiss();
        Intent intent=new Intent(SignUp.this,Home.class);
        startActivity(intent);
    }
}