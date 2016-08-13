package com.example.jim.todoandriod;

import android.content.DialogInterface;
import android.widget.AdapterView.OnItemLongClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_listview;
    TextView tv_in;
    TextView tv_out;
    //声明数据库操作对象
    SQLiteDatabase db;

    ArrayList<Money> arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取洁面的Listview
        lv_listview = (ListView) findViewById(R.id.lv_listview);
        tv_in = (TextView) findViewById(R.id.tv_In);
        tv_out = (TextView) findViewById(R.id.tv_Out);

        //从DBHelper内产生一个操作对象
        db = new DBHelper(this).getWritableDatabase();

        list();


        //长按事件
        lv_listview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取点击的账单
                final Money money = arr.get(position);
                // 创建提示选择框
                Builder build = new Builder(MainActivity.this);
                build.setTitle("请选择");
                String[] items = {"删除", "修改"};
                build.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //判断是删除还是修改
                        if (which == 0) {//删除处理a
                            // 创建SQL语句
                            String sql = "delete from bills where _id=" + money.id;
                            // 实现删除操作
                            db.execSQL(sql);
                            // 提示
                            Toast.makeText(MainActivity.this, "删除成功", 0).show();
                            // 刷新（查所有账单[）
                            list();
                        } else {//修改处理
                            Toast.makeText(MainActivity.this, "修改", 0).show();
                        }
                    }
                });
                build.show();//显示该选择框

                return false;
            }
        });

    }

    //跳转之添加窗口
    public void buttonJump(View view) {

        //创建意图
        Intent it = new Intent();
        //指定跳的窗体
        it.setClass(this, InputActivity.class);
        //开始启动意图,并声明回调事件
//        startActivity(it);
        startActivityForResult(it, 0);
    }

    //回调事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        list();
    }

    public void list() {


        Cursor cur = db.query("bills", null, null, null, null, null, null);

        //声明一个集合放所有的老师
        arr = new ArrayList<Money>();
        double moneyIn = 0.0;
        double moneyOut = 0.0;
        //循环游标对象的数据进行一行一行的查询
        while (cur.moveToNext()) {
            //获取游标里内的每一个值
            int id = cur.getInt(0);
            String time = cur.getString(3);
            String matter = cur.getString(1);
            String type = cur.getString(4);
            double price = cur.getDouble(2);


            //判断类型
            if (type.equals("收入")) {
                moneyIn += price;
            } else {
                moneyOut += price;
            }

            //封装数据查到的保存到
            Money m = new Money(id, matter, price, time, type);
            //将数据存到集合里
            arr.add(m);
        }
        //关闭游标
        cur.close();

        //创建适配器
        MonryAdater mada = new MonryAdater();
        mada.arr = arr;
        mada.ctx = this;
        //总收入
        double moneyInAll = moneyIn - moneyOut;

        lv_listview.setAdapter(mada);

        tv_in.setText("￥" + moneyInAll);
        tv_out.setText("￥" + moneyOut);
    }


    //按月份查找
    public void selectMonth(View view) {
        //获取点击的月份
        TextView tv = (TextView) view;
        String month = tv.getText() + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        Toast.makeText(this, month, 0).show();


        Cursor cur = db.query("bills", null, "strftime('%m',dtime)=?", new String[]{month}, null, null, null);

        //声明一个集合放所有的老师
        ArrayList<Money> arr = new ArrayList<Money>();
        double moneyIn = 0.0;
        double moneyOut = 0.0;
        //循环游标对象的数据进行一行一行的查询
        while (cur.moveToNext()) {
            //获取游标里内的每一个值
            int id = cur.getInt(0);
            String time = cur.getString(3);
            String matter = cur.getString(1);
            String type = cur.getString(4);
            double price = cur.getDouble(2);

            //判断类型
            if (type.equals("收入")) {
                moneyIn += price;
            } else {
                moneyOut += price;
            }

            //封装数据查到的保存到
            Money m = new Money(id, matter, price, time, type);
            //将数据存到集合里
            arr.add(m);
        }
        //关闭游标
        cur.close();

        //创建适配器
        MonryAdater mada = new MonryAdater();
        mada.arr = arr;
        mada.ctx = this;
        //总收入
        double moneyInAll = moneyIn - moneyOut;

        lv_listview.setAdapter(mada);

        tv_in.setText("￥" + moneyInAll);
        tv_out.setText("￥" + moneyOut);


    }



}
