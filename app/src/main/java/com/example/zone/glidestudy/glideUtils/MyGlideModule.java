package com.example.zone.glidestudy.glideUtils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

public class MyGlideModule implements GlideModule {
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(MyUrlLoader.MyDataModel.class, InputStream.class, new MyUrlLoader.Factory());
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }
}