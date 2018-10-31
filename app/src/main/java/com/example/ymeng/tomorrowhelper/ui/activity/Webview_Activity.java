package com.example.ymeng.tomorrowhelper.ui.activity;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;


/**
 * Author:YMeng
 * Time:2018/7/30  9:51
 * This is Webview_Activity
 */
public class Webview_Activity extends SimpleActivity implements JsBridge {
    private WebView mWebView;
    private TextView mTextView;
    private EditText mEditText;
    private Button mButton;


    private Handler mHandler;


    @Override
    protected int setLayout() {
        return R.layout.webview_activity;
    }

    @Override
    protected void initViews() {
        mWebView  = (WebView) findViewById(R.id.webview);
        mTextView  = (TextView) findViewById(R.id.web_tv);
        mEditText  = (EditText) findViewById(R.id.web_et);
        mButton  = (Button) findViewById(R.id.web_btn);
        mHandler = new Handler();
    }

    @Override
    protected void initDatas() {
        //允许webview加载js代码
        mWebView.getSettings().setJavaScriptEnabled(true);
        //给Webview添加js接口
        mWebView.addJavascriptInterface(new ImoocJsInterface(this),"imoocLauncher");

        mWebView.loadUrl("file:///android_asset/index.html");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mWebView.setWebContentsDebuggingEnabled(true);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                mWebView.loadUrl("javascript:if(window.remote){window.remote('"+str+"')}");
            }
        });

    }

    @Override
    public void setTextViewValue(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(value);
            }
        });
    }
}
