package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class WaitingActivity extends Activity {

        private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        setId();
        delay(3);
    }

    private void setId() {
        progressBar = findViewById(R.id.progress_bar);
    }

    private void goToMapActivity() {

        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
        finish();
    }

    public void delay(int seconds){
        final int milliseconds = seconds * 1000;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToMapActivity();
                    }
                }, milliseconds);
            }
        });
    }
}