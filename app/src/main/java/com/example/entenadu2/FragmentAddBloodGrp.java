package com.example.entenadu2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddBloodGrp extends Fragment {
    View view;
    protected Spinner spn;
    EditText name,mobile;
    Admin adm;
    Button add;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.freagment_add_blood_grp,container,false);
        spn=view.findViewById(R.id.addSpinnerblood);
        add=view.findViewById(R.id.btn_add_blood);
        name=view.findViewById(R.id.edt_frg_blood_name);
        mobile=view.findViewById(R.id.edt_frg_blood_mobile);
        final ArrayList<String> spnitems=new ArrayList<>();
        spnitems.add("select blood group");
        spnitems.add("A +ve");
        spnitems.add("A -ve");
        spnitems.add("B +ve");
        spnitems.add("B -ve");
        spnitems.add("AB +ve");
        spnitems.add("AB -ve");
        spnitems.add("O +ve");
        spnitems.add("O -ve");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item,spnitems);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    new ShowMsg(view.getContext(),"select a blood group to continue");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
          add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(!spn.getSelectedItem().equals("select a blood group to continue"))
                  {

                      adm=(Admin) getActivity();

                      FireBaseDataInsert fsins=new FireBaseDataInsert(name.getText().toString(),mobile.getText().toString(),spn.getSelectedItem().toString(),adm.get_spinner_value());
                      if((adm.insert_to_firebase(fsins)==true))
                      {
                          name.setText(" ");
                          mobile.setText(" ");
                      }
                  }
                  else {
                      new ShowMsg(view.getContext(),"select bood group");
                  }
              }
          });

        return view;
    }
}
