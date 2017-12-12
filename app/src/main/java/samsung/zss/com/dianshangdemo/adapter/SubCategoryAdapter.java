package samsung.zss.com.dianshangdemo.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.utils.L;

import org.w3c.dom.Text;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubCategoryBannerViewHolder) {
            ((SubCategoryBannerViewHolder) holder).bannerTv.setText(subCategorys.get(position).getTitle() + "banner");
            L.d("ZSSLOG"," onBindViewHolder position "+position);
        } else if (holder instanceof SubCategoryViewHolder) {
            ((SubCategoryViewHolder) holder).subTitleTv.setText(subCategorys.get(position).getTitle());
            L.d("ZSSLOG","title  "+subCategorys.get(position).getTitle());
            ((SubCategoryViewHolder) holder).subGrideView.setAdapter(new GridViewAdapter(context,subCategorys.get(position).getThirdsBeans()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        L.d("ZSSLOG"," getItemViewType position "+position);
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

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            subTitleTv = ((TextView) itemView.findViewById(R.id.item_sub_category_title));
            subGrideView = ((MyGridView) itemView.findViewById(R.id.item_sub_category_third_gridview));
        }
    }

    public class SubCategoryBannerViewHolder extends RecyclerView.ViewHolder {
        private ViewPager mPager;
        private TextView bannerTv;

        public SubCategoryBannerViewHolder(View itemView) {
            super(itemView);
            bannerTv = ((TextView) itemView.findViewById(R.id.item_tv_banner));
            mPager = ((ViewPager) itemView.findViewById(R.id.item_sub_category_banner));
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
                convertView = inflater.inflate(R.layout.item_sub_category_third_gridview,null);
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
}
