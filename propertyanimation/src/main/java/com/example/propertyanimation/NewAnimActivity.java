package com.example.propertyanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class NewAnimActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_anim);
        imageView = (ImageView) findViewById(R.id.iv_point);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnScale:
                doScale();
                break;
            case R.id.btnRotain:
                doRotaion();
                break;
            case R.id.btnSaleAndHide:
                doScaleAndHide();
                break;
            case R.id.btnLeft2RightHide:
                doLeft2RightHide();
                break;
            case R.id.btnDown:
                doDown();
                break;
        }

    }

    /**
     * 缩放的操作
     */
    private void doScale() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX",
                1.0f, 0f);
        anim1.setDuration(100);
        anim1.start();
        anim1.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setScaleX(1f);//在动画结束的时候将图像还原
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 旋转的操作
     */
    private void doRotaion() {
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "rotationX",
                0f, 180f);
        anim2.setDuration(1000);
        anim2.start();
    }

    /**
     * 旋转和缩放操作
     */
    private void doScaleAndHide() {
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        //此处三个值表示从可见到不可见,再到可见
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(imageView, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }

    /**
     * 旋转和缩放操作
     */
    private void doLeft2RightHide() {
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationX", 0,
                1080, 0);
        //此处三个值表示从可见到不可见,再到可见
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(imageView, pvhY, pvhX).setDuration(1000).start();
    }

    /**
     * 自由下落
     */
    private void doDown() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);

            }
        });
    }
}
