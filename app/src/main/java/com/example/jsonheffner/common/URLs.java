package com.example.jsonheffner.common;

public class URLs {
    private final static String API = "http://cinema.areas.su/";
    private final static String SIGN_IN =API+ "auth/login";
    private final static String SIGN_UP =API+ "auth/register";
    private final static String COVER = "/movies/cover";
    private final static String MOVIES_WITH_FILTER = "/movies?filter=";
    private final static String API_IMAGE = API+"/up/images";
    private final static String API_VIDEO=API+"/up/video/";

    public static String getSignUp() {
        return API+SIGN_UP;
    }

    public static String getImageUrl(String shortUrl) {
        return API_IMAGE+shortUrl;
    }

    public static String getCOVER() {
        return API+COVER;
    }

    public static String getMoviesWithFilter(String filter) {
        return API+MOVIES_WITH_FILTER+filter;
    }
}
