package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;

import org.w3c.dom.Text;

import java.security.acl.Owner;

public class LoginActivity extends Activity {

    private static int count = 0;
    private boolean abort;
    private Button loginButton;
    private TextView signUpTextView;
    private TextView email;
    private TextView password;
    private int[] backgrounds = {R.drawable.background1,R.drawable.background2,R.drawable.background3};
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = this;
        setIds();
        setOnClickListeners();


    }

    private void setOnClickListeners() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().isEmpty()){
                    email.setError("Email can not be empty");
                    return;
                } else if(password.getText().toString().isEmpty()){
                    password.setError("Email can not be empty");
                    return;
                }
                goToWaitingActivity();
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpActivity();
            }
        });

    }


    private void goToWaitingActivity() {

        Intent intent = new Intent(this,WaitingActivity.class);
        User user = null;

        if(email.getText().toString().contains("visitor")){
            user = new User("Hod","Gohasi",password.getText().toString(),email.getText().toString(), Finals.VISITOR,R.drawable.visitor);
        }else if(email.getText().toString().contains("police")){
            user = new User("Officer","Azzulay",password.getText().toString(),email.getText().toString(), Finals.POLICE_OFFICER,R.drawable.officer);
        } else if(email.getText().toString().contains("owner")){
            user = new User("Menny","Mamtera",password.getText().toString(),email.getText().toString(), Finals.PLACE_OWNER,R.drawable.owner);
        }
        else if(email.getText().toString().contains("health")){
            user = new User("Moshe","Bar Siman Tov",password.getText().toString(),email.getText().toString(), Finals.MINISTRY_OF_HEALTH,R.drawable.health);
        } else {
            email.setError("email or password are incorrect");
            return;
        }

        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
    }

    private void goToSignUpActivity() {

        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        abort = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        abort = true;

    }


    private void setIds() {

        email = findViewById(R.id.email_edit_text);
        password = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_btn);
        signUpTextView = findViewById(R.id.signUp_textView);
    }
}
