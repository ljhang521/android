package cn.edu.upc.ljh.myfirstandorid;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class ContentFragment extends Fragment {
    private View view;
    private TextView mContent;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //将布局文件解析出来
        view = inflater.inflate(R.layout.fragment_content, container, false);
        if(view != null){   //如果view不为空
            initView();
        }
        //获取Activity中的设置文字
        //setText(((MainActivity)getActivity()).getSettingText()[0]);
        return view;
    }
    public void initView(){
        mContent = (TextView) view.findViewById(R.id.content);
    }
    public void setText(String text){
        mContent.setText(text);
    }
}
