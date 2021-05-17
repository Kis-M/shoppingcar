package com.mhy.shoppingcar.fragment;

import android.app.Fragment;

public class FragmentRight extends Fragment{
    public FragmentRight getFragmentRight(int id){
        if(id == 1){
            return new FragmentRight1();
        }else if(id == 2){
            return new FragmentRight2();
        }else{
            return new FragmentRight3();
        }
    }
}
