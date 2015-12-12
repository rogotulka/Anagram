package org.rogotulka.anagram;

public interface OnLoginListener {
    void onSuccessLogin(String code);
    void onErrorLogin(String message);
}
