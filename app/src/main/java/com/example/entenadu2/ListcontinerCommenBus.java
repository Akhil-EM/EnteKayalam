package com.example.entenadu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ListcontinerCommenBus extends AppCompatActivity {
    ListView list_continer;
    String parent="";
    String type="";
    String [] names;
    String [] time;
    String appNmae;
    int url;
    ExtraFunction exf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcontiner);
        list_continer=findViewById(R.id.list_continer);
         exf=new ExtraFunction();

        type=getIntent().getStringExtra("type");
        type=exf.nullify(type);
        parent=getIntent().getStringExtra("parent");
        url=getIntent().getIntExtra("url",0);
        appNmae=getIntent().getStringExtra("appName");
        /// new ShowMsg(getApplicationContext(),type+" "+parent);
        if(appNmae==null)
        {
            setTitle(type);
        }
        else
        {
            setTitle(appNmae);
        }

        try {
            DbHelperForBusData dbBus=new DbHelperForBusData(this);
              ///new ShowMsg(getApplicationContext(),dbBus.get_data_bus("kozhikode")+"list");
//                    DbHelperForSql db=new DbHelperForSql(this);
             names=dbBus.get_data_bus_name(type).split("," );
             time=dbBus.get_data_bus_time(type).split("," );

             ////new ShowMsg(getApplicationContext(),dbcom.get_data_mobile("hospitals",type)+" ");
        }
        catch (RuntimeException e)
        {
            new ShowMsg(getApplicationContext(),e+"");
        }



        try {
                      MylistAdapterCommen adapter = new MylistAdapterCommen(this, names,time, url);
                      list_continer.setAdapter(adapter);

        }
        catch (RuntimeException r)
        {
            new ShowMsg(getApplicationContext(),r+"");
        }
        new ShowMsg(getApplicationContext(),type);
    }
}
