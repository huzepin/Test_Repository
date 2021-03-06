package cn.com.coolsoft.notebook;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.com.coolsoft.notebook.bean.GoodOrder_ItemHolder;

public class MainActivity extends AppCompatActivity implements GoodOrder_ItemHolder.OnClickSlideDeleteListener {
    private TextView mTextMessage;
    private FrameLayout FramePage;
    private FirstFragment f1;
    private SecondFragment f2;
    private ThreeFragment f3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        FramePage = findViewById(R.id.FramePage);

        android.support.v4.app.FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        f1 = FirstFragment.newInstance("",R.layout.fragment_first);
        transaction.add(R.id.FramePage,f1);
        transaction.commit();

       // navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        if(f1==null){
                            f1 = FirstFragment.newInstance("",R.layout.fragment_first); //编辑
                            transaction.add(R.id.FramePage,f1);
                        }else{
                            transaction.show(f1);
                            f1.onResume();
                        }
                        break;
                    case R.id.navigation_dashboard:
                        if(f2==null){
                            f2 =SecondFragment.newInstance("",R.layout.fragment_second);//"第二个Fragment"  记录
                            transaction.add(R.id.FramePage,f2);
                        }else{
                            transaction.show(f2);
                            f2.onResume();
                        }
                        break;
                    case R.id.navigation_notifications:
                        if(f3==null){
                            f3 = ThreeFragment.newInstance("",R.layout.fragment_three);//"第三个Fragment"  账本
                            transaction.add(R.id.FramePage,f3);
                        }else{
                            transaction.show(f3);
                            f3.onResume();
                        }
                        break;
                }
                transaction.commit();
                return true; //  返回true 这样就能在点击的时候改变导航栏上图标的颜色了，表示这一项被选中了
            }
        });

    }


    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){

            transaction.hide(f2);
        }
        if(f3!=null){

            transaction.hide(f3);
        }

    }


    //记录模板的
    @Override
    public void ondelet(Object o) {

    }
}
