package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.BingZhen;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

public class PrivaDoctorActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private WebView wv_show;
    private Button btn_select;
    private BingZhen bingZhen;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 265:
                    bingZhen = (BingZhen) msg.obj;
                    WebSettings webSettings = wv_show.getSettings();
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                    DisplayMetrics dm = new DisplayMetrics();
                    //取得窗口属性
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    //窗口的宽度
                    int screenWidth = dm.widthPixels/2-20;
                    wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:" + screenWidth + "px!important;}</style></head>" + bingZhen.getData().get(0).getDetails(), "text/html", "utf-8", null);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priva_doctor);
        String url = Constants.SERVER_URL + "MedicalCommonServlet";
        TiUser user = new TiUser();
        user.setName("1");
        MyHttpUtils.handData(handler, 265, url, user);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        btn_select = (Button) findViewById(R.id.btn_select);
        leftBtn.setOnClickListener(this);
        wv_show = (WebView) findViewById(R.id.wv_show);
        btn_select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.btn_select:
                Intent intent = new Intent(PrivaDoctorActivity.this, PopupActivity.class);
                intent.putExtra("content",bingZhen.getData().get(0).getId()+"");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        setBackgroundAlpha(0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundAlpha(1.0f);
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
