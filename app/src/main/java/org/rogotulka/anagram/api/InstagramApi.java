package org.rogotulka.anagram.api;

import org.rogotulka.anagram.api.request.Request;

public class InstagramApi {

    private static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKEN_URL = "https://api.instagram.com/oauth/access_token";

    //// TODO: 16.01.16 as request
    public static String auth() {
        return AUTH_URL + "?client_id=" + "f9c5123ea40b487a8386da485efe285a" + "&redirect_uri="
                + Request.CALLBACK_URL + "&response_type=" + "code";
    }


}
