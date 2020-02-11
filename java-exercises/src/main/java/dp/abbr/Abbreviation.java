package dp.abbr;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://www.hackerrank.com/challenges/abbr/problem
 */
public class Abbreviation {

    static String abbrevationTab(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        boolean[][] tab = new boolean[lenB][lenA];

        for (int i = 0; i < lenB; i++) {
            for (int j = 0; j < lenA; j++) {
                char ca = a.charAt(lenA - j - 1);
                char cb = b.charAt(lenB - i - 1);
                boolean equalIgnoringCase = ca == cb || Character.toUpperCase(ca) == cb;
                if (j == 0) {
                    tab[i][j] = equalIgnoringCase;
                } else {
                    tab[i][j] = equalIgnoringCase || tab[i][j - 1];
                }
            }
        }
        return tab[lenB - 1][lenA - 1] ? "YES": "NO";
    }

    static String abbreviationV2(String a, String b) {
        return abbreviationRecMem(a, b, new HashMap<>()) ? "YES": "NO";
    }

    static boolean abbreviationRecMem(String a, String b, Map<String, Boolean> mem) {
        System.out.println("A: " + a + " B: " + b);

        if (mem.containsKey(a + ":" + b)) {
            return mem.get(a + ":" + b);
        }

        int lenA = a.length();
        int lenB = b.length();


        if (lenA == 0 && lenB == 0) {
            return true;
        }

        if (lenA == 0) {
            return false;
        }

        if (lenB == 0) {
            // check remaining chars in a are all lowercase
            for (int i = 0; i < lenA; i++) {
                if (Character.isUpperCase(a.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        char ca = a.charAt(0);
        char cb = b.charAt(0);

        Boolean result = null;

        if (ca == cb) {
            result = abbreviationRecMem(a.substring(1), b.substring(1), mem);
        } else if (Character.isUpperCase(ca)) {
            System.out.println("non equal capital letter");
            return false;
        } else {
            // consider either delete or uppercase
            result = abbreviationRecMem(a.substring(1), b, mem)
                    || abbreviationRecMem(Character.toUpperCase(ca) + a.substring(1), b, mem);
        }
        mem.put(a + ":" + b, result);
        return result;
    }

    static boolean abbreviationRec(String a, String b) {
        System.out.println("A: " + a + " B: " + b);
        int lenA = a.length();
        int lenB = b.length();


        if (lenA == 0 && lenB == 0) {
            return true;
        }

        if (lenA == 0) {
            return false;
        }

        if (lenB == 0) {
            // check remaining chars in a are all lowercase
            for (int i = 0; i < lenA; i++) {
                if (Character.isUpperCase(a.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        char ca = a.charAt(0);
        char cb = b.charAt(0);

        if (ca == cb) {
            return abbreviationRec(a.substring(1), b.substring(1));
        } else if (Character.isUpperCase(ca)) {
            System.out.println("non equal capital letter");
            return false;
        } else {
            // consider either delete or uppercase
            return abbreviationRec(a.substring(1), b)
                    || abbreviationRec(Character.toUpperCase(ca) + a.substring(1), b);
        }
    }

    static String abbreviation(String a, String b) {
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        int lenA = a.length();
        int lenB = b.length();

        if (lenB > lenA) {
            System.out.println("lenB > lenA");
            return "NO";
        }

        int i = 0;
        int j = 0;
        while (i < lenA) {
            char ca = a.charAt(i);
            if (j == lenB) {
                // check remaining chars in a are all lowercase
                if (Character.isUpperCase(ca)) {
                    System.out.println("uppercase remaining char");
                    return "NO";
                }
            } else {
                char cb = b.charAt(j);
                char caUpper = Character.toUpperCase(ca);
                if (ca == cb || caUpper == cb) {
                    j++;
                } else if (Character.isUpperCase(ca)) {
                    // non equal capital letter
                    System.out.println("non equal capital letter");
                    return "NO";
                }
            }
            i++;
        }
        System.out.println("j: " + j);
        if (j == lenB) {
            System.out.println("YES");
            return "YES";
        }
        System.out.println("NO");
        return "NO";
    }

    public static void main(String[] args) {
        // YES
//        System.out.println(abbrevationTab("bBccC", "BBC"));
        // NO
        System.out.println(abbrevationTab("AfPZN", "APZNC"));
    }
}
