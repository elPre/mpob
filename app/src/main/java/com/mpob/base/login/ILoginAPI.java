package com.mpob.base.login;

import com.mpob.base.pojos.User;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public interface ILoginAPI {

    //view
    interface View{
        void showProgress();
        void hideProgress();
        void sendToDashboard();
        void hideKeyboard();
        void showKeyboard();
    }

    //presenter connector
    interface Presenter{
        void login(String user, String pass);
        void executeAction(int action, User user);
    }

    //model bring all the info alive
    interface Model {
        void wsExecuteCall(CallBack callBack);
    }

    //callbacks
    interface CallBack{
        void onSuccess();
        void onFailure();
    }

}
