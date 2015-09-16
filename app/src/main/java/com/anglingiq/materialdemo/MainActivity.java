package com.anglingiq.materialdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        setupRecyclerView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean enableBackButton() {
        return false;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExampleAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void setupToolbar() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("YO homie");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        adapter.list.add(0, "http://static.fjcdn.com/pictures/It+s+an+old+meme+how+i+feel+when+i+see_e83de6_3481193.jpg");
                        adapter.notifyItemInserted(0);
                        recyclerView.scrollToPosition(0);
                        break;
                    case R.id.action_remove:
                        adapter.list.remove(0);
                        adapter.notifyItemRemoved(0);
                        recyclerView.scrollToPosition(0);
                        break;
                    case R.id.action_open:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        } else {
                            startActivity(intent);
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
        private ArrayList<String> list = new ArrayList<String>() {{
            add("http://static.fjcdn.com/pictures/It+s+an+old+meme+how+i+feel+when+i+see_e83de6_3481193.jpg");
            add("https://thechive.files.wordpress.com/2014/06/ex-girlfriend-meme-14.jpg");
            add("http://rs2img.memecdn.com/thug-life_o_752874.jpg");
            add("http://cdn0.dailydot.com/uploaded/images/original/2015/4/9/Mr_Rogers.png");
            add("http://s.quickmeme.com/img/e8/e849d91ad0841af515b0b1d55acf5877b1bef22f8121aad8ac5137ccc2871dcc.jpg");
            add("http://s.quickmeme.com/img/c9/c9c9573e46b3fb7bd6003c62958f4e9bbe9b305801c1e14dff0ab955172c0f74.jpg");
        }};


        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final String url = list.get(position);
            Picasso.Builder builder = new Picasso.Builder(MainActivity.this);
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                }
            });
            builder.build().load(url).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    DetailActivity.launch(MainActivity.this, v, url);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.image)
            ImageView image;

            public ViewHolder(final View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
