package com.example.zone.glidestudy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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

/**
 * Created by Administrator on 2016/6/20.
 */
public class AllTypeActivity extends AppCompatActivity {
    public enum Type{
        HTTP,Thumbnail,File,Resource,Uri,GifLocal,GifHttp,Mp4,Error;
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
            public void fillData(Helper<Type> helper, Type item, boolean itemChanged, int layoutId) {
//                Glide.with(getContext()) .load(item)
//                        .placeholder(R.drawable.ic_stub).dontAnimate()
//                        .into((ImageView)helper. getView(R.id.iv));
                if(!FileUtils.getFile("1.jpg").exists()){
                    CompressUtils.saveBitmap(FileUtils.getFile("1.jpg").getPath(),
                            SampleUtils.load(getContext(),R.drawable.abcd).bitmap());
                }
                switch (item) {
                    case HTTP:
                        Glide.with(getContext()) .load(Images.imageThumbUrls[0])
                                .skipMemoryCache(true)
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case Thumbnail:
                        Glide.with(getContext()).load("https://img.alicdn.com/imgextra/i4/705956171/TB2WgYJfVXXXXb4XXXXXXXXXXXX_!!705956171.gif")
                                .fitCenter()
                                .thumbnail(0.1f)//成功了
                                .skipMemoryCache(true)
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case File:
                        Glide.with(getContext()).load(FileUtils.getFile("1.jpg"))
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case Resource:
                        Glide.with(getContext()).load(R.drawable.aaaaaaaaaaaab)
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case Uri:
                        Glide.with(getContext()).load(resourceIdToUri(getContext(), R.drawable.uria))
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case GifLocal:
                        Glide.with(getContext()).load(R.drawable.abc).asGif()
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .fitCenter()
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .error(R.drawable.ic_error)
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case GifHttp:
                        Glide.with(getContext()).load("https://img.alicdn.com/imgextra/i4/705956171/TB2WgYJfVXXXXb4XXXXXXXXXXXX_!!705956171.gif").asGif()
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .skipMemoryCache(true)
                                .fitCenter()
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .error(R.drawable.ic_error)
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case Mp4:
                        //todo 显示不了。。。 看到这页了 http://mrfu.me/2016/02/27/Glide_Displaying_Gifs_&_Videos/
//                File file = FileUtils.getFile("面具男鬼步舞教程6个基本动作鬼步舞音乐 高清.mp4");
                        File file2 = FileUtils.getFile("DCIM","Camera","VID_20160306_125251.mp4");
                        Glide.with(getContext()).load(Uri.fromFile(file2))
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .fitCenter()
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .error(R.drawable.ic_error)
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                    case Error:
                        Glide.with(getContext()).load(FileUtils.getFile("1111.jpg"))
                                .placeholder(R.drawable.ic_stub).dontAnimate()
                                .error(R.drawable.ic_error)
                                .into((ImageView)helper. getView(R.id.iv));
                        break;
                }
                helper.setText(R.id.tv,item.toString());
            }

            @Override
            public int getItemLayoutId(Type s, int position) {
                return R.layout.imageitem_type;
            }
        };
        QuickManager.with(adapter, rv).perform();
    }
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
