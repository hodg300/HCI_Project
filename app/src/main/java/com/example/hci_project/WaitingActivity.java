package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.hci_project.Utils.Finals;

public class WaitingActivity extends Activity {

        private ProgressBar progressBar;
        private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setId();
        delay(1);
    }

    private void setId() {
        progressBar = findViewById(R.id.progress_bar);
    }

    private void goToVisitorMapActivity() {

        Intent intent = new Intent(this, VisitorMapActivity.class);
        intent.putExtra(Finals.USER,user);
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
                        if(user.getRole().equals(Finals.VISITOR)) {
                            goToVisitorMapActivity();
                        } else if(user.getRole().equals(Finals.PLACE_OWNER)){

                        } else if(user.getRole().equals(Finals.POLICE_OFFICER)){

                        }else if(user.getRole().equals(Finals.MINISTRY_OF_HEALTH)){

                        }
                    }
                }, milliseconds);
            }
        });
    }
}