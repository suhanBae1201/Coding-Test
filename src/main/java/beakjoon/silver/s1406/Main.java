package beakjoon.silver.s1406;

import java.util.*;
import java.io.*;

public class Main {
    static Stack<Character> rightStack = new Stack<>();
    static Stack<Character> leftStack = new Stack<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        for (char c : s.toCharArray()) {
            leftStack.add(c);
        }

        int cmdCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < cmdCount; i++) {
            String cmd = br.readLine();
            char c = '0';
            if (cmd.length() == 3) {
                c = cmd.charAt(2);
            }
            verifyCommand(cmd.charAt(0), c);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : leftStack) {
            sb.append(ch);
        }
        while (!rightStack.isEmpty()) {
            sb.append(rightStack.pop());
        }
        System.out.println(sb);
        br.close();
    }

    static void verifyCommand(char cmd, char str) {
        if (cmd == 'L') {
            if (leftStack.isEmpty()) {
                return;
            }
            rightStack.add(leftStack.pop());
        } else if (cmd == 'D') {
            if (rightStack.isEmpty()) {
                return;
            }
            leftStack.add(rightStack.pop());
        } else if (cmd == 'B') {
            if (leftStack.isEmpty()) {
                return;
            }
            leftStack.pop();
        } else if (cmd == 'P') {
            leftStack.add(str);
        }
        return;
    }
}
