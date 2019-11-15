package com.wenjie.mvp.view.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenjie.R;
import com.wenjie.utils.DisplayUtils;
import com.wenjie.utils.ImageLoader;

public class GirlsAdapter2 extends BaseQuickAdapter<GirlBean,BaseViewHolder> {
    private Context mContext;
    public GirlsAdapter2(Context context){
        super(R.layout.item_girl2);
        this.mContext = context;
    }



    @Override
    protected void convert(BaseViewHolder helper, GirlBean item) {
        ImageView imageView = helper.itemView.findViewById(R.id.imageViewItemGirl);
        final ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = DisplayUtils.getScreenWidth((Activity) mContext) / 3 - DisplayUtils.dp2px(mContext,8);
        if(item.getScale()!=0){
            layoutParams.height = (int) (layoutParams.width*(16/9));
        }
        imageView.setBackgroundColor(Color.BLUE);
        ImageLoader.load(mContext,item.getUrl(),imageView);
//        Glide.with(mContext)
//                .load(girlBeans.get(position).getUrl())
//                .transition(new DrawableTransitionOptions().crossFade())
//                .into(holder.imageView);
    }


}
