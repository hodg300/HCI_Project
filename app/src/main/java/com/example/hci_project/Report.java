package com.example.hci_project;

import java.io.Serializable;

public class Report implements Serializable {

    String reporter;
    String date;
    String reasonOfReport;
    String nameOfPlace;
    int image;

    public Report(String reporter, String date, String reasonOfReport,String nameOfPlace, int image) {
        this.reporter = reporter;
        this.date = date;
        this.reasonOfReport = reasonOfReport;
        this.nameOfPlace = nameOfPlace;
        this.image = image;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReasonOfReport() {
        return reasonOfReport;
    }

    public void setReasonOfReport(String reasonOfReport) {
        this.reasonOfReport = reasonOfReport;
    }

    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public void setNameOfPlace(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
