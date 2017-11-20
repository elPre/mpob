package com.mpob.base.forgot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.mpob.base.R;

/**
 * Created by HOLV on 10,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class ForgotView extends AppCompatActivity
        implements IForgotPasswordAPI.View, View.OnClickListener  {

    private EditText mEmail = null;
    private Button mButton = null;
    private ProgressBar mProgressBar = null;
    private IForgotPasswordAPI.Presenter mIPresenter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_passoword);



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

                break;
            default:
                throw new IllegalStateException();
        }
    }



}
