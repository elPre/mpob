package com.mpob.base.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mpob.base.R;
import com.mpob.base.dashboard.DashboardView;
import com.mpob.base.forgot.ForgotView;
import com.mpob.base.utils.Utils;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoView extends AppCompatActivity
        implements IVideoAPI.View, View.OnClickListener  {

    private EditText mEmail = null;
    private EditText mPassword = null;
    private TextView mForgotPassword = null;
    private Button mButton = null;
    private ProgressBar mProgressBar = null;
    private IVideoAPI.Presenter mIPresenter = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

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
        switch (v.getId()) {
            case R.id.login_activity_btn:
                mIPresenter.login(Utils.getETData(mEmail), Utils.getETData(mPassword));
                break;
            case R.id.login_activity_link_forgot:
                startActivity(new Intent(VideoView.this,ForgotView.class));
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public void sendToDashboard() {
        startActivity(new Intent(VideoView.this, DashboardView.class));
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void showKeyboard() {

    }


}
