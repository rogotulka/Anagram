package org.rogotulka.anagram.ui;

public interface OnLoginListener {
    void onSuccessLogin(String code);
    void onErrorLogin(String message);
}
