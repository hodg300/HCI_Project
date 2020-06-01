package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hci_project.Utils.Finals;

public class EditPlaceActivity extends AppCompatActivity {

    private EditText nameOfPlace;
    private EditText description;
    private ImageView menu;
    private Button submitBtn;
    private Place place;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_place);
        place = OwnerHomeActivity.place;
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setOnClickListeners();
        setInfo();

    }

    private void setInfo() {
        nameOfPlace.setText(place.getName());
        description.setText(place.getDescription());
    }

    private void setOnClickListeners() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                place.setName(nameOfPlace.getText().toString());
                place.setDescription(description.getText().toString());
                goToOwnerAboutPlaceActivity();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOwnerSideMenuActivity();
            }
        });


    }

    private void goToOwnerAboutPlaceActivity(){
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
    }

    private void goToOwnerSideMenuActivity() {
        Intent intent = new Intent(this, OwnerSideMenuActivity.class);
        intent.putExtra(Finals.USER, user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setIds() {

        nameOfPlace = findViewById(R.id.name_of_place_text);
        description = findViewById(R.id.description);
        submitBtn = findViewById(R.id.submit_btn);
        menu = findViewById(R.id.menu);
    }
}
