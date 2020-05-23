package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Spinner rolePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setIds();
        setSpinner();


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
