package com.mpob.base.events;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HOLV on 23,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoGestureDetector implements  ISpecialEventsAPI.VideoPlayerGestureDetector {

    private static final String TAG = VideoGestureDetector.class.getSimpleName();
    private Context mContext = null;
    private boolean mFlagHide = false;
    private IVideoPlayerAPI mIVideoPlayerAPI = null;

    public VideoGestureDetector(Context context) {
        mContext = context;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown(MotionEvent)");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress(MotionEvent e)");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp(MotionEvent e)");

        if (mFlagHide) {
            showSystemUI();
            mFlagHide = false;
        } else {
            hideSystemUI();
            mFlagHide = true;
        }

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public void setPlayerInterface(IVideoPlayerAPI playerInterface) {
        mIVideoPlayerAPI = playerInterface;
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        ((Activity) mContext).getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        mIVideoPlayerAPI.hideControls();
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        ((Activity) mContext).getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mIVideoPlayerAPI.showControls();
    }

}
