package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.hci_project.Utils.Finals;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class WaitingActivity extends Activity {

    private ProgressBar progressBar;
    private User user;
    public static ArrayList<Place> places = new ArrayList<>();
    public static ArrayList<Report> allReports = new ArrayList<>();
    public static ArrayList<Report> susuAndSonsReports = new ArrayList<>();
    public static ArrayList<Report> telAvivMusiumReports = new ArrayList<>();
    public static ArrayList<Report> segevExpressReports = new ArrayList<>();
    public static ArrayList<Report> ahoogHatsfoniReports = new ArrayList<>();
    public static ArrayList<Report> mosesReports = new ArrayList<>();
    public static ArrayList<Report> beerGardensReports = new ArrayList<>();
    public static ArrayList<Report> alHayamReports = new ArrayList<>();
    public static ArrayList<Report> naginasReports = new ArrayList<>();
    public static boolean isDarkModeOn = false;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        activity = this;
        setId();
        createLocations();
        createReports();
        delay(1);
    }



    private void setId() {
        progressBar = findViewById(R.id.progress_bar);
    }

    private void goToMapActivity() {

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
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
                        if(user.getRole().equals(Finals.VISITOR)) {
                            goToMapActivity();
                        } else if(user.getRole().equals(Finals.PLACE_OWNER)){
                            //need to change is future
                            goToOwnerHomeActivity();

                        } else if(user.getRole().equals(Finals.POLICE_OFFICER)){
                            //need to change is future
                            goToMapActivity();

                        }else if(user.getRole().equals(Finals.MINISTRY_OF_HEALTH)){
                            //need to change is future
                            goToSearchActivity();

                        }
                    }
                }, milliseconds);
            }
        });
    }

    private void goToSearchActivity() {

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
    }

    private void goToOwnerHomeActivity() {

        Intent intent = new Intent(this, OwnerHomeActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
    }

    private void createLocations() {

        //החוג הצפוני
        places.add(new Place(Finals.AHOOG_HATSFONI,R.drawable.ahug_hatsfoni_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. ",
                new LatLng(32.113611, 34.801954),126,false,200,
                "10:00 - 23:00","Students israeli bar", 0));
        //שגב אקספרס
        places.add(new Place(Finals.SEGEV_EXPRESS,R.drawable.segev_express_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ,new LatLng(32.1100635,34.843054),250,true,250,
                "18:00 - 04:00","Italian kitchen", 1));
        //סוסו אנד סאנס
        places.add(new Place(Finals.SUSU_AND_SONS,R.drawable.susu_and_sons_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.0368665,34.9649833),121,false,130,
                "16:00 - 03:00","Texas hamburger", 2));
        //מוזיאון תל אביב
        places.add(new Place(Finals.TEL_AVIV_MUISIUM,R.drawable.tel_aviv_museum,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ,new LatLng(32.0938103,34.8110533),500,true,500,
                "08:00 - 15:00","Legacy of Israel", 3));

        places.add(new Place(Finals.MOSES,R.drawable.moses,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.1093289,34.8427159),120,false,300,
                "12:00 - 20:00" ,"Israeli Hamburger",4));

        places.add(new Place(Finals.BEER_GARDEN,R.drawable.beer_garden_img,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.0488213,34.9039284),300,true,300,
                "16:00 - 3:00" ,"Israel Bar",5));

        places.add(new Place(Finals.AL_HAYAM,R.drawable.al_hayam,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.1797829,34.8024603),228,false,250,
                "16:00 - 3:00" ,"Sea Food Restaurant and Bar",6));

        places.add(new Place(Finals.NAGISA,R.drawable.nagisa,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                new LatLng(32.093697,34.8248839),100,true,100,
                "16:00 - 3:00" ,"Japanese Kitchen",7));
    }

    private void createReports() {

        susuAndSonsReports.add(new Report(
                "Officer Azzulay",
                "20/2/20",
                "20/2/20/ \nPlace is crowded with more tham the maximum",
                Finals.SUSU_AND_SONS,
                R.drawable.susu_and_sons_img)
        );

        segevExpressReports.add(new Report(
                "Officer Shimon",
                "25/4/20",
                "25/4/20/ \nSecond strike of this place. shut down immediately",
                Finals.SEGEV_EXPRESS,
                R.drawable.segev_express_img)
        );

        ahoogHatsfoniReports.add(new Report(
                "Officer Yoni",
                "4/6/20",
                "4/6/20/ \nNot good.",
                Finals.AHOOG_HATSFONI,
                R.drawable.ahug_hatsfoni_img)
        );

        allReports.addAll(segevExpressReports);
        allReports.addAll(susuAndSonsReports);
        allReports.addAll(ahoogHatsfoniReports);



    }
}