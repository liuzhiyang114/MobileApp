package com.smartlab.mobileapp.activity;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.mapapi.MapActivity;
import com.smartlab.mobileapp.MainActivity;
import com.smartlab.mobileapp.R;
import com.smartlab.mobileapp.R.layout;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteBook extends ListActivity  implements OnClickListener{

	Intent intent;
	Bundle bundled;
	Boolean flagbook;
	String parkname;
	Button delete;
	//获取屏幕大小，以合理设定 按钮 大小及位置
	  DisplayMetrics dm = new DisplayMetrics();
	  int width = dm.widthPixels;
	  int height = dm.heightPixels;
	  
	  
	  private List<Map<String, Object>> mData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.deletebooklist);
		 	mData = getData();
	        MyAdapter adapter = new MyAdapter(this);
	        setListAdapter(adapter);
		
		
		//Bundle
				intent=this.getIntent();
				bundled=intent.getExtras();
				flagbook=bundled.getBoolean("flag");
				parkname=bundled.getString("parkname");
//				Toast.makeText(getApplicationContext(), parkname,
//		   			     Toast.LENGTH_SHORT).show();
				

				//addWegit();
				
				
	}
	
	 private List<Map<String, Object>> getData() {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("parkna", "北京市万达停车场");
	        map.put("chewei", "B区76号");
	        map.put("booktim", "9时-11时");
	        list.add(map);
	 
	        map = new HashMap<String, Object>();
	        map.put("parkna", "北京市大腕停车场");
	        map.put("chewei", "A区45号");
	        map.put("booktim", "13时-15时");
	        list.add(map);
	 
	        map = new HashMap<String, Object>();
	        map.put("parkna", "北京市万小达停车场");
	        map.put("chewei", "D区48号");
	        map.put("booktim", "17时-18时");
	        list.add(map);
	         
	        return list;
	    }
	     
	// ListView 中某项被选中后的逻辑
	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	         
	        Log.v("MyListView4-click", (String)mData.get(position).get("parkna"));
	    }
	    /**
	     * listview中点击按键弹出对话框
	     */
	    public void showInfo(){
	        new AlertDialog.Builder(this)
	        .setTitle("收费")
	        .setMessage("收费是很贵滴~~")
	        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            }
	        })
	        .show();
	         
	    }
	    public class MyAdapter extends BaseAdapter{
			 
	        private LayoutInflater mInflater;
	         
	        public MyAdapter(Context context){
	            this.mInflater = LayoutInflater.from(context);
	        }
	        @Override
	        public int getCount() {
	            // TODO Auto-generated method stub
	            return mData.size();
	        }
	        @Override
	        public Object getItem(int arg0) {
	            // TODO Auto-generated method stub
	            return null;
	        }
	        @Override
	        public long getItemId(int arg0) {
	            // TODO Auto-generated method stub
	            return 0;
	        }
	        public final class ViewHolder{
	            public TextView parkna;
	            public TextView chewei;
	            public TextView booktim;
	            public Button btndelete;
	        }
	 @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	             
	            ViewHolder holder = null;
	            if (convertView == null) {
	                 
	                holder=new ViewHolder();  
	                 
	                convertView = mInflater.inflate(R.layout.deletebooklist, null);
	                holder.btndelete = (Button)convertView.findViewById(R.id.btndelete);
	                holder.parkna = (TextView)convertView.findViewById(R.id.parkna);
	                holder.chewei = (TextView)convertView.findViewById(R.id.chewei);
	                holder.booktim = (TextView)convertView.findViewById(R.id.booktim);
	              
	                convertView.setTag(holder);
	                 
	            }else {
	                 
	                holder = (ViewHolder)convertView.getTag();
	            }
	             
	             
	            holder.parkna.setText((String)mData.get(position).get("parkna"));
	            holder.chewei.setText((String)mData.get(position).get("chewei"));
	            holder.booktim.setText((String)mData.get(position).get("booktim"));
	            holder.btndelete.setOnClickListener(new View.OnClickListener() {
	                 
	                @Override
	                public void onClick(View v) {
	                    showInfo();                 
	                }
	            });
	             
	             
	            return convertView;
	        }
	         
	    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
//			case R.id.btndelete:
//				
//		    	break;
		    default:
//		    	this.finish();
//				overridePendingTransition(R.anim.out_right_left,R.anim.in_left_right);
				break;
		}
	}

	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			this.finish();
			overridePendingTransition(R.anim.out_right_left,R.anim.in_left_right);
		}
		
		return super.onKeyDown(keyCode, event);
		
	}
	 
	
	//动态添加表格
//	  public void addWegit() { 
//	        TableLayout table = (TableLayout) findViewById(R.id.tablelayout);
//	        table.setStretchAllColumns(true);
//	      //创建Button
//	        int leng=2;
//			Button Btn[] = new Button[10];
//            for  (int r=0; r<leng; r++){    
//                  Btn[r]=new Button(this);
//                  Btn[r].setId(0+r); 
//                  Btn[r].setText("取消");
//                  Btn[r].setOnClickListener(this); 
//            }
//
//	        for (int i = 0; i < leng; i++) { 
//	            TableRow tablerow = new TableRow(DeleteBook.this); 
//	            tablerow.setBackgroundColor(Color.rgb(222, 220, 210)); 
//	            table.addView(tablerow, new TableLayout.LayoutParams( 
//    	                  LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//	            for (int j = 0; j < 4; j++) { 
//	 
//	                if ( j == 0) { 
//	                    
//	                	tablerow.addView(Btn[i]);
//	                } 
//	                    
//	                else if(j==1){
//	                	TextView testview = new TextView(DeleteBook.this); 
//	                    testview.setBackgroundResource(R.drawable.shape); 
//	                    testview.setText("北京市"); 
//	                    testview.setTextSize(18f);
//	                    testview.setTextColor(Color.BLACK);
//	                    //testview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT ));
//	                    tablerow.addView(testview);
//	                }
//	                else if(j==2){
//	                	TextView testview = new TextView(DeleteBook.this); 
//	                    testview.setBackgroundResource(R.drawable.shape); 
//	                    testview.setText("万达停车场"); 
//	                    testview.setTextSize(18f);
//	                    testview.setTextColor(Color.BLACK);
//	                    testview.setGravity(Gravity.CENTER);
//	                    tablerow.addView(testview);
//	                }
//	                else{
//	                	TextView testview = new TextView(DeleteBook.this); 
//	                    testview.setBackgroundResource(R.drawable.shape);
//	                    testview.setTextSize(18f);
//	                    testview.setTextColor(Color.BLACK);
//	                    testview.setText("13时-15时"); 
//	                    tablerow.addView(testview);
//	                }
//	                
//	                
//	                
//	                 }
//	      	  	 
//	       }
//	 
//	             
//
//	  }
	
	
	
	
	//动态添加表格2
	
}
	    
	 



