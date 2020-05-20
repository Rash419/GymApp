package com.example.workout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    private long startTime;
    private long mTimeLeft;
    private int i=0,j=0;
    private int sets;
    private CountDownTimer countDownTimer;

    private ArrayList<ExerciseList> mExercise;
    private ImageView exerciseImage;
    private TextView mDuration;
    private TextView mCountDownText;
   // private HandlerThread handlerThread = new HandlerThread("HandlerThreadName");
    //private Handler threadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        exerciseImage = findViewById(R.id.exerciseImageSA);
        mDuration = findViewById(R.id.durationTimeSA);
        mCountDownText = findViewById(R.id.countDownTimer);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("Bundle");
        mExercise = (ArrayList<ExerciseList>)args.getSerializable("ArrayList");
        sets = intent.getIntExtra("Sets",0);
        setLayout();
      //  handlerThread.start();
     //   threadHandler = new Handler(handlerThread.getLooper());
    }
    private void startTimer()
    {
        countDownTimer = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountText();
            }

            @Override
            public void onFinish() {
                i++;
                if (i <= 4) {

                    /*FragmentManager fm = getSupportFragmentManager();
                    RestDialogFragment dialogFragment = RestDialogFragment.newInstance("Rest Time: ");
                    dialogFragment.show(fm, "rest_alert");*/
                    setLayout();

                } else {
                    j++;
                    if (j <= sets - 1) {
                        i = 0;
                        Toast.makeText(getApplicationContext(), "Set-" + (j) + " completed", Toast.LENGTH_SHORT).show();
                        setLayout();
                    } else {
                        Toast.makeText(getApplicationContext(), "Exercises Completed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK)
        {
            countDownTimer.resume();
        }
    }

    private void updateCountText()
    {
        int minutes = (int) (mTimeLeft/1000)/60;
        int seconds = (int) (mTimeLeft/1000)%60;
        Log.d("Minutes & Seconds",String.valueOf(minutes)+" "+String.valueOf(seconds));
        if(minutes == 0 && seconds == 0) {
            countDownTimer.pause();
            Intent intent = new Intent(getApplicationContext(), RestActivity.class);
            startActivityForResult(intent,RESULT_OK);
        }
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mCountDownText.setText(timeLeftFormatted);

    }
    private  void setLayout()
    {
        startTime = Long.parseLong(mExercise.get(i).getmDuration());
        exerciseImage.setImageResource(mExercise.get(i).getmImageResource());
        mDuration.setText(mExercise.get(i).getmDuration());
        Log.d("Start Time",String.valueOf(startTime));
        mTimeLeft = startTime*60000;
        mTimeLeft = 10000;
        startTimer();
    }


}
