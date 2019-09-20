package com.basu.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class AutoCompleteEditTextSansPro extends android.support.v7.widget.AppCompatAutoCompleteTextView {
    public AutoCompleteEditTextSansPro(Context context) {
        super(context);
        init();
    }

    public AutoCompleteEditTextSansPro(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoCompleteEditTextSansPro(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/sourcesanspro/SourceSansPro-Regular.ttf");
            setTypeface(tf);
        }
    }
}
