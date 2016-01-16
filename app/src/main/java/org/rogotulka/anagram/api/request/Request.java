package org.rogotulka.anagram.api.request;

public interface Request<T> {
    String CALLBACK_URL = "https://github.com/rogotulka";
    T execute();
}
