package com.example.entenadu2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BloodBank extends AppCompatActivity {
    private static final String TAG = "ListViewActivity";

//    private ListAdapter fruitArrayAdapter;
    private ListView listView;
//
//    private static int colorIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);

//        colorIndex = 0;
        listView = (ListView) findViewById(R.id.list_blood);
//        fruitArrayAdapter = new ListAdapter(getApplicationContext(), R.layout.custom_list);
//        listView.setAdapter(fruitArrayAdapter);

//        List<String[]> fruitList = readData();
//        for(String[] fruitData:fruitList ) {
//            String fruitImg = fruitData[0];
//            String fruitName = fruitData[1];
//            String calories = fruitData[2];
//            int fruitImgResId = getResources().getIdentifier(fruitImg, "drawable", "com.javapapers.android.listviewcustomlayout.app");
//
//            ListContent fruit = new ListContent(fruitImgResId,fruitName,calories);
//            fruitArrayAdapter.add(fruit);

        final ArrayList<String> spnitems=new ArrayList<>();
        spnitems.add("A +ve");
        spnitems.add("A -ve");
        spnitems.add("B +ve");
        spnitems.add("B -ve");
        spnitems.add("AB +ve");
        spnitems.add("AB -ve");
        spnitems.add("O +ve");
        spnitems.add("O -ve");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,spnitems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new ShowMsg(getApplicationContext(),spnitems.get(position)+" ");
            }
        });
        }
    }

//    public List<String[]> readData(){
//        List<String[]> resultList = new ArrayList<String[]>();
//
//
//        String[] fruit1 = new String[3];
//        fruit1[0] = "cherry";
//        fruit1[1] = "Cherry";
//        fruit1[2] = "50 Calories";
//        resultList.add(fruit1);
//
//
//        String[] fruit3 = new String[3];
//        fruit3[0] = "banana";
//        fruit3[1] = "Banana";
//        fruit3[2] = "89 Calories";
//        resultList.add(fruit3);
//
//        String[] fruit4 = new String[3];
//        fruit4[0] = "apple";
//        fruit4[1] = "Apple";
//        fruit4[2] = "52 Calories";
//        resultList.add(fruit4);
//
//        return  resultList;
//    }


//}
