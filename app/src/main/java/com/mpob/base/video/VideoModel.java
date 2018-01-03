package com.mpob.base.video;

import android.content.Context;
import android.net.Uri;

import com.mpob.base.R;
import com.mpob.base.pojos.Camera;
import com.mpob.base.video.exo.ExoPlayerMPOB;

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
    private Uri mUri = null;
    private Context mContext = null;


    public VideoModel(Context context) {
        mContext = context;
        mIVideoPlayer = new ExoPlayerMPOB(context);
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
        return buildCameras();
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
        mIVideoPlayer = new ExoPlayerMPOB(mContext);
        mIVideoPlayer.setStreamUrl(mUri);
        mIVideoPlayer.setStreamExtensionToPlay(mExtension);
        init();
        play();
    }


    private List<Camera> buildCameras() {
        List<Camera> list = new ArrayList<>();
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Bunny Camera")
                .description("This video was easy to find because everybody use it")
                .url("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_drums))
                .name("Vegas Camera")
                .description("The whole world that NASA is exploring live TV")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("Forest Camera")
                .description("This forest is very cool")
                .url("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_vegas))
                .name("Vegas Camera")
                .description("This Vegas is very cool")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/t/4m/dwmyb_H264_IOS_,100BR,200BR,350BR,500BR,.mp4.csmil/master.m3u8?__b__=350&__a__=off")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_livingroom))
                .name("LivingRoom Camera")
                .description("This Vegas is very cool")
                .url("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam1))
                .name("Backyard Camera")
                .description("This stream is a wowza example that is a livestream")
                .url("http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_lake))
                .name("Lake Camera")
                .description("Cool skateboard tricks in the comby that is a real pool bakc in the day")
                .url("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam2))
                .name("Front Camera")
                .description("This series is a Crackle Original very hilarious")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/e/0m/cnmyb_H264_IOS_,100BR,200BR,350BR,500BR,650BR,800BR,.mp4.csmil/master.m3u8?__b__=650&__a__=off")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam3))
                .name("Entrance Camera")
                .description("This lake is very cool")
                .url("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Bunny Camera")
                .description("This video was easy to find because everybody use it")
                .url("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_drums))
                .name("Vegas Camera")
                .description("The whole world that NASA is exploring live TV")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("Forest Camera")
                .description("This forest is very cool")
                .url("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_vegas))
                .name("Vegas Camera")
                .description("This Vegas is very cool")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/t/4m/dwmyb_H264_IOS_,100BR,200BR,350BR,500BR,.mp4.csmil/master.m3u8?__b__=350&__a__=off")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_livingroom))
                .name("LivingRoom Camera")
                .description("This Vegas is very cool")
                .url("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam1))
                .name("Backyard Camera")
                .description("This stream is a wowza example that is a livestream")
                .url("http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_lake))
                .name("Lake Camera")
                .description("Cool skateboard tricks in the comby that is a real pool bakc in the day")
                .url("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam2))
                .name("Front Camera")
                .description("This series is a Crackle Original very hilarious")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/e/0m/cnmyb_H264_IOS_,100BR,200BR,350BR,500BR,650BR,800BR,.mp4.csmil/master.m3u8?__b__=650&__a__=off")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam3))
                .name("Entrance Camera")
                .description("This lake is very cool")
                .url("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .build());



        return list;
    }

}
