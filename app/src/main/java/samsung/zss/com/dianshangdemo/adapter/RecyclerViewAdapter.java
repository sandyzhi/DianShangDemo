package samsung.zss.com.dianshangdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import samsung.zss.com.dianshangdemo.R;

/**
 * Created by shanshanzhi on 2017/12/14.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_OTHERS = 1;

    private Context context;
    private LayoutInflater inflater;
    private List<String> datas;
    public RecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder;
        View view = null;
        if (viewType==TYPE_BANNER){
            view = inflater.inflate(R.layout.item_recyclerview_top_viewpager,null);
            return new BannerViewHolder(view);
        }else if (viewType ==TYPE_OTHERS){
            view = inflater.inflate(R.layout.item_sub_category_others,null);
            return new GoodsViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder){
            List<ImageView> imageviews  = new ArrayList<>();

            for (int i = 0; i < 3; i++) {

            }
//            MyPagerAdapter adapter = new MyPagerAdapter()
//            ((BannerViewHolder) holder).viewPager
        }else if (holder instanceof GoodsViewHolder){

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_BANNER;
        }else {
            return TYPE_OTHERS;
        }
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private ViewPager viewPager;
        public BannerViewHolder(View itemView) {
            super(itemView);
            viewPager = ((ViewPager) itemView.findViewById(R.id.recyclerview));
        }
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.item_recycler_other_textview);

        }
    }


    private class MyPagerAdapter extends PagerAdapter{
        private int[] imgIds = new int[]{R.drawable.splash_banner,R.drawable.splash,R.drawable.comment_like_icon};
        private List<ImageView> imagesviews ;

        public MyPagerAdapter(List<ImageView> imageviews) {
           this.imagesviews = imageviews;
        }

        @Override
        public int getCount() {
            return imgIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
//            container.removeView();
        }
    }
}
