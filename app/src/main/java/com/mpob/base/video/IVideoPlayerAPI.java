package com.mpob.base.video;

import android.net.Uri;

/**
 * Created by HOLV on 27,November,2017
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

}


