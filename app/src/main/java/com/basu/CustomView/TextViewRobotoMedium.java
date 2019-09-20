package com.basu.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by suhrit on 8/24/2018.
 */

public class TextViewRobotoMedium extends AppCompatTextView {
    public TextViewRobotoMedium(Context context) {
        super(context);
        init();
    }



    public TextViewRobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewRobotoMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto/Roboto-Medium.ttf");
            setTypeface(tf);
        }
    }
}
