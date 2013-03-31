package com.smartlab.mobileapp.activity;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.TimePickerDialog.OnTimeSetListener;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.mapapi.MapActivity;
import com.smartlab.mobileapp.R;
import com.smartlab.mobileapp.JSONtool.JSONObject;
import com.smartlab.mobileapp.connection.MobileClientApp;


public class BookPark extends MapActivity implements OnClickListener,Runnable{
	
	Button BookPark;
	Button back;
	String parkname;
	Intent intent;
	Bundle bundl;
	Boolean flagbook;
	String rev;
	String revretu;
	int betime;
	int entime;
	int bemin;
	int enmin;
	
	//输入时间
	Button begintime;
	Button endtime;
	TextView begin;
	TextView end;
	private ProgressDialog d;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookpark);
		BookPark=(Button)findViewById(R.id.btnbookp);
		BookPark.setOnClickListener(this);
		back=(Button)findViewById(R.id.btnback);
		back.setOnClickListener(this);
		begintime=(Button)findViewById(R.id.beginlog);
		begintime.setOnClickListener(this);
		endtime=(Button)findViewById(R.id.endlog);
		endtime.setOnClickListener(this);
		begin=(TextView)findViewById(R.id.begin);
		end=(TextView)findViewById(R.id.end);
		//Bundle
		intent=this.getIntent();
		bundl=intent.getExtras();
		flagbook=bundl.getBoolean("flag");
		parkname=bundl.getString("parkname");
		revretu=bundl.getString("revreturn");
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnbookp:
			Bookpark();
			break;  

		case R.id.beginlog:
			begintimeDialog();
			break;
			
		case R.id.endlog:
			endtimeDialog();
			break;
			
		default:
			this.finish();
			overridePendingTransition(R.anim.out_right_left,R.anim.in_left_right);
			break;
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
private void Bookpark(){
			
		if(entime>=betime&&enmin>=enmin){
			LayoutInflater layoutInflater = LayoutInflater.from(this);
			View viewaffirm = layoutInflater.inflate(R.layout.affirmid, null);
			 
			new AlertDialog.Builder(this).setTitle("请输入密码").setView(
					viewaffirm ).setPositiveButton("确定",
			       new DialogInterface.OnClickListener() {
			           @Override
			           public void onClick(DialogInterface dialog, int which) {
			        	   
			        	   //显示预定中
			        	   d=new ProgressDialog(BookPark.this);
			        	   d.setMessage("预定中......");
			        	   d.closeOptionsMenu();
			        	   d.setCancelable(false);//显示时禁止触摸屏幕
			        	   d.show();
			        	   
			        	   
			        	   Thread thread=new Thread(BookPark.this);
			        	   thread.start();
			
			           }
			       }).setNegativeButton("取消",
			       new DialogInterface.OnClickListener() {
			           @Override
			           public void onClick(DialogInterface dialog, int which) {
			                return;
			           }
			       }).show();
			}
		else{
			new AlertDialog.Builder(this)
	        //.setTitle("收费")
	        .setMessage("预定开始时间要早于结束时间哦！")
	        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            }
	        })
	        .show();
		}
	}
//取开始时间
	private void begintimeDialog(){
        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog time = new TimePickerDialog(BookPark.this, new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                begin.setText("预定开始时间为:" + hourOfDay+"点"+minute+"分");
                betime=hourOfDay;
                bemin=minute;
            }
        }, hourOfDay, minute, true);
        time.show();
    }

	//取结束时间
	private void endtimeDialog(){
        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog time = new TimePickerDialog(BookPark.this, new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                end.setText("预定结束时间为:" + hourOfDay+"点"+minute+"分");
                entime=hourOfDay;
                enmin=minute;
            }
        }, hourOfDay, minute, true);
        time.show();
    }
	private Handler handler=new Handler() {
		@Override
		public void handleMessage(Message msg)
		{
			d.dismiss();
			Toast.makeText(getApplicationContext(), revretu,
	   			     Toast.LENGTH_SHORT).show();
			//回传
      		flagbook=false;
      		Bundle bundle=new Bundle();
			bundle.putBoolean("flag", flagbook);
			bundle.putString("revreturn", revretu);
			intent.putExtras(bundle);
			
			
      		System.out.println(revretu);
        	BookPark.this.setResult(RESULT_OK, intent);
        	BookPark.this.finish();
		}
	};
	//进程
	@Override
	public void run(){
		// TODO Auto-generated method stub
		try {
			MobileClientApp mc = new MobileClientApp();
 
			revretu=mc.write("邓杰HELLO");
//			type=phone&action=reqOrder&params={"action":"reqOrder","ParkID":"123",'"BeginTime":"423","EndTime":"1","CardNo":"5"}
			for(int i=0;i<10;i++){
				Thread.sleep(1000);
				if(TextUtils.isEmpty(revretu)){
				}
				else{
					i=9;
				}
			}
			System.out.println(revretu);
			handler.sendEmptyMessage(0);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//封装预定JSON
	public static String setBook(String key,String value,String fullMsg){
		String res="";
		JSONObject jsonObj=new JSONObject(fullMsg);
		if(jsonObj.optString("action")!=""){
			jsonObj.remove("action");
			jsonObj.put("action", "reqOrder");
			res=jsonObj.toString();
		}else{
			jsonObj.put("action", "reqOrder");
			res=jsonObj.toString();
		}
		//卡号
		if(jsonObj.optString("CardNo")!=""){
			jsonObj.remove("CardNo");
			jsonObj.put("CardNo", "cardNO");
			res=jsonObj.toString();
		}else{
			jsonObj.put("CardNo", "cardNO");
			res=jsonObj.toString();
		}
		//停车场ID
		if(jsonObj.optString("ParkID")!=""){
			jsonObj.remove("ParkID");
			jsonObj.put("ParkID", "ParkID");
			res=jsonObj.toString();
		}else{
			jsonObj.put("ParkID", "ParkID");
			res=jsonObj.toString();
		}
		//开始时间
		if(jsonObj.optString("BeginTime")!=""){
			jsonObj.remove("BeginTime");
			jsonObj.put("BeginTime", "BeginTime");
			res=jsonObj.toString();
		}else{
			jsonObj.put("BeginTime", "BeginTime");
			res=jsonObj.toString();
		}
		//结束时间
		if(jsonObj.optString("EndTime")!=""){
			jsonObj.remove("EndTime");
			jsonObj.put("EndTime", "EndTime");
			res=jsonObj.toString();
		}else{
			jsonObj.put("EndTime", "EndTime");
			res=jsonObj.toString();
		}
		return res;
	}
//	type=phone&action=reqOrder&params={"action":"reqOrder","CardNo":"5","ParkID":"123",'"BeginTime":"423","EndTime":"1"}
	
	//解析json
	public static JSONObject getFirstVa(String fullMsg){
		JSONObject jsonObj=new JSONObject(fullMsg);
		//String value=jsonObj.optString(key);
		return jsonObj;
	}
	public static String getSecondVa(String key,String fullMsg){
		JSONObject jsontr=new JSONObject(getFirstVa(fullMsg));
		String value=jsontr.optString("action");
		String value1=jsontr.optString("action");
		String value2=jsontr.optString("action");
		return value;	
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			this.finish();
			overridePendingTransition(R.anim.out_right_left,R.anim.in_left_right);
		}
		return super.onKeyDown(keyCode, event);
	}

}
