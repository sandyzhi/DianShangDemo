package samsung.zss.com.dianshangdemo.Util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Platform;
import samsung.zss.com.dianshangdemo.onEmDataListener;
//http://blog.csdn.net/peixiaopao/article/details/77852902

/**
 * Created by shanshanzhi on 2017/12/24.
 */

public class OkHttpUtil {
    private static OkHttpClient client;
    private Context context;
    private static Request request;
    private static Handler handler = new Handler();
    private Platform platform;
    /**
     * OkHttpClient单例对象实例
     */
    public static OkHttpClient getClientInstance() {
        if (client == null) {
            synchronized (OkHttpUtil.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }

    public static void OkHttpGet(Context context, String url, final onEmDataListener listener) {
        request = new Request.Builder().url(url).build();
        OkHttpUtil.getClientInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败 、
                listener.OnError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //此处在异步线程中
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.OnNext(result);
                    }
                });
            }
        });
    }

    // post 调用
//    FormBody body = new FormBody.Builder().add("name", "111222").build();
//        OkHttpUtil.OkHttpPost(MainActivity.this,url, body, new OkHttpUtil.OnDataFinish() {
//        @Override
//        public void OnSuccess(final String result) {
//            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//        }
//    });
    /**
     * OkHttp的post请求
     */
    static void OkHttpPost(final Context context, String url, RequestBody body, final onEmDataListener listener) {
        request = new Request.Builder().url(url).post(body).build();
        OkHttpUtil.getClientInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "服务器连接异常!", Toast.LENGTH_SHORT).show();
                listener.OnError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.OnNext(result);
                    }
                });
            }
        });
    }
}
