package com.ocb.db;


import java.util.ArrayList;

import android.R.color;
import android.R.integer;
import android.app.AlarmManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ocb.pojo.Restaurant;

public class RestaurantDAO {
	private DBUtil dbUtil;
	private RDBAdapter rdbAdapter;
    public RDBAdapter getRdbAdapter() {
		return rdbAdapter;
	}

	public void setRdbAdapter(RDBAdapter rdbAdapter) {
		this.rdbAdapter = rdbAdapter;
	}
	public final static String tablename="Restaurant";
    public final static String creatTable="create table Restaurant(" +
    		"rid integer primary key autoincrement,rname text,raddr text,rintro text," +
    		"rlp text,rphotos text,rphone text,favornote text," +
    		"rlat DOUBLE,rlng DOUBLE)";
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
		
	}
	public boolean createTable(){
		try {
			rdbAdapter.getDb().execSQL(creatTable);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	public boolean save(Restaurant restaurant) {
		ContentValues values=new ContentValues();
		//values.put("rid", restaurant.getRestaurantID());
		values.put("rname", restaurant.getRestaurantName());
		values.put("raddr", restaurant.getRestanrantAddr());
		values.put("rlp", restaurant.getRestaurantLogoPhoto());
		values.put("rintro",restaurant.getRestaurantIntro());
		values.put("rlat", restaurant.getRestaurantLat());
		values.put("rlng", restaurant.getRestaurantLng());
		values.put("rphone", restaurant.getRestaurantPhone());
		values.put("rphotos", restaurant.getRestaurantPhotos());
		values.put("favornote", restaurant.getFavorNote());
//		values.put(tableName, tableName);
//		values.put(tableName, tableName);
//		values.put(tableName, tableName);
		
		try {
			
			
			SQLiteDatabase rdb=rdbAdapter.getDb();
			long num=rdb.insert(tablename,null, values);
			//System.out.println(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	};
	

	public ArrayList<Restaurant> getAll() {
		SQLiteDatabase rdb=rdbAdapter.getDb();
		Cursor cursor= rdb.query(tablename, null, null, null, null, null, null);
		if(!cursor.moveToFirst())
			return null;
		ArrayList<Restaurant> rList=new ArrayList<Restaurant>();
		Restaurant restaurant=new Restaurant();
		do{
			restaurant.setRestaurantID(Integer.parseInt(cursor
					.getString(cursor.getColumnIndexOrThrow("rid"))));
			restaurant.setFavorNote(cursor.getString(cursor.getColumnIndexOrThrow("favornote")));
			restaurant.setRestanrantAddr(cursor.getString(cursor.getColumnIndexOrThrow("raddr")));
			restaurant.setRestaurantIntro(cursor.getString(cursor.getColumnIndexOrThrow("rintro")));
			restaurant.setRestaurantLng(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlng"))));
			restaurant.setRestaurantLat(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlat"))));
			restaurant.setRestaurantLogoPhoto(cursor.getString(cursor.getColumnIndexOrThrow("rlp")));
			restaurant.setRestaurantName(cursor.getString(cursor.getColumnIndexOrThrow("rname")));
			restaurant.setRestaurantPhone(cursor.getString(cursor.getColumnIndexOrThrow("rphone")));
			restaurant.setRestaurantPhotos(cursor.getString(cursor.getColumnIndexOrThrow("rphotos")));
			System.out.println(restaurant.getRestanrantAddr());
			rList.add(restaurant);
			
		}while(cursor.moveToNext());
		System.out.println(rList.size());
		return rList;
		
	}

	public Restaurant getById(int rid) {
		SQLiteDatabase rdb=rdbAdapter.getDb();
		Cursor cursor= rdb.query(tablename, null, "rid=?", new String[]{String.valueOf(rid)}, null, null, null);
		if(!cursor.moveToFirst())
			return null;
		Restaurant restaurant=new Restaurant();
		do{
			restaurant.setRestaurantID(Integer.parseInt(cursor
					.getString(cursor.getColumnIndexOrThrow("rid"))));
			restaurant.setFavorNote(cursor.getString(cursor.getColumnIndexOrThrow("favornote")));
			restaurant.setRestanrantAddr(cursor.getString(cursor.getColumnIndexOrThrow("raddr")));
			restaurant.setRestaurantIntro(cursor.getString(cursor.getColumnIndexOrThrow("rintro")));
			restaurant.setRestaurantLng(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlng"))));
			restaurant.setRestaurantLat(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlat"))));
			restaurant.setRestaurantLogoPhoto(cursor.getString(cursor.getColumnIndexOrThrow("rlp")));
			restaurant.setRestaurantName(cursor.getString(cursor.getColumnIndexOrThrow("rname")));
			restaurant.setRestaurantPhone(cursor.getString(cursor.getColumnIndexOrThrow("rphone")));
			restaurant.setRestaurantPhotos(cursor.getString(cursor.getColumnIndexOrThrow("rphotos")));
		}while(cursor.moveToNext());
		return restaurant;

	}
	public ArrayList<Restaurant> getByName(String name) {
		SQLiteDatabase rdb=rdbAdapter.getDb();
		Cursor cursor= rdb.query(tablename, null, "rname like ?", new String[]{"%"+name+"%"}, null, null, null);
		if(!cursor.moveToFirst())
			return null;
		ArrayList<Restaurant> rList=new ArrayList<Restaurant>();
		Restaurant restaurant=new Restaurant();
		do{
			restaurant.setRestaurantID(Integer.parseInt(cursor
					.getString(cursor.getColumnIndexOrThrow("rid"))));
			restaurant.setFavorNote(cursor.getString(cursor.getColumnIndexOrThrow("favornote")));
			restaurant.setRestanrantAddr(cursor.getString(cursor.getColumnIndexOrThrow("raddr")));
			restaurant.setRestaurantIntro(cursor.getString(cursor.getColumnIndexOrThrow("rintro")));
			restaurant.setRestaurantLng(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlng"))));
			restaurant.setRestaurantLat(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlat"))));
			restaurant.setRestaurantLogoPhoto(cursor.getString(cursor.getColumnIndexOrThrow("rlp")));
			restaurant.setRestaurantName(cursor.getString(cursor.getColumnIndexOrThrow("rname")));
			restaurant.setRestaurantPhone(cursor.getString(cursor.getColumnIndexOrThrow("rphone")));
			restaurant.setRestaurantPhotos(cursor.getString(cursor.getColumnIndexOrThrow("rphotos")));
			System.out.println(restaurant.getRestaurantName());
			rList.add(restaurant);
			
		}while(cursor.moveToNext());
		return rList;

	}
	public ArrayList<Restaurant> getByAddr(String addr) {
		SQLiteDatabase rdb=rdbAdapter.getDb();
//		Cursor cursor= rdb.query(tablename, null, "raddr like '%?%'", new String[]{addr}, null, null, null);
		//以上会出现 bind or column index out of range problem
		
		Cursor cursor= rdb.query(tablename, null, "raddr like ?", new String[]{"%"+addr+"%"}, null, null, null);
		if(!cursor.moveToFirst())
			return null;
		ArrayList<Restaurant> rList=new ArrayList<Restaurant>();
		Restaurant restaurant=new Restaurant();
		do{
			restaurant.setRestaurantID(Integer.parseInt(cursor
					.getString(cursor.getColumnIndexOrThrow("rid"))));
			restaurant.setFavorNote(cursor.getString(cursor.getColumnIndexOrThrow("favornote")));
			restaurant.setRestanrantAddr(cursor.getString(cursor.getColumnIndexOrThrow("raddr")));
			restaurant.setRestaurantIntro(cursor.getString(cursor.getColumnIndexOrThrow("rintro")));
			restaurant.setRestaurantLng(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlng"))));
			restaurant.setRestaurantLat(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("rlat"))));
			restaurant.setRestaurantLogoPhoto(cursor.getString(cursor.getColumnIndexOrThrow("rlp")));
			restaurant.setRestaurantName(cursor.getString(cursor.getColumnIndexOrThrow("rname")));
			restaurant.setRestaurantPhone(cursor.getString(cursor.getColumnIndexOrThrow("rphone")));
			restaurant.setRestaurantPhotos(cursor.getString(cursor.getColumnIndexOrThrow("rphotos")));
			//System.out.println(restaurant.getRestanrantAddr());
			rList.add(restaurant);
			
		}while(cursor.moveToNext());
		return rList;

	}
    public boolean deleteById(int rid) {
    	try {
			SQLiteDatabase rdb=rdbAdapter.getDb();
			rdb.delete(tablename, "id=?", new String[]{String.valueOf(rid)});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
	}
    public boolean deleteAll() {
    	try {
			SQLiteDatabase rdb=rdbAdapter.getDb();
			rdb.delete(tablename, "1", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
	}
    
	
}
