package org.rogotulka.anagram.api;

import android.util.Log;

import org.rogotulka.anagram.utils.ParamPair;
import org.rogotulka.anagram.utils.Util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class Network {

    private static final String TAG = Network.class.getSimpleName();

    public static InputStream getResponse(String sheme, String host, String method,
                                          List<ParamPair> params) {
        InputStream is = null;

        try {
            URL url = new URL(sheme + "://"+ host + "/" + method + "/" + Util.getParams(params));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();
            return is;

        } catch (MalformedURLException e) {
            Log.d(TAG, "The response is: ");
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    //NOP
                }
            }
        }


        return  null;
    }


}
