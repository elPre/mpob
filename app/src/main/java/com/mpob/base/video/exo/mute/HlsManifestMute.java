package com.mpob.base.video.exo.mute;

import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;

/**
 * Created by HOLV on 10,April,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class HlsManifestMute {

    /**
     * The master playlist of an HLS stream.
     */
    public final HlsMasterPlaylist masterPlaylist;
    /**
     * A snapshot of a media playlist referred to by {@link #masterPlaylist}.
     */
    public final HlsMediaPlaylist mediaPlaylist;

    /**
     * @param masterPlaylist The master playlist.
     * @param mediaPlaylist The media playlist.
     */
    HlsManifestMute(HlsMasterPlaylist masterPlaylist, HlsMediaPlaylist mediaPlaylist) {
        this.masterPlaylist = masterPlaylist;
        this.mediaPlaylist = mediaPlaylist;
    }
}
