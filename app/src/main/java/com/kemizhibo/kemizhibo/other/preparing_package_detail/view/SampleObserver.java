package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.downloader.DocDownloadObserver;
import com.baidu.bdocreader.downloader.DocDownloadableItem;
import com.baidu.bdocreader.downloader.DocDownloadableItem.DownloadStatus;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.yhr.MyApplication;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SampleObserver extends DocDownloadObserver {

    private  int type;
    TextView tv;
    BDocInfo externDocInfo;
    Handler handler = new Handler(Looper.getMainLooper());

    public SampleObserver(TextView textView, BDocInfo docInfo,int type) {
        this.tv = textView;
        this.externDocInfo = docInfo;
        this.type = type;
    }



    @Override
    public void update(DocDownloadableItem downloader) {
        final float progress = downloader.getProgress();

        // DownloadStatus状态比较详尽，但界面显示时仅需要几类状态即可(下载中、已暂停、完成)
        final String statusForUI = getStatusForUI(downloader.getStatus());

        String failReson = downloader.getFailReason();
        int errorCode = downloader.getErrorCode();
        String locaDirForThisDoc = downloader.getLocalAbsolutePath();

        if (externDocInfo.getDocId().equals(downloader.getDocId()) && !TextUtils.isEmpty(locaDirForThisDoc)) {
            externDocInfo.setLocalFileDir(locaDirForThisDoc);
        }

        final StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("progress:").append(progress).append("%\t").append("state:").append(statusForUI).append("\n")
                .append("errorCode:").append(errorCode).append("\n")
                .append("failReson:").append(failReson).append("\n").append("savedPath:").append(locaDirForThisDoc);
        handler.post(new Runnable() {

            @Override
            public void run() {
                // tv.setText(sbuilder.toString());
                MyViewHolder myViewHolder = new MyViewHolder();
                Log.i("=======",type+"");
                if (progress == 100) {
                    if (type==1) {
                        tv.setText("已下载");
                    } else if (type==3) {
                        tv.setText("已加入授课");
                    }
                } else if ("ERROR".equals(statusForUI)) {
                    Toast.makeText(MyApplication.getContext(), "下载失败", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    /**
     * DownloadStatus状态比较详尽，但界面显示时仅需要几类状态即可(下载中、已暂停、完成)
     *
     * @param status
     * @return
     */
    public static String getStatusForUI(DownloadStatus status) {
        String statusForUI = null;

        if (status == DownloadStatus.PENDING || status == DownloadStatus.DOWNLOADING) {
            statusForUI = "downloading";
        } else if (status == DownloadStatus.PAUSED || status == DownloadStatus.ERROR) {
            statusForUI = "paused";
        } else {
            statusForUI = status.name(); // status: "completed" / "deleted manually"
        }

        return statusForUI;
    }
}
