package com.mpob.base.video;

import android.content.Context;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoPresenter implements IVideoAPI.Presenter {

    private IVideoAPI.View mView;
    private IVideoAPI.Model mModel;

    public VideoPresenter(IVideoAPI.View view) {
        mView = view;
        mModel = new VideoModel((Context) view);
    }

    @Override
    public void init() {
        mModel.init();
        mView.loadRecyclerView(mModel.bingCameras());
        mView.showProgress();
    }

    @Override
    public void play() {
        mView.hideProgress();
        mModel.play();
    }

    @Override
    public void pause() {
        mModel.pause();
    }

    @Override
    public void seekTo(long position) {

    }

    @Override
    public short configChanged(short portraitLandscape) {
        mModel.onConfigChanged(portraitLandscape);
        return portraitLandscape;
    }

    @Override
    public void releasePlayerResources() {
        mModel.releasePlayerResources();
    }

    @Override
    public void setAdapterCallBack(VideoAdapter videoAdapter) {
        mModel.setAdapterCallBack(videoAdapter);
    }
}
