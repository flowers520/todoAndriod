package com.example.jim.todoandriod;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonryAdater extends BaseAdapter{
    ArrayList<Money> arr;
    Context ctx;

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(ctx, R.layout.monry, null);
        //查找控件
        TextView id = (TextView)convertView.findViewById(R.id.tv_id);
        TextView time = (TextView)convertView.findViewById(R.id.tv_time);
        TextView matter = (TextView)convertView.findViewById(R.id.tv_matter);
        TextView price = (TextView)convertView.findViewById(R.id.tv_price);
        TextView type = (TextView)convertView.findViewById(R.id.tv_type);


        //获取数据
        Money m = arr.get(position);
        //为控件赋值
        id.setText(""+m.id);
        time.setText(m.dtime);


        matter.setText(m.matter);
        price.setText("￥"+m.price);
        type.setText(m.type);

        if (position%2==0){
//            convertView.setBackgroundColor(Color.argb(255, 80, 80, 80));
            convertView.setBackgroundColor(0xfff2eada);
        }else if (position%2==1){
            convertView.setBackgroundColor(0xffd1c7b7);
        }

        return convertView;
    }
}
