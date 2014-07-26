package com.timetablealarm.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.model.AlarmTimeDB;
import com.model.AlarmTimeDBEntity;
import com.model.AttendDB;
import com.model.AttendDBEntity;
import com.model.DBHelper;
import com.model.SleepTimeDB;
import com.model.SleepTimeDBEntity;
import com.timetablealarm.MenuSelectActivity;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmMenuActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	private Button AlarmSetButton;
	private ImageButton SleepGPSButton;
	private ListView listView;
	
	private DBHelper dBHelper;
	private SQLiteDatabase db;
	private AttendDB dao;
	private SleepTimeDB dao2;
	private AlarmTimeDB dao3;
	
	private TextView AttendanceText;
	private TextView DelayText;
	private TextView AbsenceText;
	private final int MAXLENGTH = 60;
	
	List<AlarmTimeDBEntity> entity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_menu);
		

		
		this.listView = (ListView)findViewById(R.id.SetUpAlarmButtons);
		this.listView.setOnItemClickListener(this);
		
		AlarmSetButton = (Button)findViewById(R.id.AlarmSetButton);
		AlarmSetButton.setOnClickListener(this);
		SleepGPSButton = (ImageButton)findViewById(R.id.SleepGPSButton);
		SleepGPSButton.setOnClickListener(this);
		


		this.AbsenceText = (TextView)findViewById(R.id.AbsenceText);
		this.AttendanceText = (TextView)findViewById(R.id.AttendanceText);
		this.DelayText = (TextView)findViewById(R.id.DerayText);
		
	}
	
	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

