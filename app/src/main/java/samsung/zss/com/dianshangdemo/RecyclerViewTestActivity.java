package samsung.zss.com.dianshangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewTestActivity extends Activity {
private RecyclerView  mrecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        initView();
    }

    private void initView() {
        mrecyclerView = ((RecyclerView) findViewById(R.id.recyclerview));
    }
}
