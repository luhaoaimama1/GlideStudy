

package com.example.zone.glidestudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/20.
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public void onClick(View v) {
        //todo  笔记从新整理  归块~  demo也归块
        //todo  fit ，Transformation
        //todo  animate() 自定义动画
        //todo 一些基于Glide的优秀库
        switch (v.getId()) {
            case R.id.lv:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.picAllType:
                startActivity(new Intent(this,AllTypeActivity.class));
                break;
        }
    }
}
