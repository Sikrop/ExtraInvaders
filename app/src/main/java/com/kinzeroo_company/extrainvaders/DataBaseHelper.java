package com.kinzeroo_company.extrainvaders;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Juego.db";
    public static final String TABLE_NAME = "Juego_puntaje";

    public static final String Col_1 = "ID";
    public static final String Col_2 = "Nombre";
    public static final String Col_3 = "Puntaje";

    public DataBaseHelper (Context context){
        super(context, DATABASE_NAME,null,1);
    }
     @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE" + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Puntaje INTEGER)");
     }
     @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
     }
     public boolean InsertData(String nombre, String puntaje){
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(Col_2, nombre);
         contentValues.put(Col_3,puntaje);
         long result = db.insert(TABLE_NAME,null,contentValues);
         db.close();

         //revisar si inserta
         if (result==-1){
             return  false;
         }else{
             return true;
         }
     }
}
