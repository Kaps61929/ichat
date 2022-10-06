package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatapplication.Model.User;
import com.example.chatapplication.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {
ActivitySignupBinding binding;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(signupActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we're creating your account");
binding.Signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(binding.Email.getText().toString(),binding.Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    User user=new User(binding.Username.getText().toString(),binding.Password.getText().toString(),binding.Email.getText().toString());
                    String id=task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(user);
                    Toast.makeText(signupActivity.this, "User created succesfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
});

        binding.ClickSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signupActivity.this,signInActivity.class);
                startActivity(intent);
            }
        });

    }
}