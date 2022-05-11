package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LineChart linechart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    LineDataSet linedataset=new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSets=new ArrayList<>();
    LineData linedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linechart=findViewById(R.id.chart1);
        firebaseDatabase=firebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference("data");
        RetrieveData();
    }

    private void RetrieveData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> dataVal =new ArrayList<Entry>();
                if(snapshot.hasChildren()){
                    for(DataSnapshot mydata: snapshot.getChildren()){
                        DataPoints datapoint=mydata.getValue(DataPoints.class);
                        dataVal.add(new Entry(datapoint.getTime(),datapoint.getStrain()));
                    }
                    showChart(dataVal);
                }else{
                    linechart.clear();
                    linechart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVal) {
        linedataset.setValues(dataVal);
        linedataset.setLabel("Y axis: Strain, X axis: Time");
        iLineDataSets.clear();
        iLineDataSets.add(linedataset);
        linedata=new LineData(iLineDataSets);
        linechart.clear();
        linechart.setData(linedata);
        linechart.invalidate();
    }
}