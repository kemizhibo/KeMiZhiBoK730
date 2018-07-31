package com.kemizhibo.kemizhibo.other.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.custom.CustomIndicator;

/**
 * Created by Administrator on 2018/7/30.
 */

public class FilterPopUtils {
    public static OnPopIndicatorClickListener onPopIndicatorClickListener;
    public static final String CATEGORY_MATERIAL = "material";
    public static final String CATEGORY_GRADE = "grade";
    public static final String CATEGORY_SEMESTER = "semester";

    public static PopupWindow show(Context context, String[] materials, String[] grades, String[] semesters, int materialSelectI, int gradeSelectI, int semesterSelectI, OnPopIndicatorClickListener listener){
        onPopIndicatorClickListener = listener;
        View popView = LayoutInflater.from(context).inflate(R.layout.pop_filter, null, false);
        CustomIndicator materialIndicator = popView.findViewById(R.id.material_indicator);
        materialIndicator.setSelectIndex(materialSelectI);
        materialIndicator.setTitles(materials);
        materialIndicator.setOnIndicatorClickListener(new CustomIndicator.OnIndicatorClickListener() {
            @Override
            public void onIndicatorClick(int position) {
                if(onPopIndicatorClickListener != null){
                    onPopIndicatorClickListener.onClick(CATEGORY_MATERIAL, position);
                }
            }
        });
        CustomIndicator gradeIndicator = popView.findViewById(R.id.grade_indicator);
        gradeIndicator.setSelectIndex(gradeSelectI);
        gradeIndicator.setTitles(grades);
        gradeIndicator.setOnIndicatorClickListener(new CustomIndicator.OnIndicatorClickListener() {
            @Override
            public void onIndicatorClick(int position) {
                if(onPopIndicatorClickListener != null){
                    onPopIndicatorClickListener.onClick(CATEGORY_GRADE, position);
                }
            }
        });
        CustomIndicator semesterIndicator = popView.findViewById(R.id.semester_indicator);
        semesterIndicator.setSelectIndex(semesterSelectI);
        semesterIndicator.setTitles(semesters);
        semesterIndicator.setOnIndicatorClickListener(new CustomIndicator.OnIndicatorClickListener() {
            @Override
            public void onIndicatorClick(int position) {
                if(onPopIndicatorClickListener != null){
                    onPopIndicatorClickListener.onClick(CATEGORY_SEMESTER, position);
                }
            }
        });
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(popView);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        return popupWindow;
    }

    public interface OnPopIndicatorClickListener{
        void onClick(String category, int position);
    }
}
