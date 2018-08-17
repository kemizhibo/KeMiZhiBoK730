package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.yhr.App;

import java.util.List;

/**
 * Created by asus on 2018/8/12.
 */

public class MyViewpagerAdapter extends FragmentPagerAdapter {
    private List<String> picurls;
    private Context context;

    public MyViewpagerAdapter(FragmentManager fm,List<String> picurls) {
        super(fm);
    }




    @Override
    public int getCount() {
        return picurls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View) object);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       /* SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        Uri parse = Uri.parse(picurls.get(position));
        simpleDraweeView.setImageURI(parse);
        container.addView(simpleDraweeView);*/
        TextView textView=new TextView(context);
        textView.setText("jjjjj");
        return textView;
    }
}
