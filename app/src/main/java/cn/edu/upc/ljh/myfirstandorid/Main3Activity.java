package cn.edu.upc.ljh.myfirstandorid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    GestureOverlayView gestureView;
    EditText gestureName;
    private Gesture mGesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //获取手势编辑视图
        gestureView = (GestureOverlayView) this.findViewById(R.id.gesture);
        gestureView.setGestureColor(Color.BLUE);// 设置手势绘制颜色
        gestureView.setGestureStrokeWidth(10);   // 设置手势绘制的宽度
        // 为手势完成事件绑定事件监听器,手势完成后，触发该事件
        gestureView.addOnGesturePerformedListener(new GestureOverlayView.
                OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture
                    gesture) {
                mGesture = gesture;

                // 加载save_gesture.xml界面布局视图
                View dialog = getLayoutInflater().inflate(R.layout.save_gesture,
                        null);
                ImageView image = dialog.findViewById(R.id.show);
                gestureName  = dialog.findViewById(R.id.gesture_name);
                // 根据Gesture包含的手势创建一个位图
                Bitmap bitmap = gesture.toBitmap(128, 128, 10, 0xff0000ff);
                image.setImageBitmap(bitmap);
                // 使用对话框显示dialog组件
                new AlertDialog.Builder(Main3Activity.this).setView(dialog)
                        .setPositiveButton("保存", new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Main3Activity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }
    public void saveFile(){
        if(mGesture != null){
            // 获取指定文件对应的手势库
            GestureLibrary lib = GestureLibraries
                    .fromFile("/sdcard/mygestures");
            lib.addGesture(gestureName.getText().toString(), mGesture);
            boolean result= lib.save();   //保存手势
            if(result) Toast.makeText(Main3Activity.this,"保存成功",
                    Toast.LENGTH_SHORT).show();
            else Toast.makeText(Main3Activity.this,"保存失败",
                    Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            for (int i = 0; i < permissions.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    saveFile();
                }else{
                    Toast.makeText(this, "" + "权限" + permissions[i] +
                            "申请失败,不能保存手势库文件", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
