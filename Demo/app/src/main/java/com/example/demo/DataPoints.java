package com.example.demo;

public class DataPoints {
 float time,strain;
 public DataPoints(float voltage,float strain)
 {
     this.time=voltage;
     this.strain=strain;
 }
 public DataPoints(){

 }

    public float getTime() {
     return time;
    }

    public float getStrain() {
     return strain;
    }
}
