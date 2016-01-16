package org.rogotulka.anagram.api.request;

import com.google.gson.Gson;

import org.rogotulka.anagram.api.Network;
import org.rogotulka.anagram.api.model.Token;
import org.rogotulka.anagram.utils.ParamPair;
import org.rogotulka.anagram.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class TokenRequest extends CommonRequest<Token>{
    private final String GRANT_TYPE = "authorization_code";
    private String redirectUri;
    private String code;
    private final String url = "api.instagram.com/oauth/";
    private final String method = "access_token";

    @Override
    public Token execute() {
        List<ParamPair> params = new ArrayList<>();
        params.add(new ParamPair("client_secret", CLIENT_ID));
        params.add(new ParamPair("grant_type", GRANT_TYPE));
        params.add(new ParamPair("redirect_uri", CALLBACK_URL));
        params.add(new ParamPair("code", code));
        Gson gson = new Gson();
        Token  token = gson.fromJson(Util.getStringFromInputStream(
                Network.getResponse(Util.SHEME, url, method, params)),
                Token.class);
        return token;
    }

    public String getGrantType() {
        return GRANT_TYPE;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
