package com.example.hci_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

public class FillReportActivity extends Activity {

    private Place place;
    private Button report;
    private ImageView menu;
    private RelativeLayout fillReportWindow;
    private User user;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_report);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        place = WaitingActivity.places.get(getIntent().getIntExtra(Finals.PLACE,0));
        activity = this;
        setIds();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(fillReportWindow);
    }

    private void setOnClickListeners() {

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLastActivity();
            }
        });
    }

    private void goToLastActivity(){
        onBackPressed();

        finish();
    }


    private void goToSideMenuActivity(){
        Intent intent = new Intent(this, PoliceSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        intent.putExtra(Finals.USER,user);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }



    private void setIds() {

        report = findViewById(R.id.report_btn);
        menu = findViewById(R.id.menu);
        fillReportWindow = findViewById(R.id.fill_report_window);
    }
}