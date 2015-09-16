package com.palmithor.materialdemo;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Solution found on : https://github.com/facebook/fresco/issues/22
 *
 * @since 16/09/15.
 */
public class TranslateDraweeView extends SimpleDraweeView {
    public TranslateDraweeView(Context context) {
        super(context);
    }

    public TranslateDraweeView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslateDraweeView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    public TranslateDraweeView(final Context context, final GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    // looks like overwrite this method can fix this issue
// but still don't figure out why
    public void animateTransform(final Matrix matrix) {
        invalidate();
    }
}