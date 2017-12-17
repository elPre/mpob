package com.mpob.base.login;

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
import com.mpob.base.pojos.User;
import com.mpob.base.utils.Utils;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class LoginView extends AppCompatActivity
        implements ILoginAPI.View, View.OnClickListener  {

    private EditText mEmail = null;
    private EditText mPassword = null;
    private TextView mForgotPassword = null;
    private Button mButton = null;
    private ProgressBar mProgressBar = null;
    private ILoginAPI.Presenter mIPresenter = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mIPresenter = new LoginPresenter(this);

        mEmail = (EditText) findViewById(R.id.login_activity_mail);
        mPassword = (EditText) findViewById(R.id.login_activity_password);
        mButton = (Button) findViewById(R.id.login_activity_btn);
        mForgotPassword = Utils.convertToLink((TextView) findViewById(R.id.login_activity_link_forgot));
        mForgotPassword.setClickable(true);
        mProgressBar = (ProgressBar) findViewById(R.id.login_activity_progress_bar);

        mButton.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);

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

        User user = new User();
        user.setUserName(Utils.getETData(mEmail));
        user.setPassword(Utils.getETData(mPassword));

        mIPresenter.executeAction(v.getId(),user);

    }

    @Override
    public void sendToDashboard() {
        startActivity(new Intent(LoginView.this, DashboardView.class));
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
