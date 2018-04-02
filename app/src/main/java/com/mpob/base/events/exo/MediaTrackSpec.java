package com.mpob.base.events.exo;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.MimeTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * Created by HOLV on 2,December,2017
 * My Parents On Board,
 * Santa Monica California.
 */
public class MediaTrackSpec {

	@Retention(RetentionPolicy.SOURCE)
	@IntDef({TYPE_VIDEO, TYPE_AUDIO, TYPE_TEXT, TYPE_OTHER})
	public @interface TrackType {}
	public static final int TYPE_VIDEO = 0;
	public static final int TYPE_AUDIO = 1;
	public static final int TYPE_TEXT = 2;
	public static final int TYPE_OTHER = 3;

	@TrackType
	public final int type;

	public final String id;

	public final int bitrate;

	public final String codecs;

	public final String containerMimeType;

	public final String sampleMimeType;

	public final int width;

	public final int height;

	public final String[] protectionSchemeUuids;

	public MediaTrackSpec(@TrackType int type, String id, int bitrate, String codecs, String containerMimeType, String sampleMimeType, int width,
                          int height, String[] protectionSchemeUuids) {
		this.type = type;
		this.id = id;
		this.bitrate = bitrate;
		this.codecs = codecs;
		this.containerMimeType = containerMimeType;
		this.sampleMimeType = sampleMimeType;
		this.width = width;
		this.height = height;
		this.protectionSchemeUuids = protectionSchemeUuids;
	}

	@Override
	public String toString() {
		return "MediaTrackSpec{" + "type=" + type + ", id='" + id + '\'' + ", bitrate=" + bitrate + ", codecs='" + codecs + '\'' +
				", containerMimeType='" + containerMimeType + '\'' + ", sampleMimeType='" + sampleMimeType + '\'' + ", width=" + width + ", " +
				"height=" +
				height + ", protectionSchemeUuids=" + Arrays.toString(protectionSchemeUuids) + '}';
	}

	public static MediaTrackSpec[] fromExoPlayerTrackGroups(@Nullable TrackGroupArray trackGroupArray) {
		if (trackGroupArray == null) {
			return new MediaTrackSpec[0];
		}

		List<MediaTrackSpec> specs = new ArrayList<>();

		// iterate over all of the tracks, and sort them by type
		for (int i = 0; i < trackGroupArray.length; i++) {
			final TrackGroup trackGroup = trackGroupArray.get(i);
			for (int j = 0; j < trackGroup.length; j++) {
				// create a new spec for this format
				final Format format = trackGroup.getFormat(j);
				MediaTrackSpec spec = MediaTrackSpec.fromExoPlayerFormat(format);
				specs.add(spec);
			}
		}

		return specs.toArray(new MediaTrackSpec[specs.size()]);
	}

	public static MediaTrackSpec[] fromExoPlayerTrackSelections(@Nullable TrackSelectionArray trackSelectionArray) {
		if (trackSelectionArray == null) {
			return new MediaTrackSpec[0];
		}

		List<MediaTrackSpec> specs = new ArrayList<>();

		for (int i = 0; i < trackSelectionArray.length; i++) {
			TrackSelection trackSelection = trackSelectionArray.get(i);
			if (trackSelection != null) {
				Format selectedFormat = trackSelection.getSelectedFormat();
				specs.add(fromExoPlayerFormat(selectedFormat));
			}
		}

		return specs.toArray(new MediaTrackSpec[specs.size()]);
	}

	public static MediaTrackSpec fromExoPlayerFormat(@NonNull Format format) {
		String[] uuids;
		DrmInitData drmInitData = format.drmInitData;
		if (drmInitData == null) {
			uuids = new String[0];
		} else {
			final List<String> uuidList = new ArrayList<>();
			final UUID[] drmUuids = {C.WIDEVINE_UUID, C.PLAYREADY_UUID, C.CLEARKEY_UUID};
			for (UUID drmUuid : drmUuids) {
				if (drmInitData.get(drmUuid) != null) {
					uuidList.add(drmUuid.toString());
				}
			}
			uuids = uuidList.toArray(new String[uuidList.size()]);
		}

		@TrackType
		final int type;

		String sampleMimeType = format.sampleMimeType;
		if (MimeTypes.isVideo(sampleMimeType)) {
			type = TYPE_VIDEO;
		} else if (MimeTypes.isAudio(sampleMimeType)) {
			type = TYPE_AUDIO;
		} else if (MimeTypes.isText(sampleMimeType)) {
			type = TYPE_TEXT;
		} else {
			type = TYPE_OTHER;
		}

		return new MediaTrackSpec(type, format.id, format.bitrate, format.codecs, format.containerMimeType, format.sampleMimeType, format.width,
				format.height, uuids);
	}
}
