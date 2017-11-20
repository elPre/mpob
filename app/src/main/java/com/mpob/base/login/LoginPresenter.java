package com.mpob.base.login;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class LoginPresenter implements ILoginAPI.Presenter,ILoginAPI.CallBack {

    private ILoginAPI.View mView;
    private ILoginAPI.Model mModel;

    public LoginPresenter(ILoginAPI.View view) {
        mView = view;
        mModel = new LoginModel();
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
