package most.common.letter;

import java.util.*;

public class MostCommonLetter {

    public static void main(String[] args) {

        //read the input from the console
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter some text: ");
        StringBuilder text = new StringBuilder();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.isEmpty()) {
                break;
            }
            text.append(input);
        }

        //count the number appearances of every letter
        HashMap<Character, Integer> lettersStatistics = countLetters(text);

        //sort letters by the number of appearances
        TreeMap<Character, Integer> sortedStatistics = sortStatisticsByNumberAppearances(lettersStatistics);

        //print the result
        printLettersStatistic(sortedStatistics);

    }

    public static HashMap<Character, Integer> countLetters(StringBuilder text) {
        HashMap<Character, Integer> lettersStatistics = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (Character.isAlphabetic(symbol)) {
                symbol = Character.toUpperCase(symbol);
                if (lettersStatistics.containsKey(symbol)) {
                    int currentNumberTimes = lettersStatistics.get(symbol);
                    lettersStatistics.put(symbol, ++currentNumberTimes);
                }
                else {
                    //first appearance
                    lettersStatistics.put(symbol, 1);
                }
            }
        }

        return lettersStatistics;
    }

    public static TreeMap<Character, Integer> sortStatisticsByNumberAppearances(HashMap<Character, Integer> lettersStatistics) {
        Comparator<Character> comparatorByValue = (letter1, letter2) -> {
            if (lettersStatistics.get(letter2).compareTo(lettersStatistics.get(letter1)) == 0) {
                return 1;
            }
            return lettersStatistics.get(letter2).compareTo(lettersStatistics.get(letter1));
        };

        TreeMap<Character, Integer> sortedStatistics  = new TreeMap<>(comparatorByValue);
        sortedStatistics.putAll(lettersStatistics);

        return sortedStatistics;
    }

    public static void printLettersStatistic(TreeMap<Character, Integer> sortedStatistics) {
        int maxAppearances = sortedStatistics.firstEntry().getValue();

        for (Map.Entry<Character, Integer> entry : sortedStatistics.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            System.out.print(key + ": " + value + " ");
            printHashTags(value, maxAppearances);
            System.out.println();

        }
    }

    public static void printHashTags(int currentLetterNumberAppearances, int maxAppearances) {
        int maxHashTags = 20;
        double numberHashTagsDouble = (((double) currentLetterNumberAppearances / maxAppearances)  * maxHashTags);
        int numberHashTags;
        if (numberHashTagsDouble - (int)numberHashTagsDouble > 0.5) {
            numberHashTags = (int)Math.ceil(numberHashTagsDouble);
        }
        else {
            numberHashTags = (int)Math.floor(numberHashTagsDouble);
        }

        for (int i = 0; i < numberHashTags; i++) {
            System.out.print("#");
        }
    }

}
