package com.karishan_897.smd2_musify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {
    EditText email, password;
    Button signin;
    TextView signup;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);

        mAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(Signin.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else{

                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(Signin.this, MainScreen.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Signin.this, "Sign in failed", Toast.LENGTH_LONG).show();
                                }
                            });

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Signup.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Signin.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAuth.getCurrentUser() != null){
            Intent intent = new Intent(Signin.this, MainScreen.class);
            startActivity(intent);
        }
    }

}