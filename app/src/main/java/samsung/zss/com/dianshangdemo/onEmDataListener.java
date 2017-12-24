package samsung.zss.com.dianshangdemo;

/**
 * Created by shanshanzhi on 2017/12/24.
 */

public interface onEmDataListener {
    void OnNext(String response);
    void OnError(String msg);

}
