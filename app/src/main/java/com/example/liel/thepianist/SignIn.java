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
public class SignIn extends AppCompatActivity implements View.OnClickListener
{
    private Button btnin,forgot;
    private EditText mail,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setBackgroundDrawableResource(R.drawable.background2); //background
        mAuth = FirebaseAuth.getInstance();
        mail= (EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.password);
        btnin=(Button)findViewById(R.id.btnin);
        btnin.setOnClickListener(this);
        forgot=(Button)findViewById(R.id.forgot);
        forgot.setOnClickListener(this);
    }

    public void signIn()
    {
        mAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignIn.this, "successfully sign in", Toast.LENGTH_SHORT).show();
                    Intent moveHome= new Intent(SignIn.this,Home.class);
                    startActivity(moveHome);
                }
                else
                {
                    Toast.makeText(SignIn.this, "error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view)
    {
        if(view==btnin)
        {
            signIn();
        }
        if(view==forgot)
        {
            if(mail.getText()!= null)
            {
                sendToEmail();
            }
            else
            {
                Toast.makeText(SignIn.this, "enter your mail please", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void sendToEmail()
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = mail.getText().toString();
        auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful()) {
                    Toast.makeText(SignIn.this, "your password sent to your email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
