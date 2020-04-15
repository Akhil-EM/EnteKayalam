package com.example.entenadu2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperForBusData extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME="databaseBus.db";
    private static final int DATABASE_VERSION=1;

    private static final  String SPINNER_TABLE_NAME="busdata";

    private static final  String SPINNER_COL_ID="busid";
    private static final  String SPINNER_COL_NAME="busName";
    private static final  String SPINNER_COL_TIME="busTime";
    private static final  String SPINNER_COL_TO="busTo";
    public DbHelperForBusData(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable="CREATE TABLE "+SPINNER_TABLE_NAME+"("+SPINNER_COL_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT ,"
                +SPINNER_COL_NAME+" TEXT ,"+SPINNER_COL_TIME+" TEXT ,"+SPINNER_COL_TO+" TEXT );";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS "+SPINNER_TABLE_NAME);
      onCreate(db);
    }
    public long insert_data_bus(String bus_name,String bus_time,String bus_to)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SPINNER_COL_NAME,bus_name);
        values.put(SPINNER_COL_TIME,bus_time);
        values.put(SPINNER_COL_TO,bus_to);
        return db.insert(SPINNER_TABLE_NAME,null,values);
    }

    public String get_data_bus_name(String to) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{SPINNER_COL_NAME};

     Cursor cursor=db.query(SPINNER_TABLE_NAME,columns,SPINNER_COL_TO+"=?",new String[]{to},null,null,null,null);

       // Cursor cursor=db.query(SPINNER_TABLE_NAME,columns,null,null,null,null,null,null);

        //Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" = ? AND "+COMMEN_COL_PARENT+" = ? ",new String[]{type,parent},null,null,null,null);

        //fetches every row
        //Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,null,null,null,null,null,null);
        int Lmobile = cursor.getColumnIndex(SPINNER_COL_NAME);
        String name = "";

        if (cursor.moveToFirst()) {
            do {
                name += cursor.getString(Lmobile) + ",";
            }
            while (cursor.moveToNext());
        }



        return name;
    }
    public String get_data_bus_time(String to) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{SPINNER_COL_TIME};

//     Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+"=?"
//                ,new String[]{type},null,null,null,null);

        Cursor cursor=db.query(SPINNER_TABLE_NAME,columns,SPINNER_COL_TO+"=?",new String[]{to},null,null,null,null);

        //Cursor cursor=db.query(SPINNER_TABLE_NAME,columns,null,null,null,null,null,null);

        //Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" = ? AND "+COMMEN_COL_PARENT+" = ? ",new String[]{type,parent},null,null,null,null);

        //fetches every row
        //Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,null,null,null,null,null,null);
        int Lmobile = cursor.getColumnIndex(SPINNER_COL_TIME);
        String name = "";

        if (cursor.moveToFirst()) {
            do {
                name += cursor.getString(Lmobile) + ",";
            }
            while (cursor.moveToNext());
        }



        return name;
    }


    public  void delete_data()
    {
        db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+SPINNER_TABLE_NAME);
        db.close();
    }

}
