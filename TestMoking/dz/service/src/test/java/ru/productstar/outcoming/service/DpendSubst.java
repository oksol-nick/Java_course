package ru.productstar.outcoming.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class DpendSubst implements DBInterface {

    @Override
    public void writeWordToDB(int id, String word) {
    }

    @Override
    public String getWordById(int id) {
        String[] words = {"Hello", "Hi"};
        if (id == 1 || id == 2) {
           String value = words[id - 1];
           //log.info("test" + value );
           return value;
        } else return String.format("Word with id %d was not found", id);
    }
}
