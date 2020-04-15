package com.example.entenadu2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class BusTime extends AppCompatActivity {
    ListView list;
    String [] bus_time;
    String [] bus_name;
    int url=R.drawable.bus_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_time);
        list=findViewById(R.id.list_bus);
        setTitle("BUS INFORMATION");





    }
}
