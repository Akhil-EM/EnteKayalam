package com.example.entenadu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    protected Spinner spn;
    LinearLayout lin;
    DatabaseReference mydb;
    FirebaseDatabase myfb;
    Boolean key=false;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        spn=findViewById(R.id.addSpinner);
        lin=findViewById(R.id.fragment_Loader);

        progressDialog=new ProgressDialog(this);
        ///spinner values
        myfb = FirebaseDatabase.getInstance();

        final ArrayList<String> spnitems=new ArrayList<>();
        spnitems.add("select item to add");
        spnitems.add("announcement");
        spnitems.add("blood bank");
        spnitems.add("bus time");
        spnitems.add("hospitals");
        spnitems.add("vehicles");
        spnitems.add("emergencey");
        spnitems.add("shops");
        spnitems.add("workers");
        spnitems.add("offices");
        spnitems.add("social rep");
        spnitems.add("social form");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,spnitems);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new ShowMsg(getApplicationContext(),position+" ");
                switch (position)
                {
                    case 0:
                        lin.setVisibility(view.GONE);
                        new ShowMsg(getApplicationContext(),"select something to continue");
                        break;
                    case 1:
                        FragmentAddAriyippukal frg=new FragmentAddAriyippukal();
                        fragment_change(frg);
                        break;
                    case 2:
                        FragmentAddBloodGrp frgb=new FragmentAddBloodGrp();
                        fragment_change(frgb);
                        break;
                    case 3:
                        FragmentAddBusTime frgbs=new FragmentAddBusTime();
                        fragment_change(frgbs);
                        break;
                    case 4:
                         fragment_changer_commen();
                        break;
                    case 5:
                        FragmentAddvehicles frgv=new FragmentAddvehicles();
                        fragment_change(frgv);
                        break;
                    case 6:
                        fragment_changer_commen();
                        break;
                    case 7:
                        fragment_changer_commen();
                        break;
                    case 8:
                        FragmentAddWorkers frgw=new FragmentAddWorkers();
                        fragment_change(frgw);
                        break;
                    case 9:
                        fragment_changer_commen();
                        break;
                    case 10:
                        fragment_changer_commen();
                        break;
                    case 11:
                        fragment_changer_commen();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public  String get_spinner_value()
    {
        return spn.getSelectedItem().toString();
    }
    public Boolean insert_to_firebase(Object obj)
    {
        progressDialog.setMessage("please wait...");
        progressDialog.show();
        //FireBaseDataInsert fins=new FireBaseDataInsert(name,mobile);
        mydb = myfb.getReference(spn.getSelectedItem().toString());
        mydb.push().setValue(obj)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            new ShowMsg(getApplicationContext(),"succes");
                            key=true;
                        }
                        else
                        {
                            progressDialog.dismiss();
                            new ShowMsg(getApplicationContext(),"failed");
                            key=false;
                        }
                    }
                });



       /// new ShowMsg(getApplicationContext(),name+"  "+mobile);
       if(key==true)
       {
           return true;
       }
       else
       {
           return false;
       }
    }
    private void fragment_change(Fragment frgment) {
        lin.setVisibility(View.VISIBLE);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_Loader,frgment);
        transaction.commit();

    }
    private void fragment_changer_commen() {
        lin.setVisibility(View.VISIBLE);
        FragmentAddCommen frgc=new FragmentAddCommen();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_Loader,frgc);
        transaction.commit();

    }

}
