package com.example.entenadu2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperForSql extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME="database.db";
    private static final int DATABASE_VERSION=1;

    private static final  String SPINNER_TABLE_NAME="spinitems";

    private static final  String SPINNER_COL_ID="spinitems";
    private static final  String SPINNER_COL_TYPE="type";
    private static final  String SPINNER_COL_VAL="val";
    public DbHelperForSql(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable="CREATE TABLE "+SPINNER_TABLE_NAME+"("+SPINNER_COL_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT ,"
                +SPINNER_COL_TYPE+" TEXT ,"+SPINNER_COL_VAL+" TEXT );";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS "+SPINNER_TABLE_NAME);
      onCreate(db);
    }
    public long insert_data_spinner(String type,String value)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SPINNER_COL_TYPE,type);
        values.put(SPINNER_COL_VAL,value);
        return db.insert(SPINNER_TABLE_NAME,null,values);
    }
    public String get_data_spinner( String type)
    {
        db=this.getReadableDatabase();
        String [] columns= new String[]{SPINNER_COL_VAL};

        Cursor cursor=db.query(SPINNER_TABLE_NAME,new String[]{SPINNER_COL_VAL},SPINNER_COL_TYPE+"=?"
                ,new String[]{type},null,null,null,null);        int cnt=0;
        int Lval=cursor.getColumnIndex(SPINNER_COL_VAL);
        String name="";

        if(cursor.moveToFirst())
        {
            do{

                name+=cursor.getString(Lval)+",";
            }
            while (cursor.moveToNext());
        }

        db.close();
        return name;
    }
    public  void delete_data()
    {
        db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+SPINNER_TABLE_NAME);
        db.close();
    }

}
