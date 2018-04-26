package com.mpob.base.events;


import android.content.Context;
import android.content.Intent;

import com.mpob.base.R;
import com.mpob.base.pojos.Camera;
import com.mpob.base.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOLV on 24,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class SpecialEventsModel implements
        ISpecialEventsAPI.Model,
        ISpecialEventsAPI.CallBack{


    private Context mContext = null;


    public SpecialEventsModel(Context context) {
        mContext = context;
    }


    @Override
    public void init() {

    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void seekTo(long position) {

    }

    @Override
    public void onConfigChanged(short portraitLandscape) {

    }

    @Override
    public void releasePlayerResources() {

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

        Intent intent = new Intent(mContext.getApplicationContext(),SpecialEventPlayerView.class);
        intent.putExtra(camera.getCameraUrl(), Constants.CAMERA_URL);
        intent.putExtra(camera.getExtension(), Constants.CAMERA_EXTENSION);

        Constants.DATA_TO_HOLD_URL = camera.getCameraUrl();
        Constants.DATA_TO_HOLD_EXT = camera.getExtension();

        mContext.startActivity(intent);

    }


    /**
     Lets simulate the WS calls
     */
    private List<Camera> buildCameras() {

        List<Camera> list = new ArrayList<>();


        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Description Camera WITH AAC")
                .url("https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/gear1/prog_index.m3u8")
                .thumbnail("http://demo.myparentsonboard.com/Thumbnails/Thumb_Cam1.jpg?md5=txBnfjtAWbHDTjCjBhCoHg&expires=1522643749")
                .extension("m3u8")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Live Streaming")
                .description("Live Streaming 3 AAC")
                .url("https://tungsten.aaplimg.com/VOD/bipbop_adv_example_v2/master.m3u8")
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


}
