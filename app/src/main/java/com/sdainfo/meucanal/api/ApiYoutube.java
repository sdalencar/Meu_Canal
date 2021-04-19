package com.sdainfo.meucanal.api;

import com.sdainfo.meucanal.helper.YoutubeConfig;
import com.sdainfo.meucanal.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiYoutube {

    /*
    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyBEvdftjwEHP8Ju1KRzooFUNFAQr3zuBa
    &channelId=UC-N-Lx1YOh9SgRWkuNBD8Hw
    &q=q
    */

    String URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResults=20&key=AIzaSyBEvdftjwEHP8Ju1KRzooFUNFAQr3zuBa0&channel=UC-N-Lx1YOh9SgRWkuNBD8Hw&q=q";

    @GET("search")
    Call <Resultado> recuperarVideos(@Query("part") String part, @Query("order") String order, @Query("maxResults") String maxResults, @Query("key") String key, @Query("channelId") String channelId, @Query("q") String q);
}
