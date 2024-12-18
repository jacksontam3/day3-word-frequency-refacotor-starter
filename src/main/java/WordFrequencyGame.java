import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

            List<WordFrequency> wordFrequencyList = calculateAndAggregateWordFrequencies(inputStr);


            return formatWordFrequencies(wordFrequencyList);
        } catch (Exception e) {
            return CALCULATE_ERROR + e.getMessage();
        }

    }

    private List<WordFrequency> calculateAndAggregateWordFrequencies(String inputStr) {
        return Arrays.stream(inputStr.split(SPACE_REGEX))
                .collect(Collectors.groupingBy(word -> word))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .toList();
    }

    private String formatWordFrequencies(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + SPACE + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

}