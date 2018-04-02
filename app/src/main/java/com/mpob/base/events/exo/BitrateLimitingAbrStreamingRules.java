package com.mpob.base.events.exo;

/**
 * Created by HOLV on 2,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */
public class BitrateLimitingAbrStreamingRules extends DefaultExoPlayerAbrStreamingRules {

    private static final int INDEX_NONE = -1;

    private int mMaxBitrate;

    public BitrateLimitingAbrStreamingRules() {
        mMaxBitrate = Integer.MAX_VALUE;
    }

    public BitrateLimitingAbrStreamingRules(int maxBitrate) {
        mMaxBitrate = maxBitrate;
    }

    public void setMaxBitrate(int maxBitrate) {
        mMaxBitrate = maxBitrate;
    }

    @Override
    public int selectAdaptiveTrack(MediaTrackSpec[] tracks, long estimatedBandwidth, long bufferedDurationUs) {
        // get the best bitrate, according to ExoPlayer's algorithm
        final int bestTrackIndex = super.selectAdaptiveTrack(tracks, estimatedBandwidth, bufferedDurationUs);
        final int bestTrackBitrate = tracks[bestTrackIndex].bitrate;

        // if this bitrate is allowed, use it
        if (bestTrackBitrate < mMaxBitrate) {
            return bestTrackIndex;
        }

        // otherwise, pick the highest allowed bitrate
        final int highestAllowedIndex = selectHighestAllowedBitrateTrack(tracks);
        if (highestAllowedIndex != INDEX_NONE) {
            return highestAllowedIndex;
        }

        // if no track is below the max bitrate, then just pick the lowest possible bitrate
        return selectLowestBitrateTrack(tracks);
    }

    private int selectHighestAllowedBitrateTrack(MediaTrackSpec[] tracks) {
        int highestAllowedBitrate = 0;
        int highestAllowedIndex = INDEX_NONE;

        for (int i = 0; i < tracks.length; i++) {
            final MediaTrackSpec track = tracks[i];
            final int bitrate = track.bitrate;

            if (bitrate > highestAllowedBitrate && bitrate <= mMaxBitrate) {
                highestAllowedBitrate = bitrate;
                highestAllowedIndex = i;
            }
        }

        return highestAllowedIndex;
    }

    private int selectLowestBitrateTrack(MediaTrackSpec[] tracks) {
        int lowestBitrate = Integer.MAX_VALUE;
        int lowestIndex = 0;

        for (int i = 0; i < tracks.length; i++) {
            final MediaTrackSpec track = tracks[i];
            final int bitrate = track.bitrate;

            if (bitrate < lowestBitrate) {
                lowestBitrate = bitrate;
                lowestIndex = i;
            }
        }

        return lowestIndex;
    }
}
