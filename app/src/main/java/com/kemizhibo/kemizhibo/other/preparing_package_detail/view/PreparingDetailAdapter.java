package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.bdocreader.BDocInfo;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailAdapter extends BaseAdapter {
    private FragmentManager supportFragmentManager;
    private PreparingPackageDetailBean.ContentBean.AppMaterialBean material;
    private Context context;
    private static final int TYPE_KEMISHIPIN = 0;//视频
    private static final int TYPE_USERSHIPIN = 1;//视频
    private static final int TYPE_WENDANG = 2;//文档
    private static final int TYPE_KEMIPPT = 3;//ppt
    private static final int TYPE_KEMIPUPIAN = 4;//图片
    private static final int TYPE_USERPUPIAN = 5;//图片
    private static final int TYPE_BIAOGE = 6;//表格
    private ViewPager mviewPager;
    private TextView madj;
    private ViewPager mshipinviewpager;
    private TextView mshipinadj;
    private TextView madjsucai;
    private TextView mwendang;
    private TextView mdown;
    private TextView mcheck;
    private TextView mppt;
    private TextView mcheckppt;
    private TextView mdownppt;
    private LinearLayout mlinearLayout;

    public PreparingDetailAdapter(Context context, PreparingPackageDetailBean.ContentBean.AppMaterialBean material, FragmentManager supportFragmentManager) {
        this.context = context;
        this.material = material;
        this.picurls.clear();
        this.videourls.clear();
        this.supportFragmentManager = supportFragmentManager;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0://视频
                return material.getKemiVideo();
            case 1://图片
                return material.getKemiPic();
            case 2://KEMIppt
                return material.getKemiPpt();
            case 3://文档
                return material.getKemiWord();
            case 4://表格
                return material.getKemiExcel();
            case 5://用户视频
                return material.getUserVideo();
            case 6://用户图片
                return material.getUserPic();
            case 7://用户ppt
                return material.getUserPpt();
            case 8://用户文档
                return material.getUserWord();
            case 9://用户表格
                return material.getUserExcel();
        }
       /* if (null != material.getKemiWord() && material.getKemiWord().size() > 0) {
            return material.getKemiWord().get(position);
        } else if (null != material.getUserWord() && material.getUserWord().size() > 0) {
            return material.getUserWord().get(position);
        } else if (null != material.getKemiVideo() && material.getKemiVideo().size() > 0) {
            return material.getKemiVideo().get(position);
        } else if (null != material.getUserVideo() && material.getUserVideo().size() > 0) {
            return material.getUserVideo().get(position);
        } else if (null != material.getKemiPpt() && material.getKemiPpt().size() > 0) {
            return material.getKemiPpt().get(position);
        } else if (null != material.getUserPpt() && material.getUserPpt().size() > 0) {
            return material.getUserPpt().get(position);
        } else if (null != material.getKemiExcel() && material.getKemiExcel().size() > 0) {
            return material.getKemiExcel().get(position);
        } else if (null != material.getUserExcel() && material.getUserExcel().size() > 0) {
            return material.getUserExcel().get(position);
        } else if (null != material.getKemiPic() && material.getKemiPic().size() > 0) {
            return material.getKemiPic().get(position);
        } else if (null != material.getUserPic() && material.getUserPic().size() > 0) {
            return material.getUserPic().get(position);
        }*/
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private Handler mHandler;
    private List<String> videourls = new ArrayList<>();
    private List<String> picurls = new ArrayList<>();

    private int courseId;
    private int moduleId;
    private BDocInfo docInfo;
    private boolean isjump = false;

    @Override
    public int getItemViewType(int position) {
       /* if (null != material.getKemiWord() && material.getKemiWord().size() > 0 && material.getKemiWord().get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (null != material.getUserWord() && material.getUserWord().size() > 0 && material.getUserWord().get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (null != material.getKemiVideo() && material.getKemiVideo().size() > 0 && material.getKemiVideo().get(position).getDocType() == 5) {
            return TYPE_KEMISHIPIN;
        } else if (null != material.getUserVideo() && material.getUserVideo().size() > 0 && material.getUserVideo().get(position).getDocType() == 5) {
            return TYPE_USERSHIPIN;
        } else if (null != material.getKemiPpt() && material.getKemiPpt().size() > 0 && material.getKemiPpt().get(position).getDocType() == 3) {
            return TYPE_KEMIPPT;
        } else if (null != material.getUserPpt() && material.getUserPpt().size() > 0 && material.getUserPpt().get(position).getDocType() == 3) {
            return TYPE_KEMIPPT;
        } else if (null != material.getKemiExcel() && material.getKemiExcel().size() > 0 && material.getUserExcel().get(position).getDocType() == 2) {
            return TYPE_BIAOGE;
        } else if (null != material.getUserExcel() && material.getUserExcel().size() > 0 && material.getUserExcel().get(position).getDocType() == 2) {
            return TYPE_BIAOGE;
        } else if (null != material.getKemiPic() && material.getKemiPic().size() > 0 && material.getKemiPic().get(position).getDocType() == 6) {
            return TYPE_KEMIPUPIAN;
        } else if (null != material.getUserPic() && material.getUserPic().size() > 0 && material.getKemiWord().get(position).getDocType() == 6) {
            return TYPE_USERPUPIAN;
        }*/

        return 9;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取当前条目的类型
        int itemViewType = getItemViewType(position);
        MyViewHolder holder;
        // PreparingPackageDetailBean.ContentBean.MaterialBean materialBean = material.get(position);
        Log.i("========", position + "");
        //视频 图片 ppt  word excel
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = View.inflate(context, R.layout.base_item, null);
            mlinearLayout = convertView.findViewById(R.id.base);
        }
        switch (position) {
            case 0://视频
                View view = View.inflate(context, R.layout.sucai_shipin_item, null);
                mviewPager = (ViewPager) view.findViewById(R.id.shipinviewpager);
                madj = (TextView) view.findViewById(R.id.adj);
                mlinearLayout.addView(view);
                final List<PreparingPackageDetailBean.ContentBean.DataBean> kemiVideo = material.getKemiVideo();
                mviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                    @Override
                    public Fragment getItem(int position) {
                        Fragment myFragment = new MyFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("courseid", kemiVideo.get(position).getCourseId());
                        myFragment.setArguments(bundle);
                        return myFragment;
                    }

                    @Override
                    public int getCount() {
                        return kemiVideo.size();
                    }
                });

                break;
            case 1://图片
                View view1 = View.inflate(context, R.layout.sucai_tupian_item, null);
                mviewPager = (ViewPager) view1.findViewById(R.id.viewpager);
                madjsucai = (TextView) view1.findViewById(R.id.adj);
                mlinearLayout.addView(view1);
                final List<PreparingPackageDetailBean.ContentBean.DataBean> kemiPic = material.getKemiPic();
                mviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                    @Override
                    public int getCount() {
                        return kemiPic.size();
                    }

                    @Override
                    public Fragment getItem(int position) {
                        Fragment myFragment = new MyPicFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("moduleId", kemiPic.get(position).getModuleId());
                        myFragment.setArguments(bundle);
                        return myFragment;

                    }
                });

                break;
            case 2://KEMIppt
                View view2 = View.inflate(context, R.layout.ppt_item, null);
                mppt = (TextView) view2.findViewById(R.id.mppt);
                mcheckppt = (TextView) view2.findViewById(R.id.mcheckppt);
                mdownppt = (TextView) view2.findViewById(R.id.mdownppt);
                mlinearLayout.addView(view2);
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiPpt = material.getKemiPpt();
                for (PreparingPackageDetailBean.ContentBean.DataBean kpt : kemiPpt) {
                    courseId = kpt.getCourseId();
                    moduleId = kpt.getModuleId();
                    RequestUtil.requestPPT((Activity) context, mppt, mcheckppt, mdownppt, itemViewType, moduleId);
                }
                break;
            case 3://文档
                View view3 = View.inflate(context, R.layout.wendang_item, null);
                mwendang = (TextView) view3.findViewById(R.id.mword);
                mdown = (TextView) view3.findViewById(R.id.mdown);
                mcheck = (TextView) view3.findViewById(R.id.mcheck);
                mlinearLayout.addView(view3);
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiWord = material.getKemiWord();
                for (PreparingPackageDetailBean.ContentBean.DataBean kwd : kemiWord) {
                    courseId = kwd.getCourseId();
                    moduleId = kwd.getModuleId();
                    RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, itemViewType, this.moduleId, TYPE_WENDANG);
                }

                break;
            case 4://表格
                View view4 = View.inflate(context, R.layout.wendang_item, null);
                mwendang = (TextView) view4.findViewById(R.id.mword);
                mdown = (TextView) view4.findViewById(R.id.mdown);
                mcheck = (TextView) view4.findViewById(R.id.mcheck);
                mlinearLayout.addView(view4);
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiExcel = material.getKemiExcel();
                for (PreparingPackageDetailBean.ContentBean.DataBean kel : kemiExcel) {
                    courseId = kel.getCourseId();
                    moduleId = kel.getModuleId();
                    RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, itemViewType, this.moduleId, TYPE_WENDANG);
                }
                break;
            case 5://用户视频
                View view5 = View.inflate(context, R.layout.sucai_shipin_item, null);
                mshipinviewpager = (ViewPager) view5.findViewById(R.id.shipinviewpager);
                mshipinadj = (TextView) view5.findViewById(R.id.adj);
                mlinearLayout.addView(view5);
                final List<PreparingPackageDetailBean.ContentBean.DataBean> userVideo = material.getUserVideo();
                mviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                    @Override
                    public Fragment getItem(int position) {
                        Fragment myFragment = new MyFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("courseid", userVideo.get(position).getCourseId());
                        myFragment.setArguments(bundle);
                        return myFragment;
                    }

                    @Override
                    public int getCount() {
                        return userVideo.size();
                    }
                });
                break;

            case 6://用户图片
                View view6 = View.inflate(context, R.layout.sucai_tupian_item, null);
                mviewPager = (ViewPager) view6.findViewById(R.id.viewpager);
                madjsucai = (TextView) view6.findViewById(R.id.adj);
                mlinearLayout.addView(view6);
                final List<PreparingPackageDetailBean.ContentBean.DataBean> userPic = material.getUserPic();
                mviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                    @Override
                    public int getCount() {
                        return userPic.size();
                    }

                    @Override
                    public Fragment getItem(int position) {
                        Fragment myFragment = new MyPicFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("moduleId", userPic.get(position).getModuleId());
                        myFragment.setArguments(bundle);
                        return myFragment;

                    }
                });

                break;
            case 7://用户ppt
                View view7 = View.inflate(context, R.layout.ppt_item, null);
                mppt = (TextView) view7.findViewById(R.id.mppt);
                mcheckppt = (TextView) view7.findViewById(R.id.mcheckppt);
                mdownppt = (TextView) view7.findViewById(R.id.mdownppt);
                mlinearLayout.addView(view7);
                List<PreparingPackageDetailBean.ContentBean.DataBean> userPpt = material.getUserPpt();
                for (PreparingPackageDetailBean.ContentBean.DataBean upt : userPpt) {
                    courseId = upt.getCourseId();
                    moduleId = upt.getModuleId();
                    RequestUtil.requestPPT((Activity) context, mppt, mcheck, mdownppt, itemViewType, moduleId);
                }
                break;
            case 8://用户文档
                View view8 = View.inflate(context, R.layout.wendang_item, null);
                mwendang = (TextView) view8.findViewById(R.id.mword);
                mdown = (TextView) view8.findViewById(R.id.mdown);
                mcheck = (TextView) view8.findViewById(R.id.mcheck);
                mlinearLayout.addView(view8);
                List<PreparingPackageDetailBean.ContentBean.DataBean> userWord = material.getUserWord();
                for (PreparingPackageDetailBean.ContentBean.DataBean uwd : userWord) {
                    courseId = uwd.getCourseId();
                    moduleId = uwd.getModuleId();
                    RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, itemViewType, this.moduleId, TYPE_WENDANG);
                }
                break;
            case 9://用户表格
                View view9 = View.inflate(context, R.layout.wendang_item, null);
                mwendang = (TextView) view9.findViewById(R.id.mword);
                mdown = (TextView) view9.findViewById(R.id.mdown);
                mcheck = (TextView) view9.findViewById(R.id.mcheck);
                mlinearLayout.addView(view9);
                List<PreparingPackageDetailBean.ContentBean.DataBean> userExcel = material.getUserExcel();
                for (PreparingPackageDetailBean.ContentBean.DataBean uel : userExcel) {
                    courseId = uel.getCourseId();
                    moduleId = uel.getModuleId();
                    RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, itemViewType, this.moduleId, TYPE_WENDANG);
                }
                break;
            default:
                break;
        }


        return convertView;
    }
}
