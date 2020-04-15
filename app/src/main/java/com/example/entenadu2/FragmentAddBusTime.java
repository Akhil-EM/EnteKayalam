package com.example.entenadu2;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddBusTime extends Fragment {
    View view;
    Spinner spn;
    TextView txt;
    EditText bus_name;
    Button btn;
    Admin ad;
    String time;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.freagment_add_bus_time,container,false);
        spn=view.findViewById(R.id.addSpinnerBusToType);
        bus_name=view.findViewById(R.id.edt_frg_bus_name);
        txt=view.findViewById(R.id.timepick);
        btn=view.findViewById(R.id.btn_frg_bus_add);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcalender=Calendar.getInstance();
                int hour=mcalender.get(Calendar.HOUR_OF_DAY);
                int minute=mcalender.get(Calendar.MINUTE);
                TimePickerDialog timePick;
                timePick=new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        time=hourOfDay+":"+minute;
                        ExtraFunction efs=new ExtraFunction();
                        txt.setText(efs.time_shifter(time));
                    }
                },hour,minute,false);//24 hour or not
                timePick.setTitle("choose time");
                timePick.show();
            }
        });

        ///accessing the values loccally stored in sqlite
        DbHelperForSql db=new DbHelperForSql(view.getContext());
        ArrayList<String> list= new ArrayList<>(Arrays.asList(db.get_data_spinner("bus to").split(",")));
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        spn.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(txt.getText().toString().trim().equals("choose time"))
                 {
                     new ShowMsg(view.getContext(),"please choose time");
                 }
                 else
                 {
                     ad=(Admin) getActivity();
                     fireBaseBusInsert fsins=new fireBaseBusInsert(bus_name.getText().toString(),spn.getSelectedItem().toString(),txt.getText().toString());
                     if((ad.insert_to_firebase(fsins)==true))
                     {
                         bus_name.setText(" ");
                         txt.setText("choose time");
                     }
                 }


            }
        });
        return view;
    }
}
