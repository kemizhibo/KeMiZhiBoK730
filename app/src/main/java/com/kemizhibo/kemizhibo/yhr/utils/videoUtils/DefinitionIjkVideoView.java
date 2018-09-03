package com.kemizhibo.kemizhibo.yhr.utils.videoUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.dueeeke.videoplayer.player.IjkVideoView;
import java.util.LinkedHashMap;

import retrofit2.http.HTTP;

/**
 * 清晰度切换
 * Created by xinyu on 2018/4/16.
 */

public class DefinitionIjkVideoView extends IjkVideoView implements DefinitionMediaPlayerControl {
    private LinkedHashMap<String, String> mDefinitionMap;
    private String mCurrentDefinition;

    public DefinitionIjkVideoView(@NonNull Context context) {
        super(context);
    }

    public DefinitionIjkVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DefinitionIjkVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LinkedHashMap<String, String> getDefinitionData() {
        return mDefinitionMap;
    }

    @Override
    public void switchDefinition(String definition) {
        String url = mDefinitionMap.get(definition);
        if (definition.equals(mCurrentDefinition)) return;
        mCurrentUrl = url;
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
        mCurrentDefinition = definition;
    }

    public void setDefinitionVideos(LinkedHashMap<String, String> videos) {
        this.mDefinitionMap = videos;
        this.mCurrentUrl = getValueFromLinkedMap(videos, 0);
    }

    public static String getValueFromLinkedMap(LinkedHashMap<String, String> map, int index) {
        int currentIndex = 0;
        for (String key : map.keySet()) {
            if (currentIndex == index) {
                return map.get(key);
            }
            currentIndex++;
        }
        return null;
    }

    //重写，改变清晰度
   /* @Override
    protected void startPrepare(boolean needReset) {
        super.startPrepare(needReset);
        setPlayerState(STATE_PREPARING);
        setPlayerState(isFullScreen()?PLAYER_FULL_SCREEN:PLAYER_NORMAL);
        String url =mCurrentUrl;
        DefinitionIjkVideoView.super.startPrepare(needReset);
    }*/
}
