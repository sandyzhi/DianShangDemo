package samsung.zss.com.dianshangdemo.Util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by shanshanzhi on 2017/11/5.
 */
//主界面Fragment的控制器
public class FragmentController {
    private int containerId;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    private static FragmentController controller;
    private static boolean isReload;

}
