package com.mpob.base.events.exo;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/**
 * Created by HOLV on 2,December,2017
 * My Parents On Board,
 * Santa Monica California.
 *
 * ABR switching rules ported from ExoPlayer's {@link AdaptiveTrackSelection}
 */
public class DefaultExoPlayerAbrStreamingRules implements IAbrStreamingRules {

    private static final int DEFAULT_MAX_INITIAL_BITRATE = 800000;
    private static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    private static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;

    private static final float DEFAULT_BANDWIDTH_FRACTION = 0.75f;

    private final int mMaxInitialBitrate;
    private final long mMinDurationForQualityIncreaseUs;
    private final long mMaxDurationForQualityDecreaseUs;
    private final float mBandwidthFraction;

    private int mCurrentSelectionIdx;

    public DefaultExoPlayerAbrStreamingRules() {
        this(DEFAULT_MAX_INITIAL_BITRATE, DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS, DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS,
                DEFAULT_BANDWIDTH_FRACTION);
    }

    public DefaultExoPlayerAbrStreamingRules(int maxInitialBitrate, int minDurationForQualityIncreaseMs, int maxDurationForQualityDecreaseMs,
            float bandwidthFraction) {
        mMaxInitialBitrate = maxInitialBitrate;
        mMinDurationForQualityIncreaseUs = minDurationForQualityIncreaseMs * 1_000L;
        mMaxDurationForQualityDecreaseUs = maxDurationForQualityDecreaseMs * 1_000L;
        mBandwidthFraction = bandwidthFraction;

        mCurrentSelectionIdx = 0;
    }

    @Override
    public int selectAdaptiveTrack(MediaTrackSpec[] tracks, long estimatedBandwidth, long bufferedDurationUs) {
        if (mCurrentSelectionIdx >= tracks.length) {
            mCurrentSelectionIdx = 0;
        }

        final int lastSelectionIdx = mCurrentSelectionIdx;
        mCurrentSelectionIdx = selectIdealTrack(tracks, estimatedBandwidth);
        if (mCurrentSelectionIdx == lastSelectionIdx) {
            // already using the ideal format. no need to switch up or down.
            return mCurrentSelectionIdx;
        }

        // Revert back to the current selection if conditions are not suitable for switching.
        MediaTrackSpec lastSelection = tracks[lastSelectionIdx];
        MediaTrackSpec newSelection = tracks[mCurrentSelectionIdx];
        if (newSelection.bitrate > lastSelection.bitrate && bufferedDurationUs < mMinDurationForQualityIncreaseUs) {
            // The selected track is a higher quality, but we have insufficient buffer to safely switch up. Defer switching up for now.
            mCurrentSelectionIdx = lastSelectionIdx;
        } else if (newSelection.bitrate < lastSelection.bitrate && bufferedDurationUs >= mMaxDurationForQualityDecreaseUs) {
            // The selected track is a lower quality, but we have sufficient buffer to defer switching down for now.
            mCurrentSelectionIdx = lastSelectionIdx;
        }

        return mCurrentSelectionIdx;
    }

    private int selectIdealTrack(MediaTrackSpec[] tracks, long estimatedBandwidth) {
        final long effectiveBitrate = estimatedBandwidth == NO_ESTIMATE ? mMaxInitialBitrate : (long) (estimatedBandwidth * mBandwidthFraction);

        int highestValidBitrateIdx = 0;
        int highestValidBitrate = 0;
        for (int i = 0; i < tracks.length; i++) {
            final MediaTrackSpec track = tracks[i];
            final int trackBitrate = track.bitrate;

            if (trackBitrate <= effectiveBitrate && trackBitrate > highestValidBitrate) {
                highestValidBitrate = trackBitrate;
                highestValidBitrateIdx = i;
            }
        }

        return highestValidBitrateIdx;
    }
}
