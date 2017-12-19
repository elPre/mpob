package com.mpob.base.video;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private GestureDetectorCompat mDetector = null;
    private IVideoAPI.VideoPlayerGestureDetector mGestureDetectorListener = null;
    private View thisView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mIPresenter = new VideoPresenter(this);

        thisView = this.getWindow().getDecorView().findViewById(android.R.id.content);
        thisView.requestFocus();

        mGestureDetectorListener = new VideoGestureDetector(this);
        mDetector = new GestureDetectorCompat(this,mGestureDetectorListener);
        // we will use gestures where easy to hint player actions
        thisView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                // Be sure to call the superclass implementation
                return true;
            }
        });

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
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
                || newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mIPresenter.configChanged((short)newConfig.orientation);

        }


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
