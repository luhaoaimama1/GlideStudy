package com.example.zone.glidestudy.other;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/6/20.
 */
public class GlideModelConfig implements GlideModule {

    int diskSize = 1024 * 1024 * 50;
    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//builder可调用的方法
//                .setMemoryCache(MemoryCache memoryCache)     //自定义内存
//                .setBitmapPool(BitmapPool bitmapPool)        //图片池大小
//                .setDiskCache(DiskCache.Factory diskCacheFactory)  定义缓存大小和位置
//                .setDiskCacheService(ExecutorService service)
//                .setResizeService(ExecutorService service)
//                .setDecodeFormat(DecodeFormat decodeFormat)   // 定义图片格式

        // 定义缓存大小和位置
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  //内存卡（非sd） 中
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize)); //sd卡中

        // 默认内存和图片池大小
//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
//        int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
//        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize)); // 该两句无需设置，是默认的
//        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));

        // 定义图片格式
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565); // 默认
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // 我把他拿过来了    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}