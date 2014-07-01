package com.timetablealarm.alarm;

import java.util.Calendar;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
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
}
