package com.smartlab.mobileapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartlab.mobileapp.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import android.widget.TextView;
import android.widget.Toast;

public class DeleteBook extends ListActivity  implements OnClickListener{

	Intent intent;
	Bundle bundled;
	Boolean flagbook;
	String parkname;
	Button btnbac;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	int index ;
	 //ViewHolder holder = null;
	  private List<Map<String, Object>> mData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				setContentView(R.layout.deletebooklist);
			 	mData = getData();
		        MyAdapter adapter = new MyAdapter(this);
	        	setListAdapter(adapter);

	        	btnbac=(Button)findViewById(R.id.btnback);
	        	btnbac.setOnClickListener(this);
		
		//Bundle
				intent=this.getIntent();
				
				if(intent.getExtras()!=null){
					bundled=intent.getExtras();
					flagbook=bundled.getBoolean("flag");
					parkname=bundled.getString("parkname");
					
				}
				
//				Toast.makeText(getApplicationContext(), parkname,
//		   			     Toast.LENGTH_SHORT).show();
	}
	
	 private List<Map<String, Object>> getData() {
	        //List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 
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
	         
	        //System.out.println(list);
	        return list;
	    }
	     
//	// ListView 中某项被选中后的逻辑
//	    @Override
//	    protected void onListItemClick(ListView l, View v, int position, long id) {
//	         
//	        Log.v("", (String)mData.get(position).get("parkna"));
//	    }
	    /**
	     * listview中点击按键弹出对话框
	     */
	  
	    public class MyAdapter extends BaseAdapter{
			 
	        private LayoutInflater mInflater;
	         
	        public MyAdapter(Context context){
	            this.mInflater = LayoutInflater.from(context);
	        }
	        @Override
	        public int getCount() {
	            // TODO Auto-generated method stub
	        	//System.out.println(mData.size());
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
	        	public Button btndelete;
	            public TextView parkna;
	            public TextView chewei;
	            public TextView booktim;
	           
	        }
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	             
	            ViewHolder holder = null;
	            if (convertView ==null) {
	            	index =position;
	                holder=new ViewHolder();  
	                convertView = mInflater.inflate(R.layout.listview, null);
	                holder.btndelete = (Button)convertView.findViewById(R.id.btndelete);
	                holder.parkna = (TextView)convertView.findViewById(R.id.parkna);
	                holder.chewei = (TextView)convertView.findViewById(R.id.chewei);
	                holder.booktim = (TextView)convertView.findViewById(R.id.booktim);
	              
	                convertView.setTag(holder);
	                //System.out.println(position);
	                 
	            }else {
	                 
	                holder = (ViewHolder)convertView.getTag();
	            }
	             
	             
	           
	            holder.btndelete.setOnClickListener(new View.OnClickListener() {
	                 
	                @Override
	                public void onClick(View v) {
	                    //showInfo();   
	                    
	                	new AlertDialog.Builder(DeleteBook.this)
                        .setTitle("删除")
                        .setMessage("确定删除此预定？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        	 list.remove(index);
                        	 notifyDataSetChanged();

                            }        
                                      })                                                        
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int which) {
                        	  return;
                            }        
                        }).show();       
	                    
	                }
	            });
	            holder.parkna.setText((String)mData.get(position).get("parkna"));
	            holder.chewei.setText((String)mData.get(position).get("chewei"));
	            holder.booktim.setText((String)mData.get(position).get("booktim"));
	            
	            
	            return convertView;
	        }
	         
	    }
//	    public void showInfo(){
//	    	LayoutInflater layoutInflater = LayoutInflater.from(this);
//	    	View viewaffirm = layoutInflater.inflate(R.layout.affirmid, null);
//	    	 
//	    	new AlertDialog.Builder(this).setTitle("请输入密码").setView(
//	    			viewaffirm ).setPositiveButton("确定",
//	    	       new DialogInterface.OnClickListener() {
//	    	           @Override
//	    	           public void onClick(DialogInterface dialog, int which) {
//	    	        	   Toast.makeText(getApplicationContext(), "您已取消该预定",
//	    			   			     Toast.LENGTH_SHORT).show();
//	    	        	   //回传
//	    	           }
//	    	       }).setNegativeButton("取消",
//	    	       new DialogInterface.OnClickListener() {
//	    	           @Override
//	    	           public void onClick(DialogInterface dialog, int which) {
//	    	                return;
//	    	           }
//	    	       }).show();
//	         
//	    }	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.btnback:
				this.finish();
				overridePendingTransition(R.anim.out_right_left,R.anim.in_left_right);
		    	break;
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
	 
	
}
	    
	 



