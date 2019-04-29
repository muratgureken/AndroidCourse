package com.mgureken.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vektorel on 28.04.2019.
 */

public class Db_Islem extends SQLiteOpenHelper {
    public Db_Islem(Context context)
    {
        super(context, Sabitler._VT_NAME, null,Sabitler._VT_VERSION);
    }

    private final String _TABLE_SQL="CREATE TABLE " +Sabitler._TABLE_NAME +" "+
            "( id integer primary key AUTOINCREMENT , " +
            Sabitler._K_ADI +" varchar(50) , " +
            Sabitler._SIFRE +" varchar(50), " +
            Sabitler._EMAIL +" varchar(150), " +
            Sabitler._NAME +" varchar(150) " +
            ")";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean kaydet(Kullanici temp){

        try {
            String kolonlar = ""+Sabitler._K_ADI +"," + Sabitler._SIFRE+"," +Sabitler._EMAIL+"," + Sabitler._NAME ;
            SQLiteDatabase db= this.getWritableDatabase();
            db.execSQL("INSERT INTO " + Sabitler._TABLE_NAME +"("+kolonlar+") " +
                    "values('"+temp.getUname()+"','" + temp.getPasswd()+"','"
                    +temp.getEmail()+"','"+temp.getName()+"')" );
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

        return true;
    }

    public List<Kullanici> getAllUser(){
        List<Kullanici> liste = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String [] colums = {Sabitler._K_ADI,Sabitler._SIFRE, Sabitler._EMAIL, Sabitler._NAME};
        Cursor cr =  db.query(Sabitler._TABLE_NAME,colums,null,null,null,null,null);
        if (cr.getCount()>0){
            while (cr.moveToNext()){
                Kullanici temp = new Kullanici();
                temp.setUname(cr.getString(0));
                System.out.println(cr.getString(0));
                temp.setPasswd(cr.getString(1));
                System.out.println(cr.getString(1));
                liste.add(temp);
            }
        }



        return liste;
    }
}
