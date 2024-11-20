import java.util.*;

public class WordFrequencyGame {

    public static final String Space = "\\s+";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String inputStr) {

        if (inputStr.split(Space).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(Space);

                List<WordFrequency> wordFrequencyList = Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();

                //get the wordToWordFrequency for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencyList);

                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequency);
                }
                wordFrequencyList = list;
                wordFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String sentence = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(sentence);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())) {
                ArrayList wordList = new ArrayList<>();
                wordList.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), wordList);
            } else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordFrequencyMap;
    }
}