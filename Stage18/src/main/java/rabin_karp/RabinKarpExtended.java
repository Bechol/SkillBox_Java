package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, Integer> number2position = new TreeMap<>();
    private int a = 256; // количество символов в алфавите
    private int q = 101; // некоторое простое число для вычисления хеша

    public RabinKarpExtended(String text) {
        this.text = text;
    }

    public List<Integer> search(String pattern) {
        List<Integer> indices;
        int patternLength = pattern.length(); //количество символов в паттерне
        int patternHash = 0; // вычисленный хеш для паттерна
        // вычисляем хеш паттерна
        for (int i = 0; i < patternLength; i++) {
            patternHash = (a * patternHash + pattern.charAt(i)) % q;
        }
        createIndex(patternLength);
        int finalQueryHash = patternHash;
        indices = number2position.entrySet().stream()
                .filter(entry -> {
                    if (finalQueryHash == entry.getValue()) {
                        int j;
                        // сравниваем символы в строке и паттерне
                        for (j = 0; j < patternLength; j++) {
                            if (text.charAt(entry.getKey() + j) != pattern.charAt(j)) {
                                break;
                            }
                        }
                        return j == patternLength;
                    }
                    return false;
                })
                .map(Map.Entry::getKey).collect(Collectors.toList());
        return indices;
    }


    private void createIndex(int patternLength) {
        int h = 1;
        int pos;
        int textHash = 0;

        for (pos = 0; pos < patternLength - 1; pos++)
            h = (h * a) % q;
        for (pos = 0; pos < patternLength; pos++) {
            textHash = (a * textHash + text.charAt(pos)) % q;
        }
        for (pos = 0; pos <= text.length() - patternLength; pos++) {
            number2position.put(pos, textHash);
            if (pos < text.length() - patternLength) {
                textHash = (a * (textHash - text.charAt(pos) * h) + text.charAt(pos + patternLength)) % q;
                if (textHash < 0)
                    textHash = (textHash + q);
            }
        }
    }
}