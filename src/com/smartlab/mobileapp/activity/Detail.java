package com.smartlab.mobileapp.activity;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.baidu.mapapi.MapActivity;
import com.smartlab.mobileapp.R;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Detail extends MapActivity implements OnClickListener{

	//演示图片
	String imageUrl = "http://www.2cto.com/uploadfile/2012/0317/20120317094731393.jpg"; 
	Bitmap bmImg;   
	ImageView	parkimage;
	
	
	Button btnre;
	TextView residue;
	TextView name;
	TextView count;
	TextView ParkDetail;
	TextView booktext;
	Button   download;
	Button	 Book;
	Button	Delete;
	
	
	String strname;
    String strcount;
    String strresidue;
    String strParkDetail;
    Boolean flagbook=true;
	String sendmsg ="{" +   
		    "   \"action\" : \"getGPS\"," +  
		    "   \"success\" : \"true\"," + 
		    "   \"cardNO\" : \"1234567\"," +  
		    "   \"lat\" : \"38.61748660991222\"," + 
		    "   \"lon\" : \"104.12037670612494\"," +  
		    "   \"parkName\" : \"北京市万达停车场\"," +  
		    "   \"parkamount\" : \"1000\"," +  
		    "   \"restamount\" : \"25\"," +  
		    "   \"parkinfo\" : \"每小时收费情况说明\"" +  
		"}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		btnre = (Button)findViewById(R.id.btnre);
		btnre.setOnClickListener(this);
		residue =(TextView)findViewById(R.id.residue);//剩余停车场位
		name =(TextView)findViewById(R.id.name);//停车场名
		count =(TextView)findViewById(R.id.count);//总停车位
		ParkDetail = (TextView)findViewById(R.id.ParkDetail);//停车场详细信息
		booktext= (TextView)findViewById(R.id.booktext);//预定信息
		parkimage	=(ImageView)findViewById(R.id.imagepark);//park图片
		//parkimage.setImageBitmap(returnBitMap(imageUrl)); 
		download=(Button)findViewById(R.id.parkimage);//加载图片
		download.setOnClickListener(this);
		Book=(Button)findViewById(R.id.btnbook);//预定
		Book.setOnClickListener(this);
		Delete=(Button)findViewById(R.id.btndelete);//取消预定
		Delete.setOnClickListener(this);
		


		//设置剩余车位数字粗体
//		residue = (EditText)findViewById(R.id.residue) ;
//		TextPaint paint = residue.getPaint();  
//		paint.setFakeBoldText(true); 
	
		jjson();	
		
	}
	
	//转换图片
	private Bitmap returnBitMap(String url) {
		// TODO Auto-generated method stub
		URL myFileUrl = null;   
		Bitmap bitmap = null;   
		try {   
		myFileUrl = new URL(url);   
		} catch (MalformedURLException e) {   
		e.printStackTrace();   
		}   
		try {   
		HttpURLConnection conn = (HttpURLConnection) 
		myFileUrl.openConnection();   
		conn.setDoInput(true);   
		conn.connect();   
		InputStream is = conn.getInputStream();   
		bitmap = BitmapFactory.decodeStream(is);   
		is.close();   
		} catch (IOException e) {   
		e.printStackTrace();   
		}   
		return bitmap;   
	}
	//JSON解析
	private void  jjson(){
		try{  
		    JSONTokener jsonParser = new JSONTokener(sendmsg);  
		    // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。  
		    // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）  
		    JSONObject detail = (JSONObject) jsonParser.nextValue();  
		   
		    // 接下来的就是JSON对象的操作了  
		    detail.getString("action");
		    detail.getString("success");
		    detail.getString("cardNO");
		    detail.getString("lat");
		    detail.getString("lon");
		    strname=detail.getString("parkName");
		    strcount=detail.getString("parkamount");
		    strresidue=detail.getString("restamount");
		    strParkDetail=detail.getString("parkinfo");
		    //修改Activity
		    name.setText(strname);//停车场名
		    count.setText(strcount);//总停车位
		    residue.setText(strresidue);//剩余停车场位
		    ParkDetail.setText(strParkDetail);//停车场详细信息
		} catch (JSONException ex) {  
		    // 异常处理代码  
			Toast.makeText(getApplicationContext(), "获取数据失败！",Toast.LENGTH_SHORT).show();
			this.finish();
		}  
		
	
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.parkimage://图片加载
			parkimage.setImageBitmap(returnBitMap(imageUrl)); //加载网络图片
			
			
			break;  
		case R.id.btnbook://车位预定
				Intent intent=new Intent(Detail.this,BookPark.class);
				//参数传递
				Bundle bundle=new Bundle();
				bundle.putString("parkname", strname);
				bundle.putBoolean("flag", flagbook);
				
				intent.putExtras(bundle);
				
				startActivityForResult(intent, 0);
				overridePendingTransition(R.anim.in_right_left,R.anim.out_left_right); 
			break;
			
		case R.id.btndelete://取消预定
			
			Intent intentd=new Intent(Detail.this,DeleteBook.class);
			//参数传递
			Bundle bundled=new Bundle();
			bundled.putString("parkname", strname);
			bundled.putBoolean("flag", flagbook);
			
			intentd.putExtras(bundled);
			
			startActivity(intentd);
			//startActivity(intentd);
			overridePendingTransition(R.anim.in_right_left,R.anim.out_left_right); 
			
		default:
			this.finish();
			overridePendingTransition(R.anim.in_right_left,R.anim.out_left_right); 
			break;
		}
	}
	

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
		case RESULT_OK:
			Bundle bund=data.getExtras();  //data为B中回传的Intent
			String str=bund.getString("str1");//str即为回传的值
			flagbook=bund.getBoolean("flagbook");
			booktext.setText("您已预定停车位，信息如下：\n"+strname+"	A区43号");
                      break;
 
		default:
	           break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			this.finish();
			overridePendingTransition(R.anim.in_right_left,R.anim.out_left_right);
		}
		return super.onKeyDown(keyCode, event);
	}
}
