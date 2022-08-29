package com.example.radionica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DatabaseHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Majstor.db";
    public static final String TABLE_NAME = "majstor_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "IME";
    public static final String COL_3 = "RODJENJE";
    public static final String COL_4 = "TELEFON";
    public static final String COL_5 = "OCENA";
    public static final String COL_6 = "BROJOCENA";
    public static final String COL_7=  "GRAD";
    public static final String COL_8 = "USLUGA";
    public static final String COL_9 = "CENA";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,IME TEXT,RODJENJE INTEGER ,TELEFON INTEGER, OCENA INTEGER, GRAD TEXT, USLUGA TEXT, CENA INTEGER, BROJOCENA INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String ime ,String rodjenje ,String telefon,  String grad, String usluga, String cena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,ime);
        contentValues.put(COL_3,rodjenje);
        contentValues.put(COL_4,telefon);
        contentValues.put(COL_7,grad);
        contentValues.put(COL_8,usluga);
        contentValues.put(COL_9,cena);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public int updateOcena(int id, String ocena) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor tempOcena=db.rawQuery("select OCENA, BROJOCENA from "+TABLE_NAME+" where "+COL_1 +" = '"+ id+"'",null);
        int oc=0;
        int brOc=0;
        while(tempOcena.moveToNext()){
            oc=tempOcena.getInt(0);
            brOc=tempOcena.getInt(1);
        }
        oc+=Integer.parseInt(ocena);
        brOc+=1;
        ContentValues crs= new ContentValues();

        crs.put(COL_5, oc);
        crs.put(COL_6, brOc);
        int res = db.update(TABLE_NAME, crs, COL_1 + " =?", new String[]{String.valueOf(id)});
        return res;
    }





    public Cursor pretraga(String grad, String usluga, String cena) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorr = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_7 + "='" + grad + "'"+" AND "+ COL_8 +"='"+ usluga +"'"+" OR "+ COL_9 +"='"+ cena +"'" , null);
        return cursorr;
    }

    public Cursor getMaxId() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select MAX(id) from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
