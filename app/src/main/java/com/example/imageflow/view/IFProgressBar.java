package com.example.imageflow.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.example.imageflow.R;

public class IFProgressBar extends ProgressBar {


    public IFProgressBar(Context context) {
        super(context);

        setupResource();
        setupAnimator();
    }

    public IFProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupResource();
        setupAnimator();
    }

    public IFProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupResource();
        setupAnimator();
    }

    public IFProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        setupResource();
        setupAnimator();
    }


    private void setupResource() {


        Drawable indeterminate = getResources().getDrawable(R.drawable.cir_pb);
        this.setIndeterminateDrawable(indeterminate);

    }

    private void setupAnimator() {

        ObjectAnimator anim = ObjectAnimator.ofInt(this, "progress", 0, 100);
        //  anim.setDuration(20000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

    }





}
