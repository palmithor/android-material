package com.palmithor.materialdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;

/**
 * @author palmithor
 * @since 16/09/15.
 */
public class DetailActivity extends BaseActivity {

    public static final String EXTRA_IMAGE = "DetailActivity:image";

    @Bind(R.id.image)
    SimpleDraweeView image;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        image.setImageURI(Uri.parse(getIntent().getStringExtra(EXTRA_IMAGE)));
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ActivityCompat.finishAfterTransition(this);
            return true;
        }
        return false;
    }

    @Override protected int getLayoutResource() {
        return R.layout.activity_detail;
    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }


    public static void launch(BaseActivity activity, View transitionView, String url) {

        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
