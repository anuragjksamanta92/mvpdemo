package com.basu.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by suhrit on 8/24/2018.
 */

public class SourceSansProItalic extends AppCompatTextView {
    public SourceSansProItalic(Context context) {
        super(context);
        init();
    }



    public SourceSansProItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SourceSansProItalic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/sourcesanspro/SourceSansPro-Italic.ttf");
            setTypeface(tf);
        }
    }
}
