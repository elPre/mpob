package com.mpob.base.video;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoPresenter implements IVideoAPI.Presenter,IVideoAPI.CallBack {

    private IVideoAPI.View mView;
    private IVideoAPI.Model mModel;

    public VideoPresenter(IVideoAPI.View view) {
        mView = view;
        mModel = new VideoModel();
    }

    @Override
    public void onSuccess() {

        mView.hideProgress();
        mView.sendToDashboard();
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void login(String user, String pass) {
        mView.showProgress();
        mView.hideKeyboard();
        mModel.wsExecuteCall(this);
    }


}
