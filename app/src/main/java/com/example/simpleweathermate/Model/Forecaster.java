package com.example.simpleweathermate.Model;

public class Forecaster {

    private String dateAndDay;
    private String lowTemp;
    private String highTemp;

    private void Forecaster(){

    }
    public Forecaster(String dateAndDay, String lowTemp, String highTemp) {
        this.dateAndDay = dateAndDay;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

    public String getDateAndDay() {
        return dateAndDay;
    }

    public void setDateAndDay(String dateAndDay) {
        this.dateAndDay = dateAndDay;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

}
