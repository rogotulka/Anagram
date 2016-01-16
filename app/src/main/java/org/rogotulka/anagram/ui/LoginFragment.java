package org.rogotulka.anagram.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.rogotulka.anagram.R;
import org.rogotulka.anagram.api.InstagramApi;
import org.rogotulka.anagram.api.request.Request;

public class LoginFragment extends Fragment {

    private WebView vWebWiew;

    private OnLoginListener mOnLoginListener;

    private ProgressDialog mSpinner;

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(Request.CALLBACK_URL)) {
                String urls[] = url.split("=");
                mOnLoginListener.onSuccessLogin(urls[1]);
                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            mOnLoginListener.onErrorLogin(error.toString());
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mSpinner.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mSpinner.dismiss();
        }

    }

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_instagram, container, false);
        vWebWiew = (WebView) view.findViewById(R.id.login_instagram_web_view);
        vWebWiew.setVerticalScrollBarEnabled(false);
        vWebWiew.setHorizontalScrollBarEnabled(false);
        vWebWiew.setWebViewClient(new OAuthWebViewClient());
        vWebWiew.getSettings().setJavaScriptEnabled(true);
        vWebWiew.loadUrl(InstagramApi.auth());
        mSpinner = new ProgressDialog(this.getActivity());
        return view;
    }
}
