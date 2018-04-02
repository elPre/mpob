package com.mpob.base.events.exo;


/**
 * Created by HOLV on 2,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */
public interface IAbrStreamingRules {

	long NO_ESTIMATE = -1L;

	/**
	 * Selects an adaptive track during playback
	 *
	 * @param tracks             all possible tracks from which the selection must be made
	 * @param estimatedBandwidth the estimated available network bandwidth, in bits per second, based on the download speeds of previous loads. May
	 *                           be {@link #NO_ESTIMATE} if there is no estimate available, i.e. no previous loads have been made.
	 * @param bufferedDurationUs the duration of buffer ahead of the current playback position that has already been loaded, in microseconds
	 * @return the index of the selected track in the tracks input array
	 */
	int selectAdaptiveTrack(MediaTrackSpec[] tracks, long estimatedBandwidth, long bufferedDurationUs);

}
