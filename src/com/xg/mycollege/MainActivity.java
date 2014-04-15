package com.xg.mycollege;

import java.util.Timer;
import java.util.TimerTask;
import com.xg.timetable.TimetableMain;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ˵���������еĶ�̬������Ҫͨ�����ַ�ʽʵ�֣�һ����ƽ�ƣ���һ����ͼƬ�л��γɶ�����
 * ���߿��Ը�����Ҫ��ͼƬ��ʾ��ʽ��ͼƬ�˶��켣���д���
 * ����ԭ����������ѣ���Ϊ��ѧ���ṩ˼·��ʵ�ַ�����
 * �����Լ������������������á���ҹ�ͬ������
 * @author ����
 * @qq 554188335
 * @time 2013.6.7	
 */
@SuppressLint("ResourceAsColor") public class MainActivity extends Activity {
	private RelativeLayout today_yubao; //����
    public static ForecastHandler forecastHandler ; //�������ݣ����½���
	//�л�ͼƬ��ʱ����handler��ͼƬ���
	public static Timer weather_timer;
	private Day_Lei_Handler day_lei_handler;
	private Night_Qing_Handler night_qing_handler;
	private Day_Rain_Handler Day_Rain_Handler;
	private Day_Snow_Handler Day_Snow_Handler;
	private Day_RainSnow_Handler Day_RainSnow_Handler;
	private Day_Wu_Handler Day_Wu_Handler;
	//ƽ�Ƶ�ͼƬ
	private MovingPictureView 
	w1_move1,w1_move2,w1_move3,w1_move4,w1_move5,
	w2_move1,w2_move2,w2_move3,w2_move4,w2_move5,
	w3_move1,w3_move2,w3_move3,w3_move4,w3_move5,
	w4_move1,w4_move2,w4_move3,w4_move4,w4_move5,
	w5_move1,w5_move2,w5_move3,w5_move4,w5_move5,
	w6_move1,w6_move2,w6_move3,w6_move4,w6_move5,
	w7_move1,w7_move2,w7_move3,w7_move4,w7_move5;
	private ImageView m1,m2,m3,m4,m5,m6,m7,m8,m9,m10;
	public static int imgIndex;
	//ƽ�Ƶ�ͼƬ���ڲ���
	private RelativeLayout weather_move1,weather_move2,weather_move3,weather_move4,weather_move5
	,weather_move6,weather_move7,weather_move8,weather_move9,weather_move10; 
	//�л���ͼƬ���ڲ���
	private RelativeLayout  weather_qing, weather_day_duoyun, weather_day_yin,weather_night_yin,
							weather_wu,weather_mai,weather_sha;
	
