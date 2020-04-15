package com.example.entenadu2;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;

public class SharedPref {
    Context context;

    public void SharedPref(Context context)
    {
        this.context=context;
    }

    ///declare shared prefrences
    SharedPreferences firstInstall = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
    SharedPreferences.Editor FIeditor = firstInstall.edit();
    public void firstInstall()
    {
        FIeditor.putBoolean("installed",true);
        //FIeditor.putStringSet("haa", <"njn,n,","kljlkjkjk">);
        FIeditor.commit();
    }

    public Boolean chceckFirstInstall()
    {
        return firstInstall.getBoolean("installed",false);
    }

    public Boolean clearData()
    {
        FIeditor.clear();
        FIeditor.commit();
        if(firstInstall.getBoolean("installed",false)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
