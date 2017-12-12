package samsung.zss.com.dianshangdemo.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.common.utils.L;

import java.util.ArrayList;
import java.util.List;

import samsung.zss.com.dianshangdemo.R;
import samsung.zss.com.dianshangdemo.SubCategoryBean;
import samsung.zss.com.dianshangdemo.adapter.SubCategoryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {
    private String[] categotys = new String[]{
            "推荐分类","京东名牌","国际名牌", "奢侈品", "全球购", "男装", "女装"  ,
            "男鞋" , "女鞋" , "箱包手袋" ,"电脑办公", "酒水饮料" ,"母婴童装" ,
            "医药保健", "户外运动" ,"礼品鲜花", "房产"
    };
    private ListView listView;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private List<SubCategoryBean> datas;
    private SubCategoryAdapter subAdapter;
    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        initData("推荐分类",10);
        initView(view);
        return view;
    }

    //初始化数据
    private void initData(String subtitle,int length) {
        L.d("ZSSLOG"," initdatea title "+subtitle);
        datas = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<SubCategoryBean.ThirdCategoryBean> beans = new ArrayList<>();
            SubCategoryBean subCategory = new SubCategoryBean();
            subCategory.setTitle(subtitle+i);
            for (int j = 0; j < 13; j++) {
                SubCategoryBean.ThirdCategoryBean thirdBean = new SubCategoryBean.ThirdCategoryBean();
                thirdBean.setTitle(subtitle+i+j);
                thirdBean.setImgUrl("");
                beans.add(thirdBean);
            }
            subCategory.setThirdsBeans(beans);

            datas.add(subCategory);
            L.d("ZSSLOG"," putdate title "+datas.get(i).getTitle());
        }
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerview));
        mAdapter = new MyAdapter(getActivity());
        listView.setAdapter(mAdapter);
        mAdapter.setSelectedPosition(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listView.getChildAt(position).setSelected(true);
                mAdapter.setSelectedPosition(position);
                mAdapter.notifyDataSetChanged();
                initData(categotys[position],position+5);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //do the ui-job
                        subAdapter.notifyDataSetChanged();
                    }
                });

                L.d("ZSSLOG"," datasize "+datas.size());
            }
        });

        //recycle View
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        subAdapter = new SubCategoryAdapter(getActivity(),datas);
        recyclerView.setAdapter(subAdapter);
    }
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition;
        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return categotys.length;
        }

        @Override
        public Object getItem(int position) {
            return categotys[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setSelectedPosition(int position){
            this.selectedPosition = position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null ){
                convertView = inflater.inflate(R.layout.item_list_textview,null);
                holder = new ViewHolder();
                holder.textview = (TextView) convertView.findViewById(R.id.item_categor_tv);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            if (position==selectedPosition ){
                convertView.setSelected(true);
                convertView.setBackgroundColor(Color.WHITE);
            }else{
                convertView.setSelected(false);
                convertView.setBackgroundColor(Color.GRAY);
            }
            holder.textview.setText(categotys[position]);
            return convertView;
        }
    }
    private static class ViewHolder {
        TextView textview;
    }
}
