package com.example.zone.glidestudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.zone.glidestudy.glideUtils.MyUrlLoader;
import com.example.zone.glidestudy.other.Images;
import com.example.zone.glidestudy.utils.CompressUtils;
import com.example.zone.glidestudy.utils.FileUtils;
import com.example.zone.glidestudy.utils.SampleUtils;
import com.zone.adapter.QuickManager;
import com.zone.adapter.QuickRcvAdapter;
import com.zone.adapter.callback.Helper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TargetActivity extends AppCompatActivity {
    public enum Type{
        SimpleTarget,ViewTarget;
    }
    private RecyclerView rv;
    private QuickRcvAdapter<Type> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        List<Type> list = Arrays.asList(Type.values());
        adapter=new QuickRcvAdapter<Type>(this,list){
            @Override
            public void fillData(final Helper<Type> helper, Type item, boolean itemChanged, int layoutId) {
                switch (item) {
                    case SimpleTarget:

                        int myWidth = 512;
                        int myHeight = 384;
                        Glide.with(getContext()).load(Images.imageThumbUrls[0])
                            .asBitmap()
//                            .into(new SimpleTarget<Bitmap>(myWidth, myHeight) {  //宽高 可设置可不设置
//                                @Override
//                                public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
//                                    // Do something with bitmap here.
//                                    helper.setImageBitmap(R.id.iv,bitmap);
//                                }
//                            });
                            .into(new SimpleTarget<Bitmap>() {  //宽高 可设置可不设置
                                @Override
                                public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                    // Do something with bitmap here.
                                    helper.setImageBitmap(R.id.iv,bitmap);
                                }
                            });
                        helper.setText(R.id.tv,item.toString());
                        break;
                    case ViewTarget:
                        helper.setText(R.id.tv,"ViewTarget 暂时不弄了");
                        break;
                }

            }

            @Override
            public int getItemLayoutId(Type s, int position) {
                return R.layout.imageitem_type;
            }
        };
        QuickManager.with(adapter, rv).perform();
    }
}
