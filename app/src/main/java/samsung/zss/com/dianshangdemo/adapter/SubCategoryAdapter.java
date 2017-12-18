package samsung.zss.com.dianshangdemo.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.utils.L;

import android.os.Handler;
import android.widget.Toast;

import java.util.List;

import samsung.zss.com.dianshangdemo.R;
import samsung.zss.com.dianshangdemo.SubCategoryBean;
import samsung.zss.com.dianshangdemo.custem.MyGridView;

/**
 * Created by shanshanzhi on 2017/12/12.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_BANNER = 0;
    private final static int TYPE_OTHERS = 1;

    private Context context;
    private List<SubCategoryBean> subCategorys;

    private LayoutInflater mInflater;

    public SubCategoryAdapter(Context context, List<SubCategoryBean> subCategorys) {

        this.context = context;
        this.subCategorys = subCategorys;
        mInflater = LayoutInflater.from(context);
        L.d("ZSSLOG"," SubCategoryAdapter size "+subCategorys.size());
    }
    public void updateData(List<SubCategoryBean> subCategory){
        subCategorys = subCategory;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        L.d("ZSSLOG"," onCreateViewHolder");
        if (viewType == TYPE_BANNER) {
            return new SubCategoryBannerViewHolder(mInflater.inflate(R.layout.item_sub_category_banner, null));
        } else if (viewType == TYPE_OTHERS) {
            return new SubCategoryViewHolder(mInflater.inflate(R.layout.item_sub_category_others, null));
        }
      return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubCategoryBannerViewHolder) {
            ((SubCategoryBannerViewHolder) holder).bannerTv.setText(subCategorys.get(position).getTitle() + "banner");
//            L.d("ZSSLOG"," onBindViewHolder position "+position);
            // TODO: 2017/12/17 准备viewpager的图片资源 然后设置adapter
                ((SubCategoryBannerViewHolder) holder).mPager.setAdapter(new MyPagerAdapter(context,((SubCategoryBannerViewHolder) holder).ids));
                ((SubCategoryBannerViewHolder) holder).initIndicator();
            //对数据判空
                ((SubCategoryBannerViewHolder) holder).mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                        Toast.makeText(context, " pageScrolled ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPageSelected(int position) {
                        ((SubCategoryBannerViewHolder) holder).setIndicator(position);
//                        Toast.makeText(context, " onPageSelected "+position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                ((SubCategoryBannerViewHolder) holder).mPager.setCurrentItem(0);
                ((SubCategoryBannerViewHolder) holder).mHandler.sendEmptyMessageDelayed(1,2000);
//            mHandler.sendEmptyMessageDelayed(1,2000);


        } else if (holder instanceof SubCategoryViewHolder) {
            ((SubCategoryViewHolder) holder).subTitleTv.setText(subCategorys.get(position).getTitle());
            L.d("ZSSLOG","title  "+subCategorys.get(position).getTitle());
            ((SubCategoryViewHolder) holder).subGrideView.setAdapter(new GridViewAdapter(context,subCategorys.get(position).getThirdsBeans()));
        }
    }

    @Override
    public int getItemViewType(int position) {
//        L.d("ZSSLOG"," getItemViewType position "+position);
        if (position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_OTHERS;
        }
    }

    @Override
    public int getItemCount() {
        return subCategorys == null ? 0 : subCategorys.size();
    }


    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView subTitleTv;
        private MyGridView subGrideView;
        private GridViewAdapter adapter;

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            subTitleTv = ((TextView) itemView.findViewById(R.id.item_sub_category_title));
            subGrideView = ((MyGridView) itemView.findViewById(R.id.item_sub_category_third_gridview));
            subGrideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, " gridview  position "+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class SubCategoryBannerViewHolder extends RecyclerView.ViewHolder {
        public Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int currentItem = mPager.getCurrentItem();
                if (currentItem>=(ids.length-1)){
                    mPager.setCurrentItem(0);
                }else {
                    mPager.setCurrentItem(currentItem+1);
                }
                mHandler.sendEmptyMessageDelayed(1,2000);
            }
        };
        private ViewPager mPager;
        private TextView bannerTv;
        private LinearLayout indicatar ;
        private int [] ids = new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3};
        private ImageView[] mImageView;
        public  ViewPager getPager (){
            return mPager;
        }
        public SubCategoryBannerViewHolder(View itemView) {
            super(itemView);
            bannerTv = ((TextView) itemView.findViewById(R.id.item_tv_banner));
            mPager = ((ViewPager) itemView.findViewById(R.id.item_sub_category_banner));
            indicatar = ((LinearLayout) itemView.findViewById(R.id.indactor_container));
        }
        private void initIndicator() {
            indicatar.removeAllViews();
//            for (int i = 0; i < indicatar.getChildCount(); i++) {
//
//            }
            Toast.makeText(context, "indicatoar count "+indicatar.getChildCount(), Toast.LENGTH_SHORT).show();
            mImageView = new ImageView[ids.length];
            for (int i = 0; i < ids.length; i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.indicator_image, null);
                view.findViewById(R.id.indicator_iamge).setBackgroundResource(R.drawable.shape_origin_point_pink);
                mImageView[i] = new ImageView(context);
                if (i == 0) {
                    mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_pink);
                } else {
                    mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_white);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(20, 0, 0, 0);
                    mImageView[i].setLayoutParams(layoutParams);
                }
                indicatar.addView(mImageView[i]);
            }
        }

        private void setIndicator(int position) {
            position %= ids.length;
            for (int i = 0; i < ids.length; i++) {
                mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_pink);
                if (position != i) {
                    mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_white);
                }

            }
        }
    }


    public class GridViewAdapter extends BaseAdapter{
    private Context context;
        private List<SubCategoryBean.ThirdCategoryBean> thirdBeans ;
        private LayoutInflater inflater ;

        public GridViewAdapter(Context context, List<SubCategoryBean.ThirdCategoryBean> thirdBean) {
            this.context = context;
            this.thirdBeans = thirdBean;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return thirdBeans==null?0:thirdBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return thirdBeans.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null){
                vh = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_sub_category_third_gridview,parent,false);
                vh.thirdImg = ((ImageView) convertView.findViewById(R.id.item_third_imgview));
                vh.thirdTitle = ((TextView) convertView.findViewById(R.id.item_third_textview));
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            vh.thirdTitle.setText(thirdBeans.get(position).getTitle());
            return convertView;
        }
    }


    class ViewHolder {
        TextView thirdTitle;
        ImageView thirdImg;
    }


    private class MyPagerAdapter extends PagerAdapter{
        private int []ids;
        private Context context;

        public MyPagerAdapter(Context context,int[]ids) {
            this.context = context;
            this.ids = ids;
        }

        @Override
        public int getCount() {
            return ids.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView imageView = new ImageView(mContext);
//            position %= mImageArr.length;
//            if(position<0){
//                position = mImageArr.length+position;
//            }
//            imageView.setImageResource(mImageArr[position]);
//            container.addView(imageView);
//            return imageView;
//        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View ret = null;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            Picasso.with(mContext).load(mList.get(position)).into(imageView);
            imageView.setImageResource(ids[position]);
            ret = imageView;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "positioin "+position, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(ret);
            return ret;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            if (object instanceof View){
                View view = (View) object;
                container.removeView(view);
            }
        }
    }
}
