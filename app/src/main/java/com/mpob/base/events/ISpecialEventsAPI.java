package com.mpob.base.events;

import android.content.Context;
import android.view.GestureDetector;

import com.mpob.base.pojos.Camera;

import java.util.List;

/**
 * Created by HOLV on 23,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public interface ISpecialEventsAPI {

    //view
    interface View{
        void showProgress();
        void hideProgress();
        void loadRecyclerView(List<Camera> list);
    }

    //presenter connector
    interface Presenter{

        void init();
        void play();
        void pause();
        void seekTo(long position);
        short configChanged(short portraitLandscape);
        void releasePlayerResources();
        void setAdapterCallBack(VideoAdapter videoAdapter);

    }

    //model bring all the info alive
    interface Model {

        void init();
        void play();
        void pause();
        void seekTo(long position);
        void onConfigChanged(short portraitLandscape);
        void releasePlayerResources();
        List<Camera> bringCameras();
        void setAdapterCallBack(VideoAdapter videoAdapter);

    }

    //callbacks
    interface CallBack{
        void selectCameraToPlay(Camera camera);
    }

    interface VideoPlayerGestureDetector extends GestureDetector.OnGestureListener{
        void setContext(Context context);
        void setPlayerInterface(IVideoPlayerAPI playerInterface);
    }


    interface ModelListener{
        void showSpinner();
        void hideSpinner();
    }

}
