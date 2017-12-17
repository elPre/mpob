package com.mpob.base.events;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public interface ISpecialEventsAPI {

    //view
    interface View{
        void showProgress();
        void hideProgress();
    }

    //presenter connector
    interface Presenter{
        void login(String user, String pass);
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
