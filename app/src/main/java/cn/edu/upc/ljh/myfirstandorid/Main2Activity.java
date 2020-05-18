package cn.edu.upc.ljh.myfirstandorid;


import android.Manifest;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class Main2Activity extends AppCompatActivity implements
        GestureOverlayView.OnGesturePerformedListener {
    private GestureLibrary library; //定义手势库变量
    private boolean loadStatus;  //记录手势库是否加载成功
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //第一种raw文件夹中加载手势文件
        library = GestureLibraries.fromRawResource(this, R.raw.mygestures);
        GestureOverlayView gesture = (GestureOverlayView) findViewById(R.id.
                gestures);
        gesture.addOnGesturePerformedListener(this); //增加事件监听器
     /*     //第二种加载SD卡手势文件
       ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);*/

    }
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        loadStatus = library.load(); //加载手势库
        if (loadStatus) { //如果手势库加载成功
            //识别绘制的手势，Prediction是一个相似度对象,集合中的相似度是从高到低进行排列
            ArrayList<Prediction> pres = library.recognize(gesture);
            if (!pres.isEmpty()) {
                Prediction pre = pres.get(0);//获取相似度最高的对象
                //用整型的数表示百分比，如>40%
                if (pre.score > 4) { //如果手势的相似度分数大于40%，则匹配成功
                    Toast.makeText(this, pre.name, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Main2Activity.this, "手势匹配不成功",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(Main2Activity.this, "手势库加载失败",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
