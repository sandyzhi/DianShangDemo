package samsung.zss.com.dianshangdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.common.utils.L;

import java.util.ArrayList;
import java.util.List;

import samsung.zss.com.dianshangdemo.R;
import samsung.zss.com.dianshangdemo.db.DbManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoMedioFragment extends Fragment {
    private String TAG = "ZSSLOG";
private EditText mEditText;
    private RecyclerView mRecycleView;
    private String mSearchContent = "三星手机";

    public VideoMedioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_medio,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEditText = view.findViewById(R.id.edit_text);
        mRecycleView  = view.findViewById(R.id.recycle_listview);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                L.d(TAG," onEditorAction "+actionId);

                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    if (mEditText!=null){
                        mSearchContent = mEditText.getText().toString();
                        L.i(TAG," mSearch Content "+mSearchContent);
                        search(mSearchContent);
                    }
                    return true;
                }
                //点击键盘搜索键 出发此监听
                return false;
            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: 2017/11/27
                //模糊搜索会使用
                if (s!=null){
                    mSearchContent = s.toString();
                    L.i(TAG," sea "+mSearchContent);
                }
            }
        });

    }

    //to do search result
    private void search(String searchContent) {
        if (searchContent ==null){
            searchContent = "Samsung Phone";
        }
        if (searchContent!=null){
            // TODO: 2017/11/27 插入数据库中
            DbManager.getInstance(getActivity()).insertRecord(searchContent,System.currentTimeMillis());
            List<String> allRecords = DbManager.getInstance(getActivity()).queryRecord();
            if (allRecords!=null&&allRecords.size()>0){
                for (int i = 0; i < allRecords.size(); i++) {
                    L.i(TAG," records "+ allRecords.get(i)+"  i "+"  size "+allRecords.size());
                }
            }


        }
    }

}
