package com.smartlab.mobileapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.smartlab.mobileapp.MainActivity;
import com.smartlab.mobileapp.R;


public class InputCard extends Activity implements OnClickListener{

	Button enterapp;
	Button exitapp;
	EditText idfirst;
	public String cardNO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputcard);
		enterapp=(Button)findViewById(R.id.enterfirst);
		enterapp.setOnClickListener(this);
		exitapp=(Button)findViewById(R.id.exitfirst);
		exitapp.setOnClickListener(this);
		idfirst=(EditText)findViewById(R.id.editidfirst);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.enterfirst:
			entera();
			break;

		default:
			exita();  
			break;
		}
	}
	
	private void entera() {
		if(TextUtils.isEmpty(idfirst.getText().toString())){
			showf();
		}
		else{
			cardNO=idfirst.getText().toString();
			Intent intent=new Intent(InputCard.this,MainActivity.class);
			startActivity(intent);
			this.finish();
		}
	}
	private void showf(){
        new AlertDialog.Builder(this)
        //.setTitle("")
        .setMessage("请输入卡号")
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        })
        .show();
         
    }
	
	private void exita() {
		System.exit(0);
	}
}
