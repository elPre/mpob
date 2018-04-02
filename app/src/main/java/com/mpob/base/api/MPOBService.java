package com.mpob.base.api;

import android.content.Context;

/**
 * Created by HOLV on 19,March,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class MPOBService {

    private MpobAPI mpobAPI = null;
    private Context mContext = null;
    private String mUrlService = null;

    public String getUrlService() {
        return mUrlService;
    }

    public void setUrlService(String mUrlService) {
        this.mUrlService = mUrlService;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void MPOBService() {
        mpobAPI = new Service().createService(mContext, MpobAPI.class, mUrlService);
    }


}
