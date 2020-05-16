package cn.edu.upc.ljh.myfirstandorid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentTransaction;
public class MainActivity extends AppCompatActivity {
    private FragmentTransaction beginTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart(){
        super.onStart();
        //创建Fragment
        ContentFragment contentFragment = new ContentFragment();
        MenuFragment menuFragment = new MenuFragment();
        //获取事务
        beginTransaction = getFragmentManager().beginTransaction();
        //添加Fragment
        beginTransaction.replace(R.id.foodcontent, contentFragment);
        beginTransaction.replace(R.id.menu, menuFragment);
        //提交事务
        beginTransaction.commit();
    }


}
