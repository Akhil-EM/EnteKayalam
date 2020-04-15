package com.example.entenadu2;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   // FlipperLayout flipperLayout;
    private ImageButton announce,blood,bus,hosp,vehicle,emer,shop,workers,office,socialRep,socialForum;
    DatabaseReference mydb,myref;
    FirebaseDatabase myfb;
    String n=" ",type,value;
    DbHelperForCommen dbcom;
    DbHelperForSql db2;
    DbHelperForBusData dbBus;
    String [] spinner;
    String appLang="mal";
    String imageUrl;
    SliderView sliderView;
    ProgressDialog progressDialog;
    Boolean dbUpdateStatus=false;
    int i;
    private String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        announce=findViewById(R.id.btnannouncement);
        blood=findViewById(R.id.btnblood_bank);
        bus=findViewById(R.id.btn_bus_time);
        hosp=findViewById(R.id.btn_hospitals);
        vehicle=findViewById(R.id.btn_vehicles);
        emer=findViewById(R.id.btn_emergency);
        shop=findViewById(R.id.btn_shops);
        workers=findViewById(R.id.btn_workers);
        office=findViewById(R.id.btn_sthapanagal);
        socialRep=findViewById(R.id.btn_social_rep);
        socialForum=findViewById(R.id.btn_sangadanakal);
        //flipperLayout=findViewById(R.id.flipper);
        announce.setOnClickListener(this);
        blood.setOnClickListener(this);
        bus.setOnClickListener(this);
        hosp.setOnClickListener(this);
        vehicle.setOnClickListener(this);
        emer.setOnClickListener(this);
        shop.setOnClickListener(this);
        workers.setOnClickListener(this);
        office.setOnClickListener(this);
        socialForum.setOnClickListener(this);
        socialRep.setOnClickListener(this);


         progressDialog=new ProgressDialog(this);
        sliderView = findViewById(R.id.imageSlider);

        final SliderAdapterExample adapter = new SliderAdapterExample(this);
        adapter.setCount(5);


//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
//        if( sharedPreferences.getString("applang","")!=null )
//        {
//            appLang=sharedPreferences.getString("applang","");
//        }
//
//        if(appLang=="mal")
//        {
//            new ShowMsg(getApplicationContext(),"malayalam");
//            Language_switcher_mal();
//        }
//        if(appLang =="eng")
//        {
//            new ShowMsg(getApplicationContext(),"english");
//            Language_switcher_eng();
//        }

        sliderView.setSliderAdapter(adapter);
        setTitle("എന്റെ കായലം");
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
          update_db();
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel("Mynotifications","Mynotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "msg_subscribed";
                        if (!task.isSuccessful()) {

                            Log.d(TAG, "something went wrong !!!");
                        }
                        Log.d(TAG, msg);
                       // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        myfb = FirebaseDatabase.getInstance();
        mydb = myfb.getReference("send notification");
        mydb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot child: dataSnapshot.getChildren())
//                {
//                    Notification spn=child.getValue(Notification.class);
//
//                    new NotificationManage(spn.getTittle(),spn.getContent(),getApplicationContext());
//
//                }
                update_db();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void update_db() {
        DbHelperForSql db=new DbHelperForSql(getApplicationContext());

        if(db.get_data_spinner("bus to").trim().isEmpty())
        {
            ///new ShowMsg(getApplicationContext(),"if");
            FirstRun fs=new FirstRun(getApplicationContext());
            Log.d(TAG, "update_db: "+"some thing changed!!");
            fs.clear_tables();
            fs.set_spinner();
            fs.set_commendata();
            fs.set_busdata();
        }
        else
        {
            ///new ShowMsg(getApplicationContext(),"else");
        }
    }
    private void update_db_everytime() {
            new ShowMsg(getApplicationContext(),"if");
            FirstRun fs=new FirstRun(getApplicationContext());
            Log.d(TAG, "update_db: "+"some thing changed!!");
            fs.clear_tables();
            fs.set_spinner();
            fs.set_commendata();
            fs.set_busdata();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.admin:
                activitySwitcher(Admin.class);
                //Log.d(TAG,"download url is:");

                break;
            case R.id.add_spinner_menu:
                activitySwitcher(ImageCheck.class);
                break;
            case R.id.first_settings:
                FirstRun fs=new FirstRun(getApplicationContext());
                fs.clear_tables();
                fs.set_spinner();
                fs.set_commendata();
                fs.set_busdata();
               // fs.save_images( );

                break;
            case R.id.view_SqlLite:
//
               //new ShowMsg(getApplicationContext(),image_url_fetch(0)+"");
               /// activitySwitcher(ImageCheck.class);
                ///new NotificationManage("sample notification","sample content",getApplicationContext());
//                activitySwitcher(content2.class);
//                break;
                image_url_fetch(1);
                break;
            case R.id.delete_sqlite:
                try{
                    db2=new DbHelperForSql(getApplicationContext());
                    dbcom=new DbHelperForCommen(getApplicationContext());
                    dbBus =new DbHelperForBusData(getApplicationContext());
                    db2.delete_data();
                    dbcom.delete_data();
                    dbBus.delete_data();
                }
                catch (RuntimeException r)
                {
                    new ShowMsg(getApplicationContext(),r+"");
                }
                break;
            case R.id.select_language:
                 AlertLanguage alertLanguage = new AlertLanguage();
                alertLanguage.showDialog(MainActivity.this);
//                Language_switcher();
                break;
           }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnannouncement:
               // new ShowMsg(getApplicationContext(), "clicked me ");
                InternetConnectionChecker ich=new InternetConnectionChecker();
                Boolean val;
                 val=ich.checkConnection(getApplicationContext());
                 if(val ==true)
                 {
                     activitySwitcher(Announcements.class);

                 }
                 else
                 {
                     new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                             .setTopColorRes(R.color.infoRed)
                             .setButtonsColorRes(R.color.colorPrimaryDark)
                             .setIcon(R.drawable.info_logo)
                             .setTitle(R.string.connection_info_head)
                             .setMessage(R.string.connection_indicator)
                             .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     ///new ShowMsg(getApplicationContext(),"positive clicked");
                                     Intent intent=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                     startActivity(intent);
                                 }
                             })
                             .show();
                 }

