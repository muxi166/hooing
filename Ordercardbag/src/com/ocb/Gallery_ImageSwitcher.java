package com.ocb;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class Gallery_ImageSwitcher extends Activity implements  ViewFactory {
    private ArrayList<Integer> imgList=new ArrayList<Integer>();//图像ID   

    int index = R.drawable.w05;    
    private int downX,upX;  
    private ImageSwitcher is;   
    private Gallery gallery;   

    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        /** 
         * 设置窗口无标题栏,一定要在setContentView前进行设置哦 
         */  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.mygallery);  
        //用反射机制来获取资源中的图片ID   
        Field[] fields = R.drawable.class.getDeclaredFields();   
        for (Field field : fields)   
        {   
            if (!"icon".equals(field.getName()))//除了icon之外的图片   
            {      
                int index = 0;   
                try {   
                    index = field.getInt(R.drawable.class);   
                } catch (IllegalArgumentException e) {   
                    // TODO Auto-generated catch block   
                    e.printStackTrace();   
                } catch (IllegalAccessException e) {   
                    // TODO Auto-generated catch block   
                    e.printStackTrace();   
                }   
                //保存图片ID   
                imgList.add(index);   
            }   
        } 
        System.out.println(imgList.size());
        //设置ImageSwitcher控件   
        is = (ImageSwitcher) findViewById(R.id.switcher);  
        
         is.setFactory(this);
         is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
         is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        is.setInAnimation(AnimationUtils.loadAnimation(this,   
                android.R.anim.fade_in));   
        is.setOutAnimation(AnimationUtils.loadAnimation(this,   
                android.R.anim.fade_out));   

        is.setOnTouchListener(new OnTouchListener(){   
            /*  
             * 在ImageSwitcher控件上滑动可以切换图片  
             */  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {   
                if(event.getAction()==MotionEvent.ACTION_DOWN)   
                {   
                    downX=(int) event.getX();//取得按下时的坐标   
                    return true;   
                }   
                else if(event.getAction()==MotionEvent.ACTION_UP)   
                {   
                    upX=(int) event.getX();//取得松开时的坐标   
                    int index=0;   
                    if(upX-downX>100)//从左拖到右，即看前一张   
                    {   
                        //如果是第一，则去到尾部   
                        if(gallery.getSelectedItemPosition()==0)   
                           index=gallery.getCount()-1;   
                        else  
                            index=gallery.getSelectedItemPosition()-1;   
                    }   
                    else if(downX-upX>100)//从右拖到左，即看后一张   
                    {   
                        //如果是最后，则去到第一   
                        if(gallery.getSelectedItemPosition()==(gallery.getCount()-1))   
                            index=0;   
                        else  
                            index=gallery.getSelectedItemPosition()+1;   
                    }   
                    //改变gallery图片所选，自动触发ImageSwitcher的setOnItemSelectedListener   
                    gallery.setSelection(index, true);   
                    return true;   
                }   
                return false;   
            }

               
        });   
           
        //设置gallery控件   
        gallery = (Gallery) findViewById(R.id.gallery);   
        gallery.setAdapter(new ImageAdapter(this));   
        gallery.setOnItemSelectedListener(new OnItemSelectedListener(){   
            @Override  
            public void onItemSelected(AdapterView<?> arg0, View arg1,   
                    int position, long arg3) { 
                is.setImageResource(imgList.get(position));   
            }   
            @Override  
            public void onNothingSelected(AdapterView<?> arg0) {   
                // TODO Auto-generated method stub   
            }   
               
        });   
    }   
    //设置ImgaeSwitcher   
    public View makeView() {   
        ImageView i = new ImageView(this);   
        i.setBackgroundColor(0xFF000000);   
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);//居中   
        i.setLayoutParams(new ImageSwitcher.LayoutParams(//自适应图片大小   
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));   
        return i;   
    }   
    public class ImageAdapter extends BaseAdapter {   
        public ImageAdapter(Context c) {   
            mContext = c;   
        }   
        public int getCount() {   
            return imgList.size();   
        }   
        public Object getItem(int position) {   
            return position;   
        }   
        public long getItemId(int position) {   
            return position;   
        }   
        public View getView(int position, View convertView, ViewGroup parent) {   
            ImageView i = new ImageView(mContext);   
            i.setScaleType(ImageView.ScaleType.FIT_XY);//居中   
            i.setImageResource(imgList.get(position));   
            i.setAdjustViewBounds(true);   
            i.setLayoutParams(new Gallery.LayoutParams(   
                    100, 100));   
            return i;   
        }   
        private Context mContext;   
    }
 


}
