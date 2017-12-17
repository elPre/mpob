package com.mpob.base.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by hectorleyvavillanueva on 12/11/17.
 */

public class RetainedFragment extends Fragment {


    private IVideoAPI.Presenter mPresenter;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(IVideoAPI.Presenter data) {
        this.mPresenter = data;
    }

    public IVideoAPI.Presenter getData() {
        return mPresenter;
    }


}
