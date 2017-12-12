package samsung.zss.com.dianshangdemo;

import java.util.List;

/**
 * Created by shanshanzhi on 2017/12/12.
 */

public class SubCategoryBean {
    private String title;
    private int id;
    private ThirdCategoryBean thirdCategoryBean;
    private List<ThirdCategoryBean> thirdsBeans;
    public ThirdCategoryBean getThirdCategoryBean() {
        return thirdCategoryBean;
    }

    public void setThirdCategoryBean(ThirdCategoryBean thirdCategoryBean) {
        this.thirdCategoryBean = thirdCategoryBean;
    }

    public List<ThirdCategoryBean> getThirdsBeans() {
        return thirdsBeans;
    }

    public void setThirdsBeans(List<ThirdCategoryBean> thirdsBeans) {
        this.thirdsBeans = thirdsBeans;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class ThirdCategoryBean{
        private String title;
        private String imgUrl;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
