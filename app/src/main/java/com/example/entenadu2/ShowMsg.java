package com.example.entenadu2;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ShowMsg {
    Toast toast;
    public ShowMsg(Context context, String msg)
    {
       toast=Toast.makeText(context,msg+" ",Toast.LENGTH_SHORT);
       toast.setGravity(Gravity.TOP,0,0);
       toast.show();
    }
}
