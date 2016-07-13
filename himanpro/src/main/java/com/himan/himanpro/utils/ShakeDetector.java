package com.himan.himanpro.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 摇动检测器
 * @author Nono
 *
 */
public class ShakeDetector implements SensorEventListener {
    private static final String TAG = ShakeDetector.class.getSimpleName();

    private static final double SHAKE_SHRESHOLD = 7000d;
    private Context mContext;
    private long lastTime ;
    private float last_x;
    private float last_y;
    private float last_z;
    private static int fistScreenBrightness;//记录亮屏时候的亮度
    private static final int DARK_SCREEN_BRIGHTNESS = 20;//暗屏亮度

    private SensorManager sensorManager;
    /**
     * 构造
     * @param mContext
     */
    public ShakeDetector(Context mContext){
        this.mContext = mContext;
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * 注册传感器
     */
    public boolean registerListener() {

        if (sensorManager != null) {
            Sensor sensor = sensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (sensor != null) {
                this.sensorManager.registerListener(this, sensor,
                        SensorManager.SENSOR_DELAY_GAME);
                return true;
            }
        }
        return false;
    }

    /**
     * 反注册传感器
     */
    public void unRegisterListener() {
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        //if (event.sensor.getType() == SensorManager.SENSOR_ACCELEROMETER) {
        long curTime = System.currentTimeMillis();
        //规定多久检测一次变化
        if ((curTime - lastTime) > 10) {
            long diffTime = (curTime - lastTime);
            lastTime = curTime;

            float x = event.values[0];
            float	y = event.values[1];
            float z = event.values[2];

            //摇晃实现方法
            /*float speed = Math.abs(x + y + z - last_x - last_y - last_z)
                    / diffTime * 10000;
            if (speed > SHAKE_SHRESHOLD) {
                // 检测到摇晃后执行的代码
                shakeListener.onShake();
            }
            last_x = x;
            last_y = y;
            last_z = z;*/

            if(BrightnessTools.isAutoBrightness(mContext.getContentResolver())){
                BrightnessTools.stopAutoBrightness((Activity) mContext);
            }
            fistScreenBrightness = (BrightnessTools.getScreenBrightness((Activity) mContext) == fistScreenBrightness)
                    || (BrightnessTools.getScreenBrightness((Activity) mContext) == DARK_SCREEN_BRIGHTNESS)
                    ?fistScreenBrightness: BrightnessTools.getScreenBrightness((Activity) mContext);
            //角度实现方法
            if((y>0&&y>4) || (y<0&&y<-4)){
                BrightnessTools.setBrightness((Activity) mContext,DARK_SCREEN_BRIGHTNESS);
            }else{
                BrightnessTools.setBrightness((Activity) mContext,fistScreenBrightness);
            }
        }
        //}
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    /**
     *
     * @author 监听摇晃
     *
     */
    /*
    private onShakeListener shakeListener;

    public interface onShakeListener{
        public void onShake();

    public void setOnShakeListener(onShakeListener listener){
        shakeListener = listener;
    }
    }*/

}