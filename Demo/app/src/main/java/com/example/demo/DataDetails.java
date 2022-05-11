package com.example.demo;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataDetails {
    float voltage, angle, strain;
    int time;
    public DataDetails() {

    }

    public DataDetails(float voltage, float angle, float strain) {
        this.time=time;
        this.voltage = voltage;
        this.angle = angle;
        this.strain = strain;
    }
    public String getTime() {
        //return Float.toString(time);
        String date = new SimpleDateFormat("dd/MM HH:mm:ss").format(new Date ((long)time*1000));
        return date;
    }

    public String getVoltage() {
        return Float.toString(voltage).substring(0,5);
    }

    public String getAngle() {
        return Float.toString(angle*(float)57.296).substring(0,6);
    }

    public String getStrain() {
        return Float.toString(strain).substring(0,6);
    }
}
