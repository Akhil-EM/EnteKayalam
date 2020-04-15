package com.example.entenadu2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Announcements extends AppCompatActivity {
    DatabaseReference mydb;
    FirebaseDatabase myfb;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterForAnnouncement adapterForAnnouncement;
    ArrayList<Announce> list;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        setTitle("announcements");
        progressDialog=new ProgressDialog(this);
        firebase_service();
        recyclerView=findViewById(R.id.recycler_for_announcement);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


      recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
          @Override
          public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
              Log.d("scroll data", "v:"+v+" x:"+scrollX+" y:"+scrollY+" old x:"+oldScrollX+" old y:"+oldScrollY);
          }
      });

    }

    private void firebase_service() {
        progressDialog.setMessage("please wait......");
        progressDialog.show();
        myfb=FirebaseDatabase.getInstance();
        mydb=myfb.getReference("announcement");
        list=new ArrayList<Announce>();
        mydb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list=new ArrayList<Announce>();
                for(DataSnapshot child: dataSnapshot.getChildren())
                {
                    Announce ans=child.getValue(Announce.class);
                    ///new ShowMsg(getApplicationContext(),ans.getTittle()+" "+ans.getContent()+" "+ans.getDate()+"");
                    list.add(new Announce(ans.getTittle(),ans.getContent(),ans.getDate()));
                }
                Collections.reverse(list);
                adapterForAnnouncement=new AdapterForAnnouncement(getApplicationContext(),list);
                recyclerView.setAdapter(adapterForAnnouncement);
                adapterForAnnouncement.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
