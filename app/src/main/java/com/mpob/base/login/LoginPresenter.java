package com.mpob.base.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mpob.base.R;
import com.mpob.base.dashboard.DashboardView;
import com.mpob.base.forgot.ForgotView;
import com.mpob.base.pojos.User;


/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class LoginPresenter implements ILoginAPI.Presenter,ILoginAPI.CallBack {

    private ILoginAPI.View mView = null;
    private ILoginAPI.Model mModel = null;
    private Context mContext = null;

    public LoginPresenter(ILoginAPI.View view) {
        mView = view;
        mContext = (Context) view;
        mModel = new LoginModel();
    }

    @Override
    public void onSuccess() {
        mView.hideProgress();
        mContext.startActivity(new Intent(mContext, DashboardView.class));
        ((Activity) mContext).finish();
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

    @Override
    public void executeAction(int action, User user) {
        switch (action) {
            case R.id.login_activity_btn:
                login(user.getUserName(), user.getPassword());
                break;
            case R.id.login_activity_link_forgot:
                mContext.startActivity(new Intent(mContext,ForgotView.class));
                break;
            default:
                throw new IllegalStateException();
        }
    }

}
