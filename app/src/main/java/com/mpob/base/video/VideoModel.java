package com.mpob.base.video;

import android.content.Context;
import android.net.Uri;

import com.mpob.base.video.exo.ExoPlayer;

/**
 * Created by HOLV on 3,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoModel implements IVideoAPI.Model {

    private IVideoPlayerAPI mIVideoPlayer = null;
    private String mExtension = "m3u8";
    private Uri mUri = Uri.parse("http://crackleusiosns-vh.akamaihd.net/i/1/e/0m/cnmyb_H264_IOS_,100BR,200BR,350BR,500BR,650BR,800BR,.mp4.csmil/master.m3u8?__b__=650&__a__=off");

    public VideoModel(Context context) {
        mIVideoPlayer = new ExoPlayer(context);
        mIVideoPlayer.setStreamUrl(mUri);
        mIVideoPlayer.setStreamExtensionToPlay(mExtension);
    }


    @Override
    public void init() {
        mIVideoPlayer.init();
    }

    @Override
    public void play() {
        mIVideoPlayer.play();
    }

    @Override
    public void pause() {
        mIVideoPlayer.pause();
    }

    @Override
    public void seekTo(long position) {

    }

    @Override
    public void onConfigChanged(short portraitLandscape) {
        mIVideoPlayer.onConfigurationChanged(portraitLandscape);
    }

    @Override
    public void releasePlayerResources() {
        mIVideoPlayer.releasePlayerResources();
    }
}
