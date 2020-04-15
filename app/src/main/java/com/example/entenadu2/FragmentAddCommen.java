package com.example.entenadu2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddCommen extends Fragment {
    View view;
    EditText name,mobile;
    Button btn;
    Admin ad;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.freagment_add_commen_data,container,false);
        name=view.findViewById(R.id.edt_frg_commen_name);
        mobile=view.findViewById(R.id.edt_frg_commen_mobile);
        btn=view.findViewById(R.id.btn_frg_commen_comment);
           btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   ad=(Admin) getActivity();
                     FireBaseDataInsert fsins=new FireBaseDataInsert(name.getText().toString(),mobile.getText().toString()," ",ad.get_spinner_value());
                   if((ad.insert_to_firebase(fsins)==true))
                   {
                       name.setText(" ");
                       mobile.setText(" ");
                   }

               }
           });

        return view;
    }

}
