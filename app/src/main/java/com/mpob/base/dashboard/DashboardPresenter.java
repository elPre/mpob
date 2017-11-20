package com.mpob.base.dashboard;

import com.mpob.base.login.ILoginAPI;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class DashboardPresenter implements ILoginAPI.Presenter,ILoginAPI.CallBack {

    private IDashboardAPI.View mView = null;
    private IDashboardAPI.Model mModel = null;

    public DashboardPresenter(IDashboardAPI.View view) {
        mView = view;

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void login(String user, String pass) {

    }


}
