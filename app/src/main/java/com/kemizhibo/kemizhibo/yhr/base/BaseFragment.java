package com.kemizhibo.kemizhibo.yhr.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kemizhibo.kemizhibo.yhr.activity.MainActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import butterknife.Unbinder;

/**
 * Created by yhr on 2018/4/27.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    private LoadingPager pager ;
    protected BaseActivity mActivity ;
    Unbinder unbinder;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        pager = new LoadingPager(getContext()) {
            @Override
            protected View createLoadedView() {
                return BaseFragment.this.createSuccessView();
            }

            @Override
            protected void load() {
                BaseFragment.this.load();
            }

            @Override
            protected void changeFragment() {
                //切换的方法
                /*FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.home_rel_layout, new ForTeachingFragment());
                transaction.commit();*/
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoDownloadFragment ();
            }
        };
        return pager;
    }

    public void show(){
        if(pager != null)
            pager.show();
    }

    public void setState(LoadingPager.LoadResult result){
        if(pager != null){
            pager.setState(result);
        }
    }

    /*@Override
    public void shotToast(String msg) {
        mActivity.shotToast(msg);
    }*/

    public abstract View createSuccessView() ;
    public abstract void load();


    //启动新的activity
    public void goToActivity(Class Activity,Bundle bundle){
        Intent intent = new Intent(getActivity(),Activity);
        //携带数据
        if(bundle != null && bundle.size() != 0){
            intent.putExtra("data",bundle);
        }

        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroy();
    }
}
