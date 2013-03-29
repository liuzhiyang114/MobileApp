package com.smartlab.mobileapp.activity;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartlab.mobileapp.MainActivity;
import com.smartlab.mobileapp.R;
import com.zxing.activity.CaptureActivity;


public class InputCard extends Activity implements OnClickListener, Callback{

	Button opencamera;
	Button enter;
	public static String cardNO;
	TextView myresult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputcard);
		opencamera=(Button)findViewById(R.id.openc);
		opencamera.setOnClickListener(this);
		enter=(Button)findViewById(R.id.takep);
		enter.setOnClickListener(this);
		
		myresult=(TextView)findViewById(R.id.myresult);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.openc:
			entera();
			
			break;

		default:
			exita();  
			break;
		}
	}
	
	private void entera() {
		Intent intent=new Intent(InputCard.this,CaptureActivity.class);
					//Bundle bundleimage=new Bundle();
		            startActivityForResult(intent, 0);
		            overridePendingTransition(R.anim.splash_screen_fade, R.anim.splash_screen_hold);

		            

	}

	protected void onDraw(Canvas canvas){
		Paint mpaint1=new Paint();
	}
	
	
	private void exita() {
		//System.exit(0);
		if(TextUtils.isEmpty(myresult.getText().toString())){
			new AlertDialog.Builder(this)
	        //.setTitle("收费")
	        .setMessage("请扫描您的卡号！")
	        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            }
	        })
	        .show();
		}
		else{
			cardNO=myresult.getText().toString();
			Intent intent=new Intent(InputCard.this,MainActivity.class);
			startActivity(intent);
			this.finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			this.finish();
			//overridePendingTransition(R.anim.out_right_left,R.anim.in_left_right);
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			cardNO=scanResult;
			myresult.setText(scanResult);
			
			//System.out.println(scanResult);
		}
	}
}
