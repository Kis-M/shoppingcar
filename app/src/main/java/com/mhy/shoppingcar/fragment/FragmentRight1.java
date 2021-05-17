package com.mhy.shoppingcar.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.mhy.shoppingcar.MainActivity;
import com.mhy.shoppingcar.R;
import com.mhy.shoppingcar.util.ToastUtil;

import static com.mhy.shoppingcar.fragment.FragmentLeft.goodsNums;

public class FragmentRight1 extends FragmentRight {
    int id;
    ListView listView;
    static String[] goods_name = new String[]{"牛油火锅","鸳鸯火锅","菌汤锅底","番茄锅底","三鲜锅底"};
    static double[] goods_price = new double[]{69.00,69.00,58.00,58.00,58.00};
    public static int[] goods_num = new int[]{0,0,0,0,0};
    int[] goods_image = new int[]{R.drawable.img01,R.drawable.img02,R.drawable.img03,R.drawable.img04,R.drawable.img05};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right,null);
        listView = view.findViewById(R.id.goods);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return goods_name.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = getLayoutInflater().inflate(R.layout.item_fragment_right,null);
                TextView tv_name = view.findViewById(R.id.tv_name);
                tv_name.setText(goods_name[i]);
                TextView tv_num = view.findViewById(R.id.tv_edit_buy_number);
                tv_num.setText(goods_num[i]+"");
                TextView tv_price_value = view.findViewById(R.id.tv_price_value);
                tv_price_value.setText(goods_price[i]+"");
                ImageView imageView = view.findViewById(R.id.iv_photo);
                imageView.setImageResource(goods_image[i]);
                ImageView img_add = view.findViewById(R.id.iv_edit_add);
                ImageView img_sub = view.findViewById(R.id.iv_edit_subtract);
                img_sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(goods_num[i] == 0){
                            ToastUtil.makeText(getActivity(), "商品不能再减少了");
                        }else{
                            goods_num[i]--;
                            tv_num.setText(goods_num[i]+"");
                            goodsNums[0] = getNums();
                            FragmentLeft.goodsnums[0].setVisibility(View.VISIBLE);
                            FragmentLeft.goodsnums[0].setText(goodsNums[0]+"");
                            MainActivity.total_price -= goods_price[i];
                            MainActivity.textView.setText(MainActivity.total_price + "");
                        }
                        if(goodsNums[0] == 0){
                            FragmentLeft.goodsnums[0].setVisibility(View.INVISIBLE);
                        }
                    }
                });
                img_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goods_num[i]++;
                        tv_num.setText(goods_num[i]+"");
                        goodsNums[0] = getNums();
                        FragmentLeft.goodsnums[0].setVisibility(View.VISIBLE);
                        FragmentLeft.goodsnums[0].setText(goodsNums[0]+"");
                        MainActivity.total_price += goods_price[i];
                        MainActivity.textView.setText(MainActivity.total_price + "");
                    }
                });
                return view;
            }
        };
        listView.setAdapter(adapter);
    }
    public  int getNums(){
        int nums = 0;
        for(int i=0;i<goods_num.length;i++){
            nums += goods_num[i];
        }
        return nums;
    }
    public static double getPrice(){
        double sum = 0;
        for(int i=0;i<goods_price.length;i++){
            sum += goods_price[i];
            goods_num[i] = 1;
        }
        FragmentLeft.goodsnums[0].setVisibility(View.VISIBLE);
        FragmentLeft.goodsnums[0].setText(goods_num.length+"");
        return sum;
    }
    public static void NoPrice(){
        for(int i=0;i<goods_price.length;i++){
            goods_num[i] = 0;
        }
        FragmentLeft.goodsnums[0].setVisibility(View.INVISIBLE);
    }
}
