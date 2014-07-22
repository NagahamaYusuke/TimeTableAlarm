package com.timetablealarm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ImageView imageView = (ImageView)findViewById(R.id.splashimageView1);
			
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.splash);
		
		imageView.setImageBitmap(resizeBitmapToDisplaySize(this,src));
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		@Override
		public void run() {
		  // MainActivityを呼び出す
		  Intent intent = new Intent(getApplicationContext(), MenuSelectActivity.class);
		  // アクティビティ(MainActivity)を起動する
		  startActivity(intent);
		  // SplashActivityを終了する
		  SplashActivity.this.finish();
		  }
		}, 2 * 1000); // 2000ミリ秒後（2秒後）に実行
	}
	
	private Bitmap resizeBitmapToDisplaySize(Activity activity, Bitmap src){
        int srcWidth = src.getWidth(); // 元画像のwidth
        int srcHeight = src.getHeight(); // 元画像のheight
        Log.d("test", "srcWidth = " + String.valueOf(srcWidth)
                + " px, srcHeight = " + String.valueOf(srcHeight) + " px");
 
        // 画面サイズを取得する
        Matrix matrix = new Matrix();
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float screenWidth = (float) metrics.widthPixels;
        float screenHeight = (float) metrics.heightPixels;
        Log.d("test", "screenWidth = " + String.valueOf(screenWidth)
                + " px, screenHeight = " + String.valueOf(screenHeight) + " px");
 
        float widthScale = screenWidth / srcWidth;
        float heightScale = screenHeight / srcHeight;
        Log.d("test", "widthScale = " + String.valueOf(widthScale)
                + ", heightScale = " + String.valueOf(heightScale));
//        if (widthScale > heightScale) {
//            matrix.postScale(heightScale, heightScale);
//        } else {
//            matrix.postScale(widthScale, widthScale);
//        }
        matrix.postScale(widthScale, heightScale);
        // リサイズ
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, srcWidth, srcHeight, matrix, true);
        int dstWidth = dst.getWidth(); // 変更後画像のwidth
        int dstHeight = dst.getHeight(); // 変更後画像のheight
        Log.d("test", "dstWidth = " + String.valueOf(dstWidth)
                + " px, dstHeight = " + String.valueOf(dstHeight) + " px");
        src = null;
        return dst;
    }
}
