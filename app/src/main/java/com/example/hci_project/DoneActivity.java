package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.hci_project.Utils.Finals;

public class DoneActivity extends Activity {
    private ImageView success;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        delay(2);


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
                        if(user.role.toLowerCase().equals(Finals.VISITOR)){
                            goToVisitorMapActivity();
                        }else if(user.role.toLowerCase().equals(Finals.POLICE_OFFICER)){
                            goToOfficerMapActivity();
                        }else if(user.role.toLowerCase().equals(Finals.PLACE_OWNER)){

                        }else if(user.role.toLowerCase().equals(Finals.MINISTRY_OF_HEALTH)){

                        }
                    }
                }, milliseconds);
            }
        });
    }

    private void goToOfficerMapActivity() {
    }

    private void setIds() {
        success = findViewById(R.id.success_imageView);
    }
}