//		GraphicalView graphicalView = TimeChartView();
//		LinearLayout layout = (LinearLayout)findViewById(R.id.GraphView);
//		layout.addView(graphicalView);
	}
	
	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
		

		dBHelper = new DBHelper(this);
		db = dBHelper.getReadableDatabase();
		dao = new AttendDB(this.db);
		dao2 = new SleepTimeDB(this.db);
		dao3 = new AlarmTimeDB(this.db);
		Log.d("statrt", "S");

		this.entity = dao3.findALL();
		this.listView.setAdapter(new ListArrayAdeapter());
		this.listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
		});
		

		List<AttendDBEntity> entityList = dao.findALL();
		this.AttendanceText.setText(entityList.get(0).getNum() + "日");
		Log.d("text",entityList.get(0).getNum() + "日");
		this.DelayText.setText(entityList.get(1).getNum() + "日");
		Log.d("text",entityList.get(1).getNum() + "日");
		this.AbsenceText.setText(entityList.get(2).getNum() + "日");
		Log.d("text",entityList.get(2).getNum() + "日");
		

		GraphicalView graphicalView = TimeChartView();
		LinearLayout layout = (LinearLayout)findViewById(R.id.GraphView);
		layout.addView(graphicalView);
		
	}
	
	
	private GraphicalView TimeChartView() 
    {
		
        List<SleepTimeDBEntity> entity;
        
        if(dao2.DataNum() == 0 ){

	        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	        renderer.setChartTitleTextSize(20);             //
	        renderer.setXTitle("日付");                     // X軸タイトル
	        renderer.setYTitle("睡眠時間");                 // Y軸タイトル
	        renderer.setAxisTitleTextSize(16);              // 
	        renderer.setLegendTextSize(15);                 // 凡例　テキストサイズ
	        renderer.setPointSize(5f);                      // ポイントマーカーサイズ
	        renderer.setYAxisMin(-5.0);                     // Y軸最小値
	        renderer.setYAxisMax(+15.0);                    // Y軸最大値
	        renderer.setXLabelsAlign(Align.CENTER);         // X軸ラベル配置位置
	        renderer.setYLabelsAlign(Align.RIGHT);          // Y軸ラベル配置位置
	        renderer.setAxesColor(Color.LTGRAY);            // X軸、Y軸カラー
	        renderer.setLabelsColor(Color.YELLOW);          // ラベルカラー
	        renderer.setXLabels(5);                         // X軸ラベルのおおよその数
	        renderer.setYLabels(5);                         // Y軸ラベルのおおよその数
	        renderer.setLabelsTextSize(15);                 // ラベルサイズ
	        // グリッド表示
	        renderer.setShowGrid(true);
	        // グリッド色
	        renderer.setGridColor(Color.parseColor("#00FFFF")); // グリッドカラーaqua
	        // グラフ描画領域マージン top, left, bottom, right
	        renderer.setMargins(new int[] { 30, 30, 15, 40 });  // 
	
	        // (3) データ系列の色、マーク等の設定
	        XYSeriesRenderer r = new XYSeriesRenderer();
	        r.setColor(Color.GREEN);
	        r.setPointStyle(PointStyle.CIRCLE);
	        r.setFillPoints(true);
	        renderer.addSeriesRenderer(r);                // Y軸タイトル
	        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	        TimeSeries series = new TimeSeries("");
	        dataset.addSeries(series);
	        GraphicalView mChartView=ChartFactory.getTimeChartView(this, dataset,  renderer, "MM/dd");
	        
	        return mChartView;
        } else {	
        	entity = dao2.findAll(MAXLENGTH);
	        // (1)グラフデータの準備
	        // x軸 時刻
	        String [] xStrValue;
	        // y軸 気温
	        double[] yDoubleValue;
	        
	        xStrValue = new String[entity.size() / 2];
	        yDoubleValue = new double[entity.size() / 2];
	        
	        long tmp = 0;
	        for(int i = entity.size() - 1, j = 0; i >= 0 && j < xStrValue.length; i--){
	        	if(entity.get(i).getFlag()){
	        		xStrValue[j] = entity.get(i).getYear() + "/" + String.format("%1$02d", entity.get(i).getMonth()) + "/" + String.format("%1$02d", entity.get(i).getDay());
	        		tmp = entity.get(i).getSleeptime();
	        	} else {
	        		yDoubleValue[j] = (entity.get(i).getSleeptime() - tmp) / 1000.0 / 60.0 / 60.0;
	        		tmp = 0;
		        	Log.d("test", yDoubleValue[j]  + "");
	        		j++;
	        	}
	        	Log.d("test", entity.get(i).getYear() + "/" + String.format("%1$02d", entity.get(i).getMonth()) + "/" + String.format("%1$02d", entity.get(i).getDay()));
	        	Log.d("test", entity.get(i).getSleeptime() + "");
	        }
	        
	        
	        
	        // 日付を文字形式から Date型へ変換
	        int DataCount=xStrValue.length;
	        Date[] xDateValue = new Date[DataCount];
	        for (int i = 0; i < DataCount; i++) 
	        {
	            xDateValue[i] =String2date(xStrValue[i]);
	        }
	        
	        // (2) グラフのタイトル、X軸Y軸ラベル、色等の設定
	        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	        renderer.setChartTitleTextSize(20);             //
	        renderer.setXTitle("日付");                     // X軸タイトル
	        renderer.setYTitle("睡眠時間");                 // Y軸タイトル
	        renderer.setAxisTitleTextSize(16);              // 
	        renderer.setLegendTextSize(15);                 // 凡例　テキストサイズ
	        renderer.setPointSize(5f);                      // ポイントマーカーサイズ
//	        renderer.getXAxisMin(xDateValue[0].getDate());  // X軸最小値
//	        if(xDateValue.length < MAXLENGTH / 2){
//		        renderer.setXAxisMax(xDateValue[xDateValue.length - 1].getDate());    // X軸最大値
//	        } else {
//	        renderer.setXAxisMax(xDateValue[MAXLENGTH / 2].getDate());    // X軸最大値
//	        }
	        renderer.setYAxisMin(-5.0);                     // Y軸最小値
	        renderer.setYAxisMax(+15.0);                    // Y軸最大値
	        renderer.setXLabelsAlign(Align.CENTER);         // X軸ラベル配置位置
	        renderer.setYLabelsAlign(Align.RIGHT);          // Y軸ラベル配置位置
	        renderer.setAxesColor(Color.LTGRAY);            // X軸、Y軸カラー
	        renderer.setLabelsColor(Color.YELLOW);          // ラベルカラー
	        renderer.setXLabels(5);                         // X軸ラベルのおおよその数
	        renderer.setYLabels(5);                         // Y軸ラベルのおおよその数
	        renderer.setLabelsTextSize(15);                 // ラベルサイズ
	        // グリッド表示
	        renderer.setShowGrid(true);
	        // グリッド色
	        renderer.setGridColor(Color.parseColor("#00FFFF")); // グリッドカラーaqua
	        // グラフ描画領域マージン top, left, bottom, right
	        renderer.setMargins(new int[] { 30, 30, 15, 40 });  // 
	
	        // (3) データ系列の色、マーク等の設定
	        XYSeriesRenderer r = new XYSeriesRenderer();
	        r.setColor(Color.GREEN);
	        r.setPointStyle(PointStyle.CIRCLE);
	        r.setFillPoints(true);
	        renderer.addSeriesRenderer(r);
	
	        // (4) データ系列　データの設定
	        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	        TimeSeries series = new TimeSeries("睡眠時間");     // データ系列凡例
	        for (int i = 0; i < DataCount; i++) 
	        {
	            series.add(xDateValue[i], yDoubleValue[i]);
	        }
	        dataset.addSeries(series);
	        
	        // (5)タイムチャートグラフの作成
	        GraphicalView mChartView=ChartFactory.getTimeChartView(this, dataset,  renderer, "MM/dd");
	        
	        return mChartView;
	    }
    }
    
    /*
     * 日付時刻文字列を Date型に変換
     */
    private Date String2date(String strDate) 
    {
        Date dateDate=null;
        // 日付文字列→date型変換フォーマットを指定して
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    
        try 
        {
            dateDate = sdf1.parse(strDate);
        } 
        catch (ParseException e) 
        {
            Log.e("日付変換エラー", "String2dateメソッドに引き渡された引数:" + strDate  );
        } catch (java.text.ParseException e) {
            Log.e("日付変換エラー", "String2dateメソッドに引き渡された引数:" + strDate  );
        }
        return dateDate;    
    }


	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.SleepGPSButton){
			Log.d("q","t");
			
			Intent intent = new Intent(AlarmMenuActivity.this, AlarmGPSSettingActivity.class);
			startActivity(intent);
			db.close();
		} 
		if(v == this.AlarmSetButton){

			Intent intent = new Intent(AlarmMenuActivity.this, AlarmSettingActivity.class);
			startActivity(intent);
			db.close();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自動生成されたメソッド・スタブ
		ListView list = (ListView) parent;
		long s = list.getItemIdAtPosition(position);
		
		Intent intent = new Intent(AlarmMenuActivity.this, AlarmSettingActivity.class);
		intent.putExtra("rowID", (int) s);
		startActivity(intent);
		db.close();
	}

	private class ListArrayAdeapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO 自動生成されたメソッド・スタブ
			return entity.size();
		}

		@Override
		public AlarmTimeDBEntity getItem(int position) {
			// TODO 自動生成されたメソッド・スタブ
			return entity.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO 自動生成されたメソッド・スタブ
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自動生成されたメソッド・スタブ
			Context context = AlarmMenuActivity.this;
			AlarmTimeDBEntity item = this.getItem(position);
			LinearLayout layout = new LinearLayout(context);
			if(convertView == null){
				layout.setPadding(10, 10, 10, 10);
				convertView = layout;
				
				TextView textView = new TextView(context);
				textView.setTextAppearance(context, android.R.attr.textAppearanceMedium);
				textView.setTag("text");
				if(item.getFlag())
					textView.setText("最初の授業の" + item.getHour() + "時間" + item.getMin() + "分前");
				else
					textView.setText(item.getDay() + "曜日:"+ item.getHour() + "時" + item.getMin() + "分");
				layout.addView(textView);
				
			}
			
					
			return convertView;
		}
		
	}
	
}