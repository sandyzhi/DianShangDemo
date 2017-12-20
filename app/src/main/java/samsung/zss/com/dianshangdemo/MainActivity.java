package samsung.zss.com.dianshangdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.common.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import samsung.zss.com.dianshangdemo.fragment.GuanZhuFragment;
import samsung.zss.com.dianshangdemo.fragment.HomeFragment;
import samsung.zss.com.dianshangdemo.fragment.MyPageFragment;
import samsung.zss.com.dianshangdemo.fragment.VideoMedioFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.fl_container)
    FrameLayout frameContent;
    //    @BindView(R.id.bottom_content)
    LinearLayout bottom_container_layout;
    FragmentManager fm;
    FragmentTransaction transaction;
    List<Fragment> mFragments;
    LinearLayout homeView;
    LinearLayout mediaView;
    LinearLayout guanZhuView;
    LinearLayout MyPageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        processLogic();
        setBottomListener();
        L.i("-------------ceshi hahah ");
    }

    //处理数据
    private void processLogic() {

    }

    private void initView() {
        bottom_container_layout = (LinearLayout) findViewById(R.id.bottom_content);
        homeView = (LinearLayout) bottom_container_layout.getChildAt(0);
        mediaView = (LinearLayout) bottom_container_layout.getChildAt(1);
        guanZhuView = (LinearLayout) bottom_container_layout.getChildAt(2);
        MyPageView = (LinearLayout) bottom_container_layout.getChildAt(3);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new VideoMedioFragment());
        mFragments.add(new GuanZhuFragment());
        mFragments.add(new MyPageFragment());
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            transaction.add(R.id.fl_container, mFragments.get(i));
            if (i == 0) {
                transaction.show(mFragments.get(i));
            } else {
                transaction.hide(mFragments.get(i));
            }
        }
//        transaction.commit();
//        L.i("commit test ");
//        L.i("commit test checkout -b 在本地基础上创建一个新的分支 并切换到新的分支  ");
        transaction.commitAllowingStateLoss();

    }

    private void setBottomListener() {
        Log.i("ZSSLOG", " bottom_container_layout " + bottom_container_layout);
        for (int i = 0; i < bottom_container_layout.getChildCount(); i++) {
            if (i == 0) {
                bottom_container_layout.getChildAt(i).setSelected(true);
            }
            final int position = i;
            bottom_container_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2017/11/5 设置
                    FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                    for (int j = 0; j < bottom_container_layout.getChildCount(); j++) {
                        bottom_container_layout.getChildAt(j).setSelected(false);
                        tr.hide(mFragments.get(j));
                    }
                    tr.show(mFragments.get(position));
                    bottom_container_layout.getChildAt(position).setSelected(true);
                    tr.commit();
                }
            });
        }

//        homeView.setOnClickListener(this);
//        mediaView.setOnClickListener(this);
//        guanZhuView.setOnClickListener(this);
//        MyPageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homeview:
                break;
            case R.id.mediaView:
                break;
            case R.id.guanzhuView:
                break;
            case R.id.mypageView:
                break;
        }
    }


}
