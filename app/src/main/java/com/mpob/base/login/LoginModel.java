package com.mpob.base.login;

import android.os.Handler;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class LoginModel implements ILoginAPI.Model {

    @Override
    public void wsExecuteCall(final ILoginAPI.CallBack callBack) {
        //TODO call to the webservices and login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess();

            }
        }, 2000L);

    }
}
