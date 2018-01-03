package com.junlong0716.module.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午2:21 2018/1/3
 * @modified by:
 */
public class DynamicWeatherView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    /**
     * 用于控制SurfaceView
     */
    private SurfaceHolder mHolder;
    /**
     * 声明一个线程
     */
    private Thread mThread;
    /**
     * 线程运行的标识，用于控制线程
     */
    private boolean flag;
    /**
     * 声明一张画布
     */
    private Canvas mCanvas;
    /**
     * 声明一支画笔
     */
    private Paint p;
    /**
     * 圆的坐标和半径
     */
    private int x = 50, y = 50, r = 10;

    public DynamicWeatherView(Context context) {
        super(context);
        init();
    }

    public DynamicWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DynamicWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new Thread(this);
        flag = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX(); // 获得屏幕被触摸时对应的X轴坐标
        y = (int) event.getY(); // 获得屏幕被触摸时对应的Y轴坐标
        return super.onTouchEvent(event);
    }

    private void init(){
        mHolder = getHolder();
        mHolder.addCallback(this);
        p = new Paint();
        p.setColor(Color.WHITE);
        setFocusable(true);
    }
    private void doDraw() {
        Log.d("x",x+"");
        mCanvas = mHolder.lockCanvas(); // 获得画布对象，开始对画布画画
        mCanvas.drawRGB(0, 0, 0); // 把画布填充为黑色
        mCanvas.drawCircle(x, y, r, p); // 画一个圆
        mHolder.unlockCanvasAndPost(mCanvas); // 完成画画，把画布显示在屏幕上
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP){  //当用户点击↑键时
            y--;  //设置Y轴坐标减1
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (flag) {
            doDraw(); // 调用自定义画画方法
            try {
                Thread.sleep(2000); // 让线程休息50毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
