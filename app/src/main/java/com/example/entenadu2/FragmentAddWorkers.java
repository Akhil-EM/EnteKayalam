package com.example.entenadu2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddWorkers extends Fragment {
    View view;
    Spinner spn;
    EditText name,mobile;
    Button btn;
    Admin ad;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.freagment_add_workers,container,false);
        spn=view.findViewById(R.id.addSpinnerWorkerType);
        name=view.findViewById(R.id.edt_frg_worker_name);
        mobile=view.findViewById(R.id.edt_frg_worker_mobile);
        btn=view.findViewById(R.id.btn_frg_workers);
        ///accessing the values loccally stored in sqlite
        DbHelperForSql db=new DbHelperForSql(view.getContext());
        ArrayList<String> list= new ArrayList<>(Arrays.asList(db.get_data_spinner("work type").split(",")));
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        spn.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad=(Admin) getActivity();
                FireBaseDataInsert fsins=new FireBaseDataInsert(name.getText().toString(),mobile.getText().toString(),spn.getSelectedItem().toString(),ad.get_spinner_value());
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