	private TextView content;
	//ʾ��
	private int nowindex=10; //��һ������Ĭ�������10
	private TextView nowweather;
	private LinearLayout forecast_shili_area;
	private Button kechengbiaobut;
	
	
    @SuppressLint("ResourceAsColor") @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //������Ŀ����
        setContentView(R.layout.main);
        kechengbiaobut = (Button)findViewById(R.id.kechengbiaobut);
        kechengbiaobut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,TimetableMain.class);
				startActivity(intent);
				
				// TODO Auto-generated method stub
				
			}
		});
        
        MovingPictureView.isRuning = true;
        today_yubao = (RelativeLayout)findViewById(R.id.today_yubao);
        content = (TextView)findViewById(R.id.content);
        forecast_shili_area = (LinearLayout)findViewById(R.id.forecast_shili_area);
        
		weather_qing = (RelativeLayout)findViewById(R.id.weather_qing);
		weather_day_duoyun = (RelativeLayout)findViewById(R.id.weather_day_duoyun);
		weather_day_yin = (RelativeLayout)findViewById(R.id.weather_day_yin);
		weather_night_yin = (RelativeLayout)findViewById(R.id.weather_night_yin);
		weather_wu = (RelativeLayout)findViewById(R.id.weather_wu);
		weather_mai = (RelativeLayout)findViewById(R.id.weather_mai);
		weather_sha = (RelativeLayout)findViewById(R.id.weather_sha);
		
		//������Ҫ�õ���ƽ�Ƶ�ͼƬ���غã����ݵ�ǰ������ѡ����ʾ��ЩͼƬ��
		//��������ֻΪweather_qing��weather_day_yin���������������ƽ��ͼƬ�������Ϊ������������������Ҫ��ͼƬ��Ȼ�����.
		w1_move1 = new MovingPictureView(this, R.drawable.yjjc_h_a3,-300,10,40);
		w1_move2 = new MovingPictureView(this, R.drawable.yjjc_h_a3,250,10,40);
		w1_move3 = new MovingPictureView(this, R.drawable.yjjc_h_a4,480,40,40);
		weather_qing.removeAllViews();
		weather_qing.addView(w1_move1);
		weather_qing.addView(w1_move2);
		weather_qing.addView(w1_move3);
		
		w3_move1 = new MovingPictureView(this, R.drawable.yjjc_h_d2,-250,0,30);
		w3_move2 = new MovingPictureView(this, R.drawable.yjjc_h_d3,180,60,40);
		weather_day_yin.addView(w3_move1);
		weather_day_yin.addView(w3_move2);
	
		weather_move1 = (RelativeLayout)findViewById(R.id.weather_move1);
		weather_move2 = (RelativeLayout)findViewById(R.id.weather_move2);
		weather_move3 = (RelativeLayout)findViewById(R.id.weather_move3);
		weather_move4 = (RelativeLayout)findViewById(R.id.weather_move4);
		weather_move5 = (RelativeLayout)findViewById(R.id.weather_move5);
		weather_move6 = (RelativeLayout)findViewById(R.id.weather_move6);
		weather_move7 = (RelativeLayout)findViewById(R.id.weather_move7);
		weather_move8 = (RelativeLayout)findViewById(R.id.weather_move8);
		weather_move9 = (RelativeLayout)findViewById(R.id.weather_move9);
		weather_move10 = (RelativeLayout)findViewById(R.id.weather_move10);
		m1 = (ImageView)findViewById(R.id.m1);
		m2 = (ImageView)findViewById(R.id.m2);
		m3 = (ImageView)findViewById(R.id.m3);
		m4 = (ImageView)findViewById(R.id.m4);
		m5 = (ImageView)findViewById(R.id.m5);
		m6 = (ImageView)findViewById(R.id.m6);
		m7 = (ImageView)findViewById(R.id.m7);
		m8 = (ImageView)findViewById(R.id.m8);
		m9 = (ImageView)findViewById(R.id.m9);
		m10 = (ImageView)findViewById(R.id.m10);
		day_lei_handler = new Day_Lei_Handler(this);
		night_qing_handler = new Night_Qing_Handler(this);
		Day_Rain_Handler = new Day_Rain_Handler(this);
		Day_Snow_Handler = new Day_Snow_Handler(this);
		Day_RainSnow_Handler = new Day_RainSnow_Handler(this);
		Day_Wu_Handler = new Day_Wu_Handler(this);
		
		forecastHandler = new ForecastHandler();
		//���ʾ��==================================
		nowweather = new TextView(this);
		nowweather.setTextColor(R.color.Myblack);
		nowweather.setText("����  ");
		nowweather.setTextSize(16);
		nowweather.setPadding(20, 0, 0, 0);
		nowweather.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		forecast_shili_area.addView(nowweather);
			
		TextView w1 = new TextView(this);
		w1.setTextColor(R.color.Myblack);
		w1.setText("<<-����л�ʾ��");
		w1.setTextSize(16);
		w1.setPadding(0, 0, 0, 0);
		w1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		forecast_shili_area.addView(w1);
		w1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	            Message msg = new Message();
	            Bundle b = new Bundle();
	            msg.what=nowindex;
	            msg.setData(b);
	            forecastHandler.sendMessage(msg);
				if(nowindex < 21){
					nowindex+=1;
				}else{
					nowindex=10;
				}
			}
		});
		//ʾ��==============================
		
		//������Ϣ����ʾĬ�ϵĵ�һ������ʾ��
		Message msg = new Message();
        Bundle b = new Bundle();
        msg.what=nowindex;
        msg.setData(b);
        forecastHandler.sendMessage(msg);
    }
	//����Ԥ������
    class ForecastHandler extends Handler {       
        //��������
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //ʾ�⴦��----------
            switch (msg.what) {
            case 10:
            	nowweather.setText("����");
            	day_qing();
				return;
			case 11:
				nowweather.setText("����");
				day_duoyun();
				return;					
			case 12:
				nowweather.setText("����");
				day_yin();
				return;
			case 13:
				nowweather.setText("ҹ����");
				night_yin();
				return;
			case 14:
				nowweather.setText("����");
				day_wu();
				return;
			case 15:
				nowweather.setText("����");
				day_mai();
				return;					
			case 16:
				nowweather.setText("��ɳ");
				day_sha();
				return;
			case 17:
				nowweather.setText("��ѩ");
				day_snow();
				return;
			case 18:
				nowweather.setText("�ꡤѩ");
				day_rainsnow();
				return;
			case 19:
				nowweather.setText("�׵�");
				day_lei();
				return;					
			case 20:
				nowweather.setText("ҹ����");
				night_qing();
				return;
			case 21:
				nowweather.setText("����");
				day_rain();
				return;				
			default:
				break;
			}
            //ʾ�⴦��----------
        }
    }
    
  //10����_��
    public void day_qing(){
		wordBlack();
		showweather("day_qing");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_a1);
		if(!w1_move1.isstarted){
			new Thread(w1_move1).start();//ÿһ���ƶ���ͼƬ����һ���߳�
			new Thread(w1_move2).start();
			new Thread(w1_move3).start();
		}
    } 
    
    //11����_����
    public void day_duoyun(){
    	wordBlack();
    	showweather("day_duoyun");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_c1);
		if(!w1_move1.isstarted){
			new Thread(w1_move1).start();//���������������һ��������ͼƬ��Ҳ���Ը����Լ���Ҫ����Ҫ��ͼƬ
			new Thread(w1_move2).start();
			new Thread(w1_move3).start();
		}
