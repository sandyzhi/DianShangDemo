package samsung.zss.com.dianshangdemo.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.utils.L;

import java.util.ArrayList;
import java.util.List;

import samsung.zss.com.dianshangdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private Button mShareBtn;
    private Button mShareBtn2;
private String TAG = "HomeFragment";
    private String wechatPackageName = "com.tencent.mm";
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mShareBtn = view.findViewById(R.id.shareBtn);
        mShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        mShareBtn2 = view.findViewById(R.id.shareBtn2);
        mShareBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share2();
            }
        });
    }

    //share across lableIntent
    private void share2() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        Intent chooserIntent = Intent.createChooser(intent,null);
        if (chooserIntent==null){
            return;
        }
        startActivity(chooserIntent);
    }
//com.tencent.mm.ui.tools.ShareImgUI
//    com.tencent.mm.ui.tools.AddFavoriteUI

    /**
     activityinfo UI com.tencent.mm.ui.tools.ShareImgUI
     activityinfo UI com.tencent.mm.ui.tools.AddFavoriteUI
     activityinfo UI com.tencent.mm.ui.tools.ShareToTimeLineUI
     */
    public void share() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
//        intent.setType("image/*");
        intent.setType("text/plain");
        List<LabeledIntent> targentIntents = getTargetIntents(intent);
        Intent chooserIntent = null;
        if (targentIntents!=null&&targentIntents.size()>0){
            int size = targentIntents.size();
            L.i(TAG," remove "+targentIntents.get(0).getPackage()+"  :   ");
            chooserIntent = Intent.createChooser(intent,"测试title");

//            chooserIntent = Intent.createChooser(intent,"测试title");
//            LabeledIntent[] laeledIntent = targentIntents.toArray(new LabeledIntent[targentIntents.size()]);
          }

        if (chooserIntent == null) {
            return;
        }
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,targentIntents.toArray(new LabeledIntent[targentIntents.size()]));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,targentIntents.toArray(new Parcelable[]{}));
        startActivity(chooserIntent);
    }

    private List<LabeledIntent> getTargetIntents(Intent intent) {
        List<LabeledIntent> targentsIntents = new ArrayList<>();
        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent,0);
        if (infos!=null&&infos.size()>0){
            for (ResolveInfo info : infos){
                ActivityInfo activityInfo = info.activityInfo;
                if (activityInfo.packageName.equals(wechatPackageName)){
                    L.d(TAG," equal info ");
//                    continue;
                }
                Intent in = new Intent(Intent.ACTION_SEND);
                in.setType("text/plain");
                in.setPackage(activityInfo.packageName);
                in.setClassName(activityInfo.packageName,activityInfo.name);
                L.d(TAG," packageName "+activityInfo.packageName+"    : className :  "+activityInfo.name);
                targentsIntents.add(new LabeledIntent(in,activityInfo.packageName,info.loadLabel(pm),info.icon));
            }
            return targentsIntents;
        }

        return null;
    }

    private void listSendInfo(Intent intent) {
        List<ResolveInfo> infos = getActivity().getPackageManager().queryIntentActivities(intent,0);
        if (infos!=null){
            for(ResolveInfo info :infos){
                L.d(TAG," activityinfo "+info.activityInfo.packageName);
                L.d(TAG," activityinfo UI "+info.activityInfo.name);
            }
        }
    }
}
