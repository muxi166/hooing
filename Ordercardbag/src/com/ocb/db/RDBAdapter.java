package com.ocb.db;


import android.database.sqlite.SQLiteDatabase;

public class RDBAdapter {
    private SQLiteDatabase db;

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
    
}
