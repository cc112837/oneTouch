package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ManageAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;


public class ManageActivity extends BaActivity implements View.OnClickListener {
    private ImageView leftBtn;
    private ListView lv_show;
    private ManageAdapter manageAdapter;
    private TextView tv_how;
    private List<SelfHealth.DataEntity> list=new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 150:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if ("1".equals(stepInfo.getStatus())) {
                        Toast.makeText(ManageActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(ManageActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    break;
                case 151:
                    SelfHealth selfHealth = (SelfHealth) msg.obj;
                    list.addAll(selfHealth.getData());
                    manageAdapter.notifyDataSetChanged();
                    lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Intent intent=new Intent(ManageActivity.this,AidsManagerActivity.class);
//                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        String url= Constants.SERVER_URL+"";
        MyHttpUtils.handData(handler,151,url,"");
        initView();
    }

    private void initView() {
        tv_how=(TextView) findViewById(R.id.tv_how);
        lv_show=(ListView) findViewById(R.id.lv_show);
        tv_how.setOnClickListener(this);
        manageAdapter=new ManageAdapter(ManageActivity.this,list);
        leftBtn = ((ImageView) findViewById(R.id.leftBtn));
        leftBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_how:
                Intent intent=new Intent(ManageActivity.this,AidsManagerActivity.class);
                startActivity(intent);
                break;



        }

    }
}
