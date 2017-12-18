package com.mpob.base.video;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.util.Util;
import com.mpob.base.R;

/**
 * Created by HOLV on 3,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoView extends AppCompatActivity
        implements IVideoAPI.View, View.OnClickListener  {

    private IVideoAPI.Presenter mIPresenter = null;
    private ProgressBar mProgressBar = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_video);
        //mProgressBar = (ProgressBar) findViewById(R.id.activity_video_progress_bar);
        mIPresenter = new VideoPresenter(this);
        mIPresenter.init();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //mIPresenter = mRetainedFragment.getData();
        // Checks the orientation of the screen
        //set player to height to 230dp and width to match_parent
        mIPresenter.configChanged((short)newConfig.orientation);

    }

    @Override
    public void showProgress() {
        //mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void onPause() {
        if (Util.SDK_INT <= 23) {
            mIPresenter.releasePlayerResources();
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (Util.SDK_INT > 23) {
            mIPresenter.releasePlayerResources();
        }
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            mIPresenter.init();
            mIPresenter.play();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23)) {
            mIPresenter.init();
            mIPresenter.play();
        }
    }


}
