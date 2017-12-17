package com.mpob.base.events;

import android.os.Handler;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class SpecialEventsModel implements ISpecialEventsAPI.Model {

    @Override
    public void wsExecuteCall(final ISpecialEventsAPI.CallBack callBack) {
        //TODO call to the webservices and login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess();

            }
        }, 2000L);

    }
}
