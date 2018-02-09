package com.mpob.base.dashboard;

/**
 * Created by HOLV on 11,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public interface IDashboardAPI {

    //view
    interface View{
        void showProgress();
        void hideProgress();
    }

    //presenter connector
    interface Presenter{
        void login(String user, String pass);
        void sendToPage(int pageId);
    }

    //model bring all the info alive
    interface Model {
        void wsExecuteForgotPassword(CallBack callBack);
    }

    //callbacks
    interface CallBack{
        void onSuccess();
        void onFailure();
    }

}
