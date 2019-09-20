package com.basu.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by suhrit on 8/24/2018.
 */

public class EditTextSourceSansPro extends AppCompatEditText {
    public EditTextSourceSansPro(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextSourceSansPro(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextSourceSansPro(Context context) {
        super(context);
        init();
    }

    private void init() {

        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/sourcesanspro/SourceSansPro-Regular.ttf");
            setTypeface(tf);
        }
    }
}
