package com.mhy.shoppingcar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mhy.shoppingcar.adapter.SendAsyncTask;
import com.mhy.shoppingcar.fragment.FragmentLeft;
import com.mhy.shoppingcar.fragment.FragmentRight;
import com.mhy.shoppingcar.fragment.FragmentRight1;
import com.mhy.shoppingcar.fragment.FragmentRight2;
import com.mhy.shoppingcar.fragment.FragmentRight3;
import com.mhy.shoppingcar.util.ToastUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.mhy.shoppingcar.adapter.XuanzuoActivity.renshu;
import static com.mhy.shoppingcar.adapter.XuanzuoActivity.sendData;


public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public static TextView textView;
    Button btn;
    public static Boolean isSelect = false;
    public static double total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        getFragmentManager().beginTransaction().replace(R.id.fragmentleft, new FragmentLeft()).commit();
        getFragmentManager().beginTransaction().replace(R.id.fragmentright, new FragmentRight().getFragmentRight(1)).commit();
        imageView = findViewById(R.id.iv_select_all);
        textView = findViewById(R.id.tv_total_price);
        btn = findViewById(R.id.btn_order);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelect = !isSelect;
                if (isSelect) {
                    imageView.setImageResource(R.mipmap.select);
                    total_price = FragmentRight1.getPrice() + FragmentRight2.getPrice() + FragmentRight3.getPrice();
                    textView.setText(total_price + "");
                    getFragmentManager().beginTransaction().replace(R.id.fragmentright, new FragmentRight().getFragmentRight(1)).commit();
                } else {
                    imageView.setImageResource(R.mipmap.unselect);
                    total_price = 0;
                    FragmentRight1.NoPrice();
                    FragmentRight2.NoPrice();
                    FragmentRight3.NoPrice();
                    textView.setText(total_price + "");
                    getFragmentManager().beginTransaction().replace(R.id.fragmentright, new FragmentRight().getFragmentRight(1)).commit();
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> lists = new ArrayList();
                String id2 = new String(sendData);
                id2 = "A" + id2 + "!" + renshu + "&";
                for (int i = 0; i < FragmentLeft.goodslb.length; i++) {
                    if (i == 0) {
                        for (int j = 0; j < FragmentRight1.goods_num.length; j++) {
                            for (int k = 0; k < FragmentRight1.goods_num[j]; k++) {
                                //id2 += j;
                                id2 += "0" + j;
                            }
                            lists.add(FragmentRight1.goods_num[j]);

                        }
                    } else if (i == 1) {
                        for (int j = 0; j < FragmentRight2.goods_num.length; j++) {
                            for (int k = 0; k < FragmentRight2.goods_num[j]; k++) {
                                //id2 += j + 5;
                                int L =  j + 5;
                                id2 += "0" + L;
                            }
                            lists.add(FragmentRight2.goods_num[j]);
                        }
                    } else {
                        //id2 = id2 + "B";
                        for (int j = 0; j < FragmentRight3.goods_num.length; j++) {
                            for (int k = 0; k < FragmentRight3.goods_num[j]; k++) {
                                //id2 += j;
                                id2 += "1" + j;
                            }
                            lists.add(FragmentRight3.goods_num[j]);
                        }
                    }

                }


                if (total_price > 0) {//如果有被选中的
                    if (!id2.isEmpty()) {
                        //让Double类型完整显示，不用科学计数法显示大写字母E
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        id2 = id2 + "#" + (decimalFormat.format(total_price));
                        new SendAsyncTask().execute(id2);
                        Log.d("ttt", id2);
                        ToastUtil.makeText(MainActivity.this, "订单已提交，请耐心等待上菜");
                        Intent inte = new Intent();
                        inte.putIntegerArrayListExtra("goods", lists);
                        inte.putExtra("name", total_price);
                        inte.setAction("testskippage.intent.action.skip");
                        inte.addCategory("testskippage.intent.category.skip");
                        startActivity(inte);
                    } else {
                        ToastUtil.makeText(MainActivity.this, "请选择要购买的商品");
                    }
                } else {
                    ToastUtil.makeText(MainActivity.this, "请选择要购买的商品");
                }
            }
        });

    }

}
