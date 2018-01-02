package com.mpob.base.video;

import android.content.Context;
import android.net.Uri;

import com.mpob.base.R;
import com.mpob.base.pojos.Camera;
import com.mpob.base.video.exo.ExoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOLV on 3,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoModel implements IVideoAPI.Model, IVideoAPI.CallBack {

    private IVideoPlayerAPI mIVideoPlayer = null;
    private String mExtension = "m3u8";
    private Uri mUri = Uri.parse("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8");
    private Context mContext = null;


    public VideoModel(Context context) {
        mContext = context;
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

    @Override
    public List<Camera> bingCameras() {

        List<Camera> list = new ArrayList<>();
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Bunny Camera")
                .description("This bunny is very cool")
                .url("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("Forest Camera")
                .description("This forest is very cool")
                .url("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_lake))
                .name("Lake Camera")
                .url("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8")
                .description("This lake is very cool")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_vegas))
                .name("Vegas Camera")
                .description("This Vegas is very cool")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam1))
                .name("Backyard Camera")
                .description("This bunny is very cool")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam2))
                .name("Front Camera")
                .description("This forest is very cool")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam3))
                .name("Entrance Camera")
                .description("This lake is very cool")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_livingroom))
                .name("LivingRoom Camera")
                .description("This Vegas is very cool")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_drums))
                .name("Vegas Camera")
                .description("This Vegas is very cool")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());

        return list;
    }

    @Override
    public void setAdapterCallBack(VideoAdapter videoAdapter) {
        videoAdapter.setCallBack(this);
    }

    @Override
    public void selectCameraToPlay(Camera camera) {
        pause();
        releasePlayerResources();
        mUri = Uri.parse(camera.getCameraUrl());
        mIVideoPlayer = null;
        mIVideoPlayer = new ExoPlayer(mContext);
        mIVideoPlayer.setStreamUrl(mUri);
        mIVideoPlayer.setStreamExtensionToPlay(mExtension);
        init();
        play();
    }
}
