package com.example.entenadu2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForAnnouncement extends RecyclerView.Adapter<AdapterForAnnouncement.Myholder> {
    Context context;
    ArrayList<Announce>announces;

    public AdapterForAnnouncement(Context context, ArrayList<Announce> announces) {
        this.context = context;
        this.announces = announces;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.anoncement_row,viewGroup,false);
        Myholder myholder=new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder myholder, int i) {
       myholder.date.setText(announces.get(i).getDate());
        myholder.content.setText(announces.get(i).getContent());
        myholder.tittle.setText(announces.get(i).getTittle());
    }

    @Override
    public int getItemCount() {
        return announces.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView date,tittle,content;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.announcement_row_date);
            tittle=itemView.findViewById(R.id.announcement_row_tittle);
            content=itemView.findViewById(R.id.announcement_row_content);
        }
    }
}
