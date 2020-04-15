package com.example.entenadu2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExtraFunction {
      String [] timearray;
      int time;
      String ogTime;
      int fs,ss;
    static String nullify(Object value)
    {
        return value== null ? "":value.toString();
    }

    public String time_shifter(String tm)
    {
        timearray=tm.split(":");
        fs=Integer.parseInt(timearray[0]);
        ss=Integer.parseInt(timearray[1]);
        if(fs>12)
        {
            fs=fs%12;
            ogTime=fs+":"+ss+" PM";
        }
        else
        {
            ogTime=fs+":"+ss+" AM";
        }
        return ogTime;
    }
    public String get_date_now()
    {
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyy");
        String formatted_date=df.format(c);
        return  formatted_date;
    }
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }
}
