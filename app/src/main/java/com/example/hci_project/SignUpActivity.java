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

import com.example.hci_project.Utils.Finals;

public class SignUpActivity extends Activity {


    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText email;
    private EditText confirmPassword;
    private Spinner rolePicker;
    private Button createUser_btn;
    private ProgressBar progress_bar;
    private  User user;
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
                //Set progress bar to be visible
                progress_bar.setVisibility(View.VISIBLE);
                //Fetch the password from the user
                if(checkInfoValidation()){

                    if(email.getText().toString().contains(Finals.VISITOR)){
                        user = new User(firstName.getText().toString(),lastName.getText().toString(),password.getText().toString(),email.getText().toString(),rolePicker.getSelectedItem().toString(),R.drawable.visitor);
                    }else if(email.getText().toString().contains(Finals.POLICE_OFFICER)){
                        user = new User("Officer","Azzulay",password.getText().toString(),email.getText().toString(), Finals.POLICE_OFFICER,R.drawable.officer);
                    } else if(email.getText().toString().contains(Finals.PLACE_OWNER)){
                        user = new User("Menny","Mamtera",password.getText().toString(),email.getText().toString(), Finals.PLACE_OWNER,R.drawable.owner);
                    }
                    else if(email.getText().toString().contains(Finals.MINISTRY_OF_HEALTH)){
                        user = new User("Moshe","Bar Siman Tov",password.getText().toString(),email.getText().toString(), Finals.MINISTRY_OF_HEALTH,R.drawable.health);
                    } else {
                        email.setError("email or password are incorrect");
                        return;
                    }
                    delay(1);
                }
            }
        });
    }

    private boolean checkInfoValidation() {

        if(firstName.getText().toString().isEmpty() ){
            firstName.setError("First name can not be empty");
            return false;
        } else if(lastName.getText().toString().isEmpty()){
            lastName.setError("Last name can not be empty");
            return false;
        }

        else if(email.getText().toString().isEmpty()){
            email.setError("Email name can not be empty");
            return false;
        }
        else if(password.getText().toString().isEmpty()){
            password.setError("Password name can not be empty");
            return false;
        }
        else if(confirmPassword.getText().toString().isEmpty()){
            confirmPassword.setError("Password name can not be empty");
            return false;
        }
        else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            confirmPassword.setError("Password does not match");
            return false;
        }

        return true;


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
        intent.putExtra(Finals.USER, user);
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
        email = findViewById(R.id.email_editView);
        password = findViewById(R.id.password_edit_text);
        confirmPassword = findViewById(R.id.repassword_edit_text);
        lastName = findViewById(R.id.last_name_editView);
        createUser_btn = findViewById(R.id.create_user_btn);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.INVISIBLE);
    }


}