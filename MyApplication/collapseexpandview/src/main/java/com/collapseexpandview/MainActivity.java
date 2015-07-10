package com.collapseexpandview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llExpandCollapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llExpandCollapse = (LinearLayout) findViewById(R.id.ll_expand_collapse);

        findViewById(R.id.btn_collapse_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapse(llExpandCollapse, 500, 500);
            }
        });

        findViewById(R.id.btn_expand_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(llExpandCollapse, 500, 500);
            }
        });

    }

    public static void collapse(final View v, int duration, int targetHeight){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(targetHeight,200);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }


    public static void expand(final View v, int duration, int targetHeight) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }
}