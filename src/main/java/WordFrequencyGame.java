import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split("\\s+");
                List<WorldFrequency> worldFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WorldFrequency worldFrequency = new WorldFrequency(word, 1);
                    worldFrequencyList.add(worldFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WorldFrequency>> map = getListMap(worldFrequencyList);

                List<WorldFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WorldFrequency>> entry : map.entrySet()) {
                    WorldFrequency worldFrequency = new WorldFrequency(entry.getKey(), entry.getValue().size());
                    list.add(worldFrequency);
                }
                worldFrequencyList = list;
                worldFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");
                for (WorldFrequency w : worldFrequencyList) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WorldFrequency>> getListMap(List<WorldFrequency> worldFrequencyList) {
        Map<String, List<WorldFrequency>> map = new HashMap<>();
        for (WorldFrequency worldFrequency : worldFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(worldFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(worldFrequency);
                map.put(worldFrequency.getWord(), arr);
            } else {
                map.get(worldFrequency.getWord()).add(worldFrequency);
            }
        }
        return map;
    }
}
