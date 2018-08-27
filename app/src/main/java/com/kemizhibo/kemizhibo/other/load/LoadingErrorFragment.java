package com.kemizhibo.kemizhibo.other.load;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kemizhibo.kemizhibo.R;

/**
 * Created by Administrator on 2018/8/27.
 */

public class LoadingErrorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.loading_error_page, null);
    }
}
