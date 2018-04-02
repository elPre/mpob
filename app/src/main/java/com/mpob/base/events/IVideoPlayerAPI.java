package com.mpob.base.events;

import android.net.Uri;

/**
 * Created by HOLV on 23,February,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public interface IVideoPlayerAPI {

    void init();
    void play();
    void pause();
    void seekTo(long position);
    void setStreamUrl(Uri uri);
    void setStreamExtensionToPlay(String extension);
    void onConfigurationChanged(short portraitLandscape);
    void releasePlayerResources();
    void showControls();
    void hideControls();

}


