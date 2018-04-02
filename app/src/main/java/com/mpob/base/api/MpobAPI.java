package com.mpob.base.api;

import com.mpob.base.pojos.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by HOLV on 14,March,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public interface MpobAPI {

    /**
     * Seems that we have to make only one single get method to get all the info
     * is needed for the two hours stream video.
     *
     * TODO:
     * First--->
     * The first solution would be first work on the weekend in the SM library.
     *
     *
     * 1.- Doing the call to the central router server using the end point url
     *
     * 2.- Change the end point url to start talking with the sub domain system
     *      2.1.- Once we get the url that redirects to the sub domain store it in a
     *         database/file. In that way from then on the communication will be
     *         between the phone and the sub domain
     *
     * 3.- Algorithm would be like this:
     *      3.1.- Clear from user preferences the end point url when app closes
     *      3.2.-
     *      3.3.-
     *
     * Second--->
     * Save the whole complete json that is returned in a database/file/data structure
     * Draw the recycling views.
     * Load the images.
     */


    /**
     * Login to the CENTRAL SYSTEM and get the url to talk to.
     * We have to always execute this very first method to bring the
     * URL to talk to.
     * @param request
     * @return
     */
    @POST("obtain/url")
    Call<User> obtainUrlToTalkTo(@Body User request);

    /**
     * The login with the day care system that user belongs. Bring all the info
     * from the
     * @param request
     * @return
     */
    @POST("login/url")
    Call<User> loginUser(@Body User request);

    /**
     * Here are all the methods that needs to be defined to talk with the system
     * every single time they tap the live stream button should we go for the cameras
     * short answer yes, because the stream url is changing in a certain of period.
     * @param request
     * @return
     */
    @GET("obtain/live/cameras")
    Call<Object> obtainLiveStreamCameras(@Body User request);

    /**
     * Call to the Video On Demand streams
     * Also here applies the same concept. every single time they click
     * on the button lets launch the the api call and bring the json to bring
     * the new url to play.
     * @param request
     * @return
     */
    @GET("obtain/vod/cameras")
    Call<Object> obtainVideoOnDemandCameras(@Body User request);


}
