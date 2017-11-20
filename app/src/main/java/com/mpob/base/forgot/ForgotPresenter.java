package com.mpob.base.forgot;

import com.mpob.base.login.ILoginAPI;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class ForgotPresenter implements ILoginAPI.Presenter,ILoginAPI.CallBack {

    private IForgotPasswordAPI.View mView = null;
    private IForgotPasswordAPI.Model mModel = null;

    public ForgotPresenter(IForgotPasswordAPI.View view) {
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
