package com.mpob.base.video.exo.mute;


import android.net.Uri;
import android.util.Pair;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.util.TimestampAdjuster;

import java.util.List;


/**
 * Created by HOLV on 10,April,2018
 * My Parents On Board,
 * Santa Monica California.
 */
/**
 * Factory for HLS media chunk extractors.
 */
public interface HlsExtractorFactory {

    HlsExtractorFactory DEFAULT = new DefaultHlsExtractorFactoryMute();

    /**
     * Creates an {@link Extractor} for extracting HLS media chunks.
     *
     * @param previousExtractor A previously used {@link Extractor} which can be reused if the current
     *     chunk is a continuation of the previously extracted chunk, or null otherwise. It is the
     *     responsibility of implementers to only reuse extractors that are suited for reusage.
     * @param uri The URI of the media chunk.
     * @param format A {@link Format} associated with the chunk to extract.
     * @param muxedCaptionFormats List of muxed caption {@link Format}s. Null if no closed caption
     *     information is available in the master playlist.
     * @param drmInitData {@link DrmInitData} associated with the chunk.
     * @param timestampAdjuster Adjuster corresponding to the provided discontinuity sequence number.
     * @return A pair containing the {@link Extractor} and a boolean that indicates whether it is a
     *     packed audio extractor. The first element may be {@code previousExtractor} if the factory
     *     has determined it can be re-used.
     */
    Pair<Extractor, Boolean> createExtractor(Extractor previousExtractor, Uri uri, Format format,
                                             List<Format> muxedCaptionFormats, DrmInitData drmInitData,
                                             TimestampAdjuster timestampAdjuster);

}

