package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

public class RestActivity extends AppCompatActivity {

    private long timeLeft;
    private TextView mCountText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        Intent intent = new Intent();
        mCountText = findViewById(R.id.timerText);
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                setResult(RESULT_OK);
                finish();
            }
        }.start();
    }

    private void updateText()
    {
        int seconds = (int)timeLeft/1000;
        String time = String.format(Locale.getDefault(),"%02d"+"s",seconds);
        mCountText.setText(time);
    }
}
