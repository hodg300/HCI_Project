package com.example.hci_project;

import java.io.Serializable;

public class Invitation implements Serializable {

    private String nameOfPlace;
    private String Date;
    private String hour;
    private String numOfGuests;
    private int image;

    public Invitation(String nameOfPlace, String date, String hour, String numOfGuests, int image) {
        this.nameOfPlace = nameOfPlace;
        Date = date;
        this.hour = hour;
        this.numOfGuests = numOfGuests;
        this.image = image;
    }

    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public void setNameOfPlace(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(String numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
