package com.kemizhibo.kemizhibo.yhr.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Author: 闫浩然
 * Date: on 2018/7/23.
 * Describe:点赞的动画效果
 */

public class AnimationTools {
    public static void scale(View v) {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(300);
        v.startAnimation(anim);

    }
}
