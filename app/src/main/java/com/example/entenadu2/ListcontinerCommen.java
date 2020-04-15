package com.example.entenadu2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ListcontinerCommen extends AppCompatActivity {
    ListView list_continer;
    String parent="";
    String type="";
    String [] names;
    String [] mobiles;
    String appNmae;
    int url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcontiner);
        list_continer=findViewById(R.id.list_continer);


        type=getIntent().getStringExtra("type");
        type=nullify(type);
        parent=getIntent().getStringExtra("parent");
        url=getIntent().getIntExtra("url",0);
        appNmae=getIntent().getStringExtra("appName");
         //new ShowMsg(getApplicationContext(),type+" "+parent);
        if(appNmae==null)
        {
            setTitle(type);
        }
        else
        {
            setTitle(appNmae);
        }
        try {
            DbHelperForCommen dbcom=new DbHelperForCommen(this);
              ///new ShowMsg(getApplicationContext(),dbBus.get_data_bus("kozhikode")+"list");
//                    DbHelperForSql db=new DbHelperForSql(this);
             names=dbcom.get_data_name(parent,type).split("," );
             mobiles=dbcom.get_data_mobile(parent,type).split("," );

             ////new ShowMsg(getApplicationContext(),dbcom.get_data_mobile("hospitals",type)+" ");
        }
        catch (RuntimeException e)
        {
            new ShowMsg(getApplicationContext(),e+"");
        }



        try {
                      MylistAdapterCommen adapter = new MylistAdapterCommen(this, names, mobiles, url);
                      list_continer.setAdapter(adapter);

                      list_continer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                          @Override
                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                              //new ShowMsg(getApplicationContext(), names[position] + "");
                              try {
                                  Intent intent=new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",mobiles[position],null));
                                  startActivity(intent);
                              }
                              catch(RuntimeException r)
                              {
                                  return;
                              }

                          }
                      });

        }
        catch (RuntimeException r)
        {
            new ShowMsg(getApplicationContext(),r+"");
        }
       // new ShowMsg(getApplicationContext(),type);
    }
    static String nullify(Object value)
    {
        return value== null ? "":value.toString();
    }
}
