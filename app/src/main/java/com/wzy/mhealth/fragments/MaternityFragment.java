package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.PersonTaocanActivity;
import com.wzy.mhealth.adapter.TaocanListAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MaternityFragment extends Fragment {
    private ListView lv_show;
    TaocanListAdapter maternityAdapter;
    private List<Tijian.DataEntity> list = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 171:
                    Tijian taocanEntity=(Tijian) msg.obj;
                    list.clear();
                    list.addAll(taocanEntity.getData());
                    maternityAdapter.notifyDataSetChanged();

                break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fu_chan, container, false);
        String url= Constants.SERVER_URL+"WhosiptalServlet";
        MyHttpUtils.handData(handler, 171, url, null);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_show = (ListView) v.findViewById(R.id.lv_show);
        maternityAdapter = new TaocanListAdapter(getContext(), list);
        lv_show.setAdapter(maternityAdapter);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PersonTaocanActivity.class);
                intent.putExtra("id", list.get(position).getTaocanId() + "");
                intent.putExtra("name", list.get(position).getCenterName() + "");
                intent.putExtra("tel", list.get(position).getPhone() + "");
                intent.putExtra("add", list.get(position).getAdress() + "");
                intent.putExtra("content", list.get(position).getDetails() + "");
                intent.putExtra("img", list.get(position).getImg() + "");
                intent.putExtra("second", (Serializable) list.get(position).getTaocanId());
                startActivity(intent);
            }
        });

    }


}