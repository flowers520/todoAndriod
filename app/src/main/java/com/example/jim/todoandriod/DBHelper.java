package com.example.jim.todoandriod;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jim on 16/6/30.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "money.db", null, 1);
    }


    //完成创表操作
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bills(_id integer primary key autoincrement,"+
        "matter varchar(50) not null, price double, dtime date, type char(4))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
