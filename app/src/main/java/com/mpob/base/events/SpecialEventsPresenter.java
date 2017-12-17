package com.mpob.base.events;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class SpecialEventsPresenter implements ISpecialEventsAPI.Presenter, ISpecialEventsAPI.CallBack {

    private ISpecialEventsAPI.View mView;
    private ISpecialEventsAPI.Model mModel;

    public SpecialEventsPresenter(ISpecialEventsAPI.View view) {
        mView = view;
        mModel = new SpecialEventsModel();
    }

    @Override
    public void onSuccess() {

        mView.hideProgress();

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void login(String user, String pass) {
        mView.showProgress();
        mModel.wsExecuteCall(this);
    }


}
