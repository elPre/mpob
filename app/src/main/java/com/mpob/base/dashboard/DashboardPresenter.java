package com.mpob.base.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mpob.base.R;
import com.mpob.base.events.SpecialEventsView;
import com.mpob.base.login.LoginView;
import com.mpob.base.video.VideoView;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class DashboardPresenter implements IDashboardAPI.Presenter,IDashboardAPI.CallBack {

    private IDashboardAPI.View mView = null;
    private IDashboardAPI.Model mModel = null;
    private Context mContext = null;

    public DashboardPresenter(IDashboardAPI.View view) {
        mView = view;
        mContext = (Context) view;
        mModel = new DashboardModel();
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

    @Override
    public void sendToPage(int pageId) {

        switch (pageId) {
            case R.id.activity_dashboard_live_video_btn:
                mContext.startActivity(new Intent(mContext, VideoView.class));
                break;
            case R.id.activity_dashboard_special_events_btn:
                mContext.startActivity(new Intent(mContext, SpecialEventsView.class));
                break;
            case R.id.activity_dashboard_teacher_profile_btn:

                break;
            case R.id.activity_dashboard_my_account_btn:

                break;
            case R.id.activity_dashboard_faq_btn:

                break;
            case R.id.activity_dashboard_signout_btn:
                mContext.startActivity(new Intent(mContext, LoginView.class));
                ((Activity)mContext).finish();
                break;
            default:
                throw new IllegalStateException();
        }

    }


}
