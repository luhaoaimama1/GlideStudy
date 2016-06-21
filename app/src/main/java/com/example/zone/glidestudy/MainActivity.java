package com.example.zone.glidestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zone.glidestudy.glideUtils.MyUrlLoader;
import com.example.zone.glidestudy.other.Images;
import com.zone.adapter.QuickManager;
import com.zone.adapter.QuickRcvAdapter;
import com.zone.adapter.callback.Helper;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private QuickRcvAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> list = Arrays.asList(Images.imageThumbUrls);
        adapter=new QuickRcvAdapter<String>(this,list){

            @Override
            public void fillData(Helper<String> helper, String item, boolean itemChanged, int layoutId) {
                Glide.with(getContext()) .load(item)
                        .placeholder(R.drawable.ic_stub).dontAnimate()
                        .into((ImageView)helper. getView(R.id.iv));
            }

            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.imageitem;
            }
        };
        QuickManager.with(adapter, rv).perform();
    }
}
