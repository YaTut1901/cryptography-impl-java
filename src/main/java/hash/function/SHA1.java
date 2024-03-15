package hash.function;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SHA1 {
    public static String hash(String arg) {
        List<String> phrases = getPhrases(arg);
        List<List<String>> words = phrases.stream()
                                          .map(phrase -> split(phrase, 32))
                                          .map(phrase -> extendPhrase(phrase, 80))
                                          .toList();

        return mashUp(words);
    }

    private static String mashUp(List<List<String>> phrases) {
        String h0 = "01100111010001010010001100000001";
        String h1 = "11101111110011011010101110001001";
        String h2 = "10011000101110101101110011111110";
        String h3 = "00010000001100100101010001110110";
        String h4 = "11000011110100101110000111110000";

        String a = h0;
        String b = h1;
        String c = h2;
        String d = h3;
        String e = h4;

        for (int i = 0; i < phrases.size(); i++) {
            for (int j = 0; j < phrases.getFirst().size(); j++) {
                String f;
                String k;

                if (j < 20) {
                    String BandC = and(b, c);
                    String notBandD = and(not(b), d);
                    f = or(BandC, notBandD);
                    k = "01011010100000100111100110011001";
                } else if (j < 40) {
                    String BxorC = xor(b, c);
                    f = xor(BxorC, d);
                    k = "01101110110110011110101110100001";
                } else if (j < 60) {
                    String BandC = and(b, c);
                    String BandD = and(b, d);
                    String CandD = and(c, d);
                    String BandCorBandD = or(BandC, BandD);
                    f = or(BandCorBandD, CandD);
                    k = "10001111000110111011110011011100";
                } else {
                    String BxorC = xor(b, c);
                    f = xor(BxorC, d);
                    k = "11001010011000101100000111010110";
                }
                String word = phrases.get(i).get(j);
                String tempA = binaryAdd(leftRotate(a, 5), f);
                String tempB = binaryAdd(tempA, e);
                String tempC = binaryAdd(tempB, k);
                String temp = binaryAdd(tempC, word);

                temp = truncate(temp, 32);
                e = d;
                d = c;
                c = leftRotate(b, 30);
                b = a;
                a = temp;
            }
            h0 = truncate(binaryAdd(h0, a), 32);
            h1 = truncate(binaryAdd(h1, b), 32);
            h2 = truncate(binaryAdd(h2, c), 32);
            h3 = truncate(binaryAdd(h3, d), 32);
            h4 = truncate(binaryAdd(h4, e), 32);
        }
        return binToHex(h0) + binToHex(h1) + binToHex(h2) + binToHex(h3) + binToHex(h4);
    }

    private static List<String> getPhrases(String arg) {
        String binaryString8bit = getBinary8bit(arg);
        int binaryString8bitLength = binaryString8bit.length();
        binaryString8bit = binaryString8bit + "1";

        List<String> phrases = split(binaryString8bit, 512);
        String last = phrases.getLast();

        String paddedLast = padZero(last, 448, false);
        String paddedBinaryLength = padZero(Integer.toBinaryString(binaryString8bitLength), 64, true);
        phrases.set(phrases.size() - 1, paddedLast + paddedBinaryLength);

        return phrases;
    }

    private static List<String> split(String str, int peaceSize) {
        return Arrays.stream(str.split("(?<=\\G.{" + peaceSize + "})")).collect(Collectors.toList());
    }

    private static String getBinary8bit(String arg) {
        return arg.chars()
                .mapToObj(Integer::toBinaryString)
                .map(b -> padZero(b, 8, true))
                .collect(Collectors.joining(""));
    }

    private static String padZero(String bin, Integer length, boolean prepend) {
        String padding = String.join("", Collections.nCopies(length - bin.length(), "0"));
        return prepend ? padding + bin
                       : bin + padding;
    }

    private static List<String> extendPhrase(List<String> phrase, int length) {
        for (int i = phrase.size(); i <= length - 1; i++) {
            String first = phrase.get(i - 3);
            String second = phrase.get(i - 8);
            String third = phrase.get(i - 14);
            String fourth = phrase.get(i - 16);

            String xorA = xor(first, second);
            String xorB = xor(xorA, third);
            String xorC = xor(xorB, fourth);

            String newWord = leftRotate(xorC, 1);
            phrase.add(newWord);
        }
        return phrase;
    }

    private static String leftRotate(String word, int offset) {
        int actualOffset = word.length() > offset ? offset : offset % word.length();
        return word.substring(actualOffset) + word.substring(0, actualOffset);
    }

    private static String xor(String a, String b){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            ans.append(a.charAt(i) == b.charAt(i) ? "0" : "1");
        }
        return ans.toString();
    }

    private static String or(String a, String b){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            ans.append(a.charAt(i) == '1' || b.charAt(i) == '1' ? "1" : "0");
        }
        return ans.toString();
    }

    private static String and(String a, String b){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            ans.append(a.charAt(i) == '1' && b.charAt(i) == '1' ? "1" : "0");
        }
        return ans.toString();
    }

    private static String not(String a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            ans.append(a.charAt(i) == '1' ? "0" : "1");
        }
        return ans.toString();
    }

    private static String binaryAdd(String a, String b) {
        int maxLength = Math.min(a.length(), b.length());

        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            int sum = Character.getNumericValue(a.charAt(i)) +
                    Character.getNumericValue(b.charAt(i)) +
                    carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            result.insert(0, '1');
        }
        return result.toString();
    }

    private static String truncate(String str, int length) {
        return str.substring(0, length);
    }

    private static String binToHex(String bin) {
        return new BigInteger(bin, 2).toString(16);
    }
}
