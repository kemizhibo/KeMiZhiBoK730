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
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import static com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder.mdownppt;
import static com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder.mlinearLayout;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailAdapter extends BaseAdapter {
    private FragmentManager supportFragmentManager;
    private PreparingPackageDetailBean.ContentBean.AppMaterialBean material;
    private Context context;
    private MyInnerAdapter myInnerAdapter;


    public PreparingDetailAdapter(Context context, PreparingPackageDetailBean.ContentBean.AppMaterialBean material, FragmentManager supportFragmentManager) {
        this.context = context;
        this.material = material;
        this.supportFragmentManager = supportFragmentManager;
    }


    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        Log.i("========pppp", position + "");
        if (position == 0) {
            return material.getKemiVideo();
        } else if (position == 1) {
            return material.getKemiPic();
        } else if (position == 2) {
            return material.getKemiPpt();
        } else if (position == 3) {
            return material.getKemiWord();
        } else if (position == 4) {
            return material.getKemiExcel();
        } else if (position == 5) {
            return material.getUserVideo();
        } else if (position == 6) {
            return material.getUserPic();
        } else if (position == 7) {
            return material.getUserPpt();
        } else if (position == 8) {
            return material.getUserWord();
        }
        return material.getUserExcel();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogUtils.i("====sucaiposition====", position + "");
        //视频 图片 ppt  word excel
        convertView = View.inflate(context, R.layout.base_item, null);
        LinearLayout mlinearLayout = convertView.findViewById(R.id.base);
      /*  List<Fragment> fragments =supportFragmentManager.getFragments();
        for (int i = fragments.size() - 1; i >= 0; i--) {
            supportFragmentManager.beginTransaction().remove(fragments.get(0));
        }*/
        switch (position) {
            case 0://视频
                final List<PreparingPackageDetailBean.ContentBean.DataBean> kemiVideo = material.getKemiVideo();
                if (kemiVideo.size() > 0) {
                    View view = View.inflate(context, R.layout.sucai_kemi_shipin_item, null);
                    ViewPager mviewPager = (ViewPager) view.findViewById(R.id.kemi_shipinviewpager);
                    mlinearLayout.addView(view);
                    Log.i("====sucaiposition====", "pppppp");
                    mviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            Fragment myFragment = new MyFragment();
                            Bundle bundle = new Bundle();
                            Log.i("====sucaiposition====", "加载视频");
                            bundle.putInt("courseid", kemiVideo.get(position).getCourseId());
                            bundle.putInt("moduleid", kemiVideo.get(position).getModuleId());
                            myFragment.setArguments(bundle);
                            return myFragment;
                        }

                        @Override
                        public int getCount() {
                            return kemiVideo.size();
                        }
                    });

                }

                break;

            case 1://图片
                final List<PreparingPackageDetailBean.ContentBean.DataBean> kemiPic = material.getKemiPic();
                if (kemiPic.size() > 0) {
                    View view1 = View.inflate(context, R.layout.sucai_kemi_tupian_item, null);
                    ViewPager mPicviewPager = (ViewPager) view1.findViewById(R.id.viewpager);
                    TextView madjsucai = (TextView) view1.findViewById(R.id.adj);
                    mlinearLayout.addView(view1);
                    Log.i("pppppp", kemiPic.size() + "");
                    mPicviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                        @Override
                        public int getCount() {
                            return kemiPic.size();
                        }

                        @Override
                        public Fragment getItem(int position) {
                            Fragment myFragment = new MyPicFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("courseid", kemiPic.get(position).getCourseId());
                            bundle.putInt("moduleid", kemiPic.get(position).getModuleId());
                            myFragment.setArguments(bundle);
                            return myFragment;

                        }
                    });
                }
                break;
            case 2://KEMIppt
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiPpt = material.getKemiPpt();
                if (null != kemiPpt && kemiPpt.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean kpt : kemiPpt) {
                        View view2 = View.inflate(context, R.layout.ppt_item, null);
                        TextView mppt = (TextView) view2.findViewById(R.id.mppt);
                        TextView mcheckppt = (TextView) view2.findViewById(R.id.mcheckppt);
                        final TextView mdownppt = (TextView) view2.findViewById(R.id.mdownppt);
                        mlinearLayout.addView(view2);
                        final int courseId = kpt.getCourseId();
                        int moduleId = kpt.getModuleId();
                        final String docName = kpt.getDocName();
                        final String contentIds = kpt.getContentIds();
                        RequestUtil.requestPPT((Activity) context, mppt, mcheckppt, mdownppt, 3, moduleId);
                        mdownppt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RequestUtil.requestSuCaiAdd((Activity) context, courseId, docName, 3, contentIds, mdownppt);
                            }
                        });
                    }
                }

                break;
            case 3://文档
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiWord = material.getKemiWord();
                if (null != kemiWord && kemiWord.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean kwd : kemiWord) {
                        View view3 = View.inflate(context, R.layout.wendang_item, null);
                        TextView mwendang = (TextView) view3.findViewById(R.id.mword);
                        TextView mdown = (TextView) view3.findViewById(R.id.mdown);
                        TextView mcheck = (TextView) view3.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view3);
                        int moduleId1 = kwd.getModuleId();
                        RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, 1, moduleId1);
                    }
                }


                break;
            case 4://表格
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiExcel = material.getKemiExcel();
                if (null != kemiExcel && kemiExcel.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean kel : kemiExcel) {
                        View view4 = View.inflate(context, R.layout.wendang_item, null);
                        TextView mexcal = (TextView) view4.findViewById(R.id.mword);
                        TextView mexcaldown = (TextView) view4.findViewById(R.id.mdown);
                        TextView mexcalcheck = (TextView) view4.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view4);
                        int moduleId2 = kel.getModuleId();
                        RequestUtil.requestDoc((Activity) context, mexcal, mexcaldown, mexcalcheck, 2, moduleId2);
                    }
                }

                break;
            case 5://用户视频
                final List<PreparingPackageDetailBean.ContentBean.DataBean> userVideo = material.getUserVideo();
                if (userVideo.size() > 0) {
                    View view5 = View.inflate(context, R.layout.sucai_shipin_item, null);
                    ViewPager musershipinviewpager = (ViewPager) view5.findViewById(R.id.shipinviewpager);
                    mlinearLayout.addView(view5);
                    Log.i("ppppppzzzz", userVideo.size() + "");
                    musershipinviewpager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            MyUserVideoFragment myUserVideoFragment = new MyUserVideoFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("usermoduleid", userVideo.get(position).getModuleId());
                            bundle.putInt("usercourseid", userVideo.get(position).getCourseId());
                            myUserVideoFragment.setArguments(bundle);
                            return myUserVideoFragment;
                        }

                        @Override
                        public int getCount() {
                            return userVideo.size();
                        }
                    });
                }

                break;

            case 6://用户图片
                final List<PreparingPackageDetailBean.ContentBean.DataBean> userPic = material.getUserPic();
                if (userPic.size() > 0) {
                    View view6 = View.inflate(context, R.layout.sucai_tupian_item, null);
                    ViewPager mUserviewPager = (ViewPager) view6.findViewById(R.id.user_viewpager);
                    mlinearLayout.addView(view6);
                    mUserviewPager.setAdapter(new FragmentPagerAdapter(supportFragmentManager) {
                        @Override
                        public int getCount() {
                            return userPic.size();
                        }

                        @Override
                        public Fragment getItem(int position) {
                            Fragment myFragment = new MyUserPicFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("courseid", userPic.get(position).getCourseId());
                            bundle.putInt("moduleid", userPic.get(position).getModuleId());
                            myFragment.setArguments(bundle);
                            return myFragment;

                        }
                    });
                }
                break;
            case 7://用户ppt
                List<PreparingPackageDetailBean.ContentBean.DataBean> userPpt = material.getUserPpt();

                if (null != userPpt && userPpt.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean upt : userPpt) {
                        View view7 = View.inflate(context, R.layout.ppt_item, null);
                        TextView mPpt = (TextView) view7.findViewById(R.id.mppt);
                        TextView mCheckppt = (TextView) view7.findViewById(R.id.mcheckppt);
                        final TextView mdownppt1 = (TextView) view7.findViewById(R.id.mdownppt);
                        mlinearLayout.addView(view7);
                        final int courseId1 = upt.getCourseId();
                        final int moduleId1 = upt.getModuleId();
                        final String docName1 = upt.getDocName();
                        final String contentIds1 = upt.getContentIds();
                        RequestUtil.requestPPT((Activity) context, mPpt, mCheckppt, mdownppt1, 3, moduleId1);
                        mdownppt1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RequestUtil.requestSuCaiAdd((Activity) context, courseId1, docName1, 3, contentIds1, mdownppt1);
                            }
                        });
                    }
                }


                break;
            case 8://用户文档
                List<PreparingPackageDetailBean.ContentBean.DataBean> userWord = material.getUserWord();
                if (null != userWord && userWord.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean uwd : userWord) {
                        View view8 = View.inflate(context, R.layout.wendang_item, null);
                        TextView muserwendang = (TextView) view8.findViewById(R.id.mword);
                        TextView muserdown = (TextView) view8.findViewById(R.id.mdown);
                        TextView musercheck = (TextView) view8.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view8);
                        int moduleId3 = uwd.getModuleId();
                        RequestUtil.requestDoc((Activity) context, muserwendang, muserdown, musercheck, 1, moduleId3);
                    }
                }

                break;
            case 9://用户表格
                List<PreparingPackageDetailBean.ContentBean.DataBean> userExcel = material.getUserExcel();
                if (null != userExcel && userExcel.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean uel : userExcel) {
                        View view9 = View.inflate(context, R.layout.wendang_item, null);
                        TextView muserexcalword = (TextView) view9.findViewById(R.id.mword);
                        TextView muserexcalmdown = (TextView) view9.findViewById(R.id.mdown);
                        TextView muserexcalcheck = (TextView) view9.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view9);
                        int moduleId4 = uel.getModuleId();
                        RequestUtil.requestDoc((Activity) context, muserexcalword, muserexcalmdown, muserexcalcheck, 2, moduleId4);
                    }
                }
                break;
            default:
                break;
        }


        return convertView;
    }

    class MyInnerAdapter extends FragmentPagerAdapter {
        private List<PreparingPackageDetailBean.ContentBean.DataBean> kemiVideos;

        public MyInnerAdapter(FragmentManager fm, List<PreparingPackageDetailBean.ContentBean.DataBean> ko) {
            super(fm);
            kemiVideos = ko;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            Log.i("====加载视频====", "加载视频");
            for (PreparingPackageDetailBean.ContentBean.DataBean dataBean : kemiVideos) {
                bundle.putInt("courseid", dataBean.getCourseId());
                bundle.putInt("moduleid", dataBean.getModuleId());
                myFragment.setArguments(bundle);
            }
            return myFragment;
        }

        @Override
        public int getCount() {
            return kemiVideos.size();
        }
    }
}
