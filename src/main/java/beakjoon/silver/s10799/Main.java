package beakjoon.silver.s10799;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();

        Stack<Character> stack = new Stack<>();
        char prev = ' ';
        int totalCnt = 0;

        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (prev == '(' && c == ')') {
                // Razor인 경우
                stack.pop();
                totalCnt += stack.size();

                prev = c;
                continue;
            }

            if(c == '('){
                stack.push(c);
            } else {
                stack.pop();
                totalCnt += 1;
            }
            prev = c;
        }
        System.out.println(totalCnt);
    }
}
