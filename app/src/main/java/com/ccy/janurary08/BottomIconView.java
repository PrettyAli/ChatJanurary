package com.ccy.janurary08;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 底部Tab图标类,
 * 分正常的状态和选中的状态
 * 根据滑动的偏移量改变alpha值
 * 然后显示出来
 * <p>
 * 感谢 wuyexiong
 * Created by wuyexiong on 4/25/15.
 * Modify by JFSL on 2016-11-20 20:05
 * Created by chanchaoyue on 2018/1/7.
 */

@SuppressLint("AppCompatCustomView")
public class BottomIconView extends ImageView {

    public static final int START_POSITION = 0;
    public static final int ALPHA_MAX = 255;
    //当前的alpha值
    private int mAlphaCurrent = 0;
    //选中时的图标
    private Bitmap bIconSelected;
    //未选中时的图标
    private Bitmap bIconNormal;
    //选中时的矩形(限制绘制范围)
    private Rect mRectSelected;
    //未选中时的矩形(限制绘制范围)
    private Rect mRectNormal;
    //画笔
    private Paint bPain;

    public BottomIconView(Context context) {
        super(context);
    }

    public BottomIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BottomIconView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    public final void init(int normal, int selected) {
        bIconNormal = createBitmap(normal);
        bIconSelected = createBitmap(selected);
        if (bIconNormal == null && bIconSelected == null) {
            mRectNormal = new Rect(START_POSITION, START_POSITION, bIconNormal.getWidth(), bIconNormal.getHeight());
            mRectSelected = new Rect(START_POSITION, START_POSITION, bIconSelected.getWidth(), bIconSelected.getHeight());
        }
        bPain = new Paint();
    }

    private Bitmap createBitmap(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bPain == null) {
            return;
        }
        bPain.setAlpha(ALPHA_MAX - mAlphaCurrent);
        canvas.drawBitmap(bIconNormal, null, mRectNormal, bPain);
        bPain.setAlpha(mAlphaCurrent);
        canvas.drawBitmap(bIconSelected, null, mRectSelected, bPain);
    }

    public final void changeSelectedAlpha(int alphaCurrent) {
        mAlphaCurrent = alphaCurrent;
        invalidate();
    }

    public final void transformPage(float offset) {
        changeSelectedAlpha((int) (ALPHA_MAX * (1 - offset)));
    }

}
