package ru.productstar.outcoming.service;

public interface DBInterface {

    void writeWordToDB(int id, String word);
    String getWordById(int id);

}
