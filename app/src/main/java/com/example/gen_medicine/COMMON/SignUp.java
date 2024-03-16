package com.example.gen_medicine.COMMON;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gen_medicine.USER.Home;
import com.example.gen_medicine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText name,pass,cpass,email,phone;
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
        phone=findViewById(R.id.phone);
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
                String phone1=phone.getText().toString();
                if(name1.equals(""))
                {
                    name.setError("Enter your name");
                    name.requestFocus();
                }
                else if(!email1.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
                {
                    email.setError("Enter Valid Email");
                    email.requestFocus();
                }
                else if(pass1.equals("") || pass1.length()<8)
                {
                    pass.setError("Above 8 characters");
                }
                else if(!pass1.equals(cpass1))
                {
                    cpass.setError("Password dont match");
                    cpass.requestFocus();
                }
                else if(phone1.equals(""))
                {
                    phone.setError("Enter Phone Number");
                    phone.requestFocus();
                }
                else
                {
                    pb.setTitle("Registration in process");
                    pb.setMessage("Please Wait..!");
                    pb.setCanceledOnTouchOutside(false);
                    pb.show();
                    auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                toNextActivity(email1,pass1,name1,phone1);
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
    public void toNextActivity(String email1,String pass1,String name1,String phone1)
    {
        Toast.makeText(SignUp.this,name1+" "+email1+" "+pass1,Toast.LENGTH_LONG).show();
        FirebaseDatabase.getInstance().getReference("user").child(phone1).child("Name").setValue(name1);
        FirebaseDatabase.getInstance().getReference("user").child(phone1).child("Pass").setValue(pass1);
        FirebaseDatabase.getInstance().getReference("user").child(phone1).child("Email").setValue(email1);
        pb.dismiss();
        Intent intent=new Intent(SignUp.this, Home.class);
        startActivity(intent);
    }
}