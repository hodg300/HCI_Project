package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

public class ReportActivity extends AppCompatActivity {

    private TextView filledBy;
    private TextView reportDescription;
    private ImageView menu;
    private User user;
    private Report report;
    private RelativeLayout reportWindow;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        report = (Report) getIntent().getSerializableExtra(Finals.REPORT);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        activity = this;
        setIds();
        setInfo();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(reportWindow);
    }

    private void setOnClickListeners() {

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });
    }

    private void goToSideMenuActivity() {
        Intent intent = null;
        if (user.getRole().toLowerCase().equals(Finals.MINISTRY_OF_HEALTH.toLowerCase())) {
            intent = new Intent(this, MinistryOfHealthSideMenuActivity.class);
        } else if (user.getRole().toLowerCase().equals(Finals.POLICE_OFFICER.toLowerCase())) {
            intent = new Intent(this, PoliceSideMenuActivity.class);
        }
        intent.putExtra(Finals.USER, user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setInfo() {

        filledBy.setText("Filled by " + report.reporter);
        reportDescription.setText(report.getReasonOfReport());
    }

    private void setIds() {

        filledBy = findViewById(R.id.filled_by);
        reportDescription = findViewById(R.id.report_description);
        menu = findViewById(R.id.menu);
        reportWindow = findViewById(R.id.report_window);
    }
}
