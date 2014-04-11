package com.xg.timetable;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Adapter;
public class MyAdapter {

	private Context context;
	private TimetableMain main;
	private Cursor[] cursor=new Cursor[7];
	private SimpleCursorAdapter[] adapter;
	
	private SharedPreferences preferences;
	
	public MyAdapter(Context context){
		this.context=context;
		main=(TimetableMain) context;
	}
	public void test(){
	
	
			
	}
	
}
