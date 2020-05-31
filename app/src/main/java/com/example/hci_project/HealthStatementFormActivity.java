package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.hci_project.Utils.Finals;

public class HealthStatementFormActivity extends Activity {

    CheckBox approveCheckBox;
    Invitation invitation;
    Button acceptBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_statement_form);
        invitation = (Invitation) getIntent().getSerializableExtra(Finals.INVITATION);
        setIds();
        setOnClickListeneres();
    }

    private void setOnClickListeneres() {

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(approveCheckBox.isChecked()){
                    VisitorMapActivity.invitations.add(invitation);
                    goToMapActivity();
                    popSuccessMessage();
                } else {
                    popErrorMessage();

                }
            }
        });

    }

    private void popSuccessMessage() {
        Toast.makeText(this, "Your invitation has been delivered, enjoy!", Toast.LENGTH_SHORT).show();

    }

    private void popErrorMessage() {

        Toast.makeText(this, "Helath Statement must be checked.", Toast.LENGTH_SHORT).show();

    }

    private void goToMapActivity() {

        Intent intent = new Intent(this,VisitorMapActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    private void setIds() {

        approveCheckBox = findViewById(R.id.approve_checkbox);
        acceptBtn = findViewById(R.id.accept_btn);
    }

}