package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LoginActivity extends Activity {

    private static int count = 0;
    private boolean abort;
    private Button loginButton;
    private Spinner rolePicker;
    private int[] backgrounds = {R.drawable.background1,R.drawable.background2,R.drawable.background3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeBackgroundImage();
        setIds();
        setSpinner();


    }

    @Override
    protected void onResume() {
        super.onResume();
        abort = false;
        this.changeBackground();
    }

    @Override
    protected void onPause() {
        super.onPause();
        abort = true;

    }


    private void changeBackground(){

        new Handler().postDelayed(new Runnable() {
            public void run() {

                if (abort)
                    return;

                if(count< backgrounds.length - 1){
                    count++;
                }
                else{
                    count = 0;
                }
                changeBackgroundImage();
                changeBackground();

            }
        }, 3000);
    }

    private void changeBackgroundImage(){
        if (abort)
            return;

        Animation fadeIn = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_out);
        LoginActivity.this.getWindow().
                setBackgroundDrawableResource(backgrounds[count]);
    }

    private void setSpinner() {
    //create a list of items for the spinner.
        String[] items = new String[]{"Visiter","Place Owner","Police Officer", "Ministry of Health"};
    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
    //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
    //set the spinners adapter to the previously created one.
        rolePicker.setAdapter(adapter);
    }
    private void setIds() {

        loginButton = findViewById(R.id.login_btn);
        rolePicker = findViewById(R.id.role_picker);
    }
}
