package org.rogotulka.anagram.api.request;

public interface Request<T> {
    T execute();
}
