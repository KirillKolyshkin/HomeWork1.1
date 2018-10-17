package com.example.kolys.homework2;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class RecycleDiffCallBack extends DiffUtil.ItemCallback<Planet>{

    @Override
    public boolean areItemsTheSame(@NonNull Planet planet, @NonNull Planet t1) {
        return planet.getTemp() == t1.getTemp(); //&& planet.getName().equals(t1.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Planet planet, @NonNull Planet t1) {
        return planet.equals(t1);//planet.getTemp() == t1.getTemp(); //&& planet.getName().equals(t1.getName());
    }
}
