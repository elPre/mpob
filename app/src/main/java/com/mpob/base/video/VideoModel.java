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
    public List<Camera> bingCameras() {
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
                .name("Live Streaming 1")
                .description("Description Camera NO AAC")
                .url("http://demo.myparentsonboard.com/live/cam1.m3u8?md5=CG_ukr4HvM4idm2aUABgww&expires=1521311782")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 2")
                .description("Description Camera WITH AAC")
                .url("http://demo.myparentsonboard.com/live/cam2.m3u8?md5=hodVAzBlgMPl6DQWiWjopA&expires=1521311782")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 3 AAC")
                .description("Live Streaming 3 AAC")
                .url("http://demo.myparentsonboard.com/live/cam3.m3u8?md5=o9ni7pfNZbaf655-nU3FUQ&expires=1521311782")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming 4 AAC")
                .description("Live Streaming 4 AAC")
                .url("http://demo.myparentsonboard.com/live/cam4.m3u8?md5=6Il84EhihQzwpAwJCwxxbA&expires=1521311782")
                .extension("m3u8")
                .build());


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming VOD AAC 1")
                .description("Live Streaming VOD AAC 1")
                .url("http://demo.myparentsonboard.com/ondemand/slide.mp4?md5=C5kCbtYBWZSwsWtQifDkLA&expires=1521311782")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming VOD AAC 2")
                .description("Live Streaming VOD AAC 2")
                .url("http://demo.myparentsonboard.com/ondemand/972d3473-1781-4de3-9358-a819d2562d29.mp4?md5=3INmnZntnqw-piUDH8dKYg&expires=1520365293")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("WOWZA LIVE STREAM")
                .description("Description Camera")
                .url("https://wowzaec2demo.streamlock.net/live/bigbuckbunny/playlist.m3u8")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("LIVE STREAM MPOB 1")
                .description("Description Camera 1")
                .url("http://demo.myparentsonboard.com/livelow/cam1.m3u8?md5=VkPkKS8vlCxiwXxafoW5NQ&expires=1520298689")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_drums))
                .name("LIVE STREAM MPOB 2")
                .description("Description Camera 2")
                .url("http://demo.myparentsonboard.com/livelow/cam2.m3u8?md5=SbtzKnyu_tGywDS5yQTScg&expires=1520298689")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("LIVE STREAM MPOB 3")
                .description("Description Camera 3")
                .url("http://demo.myparentsonboard.com/livelow/cam3.m3u8?md5=x_aU_hIRn0XX6XJVANjYcw&expires=1520298689")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("LIVE STREAM MPOB VOD 1")
                .description("Description Camera VOD 1 HTTP")
                .url("http://demo.myparentsonboard.com/ondemand/0d265856-cf5f-429d-8101-220dc6e35148.mp4?md5=zG9E8s2EySzFiJjK3NS-Rg&expires=1520365293")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("LIVE STREAM MPOB VOD 2")
                .description("Description Camera VOD 2 HTTPS")
                .url("https://demo.myparentsonboard.com/ondemand/603c4501-577a-4f4c-a614-8dd3eef39076.mp4?md5=AAxUVv7yovMMoYoAziFFvw&expires=1520365293")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("LIVE STREAM MPOB VOD 3")
                .description("Description Camera VOD 3 HTTP")
                .url("http://demo.myparentsonboard.com/ondemand/972d3473-1781-4de3-9358-a819d2562d29.mp4?md5=3INmnZntnqw-piUDH8dKYg&expires=1520365293")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("LIVE STREAM MPOB VOD 4")
                .description("Description Camera VOD 4 HTTPS")
                .url("https://demo.myparentsonboard.com/ondemand/9e6329d6-3ec7-4984-9267-e9e9e2f8b152.mp4?md5=TcH4rb_eUIZg5tvHQbairg&expires=1520365293")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Bunny Camera")
                .description("This video was easy to find because everybody use it")
                .url("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_drums))
                .name("Vegas Camera")
                .description("The whole world that NASA is exploring live TV")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("Forest Camera")
                .description("This forest is very cool")
                .url("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_vegas))
                .name("Vegas Camera")
                .description("This Vegas is very cool")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/t/4m/dwmyb_H264_IOS_,100BR,200BR,350BR,500BR,.mp4.csmil/master.m3u8?__b__=350&__a__=off")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_livingroom))
                .name("LivingRoom Camera")
                .description("This Vegas is very cool")
                .url("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam1))
                .name("Backyard Camera")
                .description("This stream is a wowza example that is a livestream")
                .url("http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_lake))
                .name("Lake Camera")
                .description("Cool skateboard tricks in the comby that is a real pool bakc in the day")
                .url("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam2))
                .name("Front Camera")
                .description("This series is a Crackle Original very hilarious")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/e/0m/cnmyb_H264_IOS_,100BR,200BR,350BR,500BR,650BR,800BR,.mp4.csmil/master.m3u8?__b__=650&__a__=off")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_cam3))
                .name("Entrance Camera")
                .description("This lake is very cool")
                .url("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Bunny Camera")
                .description("This video was easy to find because everybody use it")
                .url("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_drums))
                .name("Vegas Camera")
                .description("The whole world that NASA is exploring live TV")
                .url("https://nasa-i.akamaihd.net/hls/live/253565/NASA-NTV1-Public/master.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_forest))
                .name("Forest Camera")
                .description("This forest is very cool")
                .url("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8")
                .extension("m3u8")
                .build());
        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_vegas))
                .name("Vegas Camera")
                .description("This Vegas is very cool")
                .url("http://crackleusiosns-vh.akamaihd.net/i/1/t/4m/dwmyb_H264_IOS_,100BR,200BR,350BR,500BR,.mp4.csmil/master.m3u8?__b__=350&__a__=off")
                .extension("m3u8")
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

    @Override
    public void sendMessage(PlayerEnum message) {
        switch (message){
            case BUFFERING:
                //show progress bar
                mModelListener.showSpinner();
                break;
            case ENDED:
                //show progress bar
                mModelListener.showSpinner();
                break;
            case IDLE:
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
