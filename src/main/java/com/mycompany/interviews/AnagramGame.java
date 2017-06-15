package com.mycompany.interviews;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramGame implements IAnagramGame
{
	String baseWord;
    IWordDictionary dictionary;
	public List<String> rankList = new ArrayList<>();
	Map<String, Integer> baseWordCharMap = new HashMap<>();

	public AnagramGame(String baseWord, IWordDictionary dictionary)
	{
		// TODO please implement
		this.baseWord = baseWord;
		this.dictionary = dictionary;
		this.baseWordCharMap = createMapFromWord(this.baseWord, this.baseWordCharMap);
	}


	public Map<String,Integer> createMapFromWord(String wordToMap, Map<String, Integer> map) {
		for (int i = 0; i < wordToMap.length(); i++) {
			String currentLetter = Character.toString(wordToMap.charAt(i));
			if (!map.containsKey(currentLetter)) {
				map.put(currentLetter, 1);
			} else {
				map.put(currentLetter, map.get(currentLetter) + 1);
			}
		}
		return map;
	}

	/**
	 * @inheritDoc
	 * @param word
	 */
	public void submitWord(String word)
	{
		// TODO please implement
        int score = evaluateWord(word);
        if (rankList.isEmpty() && 0 < score) {
            rankList.add(word);
        }
        else if (0 < score) {
			String bigger = word;
            for (int i = 0; i < rankList.size(); i++) {

				String smaller = rankList.get(i);
                    while (smaller.length() < bigger.length()) {
                        rankList.add(i, bigger);
						bigger = smaller;
                    }

            }
        }

	}

	/**
	 * @inheritDoc
	 * @param word
	 * @return
	 */
	public int evaluateWord(String word)
	{
		// TODO please
		int result = 0;
        Map<String, Integer> givenWordMap = new HashMap<>();
        createMapFromWord(word, givenWordMap);
        boolean contains = dictionary.contains(word);
        if (contains) {
            for (String keyChar : givenWordMap.keySet()) {
                if (!baseWordCharMap.containsKey(keyChar) || givenWordMap.get(keyChar) > baseWordCharMap.get(keyChar)) {
                    result = 0;
                    break;
                }
                else {
                    result = word.length();
                }
            }
        }
		System.out.println(result);
		return result;
	}

	/**
	 * @inheritDoc
	 * @param position
	 * @return
	 */
	public int getScoreAtPosition(int position)
	{
		// TODO please implement
        if (position < rankList.size()) { return rankList.get(position).length(); }
		return -1;
	}

	/**
	 * @inheritDoc
	 * @param position
	 * @return
	 */
	public String getWordAtPosition(int position)
	{
		// TODO please implement
        if (position < rankList.size()) { return rankList.get(position); }
        return null;
	}

    public List<String> getRankList() {
        return rankList;
    }
}
