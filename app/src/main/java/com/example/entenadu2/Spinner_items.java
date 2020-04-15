package com.example.entenadu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Spinner_items extends AppCompatActivity {
    private Spinner spn;
    private Button add;
    private EditText edt;
    private String data;
    DatabaseReference mydb;
    FirebaseDatabase myfb;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_items);
        spn=findViewById(R.id.add_spinner_items);
        edt=findViewById(R.id.edtadd_spinner);
        add=findViewById(R.id.add_spinner_btn);

        progressDialog=new ProgressDialog(this);
        ///spinner values
        myfb = FirebaseDatabase.getInstance();
        mydb = myfb.getReference("SpinnerValues");
       // final DatabaseReference spinnerAdd = mydb.push();


        final ArrayList<String> spnitems=new ArrayList<>();
        spnitems.add("select item to add");
        spnitems.add("bus to");
        spnitems.add("vehicle type");
        spnitems.add("work type");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,spnitems);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    new ShowMsg(getApplicationContext(),"select a item to continue");
                    edt.setVisibility(View.GONE);
                    add.setVisibility(View.GONE);
                }
                else
                {
                    edt.setVisibility(View.VISIBLE);
                    add.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    progressDialog.setMessage("please wait...");
                    progressDialog.show();
                    new ShowMsg(getApplicationContext(),spn.getSelectedItem()+" ");
                      SpinnerClass spc=new SpinnerClass(spn.getSelectedItem()+"",edt.getText().toString().trim());
//                         mydb.child("type").setValue(spn.getSelectedItem().toString().trim());
//                         mydb.child("value").setValue(edt.getText().toString().trim())
                        mydb.push().setValue(spc)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    progressDialog.dismiss();
                                    new ShowMsg(getApplicationContext(),"succes");
                                    edt.setText("");
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    new ShowMsg(getApplicationContext(),"failed");
                                }
                            }
                        });
                }

        });
    }
}
