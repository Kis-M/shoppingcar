package com.mhy.shoppingcar.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.app.Fragment;

import com.mhy.shoppingcar.R;

public class FragmentLeft extends Fragment {
    ListView listView;
    public static String[] goodslb = new String[]{"锅底","荤菜","素菜"};
    static int[] goodsNums = new int[goodslb.length];
    static TextView[] goodsnums =new TextView[goodslb.length];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left,null);
        listView = view.findViewById(R.id.goodslb);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return goodslb.length;
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
                view = getLayoutInflater().inflate(R.layout.item_fragment_left,null);
                LinearLayout linearLayout = view.findViewById(R.id.ll);
                TextView textView = view.findViewById(R.id.tv_store_name);
                textView.setText(goodslb[i]);

                goodsnums[i] = view.findViewById(R.id.goods_nums);
                Log.d("ttt",goodsNums[i]+"");
                if(goodsNums[i] != 0){
                    goodsnums[i].setVisibility(View.VISIBLE);
                    goodsnums[i].setText(goodsNums[i]+"");
                }else{
                    goodsnums[i].setVisibility(View.INVISIBLE);
                }
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().getFragmentManager().beginTransaction().replace(R.id.fragmentright,new FragmentRight().getFragmentRight(i+1)).commit();
                    }
                });
                return view;
            }
        };
        listView.setAdapter(adapter);
    }
}
