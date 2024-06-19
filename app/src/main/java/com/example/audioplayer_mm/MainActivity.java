package com.example.audioplayer_mm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    //Declare Variables
    private Button b1,b2,b3,b4;
    private ImageView iv;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;

    //Variables used for tracking time
    private double startTime = 0;
    private double finalTime = 0;

    //Creates handler
    private Handler myHandler = new Handler();;

    //Variables with the value of milliseconds to move forward/back (10 secs)
    private int forwardTime = 10000;
    private int backwardTime = 10000;

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize our layout components
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        iv = (ImageView)findViewById(R.id.imageView);

        tx1 = (TextView)findViewById(R.id.textView2);
        tx2 = (TextView)findViewById(R.id.textView3);
        tx3 = (TextView)findViewById(R.id.textView4);

        //Song is set to the song.mp3 in raw folder
        tx3.setText("Thunderstruck - AC/DC");

        //Initialize media player
        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        //Setup seekbar
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        b2.setEnabled(false);

        //OnClick listener for play button
        b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                //Displays message when the song starts
                Toast.makeText(getApplicationContext(), "Playing Song",Toast.LENGTH_SHORT).show();
                        mediaPlayer.start();

                //Sets variables to the time for song duration and time left
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                //Sets the seekbar with the time duration of the song
                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                //Sets the textview with the song length
                tx2.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );
                //Sets the textview with the time remaining
                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                //Updates the seekbar to the time remaining
                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);

                //Updates play and pause button to know which one was clicked last
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
        });

        //OnClick listener for pause button
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Displays message when song is paused
                Toast.makeText(getApplicationContext(), "Pausing Song",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();

                //Updates play and pause button to know which one was clicked last
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
        });

        //OnClick listener for time forward button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                //Checks whether the song time can be forwarded and displays message
                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"Forward 10 Seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot Jump Forward 10 Seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //OnClick listener for time back button
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                //Checks whether the song time can be reversed and displays message
                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"Back 10 Seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot Jump Backward 10 Seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Runnable for updating the seekbar and time remaining
    private Runnable UpdateSongTime = new Runnable() {
        @SuppressLint("DefaultLocale")
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();

            //Update the textview with the time remaining
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );

            //Update the seekbar time remaining
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}