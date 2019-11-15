package com.wenjie.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by cl on 2018/5/3.
 */

public class ImageLoader {
    public static void load(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
        Glide.with(context)
                .load(url).apply(options)
                .into(iv);
    }

    public static void load(Context context, String url, ImageView iv, int placeholder) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder);//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存

        Glide.with(context)
                .load(url)
               .apply(options)
                .into(iv);
    }

    public static void load(Context context, int resId, ImageView iv) {
        Glide.with(context)
                .load(resId)
                .into(iv);
    }


    public static void loadCircle(Context context, int resId, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.transform(new CircleTransform());
        Glide.with(context)
                .load(resId)
                .into(iv);
    }
}
