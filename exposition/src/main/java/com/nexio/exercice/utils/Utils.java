package com.nexio.exercice.utils;

public class Utils {

    private static final String URL_PARAM_REGEX = "(\\{[^\\}]*\\})";

    public static String constructUrlWithParams(String urlSpring, String... params) {

        for (String param : params) {
            urlSpring = urlSpring.replaceFirst(URL_PARAM_REGEX, param);
        }
        return urlSpring;
    }

}
