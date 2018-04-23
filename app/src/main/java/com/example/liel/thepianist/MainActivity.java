package com.example.liel.thepianist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
    Button btnplay;
    SeekBar music , volume;
    TextView time , nameOfSong;
    int totaltime , place;
    private FirebaseAuth mAuth;
    int[] songs = {R.raw.shostakovich_waltz2,R.raw.chopin_nocturne9,
            R.raw.comptine_dun_autre_ete,R.raw.chopin_nocturne_in_c_sharp_minor,
            R.raw.chopin_fantaisie,R.raw.rachmaninov_prelude,
            R.raw.chopin_prelude28,R.raw.bach_solfeggietto,
            R.raw.schumann_first_loss,R.raw.mozart_turkish_march,
            R.raw.beethoven_rondo51,R.raw.schumann_the_happy_farmer,
            R.raw.mozart_la_tartine_de_beurre,R.raw.fur_elise,
            R.raw.mozart_la_tartine_de_beurre,R.raw.mozart_arietta,
            R.raw.beethoven_sonatina,R.raw.clementi_sonatina36};
    String[] songsNames = {"Shostakovich - Waltz No. 2","Chopin - Nocturne op.9 No.2",
            " Comptine d`un autre ete ", "Chopin - Nocturne in C-sharp Minor ",
            "Chopin - Fantaisie", "Rachmaninov - Prelude in C Sharp Minor",
            "Chopin - Prelude in E-Minor (op.28 no. 4)","Bach - Solfeggietto",
            "Schumann - first loss no.16 op.68","Mozart - Turkish March",
            "Beethoven - Rondo in C op. 51 no. 1","Schumann - the happy farmer",
            "Mozart - The Piano Sonata No 16 in C major", "beethoven - fur elise",
            "Mozart - La Tartine de Beurre", "Mozart - Arietta",
            "Betthoven - Sonatina", "Clementi - Sonatina Op.36 No.1 in C Major"};
    MediaPlayer song;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnplay = (Button)findViewById(R.id.btnplay);
        music = (SeekBar)findViewById(R.id.music);
        volume = (SeekBar)findViewById(R.id.volume);
        time = (TextView)findViewById(R.id.time);
        nameOfSong = (TextView)findViewById(R.id.nameOfSong);
        btnplay.setOnClickListener(this);
        playingSong();
    }
    //music
    private void playingSong()
    {
        place = (int)(Math.random()*(songs.length-0+1))+0;
        nameOfSong.setText(songsNames[place]);
        song = MediaPlayer.create(this,songs[place]);
        song.seekTo(0);
        song.setVolume(0.5f,0.5f);
        totaltime = song.getDuration();
        volume.setOnSeekBarChangeListener(this);
        music.setMax(totaltime);
        setMusic();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                while (song != null)
                {
                    try
                    {
                        Message msg = new Message();
                        msg.what = song.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {}
                }
            }
        }).start();
    }
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            int currentPosition = msg.what;
            music.setProgress(currentPosition);
            String ending = createTimeLable(currentPosition);
            String remain = createTimeLable(totaltime - currentPosition);
            time.setText(ending + " - " + remain);
        }
    };

    public String createTimeLable(int time)
    {
        String timeLable = " ";
        int min = time /1000 / 60;
        int sec = time /1000 % 60;
        timeLable = min + ":";
        if(sec<10)
        {
            timeLable += "0";
        }
        timeLable += sec;
        return timeLable;
    }

    private void setMusic() {
        music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                {
                    song.seekTo(progress);
                    music.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void onClick(View v)
    {
        if(v==btnplay)
        {
            if(!song.isPlaying())
            {
                song.start();
                btnplay.setBackgroundResource(R.drawable.pause);
            }
            else
            {
                song.pause();
                btnplay.setBackgroundResource(R.drawable.play);
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        float volumeNum = progress / 100f;
        song.setVolume(volumeNum,volumeNum);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.signIn)
        {
            Intent moveSignIn= new Intent(MainActivity.this,SignIn.class);
            startActivity(moveSignIn);
            return true;
        }
        if(id == R.id.signUp)
        {
            Intent moveSignUp = new Intent(MainActivity.this, SignUp.class);
            startActivity(moveSignUp);
            return true;
        }
        if(id == R.id.exit)
        {
            mAuth.getInstance().signOut();
            return true;
        }
        return true;
    }
}
