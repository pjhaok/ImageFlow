package com.example.imageflow.view;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.example.imageflow.R;

public class IFCardView extends CardView {


    public IFCardView(@NonNull Context context) {
        super(context);

      setupView(context);

    }


    public IFCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupView(context);

    }

    public IFCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);

    }


    private void setupView(Context context) {


        this.setRadius(2);
        this.setClickable(true);
        this.setFocusable(true);
        this.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, R.animator.lift_on_touch));
    }

}