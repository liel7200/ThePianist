package com.example.liel.thepianist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private Button sheets,keyboard,together,forKids,justPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sheets=(Button)findViewById(R.id.sheets);
        keyboard=(Button)findViewById(R.id.keyboard);
        together=(Button)findViewById(R.id.together);
        forKids=(Button)findViewById(R.id.forKids);
        justPlay=(Button)findViewById(R.id.justPlay);
        sheets.setOnClickListener(this);
        keyboard.setOnClickListener(this);
        together.setOnClickListener(this);
        forKids.setOnClickListener(this);
        justPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.sheets:
                Intent moveSheets= new Intent(Home.this,Sheets.class);
                startActivity(moveSheets);
                break;
            case R.id.keyboard:
                Intent moveKeyboard= new Intent(Home.this,WithPictures.class);
                startActivity(moveKeyboard);
                break;
            case R.id.together:
                Intent moveTogether= new Intent(Home.this,Together.class);
                startActivity(moveTogether);
                break;
            case R.id.forKids:
                Intent moveLearnSong= new Intent(Home.this,ForKids.class);
                startActivity(moveLearnSong);
                break;
            case R.id.justPlay:
                Intent moveJustPlay= new Intent(Home.this,JustPlay.class);
                startActivity(moveJustPlay);
                break;
        }
    }
}
