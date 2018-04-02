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
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/04313bb7-203f-4c96-b8fc-ab17342cdb96.mp4?md5=moIPekTyQ9anvdFHGKzT6A&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/04313bb7-203f-4c96-b8fc-ab17342cdb96.jpg?md5=DE01AuXgliqvgOmGnxJcig&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/09cef70c-71b6-46c9-a219-f297469f7010.mp4?md5=xbIBw78Y2BjxLXbhZUe1iw&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/09cef70c-71b6-46c9-a219-f297469f7010.jpg?md5=Kh8f66sCGUQ7y9K0a8IFgw&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/10e04409-0f20-4ba4-a7ff-c6b58f957110.mp4?md5=qAzLgsPU8hHDAmDzu6Fjmg&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/10e04409-0f20-4ba4-a7ff-c6b58f957110.jpg?md5=zl60VNGl87evNMcVTTHotg&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/37fe202e-e891-4a0e-a165-1c909f95f8cf.mp4?md5=jXxFkwBREUoInJunK92gcg&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/37fe202e-e891-4a0e-a165-1c909f95f8cf.jpg?md5=h-Abv77Y9GMCSZIU_MvWQQ&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/5219e5fa-e73e-4863-837f-e56ea037d375.mp4?md5=VrZxNFRqlh_a6980eCNG8g&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/5219e5fa-e73e-4863-837f-e56ea037d375.jpg?md5=oQmudqGibO6L_MiwBpThPg&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/b769ab3c-da37-4daa-aa1b-175f136235b3.mp4?md5=lgM9fj6tgkgcnnNc_tCHvw&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/b769ab3c-da37-4daa-aa1b-175f136235b3.jpg?md5=xdKx31ic8r5TTRl_Cx6N5Q&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/b89ae94a-fe07-4d5e-ad4c-10f92540a870.mp4?md5=Ovd3p2vK8UOw2EEFSPDEMw&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/b89ae94a-fe07-4d5e-ad4c-10f92540a870.jpg?md5=dCoGSF-888aZ5lHUSR5uFw&expires=1522647349")
                .extension("mp4")
                .build());

        list.add(new Camera.Builder().picture(mContext.getResources().getDrawable(R.drawable.thumb_bunny))
                .name("Name Video 1")
                .description("Description VOD 1")
                .url("http://demo.myparentsonboard.com/ondemand/ce6e2b87-272c-4b08-83eb-922190c2f99e.mp4?md5=VO2-sNdjZMcyNvTqL5hCmw&expires=1522647349")
                .thumbnail("http://demo.myparentsonboard.com/ondemand/ce6e2b87-272c-4b08-83eb-922190c2f99e.jpg?md5=1PKkR28EZPz2zMTvTKuLuA&expires=1522647349")
                .extension("mp4")
                .build());


        return list;
    }


}
