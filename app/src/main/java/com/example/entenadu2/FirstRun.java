package com.example.entenadu2;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;

public class FirstRun {
    Context context;
    DatabaseReference mydb;
    FirebaseDatabase myfb;
    String n="";
    String [] spinner;
    String img;
    int i;
    String spnData;
    public  DbHelperForSql dbspin=new DbHelperForSql(context);
    public  DbHelperForCommen dbcom=new DbHelperForCommen(context);
    public  DbHelperForBusData dbBus =new DbHelperForBusData(context);
    public FirstRun(Context context) {
        this.context = context;

    }
    public void set_spinner() {
        n=" ";
        myfb = FirebaseDatabase.getInstance();
        mydb = myfb.getReference("SpinnerValues");
        mydb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren())
                {
                    SpinnerClass spn=child.getValue(SpinnerClass.class);
                    if(spn.getValue()!=null)
                    {
                        DbHelperForSql dbspin=new DbHelperForSql(context);
                         n+=spn.getType()+"="+spn.getValue()+"\n";
                        dbspin.insert_data_spinner(spn.getType(),spn.getValue());
                        //new ShowMsg(context,n.trim()+" ");
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public  void set_commendata()
    {
         spinner = new String[]{"blood bank", "hospitals", "vehicles", "emergencey", "shops", "workers", "offices", "social rep", "social form"};
        //n="";
        for( i=0;i<spinner.length;i++)
        {
             spnData = spinner[i];
            get_fireBase(spnData);

        }
    }

    private void get_fireBase(final String parent) {

        myfb=FirebaseDatabase.getInstance();
        mydb=myfb.getReference(parent);
        mydb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren())
                {
                    FireBaseDataInsert fins=child.getValue(FireBaseDataInsert.class);
                    //FireBaseDataInsert spn=child.getValue(FireBaseDataInsert.class);
                    if(fins.getName()!=null)
                    {
                        dbcom=new DbHelperForCommen(context);
                        n+=fins.getName()+","+fins.getMobile()+","+fins.getType()+","+parent+"\n";
                       //// new ShowMsg(context,n);
                        dbcom.insert_data_commen(fins.getName(),fins.getMobile(),fins.getType(),fins.getParent());
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void set_busdata() {
        myfb=FirebaseDatabase.getInstance();
        mydb=myfb.getReference("bus time");
        mydb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren())
                {
                    ///getValue(FireBaseDataInsert.class);
                    // FireBaseDataInsert spn=child.getValue(FireBaseDataInsert.class);

                    fireBaseBusInsert fsbus=child.getValue(fireBaseBusInsert.class);
                    if(fsbus.getBus_name()!=null)
                    {
                        // dbcom=new DbHelperForCommen(getApplicationContext());

                        dbBus=new DbHelperForBusData(context);

                        n+=fsbus.getBus_name()+","+fsbus.getTime()+","+fsbus.getTo()+"\n";
                       /// new ShowMsg(context,n);
                        /// dbcom.insert_data_commen(fins.getName(),fins.getMobile(),fins.getType(),fins.getParent());
                        dbBus.insert_data_bus(fsbus.getBus_name(),fsbus.getTime(),fsbus.getTo());
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void clear_tables()
    {
        try {
            DbHelperForSql dbspin=new DbHelperForSql(context);
            DbHelperForCommen dbcom=new DbHelperForCommen(context);
            DbHelperForBusData dbBus =new DbHelperForBusData(context);
            dbspin.delete_data();
            dbBus.delete_data();
            dbcom.delete_data();
        }
        catch (RuntimeException r)
        {
            new ShowMsg(context,r+"");
        }
    }
    public void save_images( )
    {

        StorageReference storage;
        storage = FirebaseStorage.getInstance().getReference();
        StorageReference ref = storage.child("images/");
        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                String ImageUrl=task.getResult().toString();
//                Glide.with(context)
//                        .load(ImageUrl)
//                        .into();
                new ShowMsg(context,ImageUrl+"");
            }
        });


    }

}
