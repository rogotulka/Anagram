package org.rogotulka.anagram.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class Util {

    private static final String TAG = "Request";

    public static final String SHEME = "https";

    public static String getParams(List<ParamPair> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (ParamPair pair : params) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            try {
                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                Log.w(TAG, "problem encode param with name" + pair.getName());
            }

        }
        return result.toString();
    }

    public static String getStringFromInputStream(InputStream inputStream){
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(inputStream, "UTF8");
        } catch (UnsupportedEncodingException e) {
            Log.w(TAG, "exception during convert input stream to string");
        }
        StringWriter writer = new StringWriter();
        try {
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            Log.w(TAG, "exception during convert input stream to string");
        }
        return writer.toString();
    }
}
