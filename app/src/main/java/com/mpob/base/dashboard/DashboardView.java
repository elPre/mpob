package com.mpob.base.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mpob.base.R;

/**
 * Created by HOLV on 15,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class DashboardView extends AppCompatActivity
        implements IDashboardAPI.View, View.OnClickListener  {

    private Button mButtonLiveStream = null;
    private Button mButtonSpecialEvents = null;
    private Button mButtonTeacherProfile = null;
    private Button mButtonMyAccount = null;
    private Button mButtonFaq = null;
    private Button mSignOut = null;
    private ProgressBar mProgressBar = null;
    private IDashboardAPI.Presenter mIPresenter = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        mIPresenter = new DashboardPresenter(this);
        mButtonLiveStream = (Button) findViewById(R.id.activity_dashboard_live_video_btn);
        mButtonSpecialEvents = (Button) findViewById(R.id.activity_dashboard_special_events_btn);
        mButtonTeacherProfile = (Button) findViewById(R.id.activity_dashboard_teacher_profile_btn);
        mButtonMyAccount = (Button) findViewById(R.id.activity_dashboard_my_account_btn);
        mButtonFaq = (Button) findViewById(R.id.activity_dashboard_faq_btn);
        mSignOut = (Button) findViewById(R.id.activity_dashboard_signout_btn);

        mButtonLiveStream.setOnClickListener(this);
        mButtonSpecialEvents.setOnClickListener(this);
        mButtonTeacherProfile.setOnClickListener(this);
        mButtonMyAccount.setOnClickListener(this);
        mButtonFaq.setOnClickListener(this);
        mSignOut.setOnClickListener(this);

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        mIPresenter.sendToPage(v.getId());
    }



}
