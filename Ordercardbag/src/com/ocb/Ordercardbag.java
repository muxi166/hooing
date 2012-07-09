package com.ocb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

import com.ocb.db.DBUtil;
import com.ocb.db.RDBAdapter;
import com.ocb.db.RestaurantDAO;
import com.ocb.pojo.Restaurant;
import com.ocb.util.Http;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class Ordercardbag extends Activity {
    /** Called when the activity is first created. */
	private ProgressDialog myDialog = null; 
	 
    private String fileName;  
    private String message;
    Http http=new Http();
    Bitmap bitmap; 
    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 /** 
         * 设置窗口无标题栏,一定要在setContentView前进行设置哦 
         */  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //SQLiteDatabase db=this.openOrCreateDatabase("ordercard.db", MODE_APPEND, null);
        DBUtil dbUtil=new DBUtil(getApplicationContext());
        RDBAdapter rdbAdapter=new RDBAdapter();
        RestaurantDAO rDAO=new RestaurantDAO();
        rDAO.setRdbAdapter(rdbAdapter);
        rdbAdapter.setDb(dbUtil.getWritableDatabase());
//        Restaurant rt=new Restaurant();
//        rt.setRestanrantAddr("bupt");
//        rt.setRestaurantName("尚餐厅");
//        rt.setRestaurantIntro("湘菜馆");
//        rDAO.save(rt);

        //rDAO.deleteAll();
        ArrayList<Restaurant> rsRestaurant=rDAO.getByName("尚");
     //   Toast.makeText(getBaseContext(), rsRestaurant.get(0).getRestaurantName(), Toast.LENGTH_SHORT);
        AlertDialog.Builder builder=new AlertDialog.Builder(this).setMessage(rsRestaurant.get(0).getRestaurantName()).setTitle("rs").setPositiveButton("OK", null);
        builder.show();
//        
      // rDAO.createTable();
//        String fileUrl = "http://pic.yesky.com/imagelist/09/01/11277904_7147.jpg";  
//        http.setFilePath("/sdcard/"+"ok/");
//        myDialog = ProgressDialog.show(Ordercardbag.this, "下载数据", "数据正在下载中，请稍等...",true); 
//       new Thread(httpRunnable).start();
        //DBUtil dbUtil=new DBUtil(this);
     
       


//        rDAO.setRdbAdapter(rdbAdapter);
//       // rDAO.createTable();
//        
////        rDAO.setDbUtil(dbUtil);
//        Restaurant rt=new Restaurant();
//        rt.setRestanrantAddr("bupt");
//        rt.setRestaurantName("学尚餐厅");
//        rt.setRestaurantIntro("湘菜馆");
//        rDAO.save(rt);
//        ArrayList<Restaurant> rList=rDAO.getAll();
//        System.out.println(rList.size());
        //System.out.println("");
        
       
    }
    private Runnable httpRunnable=new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
	        http.saveFile(http.getFileSourceURL());
		    messageHandler.sendMessage(messageHandler.obtainMessage());
		}};
    private Handler messageHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            myDialog.dismiss();  
            //System.out.println("File exits: "+new File(http.getFilePath() + http.getFileName()).exists());
            Bitmap bitmap=BitmapFactory.decodeFile(http.getFilePath()+http.getFileName());
            ImageView imageView=(ImageView)findViewById(R.id.ImageView01);
            if(bitmap!=null)
              imageView.setImageBitmap(bitmap);
        }  
    };
}