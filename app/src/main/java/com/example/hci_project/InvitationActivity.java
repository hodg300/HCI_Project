package com.example.hci_project;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class InvitationActivity extends Activity  implements DatePickerDialog.OnDateSetListener{

    private TextView header;
    private Button date;
    private TextView numOfGuests;
    private ImageView plusSign;
    private ImageView minusSign;
    private Button confirmBtn;
    private TimePicker timePicker;
    private User user;
    private int placeIndex;
    private RelativeLayout invitationWindow;
    private Invitation invitation;
    public static Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        placeIndex = getIntent().getIntExtra(Finals.PLACE_INDEX,0);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        activity = this;
        setIds();
        setInfo();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(invitationWindow);
    }

    private void setOnClickListeners() {

        minusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(numOfGuests.getText().toString()) > 0){
                    numOfGuests.setText( "" + (Integer.parseInt(numOfGuests.getText().toString()) - 1));
                }
            }
        });

        plusSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numOfGuests.setText( "" + (Integer.parseInt(numOfGuests.getText().toString()) + 1));
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createInvitation();
                goToHealthStatementFormActivity();


            }
        });
    }

    private void goToHealthStatementFormActivity() {

        Intent intent = new Intent(this,HealthStatementFormActivity.class);
        intent.putExtra(Finals.INVITATION,invitation);
        startActivity(intent);
    }

    private void createInvitation() {

         invitation = new Invitation(user.firstName + user.lastName,WaitingActivity.places.get(placeIndex).getName(),
                date.getText().toString(),
                timePicker.getHour() + ":" +  timePicker.getMinute(),numOfGuests.getText().toString(), WaitingActivity.places.get(placeIndex).getImage());
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();

    }

    private void setInfo() {

        header.setText(WaitingActivity.places.get(placeIndex).getName());
        date.setText(ZonedDateTime.now().getDayOfMonth() + " / "  +ZonedDateTime.now().getMonthValue() + " / " + ZonedDateTime.now().getYear());
    }

    private void setIds() {

        header = findViewById(R.id.invitation_title);
        date = findViewById(R.id.date);
        numOfGuests = findViewById(R.id.num_of_guests);
        plusSign = findViewById(R.id.plus_box);
        minusSign = findViewById(R.id.minus_box);
        confirmBtn = findViewById(R.id.approve_invitation_btn);
        timePicker = findViewById(R.id.time_picker);
        invitationWindow = findViewById(R.id.invitation_window);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        date.setText(dayOfMonth +" / " + month + " / " + year);
    }
}
