package com.example.entenadu2;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MyDialog extends Dialog
    {

        public Activity c;
        public Dialog d;
        public Button yes, no;
        public TextView ok;
        public CheckBox dont;
        public MyDialog(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.my_dialog);
            ok=(TextView) findViewById(R.id.buttonOk);
            dont=(CheckBox) findViewById(R.id.dialog_checkBox);


          ok.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dismiss();
              }
          });

        }


}
