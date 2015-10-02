package com.example.database;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteHelper extends SQLiteOpenHelper{
	public MySQLiteHelper(Context context,String name,CursorFactory factory,int version){
	    super(context,name,factory,version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists test_info("
		+"id integer,"
		+"word varchar,"
		+"meaning varchar)");
	}
    
}

