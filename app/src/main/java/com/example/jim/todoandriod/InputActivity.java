package com.example.jim.todoandriod;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    //声明数据库操作对象
    SQLiteDatabase db;
    //声明控件
    EditText et_time;
    EditText et_matter;
    EditText et_price;
    RadioButton rb_zc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        //查找控件
        et_time = (EditText)findViewById(R.id.et_time);
        et_price = (EditText)findViewById(R.id.et_price);
        et_matter = (EditText)findViewById(R.id.et_thing);
        rb_zc = (RadioButton)findViewById(R.id.radioButton2);

        //创建数据库操作对象
        db = new DBHelper(this).getWritableDatabase();

    }

    //添加
    public void buttonIn(View view){
        //获取输入的数据
        String time = et_time.getText()+"";

        String matter = et_matter.getText()+"";
        double price = Double.parseDouble(et_price.getText()+"");
        String type = "收入";
        if (rb_zc.isChecked()){
            type = "支出";
        }

        //创建SQL语句
        String sql = "insert into bills(matter,price,dtime,type) values ('"+matter+"','"+price+"','"+time+"','"+type+"')";
        //实现添加功能
        db.execSQL(sql);
        //提示
        Toast.makeText(this,"添加成功", 0).show();
    }

    //guanb
    public void buttonOut(View view){
        finish();
    }
}
