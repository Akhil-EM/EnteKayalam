package com.example.entenadu2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperForCommen extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME="db2.db";
    private static final int DATABASE_VERSION=1;

    private static final  String COMMEN_TABLE_NAME="commenItems";

    private static final  String COMMEN_COL_ID="commenId";
    private static final  String COMMEN_COL_NAME="commenName";
    private static final  String COMMEN_COL_MOBILE="commenMobile";
    private static final  String COMMEN_COL_TYPE="commenType";
    private static final  String COMMEN_COL_PARENT="commenParent";
    public DbHelperForCommen(Context context)
    {
        super(context,DATABASE_NAME,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable="create table commenItems "+"( id integer primary key ,commenName text ,commenMobile text ,commenType text ,commenParent text);";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+COMMEN_TABLE_NAME);
        onCreate(db);
    }
    public long insert_data_commen(String name,String mobile,String type,String parent)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("commenName",name);
        values.put("commenMobile",mobile);
        values.put("commenType",type);
        values.put("commenParent",parent);
        return db.insert(COMMEN_TABLE_NAME,null,values);
    }
    public  void delete_data()
    {
        db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+COMMEN_TABLE_NAME);
        db.close();
    }
    public String get_data_commen(String type,String parent ) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{COMMEN_COL_NAME, COMMEN_COL_MOBILE,COMMEN_COL_PARENT,COMMEN_COL_TYPE};

        Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" = ? AND "+COMMEN_COL_PARENT+" = ? ",new String[]{type,parent},null,null,null,null);
       /// Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,null,null,null,null,null,null);

        //fetches every row
       // Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,null,null,null,null,null,null);
        int cnt = 0;
        int Lname = cursor.getColumnIndex(COMMEN_COL_NAME);
        int Lmobile = cursor.getColumnIndex(COMMEN_COL_MOBILE);
         int Lpar=cursor.getColumnIndex(COMMEN_COL_PARENT);
         int Ltype=cursor.getColumnIndex(COMMEN_COL_TYPE);
        String name = "";

        if (cursor.moveToFirst()) {
            do {
                name += cursor.getString(Lname) + ","+ cursor.getString(Lmobile) + ","+cursor.getString(Lpar)+","+cursor.getString(Ltype)+"\n";
            }
            while (cursor.moveToNext());
        }



        return name;
    }
    public String get_data_mobile( String parent,String type) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{COMMEN_COL_MOBILE};

//     Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+"=?"
//                ,new String[]{type},null,null,null,null);

        Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" LIKE ? AND "+COMMEN_COL_PARENT+" LIKE ? ",new String[]{"%"+type+"%","%"+parent+"%"},null,null,null,null);

        //Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" = ? AND "+COMMEN_COL_PARENT+" = ? ",new String[]{type,parent},null,null,null,null);

        //fetches every row
         //Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,null,null,null,null,null,null);
        int Lmobile = cursor.getColumnIndex(COMMEN_COL_MOBILE);
        String name = "";

        if (cursor.moveToFirst()) {
            do {
                name += cursor.getString(Lmobile) + ",";
            }
            while (cursor.moveToNext());
        }



        return name;
    }
    public String get_data_name( String parent,String type) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{COMMEN_COL_NAME};

//        Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+"=?"
//                ,new String[]{type},null,null,null,null);
       /// Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" = ? AND "+COMMEN_COL_PARENT+" = ? ",new String[]{"lorry"," "},null,null,null,null);

        Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,COMMEN_COL_TYPE+" LIKE ? AND "+COMMEN_COL_PARENT+" LIKE ? ",new String[]{"%"+type+"%","%"+parent+"%"},null,null,null,null);


        //fetches every row
        // Cursor cursor=db.query(COMMEN_TABLE_NAME,columns,null,null,null,null,null,null);
        int cnt = 0;
        int Lname = cursor.getColumnIndex(COMMEN_COL_NAME);
        String name = "";

        if (cursor.moveToFirst()) {
            do {
                name += cursor.getString(Lname)+ ",";
            }
            while (cursor.moveToNext());
        }



        return name;
    }
}
