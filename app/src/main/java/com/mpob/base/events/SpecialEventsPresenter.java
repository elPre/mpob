package com.mpob.base.events;

import android.content.Context;

/**
 * Created by HOLV on 23,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class SpecialEventsPresenter implements
        ISpecialEventsAPI.Presenter{

    private ISpecialEventsAPI.View mView = null;
    private ISpecialEventsAPI.Model mModel = null;

    public SpecialEventsPresenter(ISpecialEventsAPI.View view) {
        mView = view;
        mModel = new SpecialEventsModel((Context) view);
    }


    @Override
    public void init() {
        mView.loadRecyclerView(mModel.bringCameras());
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
    public short configChanged(short portraitLandscape) {
        return 0;
    }

    @Override
    public void releasePlayerResources() {

    }

    @Override
    public void setAdapterCallBack(VideoAdapter videoAdapter) {
        mModel.setAdapterCallBack(videoAdapter);
    }


}
