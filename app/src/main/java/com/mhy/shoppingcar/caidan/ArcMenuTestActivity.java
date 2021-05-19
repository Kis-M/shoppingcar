package com.mhy.shoppingcar.caidan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.mhy.shoppingcar.MainActivity;
import com.mhy.shoppingcar.R;
import com.mhy.shoppingcar.adapter.SendAsyncTask;
import com.zjywidget.widget.arcmenu.YArcMenuView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mhy.shoppingcar.adapter.XuanzuoActivity.sendData;


public class ArcMenuTestActivity extends AppCompatActivity implements View.OnClickListener {

    private YArcMenuView mArcMenuView;
    private Button btn_tiancai;
    private ListView listView;
    String goods_names[] = {"牛油火锅", "鸳鸯火锅", "菌汤锅底", "番茄锅底", "三鲜锅底",
            "虾滑", "毛肚", "火锅牛排", "精品小肥羊", "秘制羊肉",
            "土豆", "娃娃菜", "金针菇", "藕片", "甜玉米"};
    int goods_imagges[] = {R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04, R.drawable.img05,
            R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09, R.drawable.img10,
            R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15};
    String goods_price[] = {"¥69.00", "¥69.00", "¥58.00", "¥58.00", "¥58.00", "¥39.00", "¥49.00", "¥38.00", "¥40.00", "¥45.00",
            "¥11.00", "¥13.00", "¥12.00", "¥12.00", "¥11.00"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_menu);
        btn_tiancai = findViewById(R.id.tiancai);
        btn_tiancai.setOnClickListener(this);
        listView = findViewById(R.id.choosed);
        TextView sum = (TextView) findViewById(R.id.sum);
        if (MainActivity.total_price > 0) {
            List<Map<String, Object>> chooses = new ArrayList<>();
            ArrayList<Integer> lists = getIntent().getIntegerArrayListExtra("goods");
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i) > 0) {
                    Map<String, Object> list = new HashMap<>();
                    list.put("name", goods_names[i]);
                    list.put("num", lists.get(i));
                    list.put("price", goods_price[i]);
                    list.put("image", goods_imagges[i]);
                    chooses.add(list);
                }
            }
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new SimpleAdapter(this, chooses, R.layout.item_choosed,
                    new String[]{"name", "num","price", "image"}, new int[]{R.id.tv_name, R.id.tv_edit_buy_number,R.id.tv_price, R.id.iv_photo}));
            btn_tiancai.setVisibility(View.VISIBLE);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            sum.setText("当前消费" + decimalFormat.format(MainActivity.total_price) + "元");
        } else {
            listView.setVisibility(View.INVISIBLE);
            btn_tiancai.setVisibility(View.INVISIBLE);
            sum.setText("欢迎光临！");
        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("YArcMenuView");
        }

        mArcMenuView = findViewById(R.id.arc_menu);
        List<Integer> menuItems = new ArrayList<>();
        menuItems.add(R.drawable.zhifu);
        menuItems.add(R.drawable.diancan);
        menuItems.add(R.drawable.hujiao);
        mArcMenuView.setMenuItems(menuItems);

        mArcMenuView.setClickItemListener(new YArcMenuView.ClickMenuListener() {
            @Override
            public void clickMenuItem(int resId) {
                switch (resId) {
                    case R.drawable.zhifu:
                        Toast.makeText(getApplicationContext(), "点击了支付", Toast.LENGTH_SHORT).show();
                        new SendAsyncTask().execute("C" + sendData);
                        try {
                            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(ArcMenuTestActivity.this, "打开失败，请检查是否安装了支付宝", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.drawable.diancan:
                        Toast.makeText(getApplicationContext(), "点击了点餐", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ArcMenuTestActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.drawable.hujiao:
                        Toast.makeText(getApplicationContext(), "点击了呼叫服务员", Toast.LENGTH_SHORT).show();
                        new SendAsyncTask().execute("B" + sendData);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tiancai) {
            finish();
        }
    }
}
