package beakjoon.bronze.b10808;

import java.util.*;

/**
 * @author 배수한
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        char[] chars = s.toCharArray();

        int[] result = new int[26];
        for (char aChar : chars) {
            result[(aChar - 'a')]++;
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
