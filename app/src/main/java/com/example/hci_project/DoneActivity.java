package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class DoneActivity extends Activity {
    private ImageView success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        setIds();
        delay(2);

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

    private void setIds() {
        success = findViewById(R.id.success_imageView);
    }
}