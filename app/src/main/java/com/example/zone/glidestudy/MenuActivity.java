

package com.example.zone.glidestudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.zone.glidestudy.glideUtils.MyUrlLoader;
import com.example.zone.glidestudy.other.Images;
import com.example.zone.glidestudy.utils.CompressUtils;
import com.example.zone.glidestudy.utils.FileUtils;
import com.example.zone.glidestudy.utils.SampleUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        customUrl();
        saveFile();
    }

    private void saveFile() {
        if(!FileUtils.getFile("1.jpg").exists()){
            CompressUtils.saveBitmap(FileUtils.getFile("1.jpg").getPath(),
                    SampleUtils.load(this,R.drawable.abcd).bitmap());
        }
    }

    private void customUrl() {
        //.using 非全局配置  多了一个 using  不过Model是一样的
        Glide.with(this)
                .using(new MyUrlLoader(this))
                .load(new MyUrlLoader.CustomUrlModel(Images.imageThumbUrls[0]))
                .downloadOnly(222,333);

        //全局配置
        Glide.with(this)
                .load(new MyUrlLoader.CustomUrlModel(Images.imageThumbUrls[3]))
                .downloadOnly(666,999);
    }

    @Override
    public void onClick(View v) {
        //todo  animate() 自定义动画    http://mrfu.me/2016/02/28/Glide_Custom_Animations_with_animate()/
        //todo Transformation:         https://github.com/wasabeef/glide-transformations
        switch (v.getId()) {
            case R.id.lv:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.picAllType:
                startActivity(new Intent(this,AllTypeActivity.class));
                break;
            case R.id.target:
                startActivity(new Intent(this,TargetActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearDiskCache();//清楚 磁盘缓存
        Glide.get(this).clearMemory();//清楚内存
    }
}
