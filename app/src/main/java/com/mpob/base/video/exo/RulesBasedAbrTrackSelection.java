package com.mpob.base.video.exo;


import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;

/**
 * Created by HOLV on 2,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */
public class RulesBasedAbrTrackSelection extends BaseTrackSelection {

	private final BandwidthMeter mBandwidthMeter;
	private final IAbrStreamingRules mAbrStreamingRules;

	private MediaTrackSpec[] mTracks;
	private int mSelectedTrack;

	public RulesBasedAbrTrackSelection(TrackGroup group, int[] tracks, BandwidthMeter bandwidthMeter, IAbrStreamingRules abrStreamingRules) {
		super(group, tracks);

		mBandwidthMeter = bandwidthMeter;
		mAbrStreamingRules = abrStreamingRules;

		mTracks = new MediaTrackSpec[tracks.length];
		for (int i = 0; i < tracks.length; i++) {
			final int trackIdx = tracks[i];
			final Format format = group.getFormat(trackIdx);
			final MediaTrackSpec trackSpec = MediaTrackSpec.fromExoPlayerFormat(format);
			mTracks[i] = trackSpec;
		}
	}


	@Override
	public int getSelectedIndex() {
		return getIndexInTrackGroup(mSelectedTrack);
	}

	@Override
	public int getSelectionReason() {
		return C.SELECTION_REASON_ADAPTIVE;
	}

	@Override
	public Object getSelectionData() {
		return null;
	}

	@Override
	public void updateSelectedTrack(long playbackPositionUs, long bufferedDurationUs, long availableDurationUs) {
		long bitrateEstimate = mBandwidthMeter.getBitrateEstimate();
		if (bitrateEstimate == BandwidthMeter.NO_ESTIMATE) {
			bitrateEstimate = IAbrStreamingRules.NO_ESTIMATE;
		}
		mSelectedTrack = mAbrStreamingRules.selectAdaptiveTrack(mTracks, bitrateEstimate, bufferedDurationUs);
	}

	/**
	 * Factory for {@link RulesBasedAbrTrackSelection} instances.
	 */
	public static final class Factory implements TrackSelection.Factory {

		private final BandwidthMeter mBandwidthMeter;
		private final IAbrStreamingRules mAbrStreamingRules;

		/**
		 * @param bandwidthMeter                    Provides an estimate of the currently available bandwidth.
		 */
		public Factory(BandwidthMeter bandwidthMeter, IAbrStreamingRules streamingRules) {
			mBandwidthMeter = bandwidthMeter;
			mAbrStreamingRules = streamingRules;
		}

		@Override
		public RulesBasedAbrTrackSelection createTrackSelection(TrackGroup group, int... tracks) {
			return new RulesBasedAbrTrackSelection(group, tracks, mBandwidthMeter, mAbrStreamingRules);
		}

	}
}
