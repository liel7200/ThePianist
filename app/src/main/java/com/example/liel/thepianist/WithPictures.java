package com.example.liel.thepianist;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WithPictures extends AppCompatActivity  implements View.OnTouchListener
{
    ImageView click;
    Button[] buttons = new Button[12];
    SoundPool[] sp = new SoundPool[12];
    int[] pictures = {R.drawable.do_1,  R.drawable.remol,
            R.drawable.re, R.drawable.mimol,
            R.drawable.mi, R.drawable.fa,
            R.drawable.solmol, R.drawable.sol,
            R.drawable.lamol, R.drawable.la,
            R.drawable.simol, R.drawable.si };
    String[] name = new String[]{"DO","REb or DO# ","RE","MIb  or RE#","MI","FA","SOLb or FA#","SOL","LAb or SOL#","LA","SIb or LA#","SI"};
    int[] sounds = new int[12];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_pictures);
        click = (ImageView)findViewById(R.id.click);
        creatSoundPool();
        loadSounds();
        matchToId();
        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i].setOnTouchListener(this);
        }
    }

    private void loadSounds()
    {
        sounds[0] = sp[0].load(this, R.raw.sdo1, 1);
        sounds[1] = sp[1].load(this, R.raw.sremol, 1);
        sounds[2] = sp[2].load(this, R.raw.sre, 1);
        sounds[3] = sp[3].load(this, R.raw.smimol, 1);
        sounds[4] = sp[4].load(this, R.raw.smi, 1);
        sounds[5] = sp[5].load(this, R.raw.sfa, 1);
        sounds[6] = sp[6].load(this, R.raw.ssolmol, 1);
        sounds[7] = sp[7].load(this, R.raw.ssol, 1);
        sounds[8] = sp[8].load(this,R.raw.slamol,1);
        sounds[9] = sp[9].load(this, R.raw.sla, 1);
        sounds[10] = sp[10].load(this, R.raw.ssimol, 1);
        sounds[11] = sp[11].load(this, R.raw.ssi, 1);
    }

    public void matchToId()
    {
        buttons[0] = (Button) findViewById(R.id.do1);
        buttons[1] = (Button) findViewById(R.id.remol);
        buttons[2] = (Button) findViewById(R.id.re);
        buttons[3] = (Button) findViewById(R.id.mimol);
        buttons[4] = (Button) findViewById(R.id.mi);
        buttons[5] = (Button) findViewById(R.id.fa);
        buttons[6] = (Button) findViewById(R.id.solmol);
        buttons[7] = (Button) findViewById(R.id.sol);
        buttons[8] = (Button) findViewById(R.id.lamol);
        buttons[9] = (Button) findViewById(R.id.la);
        buttons[10] = (Button) findViewById(R.id.simol);
        buttons[11] = (Button) findViewById(R.id.si);
    }

    public void creatSoundPool()
    {
        //phase 1 - check which sdk the user has
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            AudioAttributes aa = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
            for(int i=0;i<sp.length;i++)
            {
                sp[i] = new SoundPool.Builder().setMaxStreams(10).setAudioAttributes(aa).build();
            }
        }
        else
        {
            for(int i=0;i<sp.length;i++)
            {
                sp[i] = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        float f = (float) 0.8;
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            for(int i=0;i<buttons.length;i++)
            {
                if(v == buttons[i])
                {
                    sp[i].play(sounds[i], 1, 1, 0, 0, 1);
                    click.setBackgroundResource(pictures[i]);
                }
            }
            v.setAlpha(1);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            v.setAlpha(f);
        }
        return true;
    }
}