//

//                new LovelyProgressDialog(this)
//                        .setIcon(R.drawable.info_logo)
//                        .setTitle(R.string.connection_info_head)
//                        .setTopColorRes(R.color.infoRed)
//                        .show();



                break;
            case R.id.btnblood_bank:
                //new ShowMsg(getApplicationContext(), blood.getResources()+"");
                //blood.setImageResource(R.id.add_content_ariyippukal);
                 String [] blood={"A +ve","A -ve","B +ve","B -ve","AB +ve","AB -ve","O +ve","O -ve"};
               activitySwitcher(Listcontiner.class,blood,R.drawable.blood_icon,"blood bank");
                break;
            case R.id.btn_bus_time:
                DbHelperForSql dbBus=new DbHelperForSql(getApplicationContext());
                String [] to=dbBus.get_data_spinner("bus to").split(",");
                activitySwitcher(Listcontiner.class,to,R.drawable.bus_time,"Bus info");
                break;
            case R.id.btn_hospitals:
               /// activitySwitcherCommen(ListcontinerCommen.class,null,R.drawable.hospitals,"hospitals","hospitals");

                MyDialog cdd=new MyDialog(MainActivity.this);
                cdd.show();

                break;
            case R.id.btn_vehicles:
                DbHelperForSql db=new DbHelperForSql(getApplicationContext());
                String [] vehicles=db.get_data_spinner("vehicle type").split(",");
                activitySwitcher(Listcontiner.class,vehicles,R.drawable.vehicles,"Vehicles");
                break;
            case R.id.btn_emergency:
                activitySwitcherCommen(ListcontinerCommen.class,null,R.drawable.emergency_blue,"emergencey","emergencey");
                break;
            case R.id.btn_shops:
                activitySwitcherCommen(ListcontinerCommen.class,null,R.drawable.shops,"shops","shops");
                break;
            case R.id.btn_workers:
                DbHelperForSql db2=new DbHelperForSql(getApplicationContext());
                String [] workers=db2.get_data_spinner("work type").split(",");
                activitySwitcher(Listcontiner.class,workers,R.drawable.workers,"Workers");
                break;
            case R.id.btn_sthapanagal:
                activitySwitcherCommen(ListcontinerCommen.class,null,R.drawable.sthapanagal,"offices","offices");
                break;
            case R.id.btn_sangadanakal:
                activitySwitcherCommen(ListcontinerCommen.class,null,R.drawable.sangadakal,"social form","social form");
                break;
            case R.id.btn_social_rep:
                activitySwitcherCommen(ListcontinerCommen.class,null,R.drawable.jana_prethinidhi,"social rep","social rep");
                break;
        }
    }

    private void activitySwitcher( Class to,String [] tittle,int img_url,String appName) {
        update_db_everytime();
        Intent in= new Intent(getApplicationContext(),to);
        in.putExtra("tittle",tittle);
        in.putExtra("imgUrl",img_url);
        in.putExtra("appName",appName);
        startActivity(in);
    }
    private void activitySwitcherCommen( Class to,String [] tittle,int img_url,String appName,String parent) {
//        update_db_everytime();
        Intent in= new Intent(getApplicationContext(),to);
        in.putExtra("tittle",tittle);
        in.putExtra("url",img_url);
        in.putExtra("appName",appName);
        in.putExtra("parent",parent);
        startActivity(in);
    }
    private void activitySwitcher( Class to) {
        update_db_everytime();
        Intent in= new Intent(getApplicationContext(),to);
        startActivity(in);
    }
    public void image_url_fetch(int cnt)
    {
        final String file="Myfile";
        final StorageReference ref;
        StorageReference storage;
        storage = FirebaseStorage.getInstance().getReference();
        ref = storage.child("images").child("imageNo"+cnt);
//        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                imageUrl=task.getResult().toString()+",";
//                Log.d(TAG, "image url: "+imageUrl);
//                return;
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                new ShowMsg(getApplicationContext(),"data not found");
//            }
//        });
//        return imageUrl;



        final long ONE_MEGABYTE = 1024 * 1024;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Uid", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        progressDialog.setMessage("please wait");
        progressDialog.show();



        ref.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
               /// Log.d(TAG, "image bytes"+sharedPreferences.getString("imageArray",null));

//
//                editor.putString("imageArray",bytes.toString());
//                editor.commit();


                try {
                    FileOutputStream fileOutputStream=openFileOutput(file,MODE_PRIVATE);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.d(TAG, "onSuccess: "+e);
                }

                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d(TAG, "error: "+exception);
            }
        });
        Log.d(TAG, "image bytes"+sharedPreferences.getString("imageArray",null));
    }
    public void Language_switcher_mal()
    {
        Log.d(TAG, "Language_switcher: ");
        setTitle(R.string.app_name_mala);
    }
    public void Language_switcher_eng()
    {
        Log.d(TAG, "Language_switcher: ");
        setTitle(R.string.app_name_eng);
    }


}
