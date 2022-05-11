package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    Button rep,data,graph;
    ImageView github,youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        rep=findViewById(R.id.report);
        data=findViewById(R.id.data);
        graph=findViewById(R.id.graph);
        github=findViewById(R.id.git);
        youtube=findViewById(R.id.youtube);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartActivity.this,ViewData.class);
                startActivity(intent);
            }
        });
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent2=new Intent(StartActivity.this,ReportActivity.class);
                //startActivity(intent2);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1T4_nxYYheU5WWfvge7yJ1dm6tEDwU2bx/view?usp=sharing")));
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Gaurav-1200/InclinationSensorLiveMonitoring")));
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wX9YsH05SDs")));
            }
        });
    }
}