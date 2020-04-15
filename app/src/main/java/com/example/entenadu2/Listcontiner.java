package com.example.entenadu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Listcontiner extends AppCompatActivity {
   ListView list_continer;
    String [] tittle;
    int url;
    String appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcontiner);
        list_continer=findViewById(R.id.list_continer);



        tittle=getIntent().getStringArrayExtra("tittle");
        url=getIntent().getIntExtra("imgUrl",0);
        appName=getIntent().getStringExtra("appName");
        setTitle(appName);

//         new ShowMsg(getApplicationContext(),getIntent().getStringArrayExtra("tittle")+","
//                 +getIntent().getIntExtra("imgUrl",0)+","+getIntent().getStringExtra("appName"));
        MylistAdapter adapter=new MylistAdapter(this,tittle,url);
        list_continer.setAdapter(adapter);

        list_continer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(appName.equals("Bus info"))
                {
                    Intent in=new Intent(getApplicationContext(),ListcontinerCommenBus.class);
                    in.putExtra("type",tittle[position]);
                    in.putExtra("parent",appName);
                    in.putExtra("url",url);
                    startActivity(in);
                }
                else
                {
                    Intent in=new Intent(getApplicationContext(),ListcontinerCommen.class);
                    in.putExtra("type",tittle[position]);
                    in.putExtra("parent",appName);
                    in.putExtra("url",url);
                    startActivity(in);
                }

            }
        });
    }
}
