package com.example.zone.glidestudy.glideUtils;

import android.content.Context;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

//-------------------------------------------第三步 extentds 然后更改getUrl方法-----------------------------------------
public class MyUrlLoader extends BaseGlideUrlLoader<MyUrlLoader.MyDataModel> {

    public MyUrlLoader(Context context) {
        super(context);
    }
    public MyUrlLoader(Context context, ModelCache<MyDataModel, GlideUrl> modelCache) {
        super(context, modelCache);
    }

    public MyUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader) {
        super(concreteLoader);
    }

    public MyUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader, ModelCache<MyDataModel, GlideUrl> modelCache) {
        super(concreteLoader, modelCache);
    }

    @Override
    protected String getUrl(MyDataModel model, int width, int height) {
        // Construct the url for the correct size here.
        System.out.println("gagga");
        return model.buildUrl(width, height);
    }

    //-------------------------------------------最后一步  初始化Loader-----------------------------------------------------------------------
    public static class Factory implements ModelLoaderFactory<MyUrlLoader.MyDataModel, InputStream> {
        @Override
        public ModelLoader<MyUrlLoader.MyDataModel, InputStream> build(Context context, GenericLoaderFactory factories) {
            System.out.println("疯子");
            return new MyUrlLoader(context);
        }
        @Override
        public void teardown() {

        }
    }

//-----------------------------------------------------第一步 设置更改url的接口-------------------------------------------------------------
    public  interface MyDataModel {
         String buildUrl(int width, int height);
    }
    //--------------------------------------------------第二步  实现更改url的接口----------------------------------------------------------------
    public static class CustomUrlModel implements MyDataModel {
        private String baseImageUrl;

        public CustomUrlModel(String baseImageUrl) {
            this.baseImageUrl = baseImageUrl;
        }

        @Override
        public String buildUrl(int width, int height) {
            String print = baseImageUrl + "?w=" + width + "&h=" + height;
            System.out.println("print--------____________________------>"+print);
            return baseImageUrl + "?w=" + width + "&h=" + height;
        }
    }

}
