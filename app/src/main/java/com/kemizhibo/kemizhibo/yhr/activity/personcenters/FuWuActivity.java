package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.graphics.PointF;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import java.io.File;
import butterknife.BindView;

public class FuWuActivity extends BaseActivity {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.iv_xiangqing1)
    SubsamplingScaleImageView ivXiangqing1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fu_wu;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        ivXiangqing1.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        ivXiangqing1.setMinScale(1.0F);//最小显示比例
        ivXiangqing1.setMaxScale(1.0F);//最大显示比例
        //final String testUrl = "http://cache.attach.yuanobao.com/image/2016/10/24/332d6f3e63784695a50b782a38234bb7/da0f06f8358a4c95921c00acfd675b60.jpg";
        //下载图片保存到本地
        Glide.with(this)
                .load(R.drawable.newxieyi).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                ivXiangqing1.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(1.0F, new PointF(0, 0), 0));
            }});
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("使用协议");
        publicTitleBarRoot.buildFinish();
    }
}
