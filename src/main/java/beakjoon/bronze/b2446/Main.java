package beakjoon.bronze.b2446;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        int size = num - 1;
        for (int i = size; i >= 0; i--) {
            int repeat = 2 * i + 1;
            String s = "*".repeat(repeat);
            String blank = " ".repeat(num - i - 1);
            System.out.println(blank + s);
        }

        size = num;
        for (int i = 1; i < size; i++) {
            int repeat = 2 * i + 1;
            String s = "*".repeat(repeat);
            String blank = " ".repeat(num - i - 1);
            System.out.println(blank + s);
        }
    }

}
