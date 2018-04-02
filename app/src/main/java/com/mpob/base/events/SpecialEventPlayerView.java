package com.mpob.base.events;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.util.Util;
import com.mpob.base.R;
import com.mpob.base.events.exo.ExoPlayerMPOB;
import com.mpob.base.events.exo.IExoPlayerMPOBListener;
import com.mpob.base.events.exo.PlayerEnum;
import com.mpob.base.utils.Constants;

/**
 * Created by HOLV on 23,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */
public class SpecialEventPlayerView extends AppCompatActivity
        implements IExoPlayerMPOBListener{

    private IVideoPlayerAPI mIVideoPlayerAPI = null;
    private boolean mIsPlaying = false;
    private String mUrlToPlay = null;
    private String mExtensionToPlay = null;

    private GestureDetectorCompat mDetector = null;
    private ISpecialEventsAPI.VideoPlayerGestureDetector mGestureDetectorListener = null;
    private View thisView = null;
    private ProgressBar mProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_events);

        mProgressBar = (ProgressBar) findViewById(R.id.activity_video_progress);

        //makes transparent the status and navigation bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mIVideoPlayerAPI = new ExoPlayerMPOB(this,this);

        mUrlToPlay = Constants.DATA_TO_HOLD_URL;
        mExtensionToPlay = Constants.DATA_TO_HOLD_EXT;

        mIVideoPlayerAPI.setStreamUrl(Uri.parse(mUrlToPlay));
        mIVideoPlayerAPI.setStreamExtensionToPlay(mExtensionToPlay);
        mIVideoPlayerAPI.init();
        mIVideoPlayerAPI.play();

        thisView = this.getWindow().getDecorView().findViewById(R.id.transparent_layout_test);
        thisView.requestFocus();

        mGestureDetectorListener = new VideoGestureDetector(this);
        mGestureDetectorListener.setPlayerInterface(mIVideoPlayerAPI);
        mDetector = new GestureDetectorCompat(this, mGestureDetectorListener);
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
    public void onPause() {
        if (Util.SDK_INT <= 23) {
            mIsPlaying = true;
            mIVideoPlayerAPI.releasePlayerResources();
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (Util.SDK_INT > 23) {
            mIsPlaying = true;
            mIVideoPlayerAPI.releasePlayerResources();
        }
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            if (mIsPlaying) {
                mIVideoPlayerAPI.setStreamUrl(Uri.parse(mUrlToPlay));
                mIVideoPlayerAPI.setStreamExtensionToPlay(mExtensionToPlay);
                mIVideoPlayerAPI.init();
                mIVideoPlayerAPI.play();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23)) {
            if (mIsPlaying) {
                mIVideoPlayerAPI.setStreamUrl(Uri.parse(mUrlToPlay));
                mIVideoPlayerAPI.setStreamExtensionToPlay(mExtensionToPlay);
                mIVideoPlayerAPI.init();
                mIVideoPlayerAPI.play();
            }
        }
    }


    // This callback is called only when there is a saved instance previously saved using
    // onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mIsPlaying = savedInstanceState.getBoolean("isPlaying");
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isPlaying", mIsPlaying);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }


    @Override
    public void sendMessage(PlayerEnum message) {
        switch (message){
            case BUFFERING:
                //show progress bar
                mProgressBar.setVisibility(View.VISIBLE);
                break;
            case ENDED:
            case IDLE:
            case READY:
                //hide progress bar
                mProgressBar.setVisibility(View.INVISIBLE);
                break;
        }
    }


}
