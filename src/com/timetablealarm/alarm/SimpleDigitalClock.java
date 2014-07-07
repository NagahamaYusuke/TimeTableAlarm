package com.timetablealarm.alarm;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class SimpleDigitalClock extends TextView {
	private Calendar mCalendar;
    private Runnable mTicker;
    private Handler mHandler;
    private boolean mTickerStopped = false;
    private static final String FORMAT = "k:mm";
 
    public SimpleDigitalClock(Context context) {
        super(context);
        initClock();
    }
 
    public SimpleDigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClock();
    }
 
    private void initClock() {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
    }
    
    /**
	 * 子Viewの位置を決める
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		resize();
	}
 
    @Override
    protected void onAttachedToWindow() {
        // ViewがWindowにアタッチされたとき（結合したとき）に呼び出される。
        mTickerStopped = false;
        super.onAttachedToWindow();
        mHandler = new Handler();
 
        mTicker = new Runnable() {
            @Override
            public void run() {
                if (mTickerStopped)
                    return;
                mCalendar.setTimeInMillis(System.currentTimeMillis());
                setText(DateFormat.format(FORMAT, mCalendar));
                invalidate();
                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - now % 1000);
                mHandler.postAtTime(mTicker, next);
            }
        };
        mTicker.run();
    }
 
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTickerStopped = true;
    }
    
    /**
	 * テキストサイズ調整
	 */
	private void resize()
	{
		/** 最小のテキストサイズ */
		final float MIN_TEXT_SIZE = 10f;

		int viewHeight = this.getHeight();	// Viewの縦幅
		int viewWidth = this.getWidth();	// Viewの横幅

		// テキストサイズ
		float textSize = getTextSize();

		// Paintにテキストサイズ設定
		Paint paint = new Paint();
		paint.setTextSize(textSize);

		// テキストの縦幅取得
		FontMetrics fm = paint.getFontMetrics();
		float textHeight = (float) (Math.abs(fm.top)) + (Math.abs(fm.descent));

		// テキストの横幅取得
		float textWidth = paint.measureText(this.getText().toString());

		// 縦幅と、横幅が収まるまでループ
		while (viewHeight < textHeight | viewWidth < textWidth)
		{
			// 調整しているテキストサイズが、定義している最小サイズ以下か。
			if (MIN_TEXT_SIZE >= textSize)
			{
				// 最小サイズ以下になる場合は最小サイズ
				textSize = MIN_TEXT_SIZE;
				break;
			}

			// テキストサイズをデクリメント
			textSize--;

			// Paintにテキストサイズ設定
			paint.setTextSize(textSize);

			// テキストの縦幅を再取得
			fm = paint.getFontMetrics();
			textHeight = (float) (Math.abs(fm.top)) + (Math.abs(fm.descent));

			// テキストの横幅を再取得
			textWidth = paint.measureText(this.getText().toString());
		}

		// テキストサイズ設定
		setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
	}
}
