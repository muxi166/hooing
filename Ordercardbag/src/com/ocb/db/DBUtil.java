package com.ocb.db;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {

	private static String dbname="oc.db";
	private  String tablename;
	private SQLiteDatabase db;
	public SQLiteDatabase getDb() {
		return db;
	}
	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
	private String creatTable="create table Restaurant(" +
	"rid integer primary key autoincrement,rname text,raddr text,rintro text," +
	"rlp text,rphotos text,rphone text,favornote text," +
	"rlat DOUBLE,rlng DOUBLE)";
	public DBUtil(Context cx) {
		// TODO Auto-generated constructor stub
		super(cx, dbname, null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		this.db=database;
		db.execSQL(creatTable);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
