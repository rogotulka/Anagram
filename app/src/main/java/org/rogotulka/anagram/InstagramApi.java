package org.rogotulka.anagram;

public class InstagramApi {

    public static final String API_URL = "https://api.instagram.com/v1";
    public static final String CALLBACK_URL = "https://github.com/rogotulka";
    private static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKEN_URL = "https://api.instagram.com/oauth/access_token";
    private static final String CLIENT_ID = "f9c5123ea40b487a8386da485efe285a";


    public static String auth() {
        return AUTH_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACK_URL + "&response_type=" + "code";
    }


}
