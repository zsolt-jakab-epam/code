package com.mycompany.interviews;


import java.util.List;
import java.util.function.Consumer;

public interface IWordDictionary {

    /**
     * loads the dictionary
     */
    void load(Consumer<List> callback);

    /**
     * @param word The word to look for
     * @return true if the dictionary contains the word
     */
    boolean contains(String word);

    /**
     *
     * @return number of rankList in the dictionary
     */
    int size();
}