//		new Thread(w2_move1).start();
//		new Thread(w2_move2).start();
//		new Thread(w2_move3).start();
//		new Thread(w2_move4).start();
//		new Thread(w2_move5).start();	
    }
    //12����
    public void day_yin(){
    	wordWhite();
    	showweather("day_yin");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_d1);
		if(!w3_move1.isstarted){
			new Thread(w3_move1).start();
			new Thread(w3_move2).start();
		}
    }
    //13ҹ������
    public void night_yin(){
    	wordWhite();
    	showweather("night_yin");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_l1);
//		new Thread(w4_move1).start();
//		new Thread(w4_move2).start();
    }
    //14����
    public void day_wu(){
		wordBlack();
		showweather("day_wu");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_i1);
//		new Thread(w5_move1).start();
//		new Thread(w5_move2).start();
    }
    //15��
    public void day_mai(){
		wordBlack();
		showweather("day_mai");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_j1);
    }
    //16��ɳ
    public void day_sha(){
		wordBlack();
		showweather("day_sha");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_k1);
    }
    //17ѩ
    public void day_snow(){
    	wordBlack();
    	showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_g1);
		m1.setImageResource(R.drawable.yjjc_h_g2);
		m2.setImageResource(R.drawable.yjjc_h_g3);
		m3.setImageResource(R.drawable.yjjc_h_g4);
		m4.setImageResource(R.drawable.yjjc_h_g5);
    	Day_Snow_Timer chage = new Day_Snow_Timer();
 		Thread chageimg = new Thread(chage);
 		chageimg.start();
    }
    //18���ѩ
    public void day_rainsnow(){
    	wordWhite();
    	showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_h1);
		m1.setImageResource(R.drawable.yjjc_h_h2);
		m2.setImageResource(R.drawable.yjjc_h_h3);
		m3.setImageResource(R.drawable.yjjc_h_h4);
    	Day_RainSnow_Timer chage = new Day_RainSnow_Timer();
 		Thread chageimg = new Thread(chage);
 		chageimg.start();
    }
    
    //19����
    public void day_lei(){
    	wordWhite();
    	showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_f1);
		m1.setImageResource(R.drawable.yjjc_h_f2);
		m2.setImageResource(R.drawable.yjjc_h_f3);
		m3.setImageResource(R.drawable.yjjc_h_f4);
		m4.setImageResource(R.drawable.yjjc_h_f5);
		m5.setImageResource(R.drawable.yjjc_h_f6);
		m6.setImageResource(R.drawable.yjjc_h_f7);
		m7.setImageResource(R.drawable.yjjc_h_f8);
    	Day_Lei_Timer chage = new Day_Lei_Timer();
 		Thread chageimg = new Thread(chage);
 		chageimg.start();
    } 
    //20ҹ��_��
    public void night_qing(){
    	wordWhite();
    	showweather("other");
		today_yubao.setBackgroundResource(R.drawable.yjjc_h_b1);
		m1.setImageResource(R.drawable.yjjc_h_b2);
		m2.setImageResource(R.drawable.yjjc_h_b3);
		m3.setImageResource(R.drawable.yjjc_h_b4);
		m4.setImageResource(R.drawable.yjjc_h_b5);
		m5.setImageResource(R.drawable.yjjc_h_b6);
    	Night_Qing_Timer chage = new Night_Qing_Timer();
 		Thread chageimg = new Thread(chage);
 		chageimg.start();
    }
    //21��
    public void day_rain(){
    		wordWhite();
    		showweather("other");
			today_yubao.setBackgroundResource(R.drawable.yjjc_h_e1);
			m1.setImageResource(R.drawable.yjjc_h_e2);
			m2.setImageResource(R.drawable.yjjc_h_e3);
			m3.setImageResource(R.drawable.yjjc_h_e4);
			m4.setImageResource(R.drawable.yjjc_h_e5);
	 		Day_Rain_Timer chage = new Day_Rain_Timer();
	 		Thread chageimg = new Thread(chage);
	 		chageimg.start();
    }
    
    class Day_Rain_Timer implements Runnable{
		@Override
		public void run(){
			if(MainActivity.this.weather_timer != null){
				MainActivity.this.weather_timer.cancel();
			}
			MainActivity.this.weather_timer = new Timer();
            TimerTask t = new TimerTask() {
        		@Override
        		public void run() {
    	            if(MainActivity.imgIndex > 3){
    	            	MainActivity.imgIndex = 0;
    	            }
    	            Message msg = new Message();
    	            Bundle b = new Bundle();
    	            b.putString("index", String.valueOf(MainActivity.imgIndex));
    	            msg.setData(b);
    	            MainActivity.imgIndex += 1;
    	            MainActivity.this.Day_Rain_Handler.sendMessage(msg);
        		}
        	};
        	MainActivity.this.weather_timer.schedule(t, 0, 300); 
		}
	}
    
    class Night_Qing_Timer implements Runnable{
		@Override
		public void run(){
			if(MainActivity.this.weather_timer != null){
				MainActivity.this.weather_timer.cancel();
			}
			MainActivity.this.weather_timer = new Timer();
            TimerTask t = new TimerTask() {
        		@Override
        		public void run() {
    	            if(MainActivity.imgIndex > 4){
    	            	MainActivity.imgIndex = 0;
    	            }
    	            Message msg = new Message();
    	            Bundle b = new Bundle();
    	            b.putString("index", String.valueOf(MainActivity.imgIndex));
    	            msg.setData(b);
    	            MainActivity.imgIndex += 1;
    	            MainActivity.this.night_qing_handler.sendMessage(msg);
        		}
        	};
        	MainActivity.this.weather_timer.schedule(t, 0, 1*500);
		}
	}

    class Day_Wu_Timer implements Runnable{
		@Override
		public void run(){
			if(MainActivity.this.weather_timer != null){
				MainActivity.this.weather_timer.cancel();
			}
			MainActivity.this.weather_timer = new Timer();
            TimerTask t = new TimerTask() {
        		@Override
        		public void run() {
    	            if(MainActivity.imgIndex > 4){
    	            	MainActivity.imgIndex = 0;
    	            }
    	            Message msg = new Message();
    	            Bundle b = new Bundle();
    	            b.putString("index", String.valueOf(MainActivity.imgIndex));
    	            msg.setData(b);
    	            MainActivity.imgIndex += 1;
    	            MainActivity.this.Day_Wu_Handler.sendMessage(msg);
        		}
        	};
        	MainActivity.this.weather_timer.schedule(t, 0, 1*500);
		}
	}

	class Day_Lei_Timer implements Runnable{
		@Override
		public void run(){
			if(MainActivity.this.weather_timer != null){
				MainActivity.this.weather_timer.cancel();
			}
			MainActivity.this.weather_timer = new Timer();
            TimerTask t = new TimerTask() {
        		@Override
        		public void run() {
    	            if(MainActivity.imgIndex > 15){
    	            	MainActivity.imgIndex = 0;
    	            }
    	            Message msg = new Message();
    	            Bundle b = new Bundle();
    	            b.putString("index", String.valueOf(MainActivity.imgIndex));
    	            msg.setData(b);
    	            MainActivity.imgIndex += 1;
    	            MainActivity.this.day_lei_handler.sendMessage(msg);
        		}
        	};
        	MainActivity.this.weather_timer.schedule(t, 0, 1*200);
		}
	}

    class Day_Snow_Timer implements Runnable{
		@Override
		public void run(){
			if(MainActivity.this.weather_timer != null){
				MainActivity.this.weather_timer.cancel();
			}
			MainActivity.this.weather_timer = new Timer();
            TimerTask t = new TimerTask() {
        		@Override
        		public void run() {
    	            if(MainActivity.imgIndex > 3){
    	            	MainActivity.imgIndex = 0;
    	            }
    	            Message msg = new Message();
    	            Bundle b = new Bundle();
    	            b.putString("index", String.valueOf(MainActivity.imgIndex));
    	            msg.setData(b);
    	            MainActivity.imgIndex += 1;
    	            MainActivity.this.Day_Snow_Handler.sendMessage(msg);
        		}
        	};
        	MainActivity.this.weather_timer.schedule(t, 0, 300); 
		}
	}

    class Day_RainSnow_Timer implements Runnable{
		@Override
		public void run(){
			if(MainActivity.this.weather_timer != null){
				MainActivity.this.weather_timer.cancel();
				System.gc();
			}
			MainActivity.this.weather_timer = new Timer();
            TimerTask t = new TimerTask() {
        		@Override
        		public void run() {
    	            if(MainActivity.imgIndex > 2){
    	            	MainActivity.imgIndex = 0;
    	            }
    	            Message msg = new Message();
    	            Bundle b = new Bundle();
    	            b.putString("index", String.valueOf(MainActivity.imgIndex));
    	            msg.setData(b);
    	            MainActivity.imgIndex += 1;
    	            MainActivity.this.Day_RainSnow_Handler.sendMessage(msg);
        		}
        	};
        	MainActivity.this.weather_timer.schedule(t, 0, 300); 
		}
	}
    
    class Day_Snow_Handler extends Handler{
    	private Activity context;
        public Day_Snow_Handler() {
        }
        public Day_Snow_Handler(Activity context) {
        	this.context = context;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgindex = 20;
            Bundle b = msg.getData();
            if(b.getString("index")!=null){
            	msgindex = Integer.parseInt(b.getString("index"));
            }
            if(msgindex == 0){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }else if(msgindex == 1){
            	weather_move1.setVisibility(View.INVISIBLE);
            	weather_move2.setVisibility(View.VISIBLE);
            }else if(msgindex == 2){
            	weather_move2.setVisibility(View.INVISIBLE);
            	weather_move3.setVisibility(View.VISIBLE);
            }else if(msgindex == 3){
            	weather_move3.setVisibility(View.INVISIBLE);
            	weather_move4.setVisibility(View.VISIBLE);
            }else{// if(msgindex == 4){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }
        }
    }

    class Day_Wu_Handler extends Handler{
    	private Activity context;
        public Day_Wu_Handler() {
        }
        public Day_Wu_Handler(Activity context) {
        	this.context = context;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgindex = 20;
            Bundle b = msg.getData();
            if(b.getString("index")!=null){
            	msgindex = Integer.parseInt(b.getString("index"));
            }
            if(msgindex == 0){
            	weather_move5.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }else if(msgindex == 1){
            	weather_move1.setVisibility(View.INVISIBLE);
            	weather_move2.setVisibility(View.VISIBLE);
            }else if(msgindex == 2){
            	weather_move2.setVisibility(View.INVISIBLE);
            	weather_move3.setVisibility(View.VISIBLE);
            }else if(msgindex == 3){
            	weather_move3.setVisibility(View.INVISIBLE);
            	weather_move4.setVisibility(View.VISIBLE);
            }else if(msgindex == 4){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move5.setVisibility(View.VISIBLE);
            }else{
            	
            }
        }
    }

    class Day_RainSnow_Handler extends Handler{
    	private Activity context;
        public Day_RainSnow_Handler() {
        }
        public Day_RainSnow_Handler(Activity context) {
        	this.context = context;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgindex = 20;
            Bundle b = msg.getData();
            if(b.getString("index")!=null){
            	msgindex = Integer.parseInt(b.getString("index"));
            }
            if(msgindex == 0){
            	weather_move3.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }else if(msgindex == 1){
            	weather_move1.setVisibility(View.INVISIBLE);
            	weather_move2.setVisibility(View.VISIBLE);
            }else if(msgindex == 2){
            	weather_move2.setVisibility(View.INVISIBLE);
            	weather_move3.setVisibility(View.VISIBLE);
            }else{
            	
            }
        }
    }

    class Day_Rain_Handler extends Handler{
    	private Activity context;
        public Day_Rain_Handler() {
        }
        public Day_Rain_Handler(Activity context) {
        	this.context = context;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgindex = 20;
            Bundle b = msg.getData();
            if(b.getString("index")!=null){
            	msgindex = Integer.parseInt(b.getString("index"));
            }
            if(msgindex == 0){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }else if(msgindex == 1){
            	weather_move1.setVisibility(View.INVISIBLE);
            	weather_move2.setVisibility(View.VISIBLE);
            }else if(msgindex == 2){
            	weather_move2.setVisibility(View.INVISIBLE);
            	weather_move3.setVisibility(View.VISIBLE);
            }else if(msgindex == 3){
            	weather_move3.setVisibility(View.INVISIBLE);
            	weather_move4.setVisibility(View.VISIBLE);
            }else{// if(msgindex == 4){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }
        }
    }

    class Night_Qing_Handler extends Handler{
    	private Activity context;
        public Night_Qing_Handler() {
        }
        public Night_Qing_Handler(Activity context) {
        	this.context = context;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgindex = 20;
            Bundle b = msg.getData();
            if(b.getString("index")!=null){
            	msgindex = Integer.parseInt(b.getString("index"));
            }
            if(msgindex == 0){
            	weather_move5.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }else if(msgindex == 1){
            	weather_move1.setVisibility(View.INVISIBLE);
            	weather_move2.setVisibility(View.VISIBLE);
            }else if(msgindex == 2){
            	weather_move2.setVisibility(View.INVISIBLE);
            	weather_move3.setVisibility(View.VISIBLE);
            }else if(msgindex == 3){
            	weather_move3.setVisibility(View.INVISIBLE);
            	weather_move4.setVisibility(View.VISIBLE);
            }else if(msgindex == 4){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move5.setVisibility(View.VISIBLE);
            }else{	//if(msgindex == 5){
            	weather_move5.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }
        }
    }
	
    class Day_Lei_Handler extends Handler{
    	private Activity context;
        public Day_Lei_Handler() {
        }
        public Day_Lei_Handler(Activity context) {
        	this.context = context;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // ����UI
            int msgindex = 20;
            Bundle b = msg.getData();
            if(b.getString("index")!=null){
            	msgindex = Integer.parseInt(b.getString("index"));
            }
            if(msgindex == 0){
            	weather_move7.setVisibility(View.INVISIBLE);
            	weather_move1.setVisibility(View.VISIBLE);
            }else if(msgindex == 1){
            	weather_move1.setVisibility(View.INVISIBLE);
            	weather_move2.setVisibility(View.VISIBLE);
            }else if(msgindex == 2){
            	weather_move2.setVisibility(View.INVISIBLE);
            	weather_move3.setVisibility(View.VISIBLE);
            }else if(msgindex == 3){
            	weather_move3.setVisibility(View.INVISIBLE);
            	weather_move4.setVisibility(View.VISIBLE);
            }else if(msgindex == 4){
            	weather_move4.setVisibility(View.INVISIBLE);
            	weather_move5.setVisibility(View.VISIBLE);
            }else if(msgindex == 5){
            	weather_move5.setVisibility(View.INVISIBLE);
            	weather_move6.setVisibility(View.VISIBLE);
            }else if(msgindex == 6){
            	weather_move6.setVisibility(View.INVISIBLE);
            	weather_move7.setVisibility(View.VISIBLE);
            }else{
            	weather_move7.setVisibility(View.INVISIBLE);
            }
        }
    }
    
    //����
    public void wordBlack(){
    	int color = this.getResources().getColor(R.color.Myblack);
    	content.setTextColor(color);
    } 
    //����
    public void wordWhite(){
    	int color = this.getResources().getColor(R.color.MyWhite);
    	content.setTextColor(color);
    }
    
  //��ʾĳһ����,��ʾ֡����������other��
    public void showweather(String weather){
    	initWeatherLayout();
    	if(weather.equals("day_qing")){
    		weather_qing.setVisibility(View.VISIBLE);
    	}else if(weather.equals("day_duoyun")){
    		weather_qing.setVisibility(View.VISIBLE);//û��Ϊweather_day_duoyun���ͼƬ��������ʱ�õ���day_qing�����е�ͼƬ��������Լ���create�����м��أ�������Ϳ�����ʾ��
    		weather_day_duoyun.setVisibility(View.VISIBLE);
    	}else if(weather.equals("day_yin")){
    		weather_day_yin.setVisibility(View.VISIBLE);
    	}else if(weather.equals("night_yin")){
    		weather_night_yin.setVisibility(View.VISIBLE);
    	}else if(weather.equals("day_wu")){
    		weather_wu.setVisibility(View.VISIBLE);
    	}else if(weather.equals("day_mai")){
    		weather_mai.setVisibility(View.VISIBLE);
    	}else if(weather.equals("day_sha")){
    		weather_sha.setVisibility(View.VISIBLE);
    	}else{
    		
    	}
    }
    //��ʼ����������
    public void initWeatherLayout(){
		if(MainActivity.this.weather_timer != null){
			MainActivity.this.weather_timer.cancel();
		}
		weather_qing.setVisibility(View.INVISIBLE);
		weather_day_duoyun.setVisibility(View.INVISIBLE);
		weather_day_yin.setVisibility(View.INVISIBLE);
		weather_night_yin.setVisibility(View.INVISIBLE);
		weather_wu.setVisibility(View.INVISIBLE);
		weather_mai.setVisibility(View.INVISIBLE);
		weather_sha.setVisibility(View.INVISIBLE);
		weather_move1.setVisibility(View.INVISIBLE);
		weather_move2.setVisibility(View.INVISIBLE);
		weather_move3.setVisibility(View.INVISIBLE);
		weather_move4.setVisibility(View.INVISIBLE);
		weather_move5.setVisibility(View.INVISIBLE);
		weather_move6.setVisibility(View.INVISIBLE);
		weather_move7.setVisibility(View.INVISIBLE);
		weather_move8.setVisibility(View.INVISIBLE);
		weather_move9.setVisibility(View.INVISIBLE);
		weather_move10.setVisibility(View.INVISIBLE);
    }
}