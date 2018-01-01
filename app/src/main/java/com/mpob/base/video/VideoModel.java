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
    private Uri mUri = Uri.parse("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8");


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
