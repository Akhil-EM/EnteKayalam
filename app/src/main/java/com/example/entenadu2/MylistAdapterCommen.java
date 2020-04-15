package com.example.entenadu2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MylistAdapterCommen extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] tittle;
    private final String[] mobilearray;
    private final int  imageUrl;


    public MylistAdapterCommen(Activity context, String[] tittle, String [] mobilearray,int imageUrl) {
        super(context, R.layout.custom_list,tittle);
        this.context = context;
        this.tittle = tittle;
        this.mobilearray = mobilearray;
        this.imageUrl = imageUrl;
    }
    public View getView(int postion, View view, ViewGroup parent)
    {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View RowView=layoutInflater.inflate(R.layout.custom_list_commen,null,true);
        TextView name=RowView.findViewById(R.id.name_for_list);
        TextView mobile=RowView.findViewById(R.id.phone_for_list);
        ImageView image=RowView.findViewById(R.id.icon_list);
        ImageView img=RowView.findViewById(R.id.call_icon);
        if(imageUrl==R.drawable.bus_time)
        {
            ExtraFunction EFX=new ExtraFunction();
            img.setVisibility(View.GONE);
            name.setText(tittle[postion]);
            mobile.setText(mobilearray[postion]);
            image.setImageResource(imageUrl);
        }
        name.setText(tittle[postion]);
        mobile.setText(mobilearray[postion]);
        image.setImageResource(imageUrl);
        return RowView;
    }

}
