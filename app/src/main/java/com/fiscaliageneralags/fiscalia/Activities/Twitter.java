package com.fiscaliageneralags.fiscalia.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fiscaliageneralags.fiscalia.MainActivity;
import com.fiscaliageneralags.fiscalia.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Twitter extends AppCompatActivity {
    @BindView(R.id.comunicacion_social_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.comunicacion_social_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;
    private MaterialDialog processDialog;

    private Twitter selfInstance;
    private TweetTimelineRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selfInstance = this;
        setContentView(R.layout.activity_twitter);
        unbinder = ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        processDialog = new MaterialDialog.Builder(this)
                .content("Cargando, por favor espere.")
                .progress(true, 0)
                .build();
        processDialog.setCanceledOnTouchOutside(false);
        processDialog.show();
        Context context= this;
        // Async Task To load all the Tweets
        new AsyncTask<Context,Void,TweetTimelineRecyclerViewAdapter>() {
            @Override
            protected TweetTimelineRecyclerViewAdapter doInBackground(Context... contexts) {
                com.twitter.sdk.android.core.Twitter.initialize(context);
                UserTimeline userTimeline = new UserTimeline.Builder().screenName("fge_ags").build();
                userTimeline.next(null, new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        processDialog.dismiss();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        processDialog.dismiss();
                        Toast.makeText(context, "Ocurrido un error al cargar el contenido, por favor revise su conexión a Internet.", Toast.LENGTH_LONG).show();
                        Salir();
                    }
                });
                return new TweetTimelineRecyclerViewAdapter.Builder(context)
                        .setTimeline(userTimeline)
                        .setViewStyle(R.style.tw__TweetLightStyle)
                        .build();
            }
            @Override
            protected void onPostExecute(TweetTimelineRecyclerViewAdapter tweetTimelineRecyclerViewAdapter) {
                super.onPostExecute(tweetTimelineRecyclerViewAdapter);
                adapter = tweetTimelineRecyclerViewAdapter;
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }.execute(this);
      //  swipeRefreshLayout.setOnRefreshListener(this);
    }
    public void Salir(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //@Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        adapter.refresh(new Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void failure(TwitterException exception) {
                //Error Loading
                //TODO: Show error message to user.
            }
        });
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
