package com.smartlab.mobileapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.MapActivity;
import com.smartlab.mobileapp.R;

public class BookPark extends MapActivity implements OnClickListener{
	
	Button BookPark;
	Button back;
	String parkname;
	Intent intent;
	Bundle bundl;
	Boolean flagbook;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookpark);
		BookPark=(Button)findViewById(R.id.btnbookp);
		BookPark.setOnClickListener(this);
		back=(Button)findViewById(R.id.btnback);
		back.setOnClickListener(this);
		//Bundle
		intent=this.getIntent();
		bundl=intent.getExtras();
		flagbook=bundl.getBoolean("flag");
		parkname=bundl.getString("parkname");
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnbookp:
			Bookpark();
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
			
	LayoutInflater layoutInflater = LayoutInflater.from(this);
	View viewaffirm = layoutInflater.inflate(R.layout.affirmid, null);
	 
	new AlertDialog.Builder(this).setTitle("请输入密码").setView(
			viewaffirm ).setPositiveButton("确定",
	       new DialogInterface.OnClickListener() {
	           @Override
	           public void onClick(DialogInterface dialog, int which) {
	        	   Toast.makeText(getApplicationContext(), "您已成功预定"+parkname,
			   			     Toast.LENGTH_SHORT).show();
	        	   //回传
	          		flagbook=false;
	            	BookPark.this.setResult(RESULT_OK, intent);
	            	BookPark.this.finish();
	           }
	       }).setNegativeButton("取消",
	       new DialogInterface.OnClickListener() {
	           @Override
	           public void onClick(DialogInterface dialog, int which) {
	                return;
	           }
	       }).show();
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
