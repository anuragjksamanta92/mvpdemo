package com.basu.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.basu.utils.FontCache;


/**
 * Created by root on 19/3/18.
 */

public class OpenSansSemiBoldTextView extends TextView {

    public OpenSansSemiBoldTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public OpenSansSemiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public OpenSansSemiBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/sourcesanspro/SourceSansPro-Bold.ttf", context);
        setTypeface(customFont);
    }
}

