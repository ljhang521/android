package cn.edu.upc.ljh.myfirstandorid;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
@SuppressLint("NewApi")
public class MenuFragment extends Fragment {
    private View view;
    private int[] settingicon;
    private String[] foodNames;
    private String[] settingText;
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //解析布局
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        //获取Acitivty实例对象
        MainActivity activity = (MainActivity) getActivity();
        //获取Activity中的图标数组
        settingicon = activity.getIcons();
        //获取Activity中定义的川菜名称
        foodNames =  activity.getNames();
        //获取Activity中的设置文字数组
        settingText = activity.getSettingText();
        if (view != null) { // 如果view不为空
            initView();
        }
        //为ListView设置条目监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position,
                                    long id) {
                //点击左侧的川菜列表item，右侧菜品做法内容相应改变逻辑
                //通过Activity实例获取另一个Fragment实例
                ContentFragment listFragment = (ContentFragment)((MainActivity)
                        getActivity()).getFragmentManager().findFragmentById(R.id.foodcontent);
                //设置菜品类表点击位置对应的菜品做法的Fragment文字
                listFragment.setText(settingText[position]);
            }
        });
        return view;
    }
    //初始化控件的方法
    private void initView() {
        mListView = (ListView) view.findViewById(R.id.menulist);
        if (settingicon != null) {
            mListView.setAdapter(new MyAdapter());
        }
    }
    //适配器
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return settingicon.length;
        }
        @Override
        public Object getItem(int position) {
            return settingicon[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getActivity(), R.layout.item_list, null);
            ImageView mNameTV = (ImageView) convertView
                    .findViewById(R.id.food_icon);
            mNameTV.setBackgroundResource(settingicon[position]);
            TextView mFoodName = (TextView) convertView.findViewById
                    (R.id.food_name);
            mFoodName.setText(foodNames[position]);
            return convertView;
        }
    }
}
