package com.mpob.base.video;

import android.content.Context;
import android.net.Uri;

import com.mpob.base.R;
import com.mpob.base.pojos.Camera;
import com.mpob.base.video.exo.ExoPlayerMPOB;
import com.mpob.base.video.exo.IExoPlayerMPOBListener;
import com.mpob.base.video.exo.PlayerEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOLV on 3,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoModel implements
        IVideoAPI.Model,
        IVideoAPI.CallBack,
        IExoPlayerMPOBListener {

    private IVideoPlayerAPI mIVideoPlayer = null;
    private String mExtension = "";
    private Uri mUri = null;
    private Context mContext = null;
    private IVideoAPI.ModelListener mModelListener = null;
    private boolean mReleaseResources = false;


    public VideoModel(Context context) {
        mContext = context;
        mIVideoPlayer = new ExoPlayerMPOB(context,this);
        mIVideoPlayer.setStreamUrl(mUri);
        mIVideoPlayer.setStreamExtensionToPlay(mExtension);
    }

    public VideoModel(Context context, IVideoAPI.ModelListener listener) {
        mContext = context;
        mModelListener = listener;
    }


    @Override
    public void init() {
        mIVideoPlayer.init();
    }

    @Override
    public void play() {
        if (checkIVideoPlayer()) {
            if (mReleaseResources) {
                mReleaseResources =false;
                init();
            }
            mIVideoPlayer.play();
        }
    }

    @Override
    public void pause() {
        if (checkIVideoPlayer()) {
            mIVideoPlayer.pause();
        }

    }

    @Override
    public void seekTo(long position) {

    }

    @Override
    public void onConfigChanged(short portraitLandscape) {
        if (checkIVideoPlayer()) {
            mIVideoPlayer.onConfigurationChanged(portraitLandscape);
        }
    }

    @Override
    public void releasePlayerResources() {
        if (checkIVideoPlayer()) {
            mIVideoPlayer.releasePlayerResources();
            mReleaseResources = true;
        }
    }

    @Override
    public List<Camera> bringCameras() {
        return buildCameras();
    }

    @Override
    public void setAdapterCallBack(VideoAdapter videoAdapter) {
        videoAdapter.setCallBack(this);
    }

    @Override
    public void selectCameraToPlay(Camera camera) {

        if (checkIVideoPlayer() && !mReleaseResources) {
            pause();
            releasePlayerResources();
        }
        mReleaseResources =false;
        mUri = Uri.parse(camera.getCameraUrl());
        mExtension = camera.getExtension();
        mIVideoPlayer = null;
        mIVideoPlayer = new ExoPlayerMPOB(mContext,this);
        mIVideoPlayer.setStreamUrl(mUri);
        mIVideoPlayer.setStreamExtensionToPlay(mExtension);
        init();
        play();
    }

    private boolean checkIVideoPlayer() {
        return mIVideoPlayer != null;
    }

    /**
     Lets simulate the WS calls
     */
    private List<Camera> buildCameras() {
        List<Camera> list = new ArrayList<>();

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Description Camera WITH AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam1.m3u8?md5=grQlnqN5ClUvpuMqGj9BKg&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam1.jpg?md5=txBnfjtAWbHDTjCjBhCoHg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Live Streaming 3 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam2.m3u8?md5=Yqz02m12mSIwJAHzR7JMVA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam2.jpg?md5=DDOW0luTQBjltCtbQml9Eg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 4 AAC")
                .description("Live Streaming 4 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam3.m3u8?md5=rI2Afong2JBaTcZWhWP5pA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam3.jpg?md5=h5JH3c_hSRq4Up2vOQ66rg&expires=1522643749")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming VOD AAC 1")
                .description("Live Streaming VOD AAC 1")
                .url("http://demo.myparentsonboard.com/livelow/cam4.m3u8?md5=fNQzw5Sx7bk2cYTmv78VjA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam4.jpg?md5=4uGe8ejEp-bXoGyBPQlQ3A&expires=1522643749")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Description Camera WITH AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam1.m3u8?md5=grQlnqN5ClUvpuMqGj9BKg&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam1.jpg?md5=txBnfjtAWbHDTjCjBhCoHg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Live Streaming 3 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam2.m3u8?md5=Yqz02m12mSIwJAHzR7JMVA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam2.jpg?md5=DDOW0luTQBjltCtbQml9Eg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 4 AAC")
                .description("Live Streaming 4 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam3.m3u8?md5=rI2Afong2JBaTcZWhWP5pA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam3.jpg?md5=h5JH3c_hSRq4Up2vOQ66rg&expires=1522643749")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming VOD AAC 1")
                .description("Live Streaming VOD AAC 1")
                .url("http://demo.myparentsonboard.com/livelow/cam4.m3u8?md5=fNQzw5Sx7bk2cYTmv78VjA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam4.jpg?md5=4uGe8ejEp-bXoGyBPQlQ3A&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Description Camera WITH AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam1.m3u8?md5=grQlnqN5ClUvpuMqGj9BKg&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam1.jpg?md5=txBnfjtAWbHDTjCjBhCoHg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Live Streaming 3 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam2.m3u8?md5=Yqz02m12mSIwJAHzR7JMVA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam2.jpg?md5=DDOW0luTQBjltCtbQml9Eg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 4 AAC")
                .description("Live Streaming 4 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam3.m3u8?md5=rI2Afong2JBaTcZWhWP5pA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam3.jpg?md5=h5JH3c_hSRq4Up2vOQ66rg&expires=1522643749")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming VOD AAC 1")
                .description("Live Streaming VOD AAC 1")
                .url("http://demo.myparentsonboard.com/livelow/cam4.m3u8?md5=fNQzw5Sx7bk2cYTmv78VjA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam4.jpg?md5=4uGe8ejEp-bXoGyBPQlQ3A&expires=1522643749")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Description Camera WITH AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam1.m3u8?md5=grQlnqN5ClUvpuMqGj9BKg&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam1.jpg?md5=txBnfjtAWbHDTjCjBhCoHg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Live Streaming 3 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam2.m3u8?md5=Yqz02m12mSIwJAHzR7JMVA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam2.jpg?md5=DDOW0luTQBjltCtbQml9Eg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 4 AAC")
                .description("Live Streaming 4 AAC")
                .url("http://demo.myparentsonboard.com/livelow/cam3.m3u8?md5=rI2Afong2JBaTcZWhWP5pA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam3.jpg?md5=h5JH3c_hSRq4Up2vOQ66rg&expires=1522643749")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming VOD AAC 1")
                .description("Live Streaming VOD AAC 1")
                .url("http://demo.myparentsonboard.com/livelow/cam4.m3u8?md5=fNQzw5Sx7bk2cYTmv78VjA&expires=1522643749")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam4.jpg?md5=4uGe8ejEp-bXoGyBPQlQ3A&expires=1522643749")
                .extension("m3u8")
                .build());

        return list;
    }

    @Override
    public void sendMessage(PlayerEnum message) {
        switch (message){
            case ENDED:
            case IDLE:
            case BUFFERING:
                //show progress bar
                mModelListener.showSpinner();
                break;
            case READY:
                //hide progress bar
                mModelListener.hideSpinner();
                break;
        }
    }
}
