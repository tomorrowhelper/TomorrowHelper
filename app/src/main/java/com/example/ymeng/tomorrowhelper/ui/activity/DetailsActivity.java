package com.example.ymeng.tomorrowhelper.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.model.bean.DetailsBean;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;
import com.example.ymeng.tomorrowhelper.ui.adapter.DetailsAdapter;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:YMeng
 * Time:2018/10/29  15:29
 * This is DetailsActivity
 * rv_details
 */
public class DetailsActivity extends SimpleActivity {
    private final OkHttpClient client = new OkHttpClient();
    private RecyclerView mRecycler;

    @Override
    protected int setLayout() {
        return R.layout.details_activity;
    }

    @Override
    protected void initViews() {
        mRecycler = (RecyclerView) findViewById(R.id.rv_details);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void initDatas() {
        enqueue();
    }
String url = "http://124.205.59.226:83/SanfoBI/api/stockquery.do?partnerid=181016&styleid=1001011272,1001012888&sid=cb5e349332320d0cbf17fe1522a32d03";
    private void enqueue() {
        //1,创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
//2,创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
//3,新建一个call对象
        Call call = mOkHttpClient.newCall(request);
//4，请求加入调度，这里是异步Get请求回调
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DetailsBean result = new Gson().fromJson(json,  DetailsBean.class);

                        DetailsAdapter mAdapter = new DetailsAdapter(R.layout.item_details, result.getData());
                        mRecycler.setAdapter(mAdapter);
                    }
                });


            }

        });


    }
}
