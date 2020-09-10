package com.example.learderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        Objects.requireNonNull(getSupportActionBar()).hide();
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        Thread splashScreenStarter = new Thread() {
            public void run() {
                int i = 0;
                try {
                    int waitingTime = 0;
                    while (waitingTime < 1500) {
                        sleep(150);
                        waitingTime = waitingTime + 100;

                    }
                    Intent intent = new Intent(MainActivity.this, LeaderBoardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    finish();

                }
            }

        };
        splashScreenStarter.start();
    }
}