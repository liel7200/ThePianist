package com.example.liel.thepianist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button btnup;
    private EditText mail,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setBackgroundDrawableResource(R.drawable.background2); //background
        mAuth = FirebaseAuth.getInstance();
        mail= (EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.password);
        btnup = (Button)findViewById(R.id.btnup);
        btnup.setOnClickListener(this);
    }

    public void signUp()
    {
        mAuth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "success", Toast.LENGTH_SHORT).show();
                    Intent moveHome2= new Intent(SignUp.this,Home.class);
                    startActivity(moveHome2);
                }
                else
                {
                    Toast.makeText(SignUp.this, "error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        if(v == btnup)
        {
            signUp();
        }
    }
}