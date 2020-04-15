package com.example.entenadu2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

class MylistAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] tittle;
    private final int  imageUrl;


    public MylistAdapter( Activity context, String[] tittle, int imageUrl) {
        super(context, R.layout.custom_list,tittle);
        this.context = context;
        this.tittle = tittle;
        this.imageUrl = imageUrl;
    }
    public View getView(int postion, View view, ViewGroup parent)
    {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View RowView=layoutInflater.inflate(R.layout.custom_list,null,true);


        TextView txt=RowView.findViewById(R.id.title_for_list);
        ImageView image=RowView.findViewById(R.id.icon_list);

        txt.setText(tittle[postion]);
        image.setImageResource(imageUrl);
        return RowView;
    }

}
