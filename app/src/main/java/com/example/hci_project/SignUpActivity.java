package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class SignUpActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private Spinner rolePicker;
    private Button createUser_btn;
    private ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setIds();
        setSpinner();
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        createUser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_bar.setVisibility(View.VISIBLE);
                delay(1);
            }
        });
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
                        goToDoneActivity();
                    }
                }, milliseconds);
            }
        });
    }

    private void goToDoneActivity() {

        Intent intent = new Intent(this,DoneActivity.class);
        startActivity(intent);
        finish();
    }



    private void setSpinner() {
        //create a list of items for the spinner.
        String[] items = new String[]{"Visitor","Place Owner","Police Officer", "Ministry of Health"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        rolePicker.setAdapter(adapter);
    }

    private void setIds() {
        firstName = findViewById(R.id.first_name_editView);
        rolePicker = findViewById(R.id.role_picker);
        lastName = findViewById(R.id.last_name_editView);
        createUser_btn = findViewById(R.id.create_user_btn);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.INVISIBLE);
    }


}