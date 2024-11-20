import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

    public String getWordFrequency(String inputStr) {

        if (inputStr.split(SPACE_REGEX).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordFrequency> wordFrequencyList = getWordFrequencies(inputStr);

                Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencyList);

                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequency);
                }
                wordFrequencyList = list;


                return getString(wordFrequencyList);
            } catch (Exception e) {
                return CALCULATE_ERROR + e.getMessage();
            }
        }
    }

    private static String getString(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + SPACE + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getWordFrequencies(String inputStr) {
        String[] words = inputStr.split(SPACE_REGEX);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord, Collectors.toCollection(ArrayList::new)));
    }
}