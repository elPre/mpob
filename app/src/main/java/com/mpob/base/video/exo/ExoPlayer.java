package com.mpob.base.video.exo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.mpob.base.BuildConfig;
import com.mpob.base.R;
import com.mpob.base.video.IVideoPlayerAPI;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by HOLV on 30,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class ExoPlayer implements IVideoPlayerAPI, com.google.android.exoplayer2.ExoPlayer.EventListener {

    private static final String TAG = ExoPlayer.class.getName();

    private static final int RESOURCE_ID_SIMPLE_PLAYER_FRAME = R.id.activity_video_framelayout;
    private static final int RESOURCE_ID_SIMPLE_EXOPLAYER_ASPECTRATIO_FRAME = R.id.activity_video_exo_player_aspect_ratio;
    private static final int RESOURCE_ID_RELATIVE_LAYOUT_PARENT = R.id.activity_video_relativelayout_parent;
    private static final int LANDSCAPE_MODE = 2;

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private final long DURATION_UNKNOWN = 0;
    private final int SURFACE_TYPE_NONE = 0;
    private final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private final String DRM_SCHEME_LABEL_WIDEVINE = "widevine";
    private final String DRM_SCHEME_LABEL_PLAYREADY = "playready";
    private final String DRM_SCHEME_LABEL_CENC = "cenc";
    private final String DRM_SCHEME_UUID_EXTRA = "drm_scheme_uuid";
    private final String DRM_LICENSE_URL_EXTRA = "drm_license_url";
    private final String UNKNOWN_DRM_UUID_TYPE = "00000000-0000-0000-0000-000000000000";

    private AspectRatioFrameLayout mAspectRatioFrameLayout = null;
    private FrameLayout mFrameLayout = null;
    private RelativeLayout mRelativeLayout = null;

    private SimpleExoPlayer mPlayer = null;
    private Context mContext = null;
    private DefaultTrackSelector mTrackSelector = null;
    private TrackGroupArray mLastSeenTrackGroupArray = null;
    private MediaSource mActiveMediaSource = null;
    private DefaultRenderersFactory mRenderersFactoryForMediaSource = null;
    private DataSource.Factory mMediaDataSourceFactory = null;
    private DrmSessionManager<FrameworkMediaCrypto> mDrmSessionManager = null;
    private UUID mDrmSchemeUuid = null;
    private View mSurfaceView = null;
    private Handler mMainHandler = null;
    private String mUserAgent = null;
    private DefaultDrmSessionManager.EventListener mDefaultDrmSessionManagerListener = null;
    private AdaptiveMediaSourceEventListener mAdaptiveMediaSourceEventListener = null;
    private TrackSelection.Factory mAdaptiveTrackSelectionFactory = null;
    private ExtractorMediaSource.EventListener mExtractorMediaSourceEventListener = null;
    private BitrateLimitingAbrStreamingRules mAbrRules = null;
    private Uri mUri = null;
    private String mExtension = null;

    private int mResumeWindow = 0;
    private long mResumePosition = 0;
    private int mResizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT;
    private int mSurfaceType = SURFACE_TYPE_SURFACE_VIEW;
    private int mHeight = 0;

    private boolean mActiveMediaSourceHaveResumePosition = false;
    private boolean mShouldAutoPlay = false;

    private EventLogger mEventLogger = null;


    private static final CookieManager DEFAULT_COOKIE_MANAGER;

    static {
        DEFAULT_COOKIE_MANAGER = new CookieManager();
        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public ExoPlayer(Context context) {
        this.mContext = context;
    }


    //#################################################################################################
    //#################################################################################################
    //#################################################################################################
    //#################################################################################################
    //#################################################################################################
    //#################################################################################################

    @Override
    public void init() {
        Log.d(TAG, "init player");
        if (needNewPlayer()) {
            createDataSource();
            createAdaptiveFactory();
            createRenderFactory();
            setUpMediaSource(getUri(), getExtension());
            createNewPlayer();
            clearResumePosition();
            createFrameLayoutAndAspectRatio();
            setPlayerLayoutFrame();
            resetViewSurface();
        } else {
            Log.d(TAG, "start from the frame was stopped");
        }
    }

    @Override
    public void play() {
        mPlayer.setPlayWhenReady(true);
    }

    @Override
    public void pause() {
        mPlayer.setPlayWhenReady(false);
    }

    @Override
    public void seekTo(long position) {
        mPlayer.seekTo(mResumeWindow, position);
    }

    @Override
    public void setStreamUrl(Uri uri) {
        mUri = uri;
    }

    @Override
    public void setStreamExtensionToPlay(String extension) {
        mExtension = extension;
    }

    @Override
    public void onConfigurationChanged(short portraitLandscape) {

        Log.d(TAG, "onConfigurationChanged " + portraitLandscape);
        RelativeLayout.LayoutParams params;
        if (mFrameLayout == null) {
            return;
        }

        if (portraitLandscape == LANDSCAPE_MODE) {
            params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
        } else {
            params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, mHeight);
        }
        mFrameLayout.setLayoutParams(params);
    }

    @Override
    public void releasePlayerResources() {
        releasePlayer();
    }

    //#################################################################################################
    //#################################################################################################
    //#################################################################################################
    //#################################################################################################
    //#################################################################################################
    //#################################################################################################


    /********************************/

    private DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(mContext, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter));
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        mUserAgent = Util.getUserAgent(mContext, "MyParentsOnBoard");
        return new DefaultHttpDataSourceFactory(mUserAgent, bandwidthMeter);
    }

    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(boolean useBandwidthMeter) {
        return buildHttpDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    /********************************/

    /**
     * Helper to DRM setup - widevine requires key properties in some cases.
     */
    public void setupDrm(UUID drmSchemeUuid, String drmLicenseUrl, String[] drmKeyRequestProperties) {

        mDrmSchemeUuid = (drmSchemeUuid != null && drmSchemeUuid.toString().equals(UNKNOWN_DRM_UUID_TYPE))
                ? drmSchemeUuid : null;
        mDrmSessionManager = getDrmSessionManager(drmSchemeUuid, drmLicenseUrl, drmKeyRequestProperties);
        if (mDrmSessionManager == null) {
            Log.i(TAG, "setup drm found drm manager not available");
        }
        mRenderersFactoryForMediaSource = new DefaultRenderersFactory(mContext,
                mDrmSessionManager, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);

        mLastSeenTrackGroupArray = null;
    }

    /**
     * get drm session manager if possible
     * <p>
     * {@link FrameworkMediaCrypto} gives us a running start on decoding.
     *
     * @param drmSchemeUuid             {@link UUID}
     * @param drmLicenseUrl             String
     * @param keyRequestPropertiesArray String[]
     * @return {@link DrmSessionManager<>}
     */
    protected DrmSessionManager<FrameworkMediaCrypto> getDrmSessionManager(
            UUID drmSchemeUuid, String drmLicenseUrl, String[] keyRequestPropertiesArray) {

        DrmSessionManager<FrameworkMediaCrypto> drmSessionManager = null;
        if (drmSchemeUuid != null) {
            try {
                drmSessionManager = buildDrmSessionManager(drmSchemeUuid, drmLicenseUrl, keyRequestPropertiesArray);
            } catch (UnsupportedDrmException e) {
                int errorStringId = Util.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2
                        ? 1//R.string.error_drm_not_supported
                        : 2;
                if (mContext != null) {
                    Log.e(TAG, "error_drm_not_supported" + errorStringId);
                }
                return drmSessionManager;
            }
        }
        return drmSessionManager;
    }


    /**
     * build drm session manager from basic license parameters
     * <p>
     * {@link FrameworkMediaDrm} takes care of setting up the drm commication framework.
     *
     * @param uuid                      {@link UUID}
     * @param licenseUrl                String
     * @param keyRequestPropertiesArray String[]
     * @return {@link DrmSessionManager<>}
     * @throws UnsupportedDrmException
     */
    private DrmSessionManager<FrameworkMediaCrypto> buildDrmSessionManager(
            UUID uuid, String licenseUrl, String[] keyRequestPropertiesArray) throws UnsupportedDrmException {
        if (Util.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // not supported
            return null;
        }

        HttpMediaDrmCallback drmCallback = new HttpMediaDrmCallback(licenseUrl, buildHttpDataSourceFactory(false));
        if (keyRequestPropertiesArray != null) {
            for (int i = 0; i < keyRequestPropertiesArray.length - 1; i += 2) {
                drmCallback.setKeyRequestProperty(keyRequestPropertiesArray[i],
                        keyRequestPropertiesArray[i + 1]);
            }
        }

        // descriptive - optional params that could be added to drm key params as last resort
        HashMap<String, String> optionalKeyRequestParameters = null;
        return new DefaultDrmSessionManager<>(
                uuid,
                FrameworkMediaDrm.newInstance(uuid),
                drmCallback,
                optionalKeyRequestParameters,
                mMainHandler,
                mDefaultDrmSessionManagerListener);
    }


    /**
     * build media source based on stream type
     * <p>
     * Uses exoplayer default media source implementations.
     *
     * @param uri               {@link Uri}
     * @param overrideExtension String
     * @return {@link MediaSource}
     */
    private MediaSource buildMediaSource(Uri uri, String overrideExtension) {

        int type = TextUtils.isEmpty(overrideExtension) ? Util.inferContentType(uri)
                : Util.inferContentType("." + overrideExtension);

        switch (type) {
            case C.TYPE_SS:  // smooth streaming
                return new SsMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultSsChunkSource.Factory(mMediaDataSourceFactory), mMainHandler, mAdaptiveMediaSourceEventListener);
            case C.TYPE_DASH: // dash
                return new DashMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultDashChunkSource.Factory(mMediaDataSourceFactory), mMainHandler, mAdaptiveMediaSourceEventListener);
            case C.TYPE_HLS: // hls (http live streaming
                return new HlsMediaSource(uri, mMediaDataSourceFactory, mMainHandler, mAdaptiveMediaSourceEventListener);
            case C.TYPE_OTHER: // other then use extractor to try to figure it out
                return new ExtractorMediaSource(uri, mMediaDataSourceFactory, new DefaultExtractorsFactory(),
                        mMainHandler, mExtractorMediaSourceEventListener);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }


    /**
     * Creates the Adaptive track selector from a Factory given as a param a
     * Default Bandwidth Meter
     * Creates the TrackSelector for the Video Audio ClosedCaptions
     */
    private void createAdaptiveFactory() {
        Log.d(TAG, "createAdaptiveFactory");
        mAdaptiveTrackSelectionFactory = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
        mTrackSelector = new DefaultTrackSelector(mAdaptiveTrackSelectionFactory);

        mEventLogger = new EventLogger(mTrackSelector);

        mDefaultDrmSessionManagerListener = mEventLogger;
        mAdaptiveMediaSourceEventListener = mEventLogger;
        mExtractorMediaSourceEventListener = mEventLogger;

    }

    /**
     * Builds {@link Renderer} instances for use by a {@link SimpleExoPlayer}.
     */
    private void createRenderFactory() {
        Log.d(TAG, "createRenderFactory");
        mRenderersFactoryForMediaSource = new DefaultRenderersFactory(mContext,
                mDrmSessionManager, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);
    }

    /**
     * Creates the DataSource object (the url to stream)
     * Creates the Handler that is required to datasource creation
     * Creates the Biterate for better performance of live streaming
     */
    private void createDataSource() {
        Log.d(TAG, "createDataSource");
        if (CookieHandler.getDefault() != DEFAULT_COOKIE_MANAGER) {
            CookieHandler.setDefault(DEFAULT_COOKIE_MANAGER);
        }
        mShouldAutoPlay = true;
        mMainHandler = new Handler();
        mResizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT;
        mMediaDataSourceFactory = buildDataSourceFactory(BANDWIDTH_METER);
        mAbrRules = new BitrateLimitingAbrStreamingRules();

    }


    /**
     * Set up the MediaSource that is the
     * end point where the bytes are coming for the streaming
     *
     * @param uris       {@link Uri}[]
     * @param extensions String[]
     */
    public void setUpMediaSource(Uri uris, String extensions) {
        Log.d(TAG, "setUpMediaSource");
        // create media sources based on uris
        MediaSource[] mediaSources = new MediaSource[1];
        String uriString = uris != null ? uris.toString() : "";
        mediaSources[0] = null;
        try {
            mediaSources[0] = buildMediaSource(uris, extensions);
        } catch (IllegalStateException e0) {
            Log.i(TAG, "start playback failed to build media source for uri > " + uriString);
        }
        // setting up the video to stream
        mActiveMediaSource = mediaSources.length == 1 ? mediaSources[0]
                : new ConcatenatingMediaSource(mediaSources);

    }

    /**
     * Creates a brand new Player
     */
    private void createNewPlayer() {
        // configure new player
        Log.d(TAG, "createNewPlayer & prepare with mediasource and move it to a position");
        mPlayer = ExoPlayerFactory.newSimpleInstance(mRenderersFactoryForMediaSource, mTrackSelector);
        mPlayer.addListener(this);
        mPlayer.setTextOutput(null);
        mPlayer.addListener(mEventLogger);
        mPlayer.setAudioDebugListener(mEventLogger);
        mPlayer.setVideoDebugListener(mEventLogger);
        mActiveMediaSourceHaveResumePosition = mResumeWindow != C.INDEX_UNSET;
        if (mActiveMediaSourceHaveResumePosition) {
            seekTo(mResumePosition);
        }
        boolean haveResumePosition = mResumeWindow != C.INDEX_UNSET;
        mPlayer.prepare(mActiveMediaSource, !haveResumePosition, false);
    }

    /**
     * Easy helper check is the player available
     *
     * @return boolean
     */
    private boolean needNewPlayer() {
        return mPlayer == null;
    }

    /**
     * Clear the Surface view
     * that is where the video is rendered
     */
    private void resetViewSurface() {
        if (mPlayer != null) {
            Log.d(TAG, "resetViewSurface");
            if (mSurfaceView instanceof TextureView) {
                mPlayer.clearVideoTextureView((TextureView) mSurfaceView);
                mPlayer.setVideoTextureView((TextureView) mSurfaceView);
            } else if (mSurfaceView instanceof SurfaceView) {
                mPlayer.clearVideoSurfaceView((SurfaceView) mSurfaceView);
                mPlayer.setVideoSurfaceView((SurfaceView) mSurfaceView);
            }
        }
    }


    private void releasePlayer() {
        if (mPlayer != null) {
            mShouldAutoPlay = mPlayer.getPlayWhenReady();
            updateResumePosition();
            mPlayer.release();
            mPlayer = null;
            mTrackSelector = null;
            mAspectRatioFrameLayout = null;
            mFrameLayout = null;
            mSurfaceView = null;
        }
    }

    private void updateResumePosition() {
        mResumeWindow = mPlayer.getCurrentWindowIndex();
        mResumePosition = Math.max(0, mPlayer.getCurrentPosition());
    }

    private void clearResumePosition() {
        mResumeWindow = C.INDEX_UNSET;
        mResumePosition = C.TIME_UNSET;
    }


    /**
     *
     */
    private void setPlayerLayoutFrame() {
        // Create a surface view and insert it into the content frame, if there is one.
        Log.d(TAG, "setPlayerFrameSurface");
        if (mSurfaceType != SURFACE_TYPE_NONE) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (mFrameLayout.getChildCount() == 1) {
                // might be an existing surface view
                View framelayoutChild = mFrameLayout.getChildAt(0);
                if (framelayoutChild instanceof SurfaceView) {
                    // good news we dont need a new one!
                    Log.d(TAG, "setPlayerFramneSurface found existing surface view");
                } else {
                    // we need one
                    mSurfaceView = mSurfaceType == SURFACE_TYPE_TEXTURE_VIEW ? new TextureView(mContext)
                            : new SurfaceView(mContext);
                    mSurfaceView.setLayoutParams(params);
                    mFrameLayout.addView(mSurfaceView, 0);
                }
            } else if (mFrameLayout.getChildCount() == 0) {
                // we definitely need one surface view
                mSurfaceView = mSurfaceType == SURFACE_TYPE_TEXTURE_VIEW ? new TextureView(mContext)
                        : new SurfaceView(mContext);
                mSurfaceView.setLayoutParams(params);
                mFrameLayout.addView(mSurfaceView, 0);
            }else{
                mSurfaceView = mSurfaceType == SURFACE_TYPE_TEXTURE_VIEW ? new TextureView(mContext)
                        : new SurfaceView(mContext);
                mSurfaceView.setLayoutParams(params);
                mFrameLayout.addView(mSurfaceView, 0);
            }
        } else {
            mSurfaceView = null;
        }

    }

    private void createFrameLayoutAndAspectRatio() {
        Log.d(TAG, "createFrameLayoutAndAspectRatio");
        mRelativeLayout = (RelativeLayout) ((Activity) mContext).findViewById(RESOURCE_ID_RELATIVE_LAYOUT_PARENT);
        mAspectRatioFrameLayout = (AspectRatioFrameLayout) ((Activity) mContext).findViewById(RESOURCE_ID_SIMPLE_EXOPLAYER_ASPECTRATIO_FRAME);
        mFrameLayout = (FrameLayout) ((Activity) mContext).findViewById(RESOURCE_ID_SIMPLE_PLAYER_FRAME);

        mFrameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeight = mFrameLayout.getMeasuredHeight();
                if(mHeight>0) {
                    mFrameLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }


    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }

    public void setExtension(String mExtension) {
        this.mExtension = mExtension;
    }

    public Uri getUri() {
        return mUri;
    }

    public String getExtension() {
        return mExtension;
    }
}
