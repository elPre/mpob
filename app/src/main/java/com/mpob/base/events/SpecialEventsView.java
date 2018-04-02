package com.mpob.base.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mpob.base.R;
import com.mpob.base.pojos.Camera;

import java.util.List;

/**
 * Created by HOLV on 23,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class SpecialEventsView extends AppCompatActivity
        implements ISpecialEventsAPI.View {

    private ISpecialEventsAPI.Presenter mIPresenter = null;
    private RecyclerView mRecyclerView = null;
    private VideoAdapter mVideoAdapter = null;
    private boolean mIsLoadedList = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_special_events_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_special_event_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        if (!mIsLoadedList) {
            mIPresenter = new SpecialEventsPresenter(this);
            mIPresenter.init();
            mIsLoadedList = true;
        }

    }


    // This callback is called only when there is a saved instance previously saved using
    // onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mIsLoadedList = savedInstanceState.getBoolean("isPlaying");
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isPlaying", mIsLoadedList);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showProgress() {}

    @Override
    public void hideProgress() {}

    @Override
    public void loadRecyclerView(List<Camera> list) {
        mVideoAdapter = new VideoAdapter(getApplicationContext(), list);
        mIPresenter.setAdapterCallBack(mVideoAdapter);
        mRecyclerView.setAdapter(mVideoAdapter);
    }


}
