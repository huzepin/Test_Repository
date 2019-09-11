package cn.com.coolsoft.notebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author admin
 * @time 2019/9/9 09
 */
public class ThreeFragment extends Fragment {

    private String context="xxxxxxxxxxxxx";
    private TextView mTextView;
    //要显示的页面
    private int FragmentPage;

    public  static ThreeFragment newInstance(String context, int iFragmentPage){

        ThreeFragment myFragment = new ThreeFragment();
        myFragment.context = context;
        myFragment.FragmentPage = iFragmentPage;
        return  myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(FragmentPage,container,false);

        return view;

    }
}
