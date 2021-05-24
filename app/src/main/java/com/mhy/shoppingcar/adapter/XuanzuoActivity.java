package com.mhy.shoppingcar.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mhy.shoppingcar.R;
import com.mhy.shoppingcar.caidan.ArcMenuTestActivity;

public class XuanzuoActivity extends AppCompatActivity implements View.OnClickListener {
    public static String sendData, renshu;//调用全局数据变量，方便调用
    private EditText et_data, et_renshu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzuo);

        Button btn_link = findViewById(R.id.btn_link);
        et_data = findViewById(R.id.et_data);
        et_renshu = findViewById(R.id.et_renshu);
        btn_link.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //连接
        if (v.getId() == R.id.btn_link) {
            if (et_data.length() != 0 && et_renshu.length() != 0) {
                sendData = et_data.getText().toString().trim();//输入的桌号
                renshu = et_renshu.getText().toString().trim();//输入的人数
                Intent i = new Intent(XuanzuoActivity.this, ArcMenuTestActivity.class);
                startActivity(i);
                finish();
            } else if (et_data.length() == 0 && et_renshu.length() != 0) {
                Toast.makeText(getApplicationContext(), "请输入座位号！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "请输入人数！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
