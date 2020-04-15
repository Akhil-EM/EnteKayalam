package com.example.entenadu2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.entenadu2.R;

public class AlertLanguage {
    public void showDialog(final Context context ){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_for_language);
        Button Btn_ok = (Button) dialog.findViewById(R.id.btn_ok);
        Button Btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final CheckBox english=(CheckBox)dialog.findViewById(R.id.checkBoxenglish);
        final CheckBox malayalam=(CheckBox)dialog.findViewById(R.id.checkBoxmalayalam);

        english.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    malayalam.setChecked(false);
                }

            }
        });
        malayalam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    english.setChecked(false);
                }
            }
        });


        Btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ///  context.this.setTitle(R.string.app_name_mala);
                //mainActivity.Language_switcher();
                SharedPreferences firstInstall = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor FIeditor = firstInstall.edit();
                if(english.isChecked() || malayalam.isChecked())
                {
                    if(english.isChecked())
                    {
                        ///FIeditor.putString("applang","eng");
//                        MainActivity mainActivity=new MainActivity();
//                        mainActivity.appLang="eng";

                    }
                    if(malayalam.isChecked())
                    {
                        //FIeditor.putString("applang","mal");
//                        MainActivity mainActivity=new MainActivity();
//                        mainActivity.appLang="mal";
                    }
                    dialog.dismiss();
                }
                else
                {
                    new ShowMsg(context,"please select one");
                }



            }
        });
        Btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
