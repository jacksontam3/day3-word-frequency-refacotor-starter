import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

    public String getWordFrequency(String inputStr) {

            try {

                if (inputStr.split(SPACE_REGEX).length == 1) {
                    return inputStr + " 1";
                }

                List<WordFrequency> wordFrequencyList = calculateWordFrequencies(inputStr);

                wordFrequencyList = aggregateWordFrequencies(wordFrequencyList);


                return formatWordFrequencies(wordFrequencyList);
            } catch (Exception e) {
                return CALCULATE_ERROR + e.getMessage();
            }

    }

    private List<WordFrequency> aggregateWordFrequencies(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencyList);

        return wordToWordFrequency.entrySet().stream().map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size())).toList();
    }

    private static String formatWordFrequencies(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + SPACE + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> calculateWordFrequencies(String inputStr) {
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