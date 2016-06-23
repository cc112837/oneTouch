package com.wzy.mhealth.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/23 14:35
 * 修改人：Administrator
 * 修改时间：2016/6/23 14:35
 * 修改备注：
 */
public class TaoCanAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<String> list;

    public TaoCanAdapter(Context context,List<String> list) {
        mInflater = LayoutInflater.from(context);
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.taocan_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.afterprice = (TextView) convertView.findViewById(R.id.tv_afterprice);
            viewHolder.beforeprice = (TextView) convertView.findViewById(R.id.tv_beforeprice);
            viewHolder.afterprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    static class ViewHolder {
        public TextView name;
        public  TextView afterprice;
        public TextView beforeprice;

    }

}