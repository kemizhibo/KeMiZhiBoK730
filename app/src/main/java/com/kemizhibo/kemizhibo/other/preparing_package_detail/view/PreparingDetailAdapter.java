package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.bdocreader.BDocInfo;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.utils.DownloadUtil;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil.getDocMessage;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailAdapter extends BaseAdapter {
    private FragmentManager supportFragmentManager;
    private PreparingPackageDetailBean.ContentBean.AppMaterialBean material;
    private Context context;


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
        return null;
    }

    @Override
    public long getItemId(int position) {
        Log.i("========pppp", position + "");
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LogUtils.i("====sucaiposition====", position + "");
        //视频 图片 ppt  word excel
        convertView = View.inflate(context, R.layout.base_item, null);
        LinearLayout mlinearLayout = convertView.findViewById(R.id.base);
        switch (position) {
            case 0://视频
                final List<PreparingPackageDetailBean.ContentBean.DataBean> kemiVideo = material.getKemiVideo();
                if (kemiVideo.size() > 0) {
                    View view = View.inflate(context, R.layout.sucai_kemi_shipin_item, null);
                    ViewPager mviewPager = (ViewPager) view.findViewById(R.id.kemi_shipinviewpager);
                    mlinearLayout.addView(view);
                    Log.i("====sucaiposition====", "pppppp");
                    mviewPager.setAdapter(new FragmentStatePagerAdapter(supportFragmentManager) {
                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                            MyFragment myFragment = (MyFragment) super.instantiateItem(container, position);;
                            Bundle bundle = new Bundle();
                            Log.i("====sucaiposition====", "加载视频");
                            bundle.putInt("courseid", kemiVideo.get(position).getCourseId());
                            bundle.putInt("moduleid", kemiVideo.get(position).getModuleId());
                            bundle.putString("url", kemiVideo.get(position).getUrl());
                            bundle.putString("logo", kemiVideo.get(position).getVideoLogo());
                            bundle.putString("introduce", kemiVideo.get(position).getVideoIntroduce());
                            myFragment.setArguments(bundle);
                            return myFragment;
                        }

                        @Override
                        public Fragment getItem(int position) {
                            Fragment myFragment = new MyFragment();
                          /*  Bundle bundle = new Bundle();
                            Log.i("====sucaiposition====", "加载视频");
                            bundle.putInt("courseid", kemiVideo.get(position).getCourseId());
                            bundle.putInt("moduleid", kemiVideo.get(position).getModuleId());
                            myFragment.setArguments(bundle);*/
                            return myFragment;
                        }

                        @Override
                        public int getCount() {
                            return kemiVideo.size();
                        }

                        @Override
                        public int getItemPosition(@NonNull Object object) {
                            return PagerAdapter.POSITION_NONE;
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
                    mPicviewPager.setAdapter(new FragmentStatePagerAdapter(supportFragmentManager) {
                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                            MyPicFragment myFragment = (MyPicFragment) super.instantiateItem(container, position);;
                            Bundle bundle = new Bundle();
                            bundle.putInt("courseid", kemiPic.get(position).getCourseId());
                            bundle.putInt("moduleid", kemiPic.get(position).getModuleId());
                            bundle.putString("url", kemiPic.get(position).getUrl());
                            bundle.putString("introduce", kemiPic.get(position).getVideoIntroduce());
                            myFragment.setArguments(bundle);
                            return myFragment;
                        }
                        @Override
                        public int getItemPosition(@NonNull Object object) {
                            return PagerAdapter.POSITION_NONE;
                        }
                        @Override
                        public int getCount() {
                            return kemiPic.size();
                        }

                        @Override
                        public Fragment getItem(int position) {
                            Fragment myFragment = new MyPicFragment();
                          /*  Bundle bundle = new Bundle();
                            bundle.putInt("courseid", kemiPic.get(position).getCourseId());
                            bundle.putInt("moduleid", kemiPic.get(position).getModuleId());
                            myFragment.setArguments(bundle);*/
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
                        //RequestUtil.requestPPT((Activity) context, mppt, mcheckppt, mdownppt, 3, moduleId);
                        mppt.setText(docName);
                        final PreparingPackageDetailBean.ContentBean.DataBean mkpt = kpt;
                        mcheckppt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //isjump = true;

                                getDocMessage((Activity) context, String.valueOf(mkpt.getDocId()), null, 3, true);
                            }
                        });
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
                        final TextView mdown = (TextView) view3.findViewById(R.id.mdown);
                        final TextView mcheck = (TextView) view3.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view3);
                        int moduleId1 = kwd.getModuleId();
                        //RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, 1, moduleId1);
                        final PreparingPackageDetailBean.ContentBean.DataBean mkwd = kwd;
                        mwendang.setText(kwd.getDocName());
                        mdown.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast("开始下载");
                                //   if (url != null) {
                                //getDocMessage((Activity) context, String.valueOf(mkwd.getDocId()), mdown, 1, false);
                                //  }
                                DownloadUtil.get().download(mkwd.getUrl(), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                                    @Override
                                    public void onDownloadSuccess() {
                                        Activity activity = (Activity) context;
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mdown.setText("已下载");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onDownloading(int progress) {

                                    }

                                    @Override
                                    public void onDownloadFailed() {

                                    }
                                });
                            }
                        });
                        mcheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //isjump = true;
                                LogUtils.i("DocId", "mkel.getDocId():" + mkwd.getDocId());
                                getDocMessage((Activity) context, String.valueOf(mkwd.getDocId()), mcheck, 1, true);
                            }
                        });
                    }
                }


                break;
            case 4://表格
                List<PreparingPackageDetailBean.ContentBean.DataBean> kemiExcel = material.getKemiExcel();
                if (null != kemiExcel && kemiExcel.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean kel : kemiExcel) {
                        View view4 = View.inflate(context, R.layout.wendang_item, null);
                        ImageView icon = (ImageView) view4.findViewById(R.id.icon);
                        icon.setImageResource(R.mipmap.ic_launcher);
                        TextView mexcal = (TextView) view4.findViewById(R.id.mword);
                        final TextView mexcaldown = (TextView) view4.findViewById(R.id.mdown);
                        final TextView mexcalcheck = (TextView) view4.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view4);
                        int moduleId2 = kel.getModuleId();
                        //RequestUtil.requestDoc((Activity) context, mexcal, mexcaldown, mexcalcheck, 2, moduleId2);
                        final PreparingPackageDetailBean.ContentBean.DataBean mkel = kel;
                        mexcal.setText(mkel.getDocName());
                        mexcaldown.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast("开始下载");
                                //   if (url != null) {
                                //getDocMessage((Activity) context, String.valueOf(mkel.getDocId()), mexcaldown, 2, false);
                                //  }
                                DownloadUtil.get().download(mkel.getUrl(), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                                    @Override
                                    public void onDownloadSuccess() {
                                        Activity activity = (Activity) context;
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mexcaldown.setText("已下载");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onDownloading(int progress) {

                                    }

                                    @Override
                                    public void onDownloadFailed() {

                                    }
                                });

                            }
                        });
                        mexcalcheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //isjump = true;
                                getDocMessage((Activity) context, String.valueOf(mkel.getDocId()), mexcalcheck, 2, true);
                            }
                        });
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
                    musershipinviewpager.setAdapter(new FragmentStatePagerAdapter(supportFragmentManager) {
                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                            MyUserVideoFragment myFragment = (MyUserVideoFragment) super.instantiateItem(container, position);;
                            Bundle bundle = new Bundle();
                            bundle.putInt("usermoduleid", userVideo.get(position).getModuleId());
                            bundle.putInt("usercourseid", userVideo.get(position).getCourseId());
                            bundle.putString("url", userVideo.get(position).getUrl());
                            bundle.putString("logo", userVideo.get(position).getUserVideoLogo());
                            bundle.putString("introduce", userVideo.get(position).getIntroduce());
                            myFragment.setArguments(bundle);
                            return myFragment;
                        }
                        @Override
                        public int getItemPosition(@NonNull Object object) {
                            return PagerAdapter.POSITION_NONE;
                        }
                        @Override
                        public Fragment getItem(int position) {
                            MyUserVideoFragment myUserVideoFragment = new MyUserVideoFragment();
                           /* Bundle bundle = new Bundle();
                            bundle.putInt("usermoduleid", userVideo.get(position).getModuleId());
                            bundle.putInt("usercourseid", userVideo.get(position).getCourseId());
                            myUserVideoFragment.setArguments(bundle);*/
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
                    mUserviewPager.setAdapter(new FragmentStatePagerAdapter(supportFragmentManager) {
                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                            MyUserPicFragment myFragment = (MyUserPicFragment) super.instantiateItem(container, position);;
                            Bundle bundle = new Bundle();
                            bundle.putInt("courseid", userPic.get(position).getCourseId());
                            bundle.putInt("moduleid", userPic.get(position).getModuleId());
                            bundle.putString("url", userPic.get(position).getUrl());
                            bundle.putString("introduce", userPic.get(position).getIntroduce());
                            myFragment.setArguments(bundle);
                            return myFragment;
                        }
                        @Override
                        public int getItemPosition(@NonNull Object object) {
                            return PagerAdapter.POSITION_NONE;
                        }
                        @Override
                        public int getCount() {
                            return userPic.size();
                        }

                        @Override
                        public Fragment getItem(int position) {
                            Fragment myFragment = new MyUserPicFragment();
                          /*  Bundle bundle = new Bundle();
                            bundle.putInt("courseid", userPic.get(position).getCourseId());
                            bundle.putInt("moduleid", userPic.get(position).getModuleId());
                            myFragment.setArguments(bundle);*/
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
                        final TextView mCheckppt = (TextView) view7.findViewById(R.id.mcheckppt);
                        final TextView mdownppt1 = (TextView) view7.findViewById(R.id.mdownppt);
                        mlinearLayout.addView(view7);
                        final int courseId1 = upt.getCourseId();
                        final int moduleId1 = upt.getModuleId();
                        final String docName1 = upt.getDocName();
                        final String contentIds1 = upt.getContentIds();
                        //RequestUtil.requestPPT((Activity) context, mPpt, mCheckppt, mdownppt1, 3, moduleId1);
                        final PreparingPackageDetailBean.ContentBean.DataBean mupt = upt;
                        mPpt.setText(mupt.getDocName());

                        mCheckppt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //isjump = true;
                                getDocMessage((Activity) context, String.valueOf(mupt.getDocId()), mCheckppt, 3, true);
                            }
                        });
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
                    for (final PreparingPackageDetailBean.ContentBean.DataBean uwd : userWord) {
                        View view8 = View.inflate(context, R.layout.wendang_item, null);
                        TextView muserwendang = (TextView) view8.findViewById(R.id.mword);
                        final TextView muserdown = (TextView) view8.findViewById(R.id.mdown);
                        final TextView musercheck = (TextView) view8.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view8);
                        int moduleId3 = uwd.getModuleId();
                        //RequestUtil.requestDoc((Activity) context, muserwendang, muserdown, musercheck, 1, moduleId3);
                        final PreparingPackageDetailBean.ContentBean.DataBean muwd = uwd;
                        muserwendang.setText(muwd.getDocName());
                        muserdown.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast("开始下载");
                                //   if (url != null) {
                                //getDocMessage((Activity) context, String.valueOf(muwd.getDocId()), muserdown, 1, false);
                                //  }
                                DownloadUtil.get().download(muwd.getUrl(), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                                    @Override
                                    public void onDownloadSuccess() {
                                        Activity activity = (Activity) context;
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                muserdown.setText("已下载");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onDownloading(int progress) {

                                    }

                                    @Override
                                    public void onDownloadFailed() {

                                    }
                                });
                            }
                        });
                        musercheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //isjump = true;
                                LogUtils.i("DocId", "mkel.getDocId():" + uwd.getDocId());
                                getDocMessage((Activity) context, String.valueOf(muwd.getDocId()), musercheck, 1, true);
                            }
                        });
                    }
                }

                break;
            case 9://用户表格
                List<PreparingPackageDetailBean.ContentBean.DataBean> userExcel = material.getUserExcel();
                if (null != userExcel && userExcel.size() > 0) {
                    for (PreparingPackageDetailBean.ContentBean.DataBean uel : userExcel) {
                        View view9 = View.inflate(context, R.layout.wendang_item, null);
                        ImageView icon = (ImageView) view9.findViewById(R.id.icon);
                        icon.setImageResource(R.mipmap.ic_launcher);
                        TextView muserexcalword = (TextView) view9.findViewById(R.id.mword);
                        final TextView muserexcalmdown = (TextView) view9.findViewById(R.id.mdown);
                        final TextView muserexcalcheck = (TextView) view9.findViewById(R.id.mcheck);
                        mlinearLayout.addView(view9);
                        int moduleId4 = uel.getModuleId();
                        //RequestUtil.requestDoc((Activity) context, muserexcalword, muserexcalmdown, muserexcalcheck, 2, moduleId4);
                        final PreparingPackageDetailBean.ContentBean.DataBean muel = uel;
                        muserexcalword.setText(muel.getDocName());
                        muserexcalmdown.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast("开始下载");
                                //   if (url != null) {
                                //RequestUtil.getDocMessage((Activity) context, String.valueOf(muel.getDocId()), muserexcalmdown, 2, false);
                                //  }
                                DownloadUtil.get().download(muel.getUrl(), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                                    @Override
                                    public void onDownloadSuccess() {
                                        Activity activity = (Activity) context;
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                muserexcalmdown.setText("已下载");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onDownloading(int progress) {

                                    }

                                    @Override
                                    public void onDownloadFailed() {

                                    }
                                });
                            }
                        });
                        muserexcalcheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //isjump = true;
                                RequestUtil.getDocMessage((Activity) context, String.valueOf(muel.getDocId()), muserexcalcheck, 2, true);
                            }
                        });
                    }
                }
                break;
            default:
                break;
        }


        return convertView;
    }
}
