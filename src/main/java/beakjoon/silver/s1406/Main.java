package beakjoon.silver.s1406;

import java.util.*;
import java.io.*;

public class Main {
    static int cursor = 0;
    static List<Character> list = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner scanner = new Scanner(System.in);
        String s = br.readLine();

        for (char c : s.toCharArray()) {
            list.add(c);
        }
        cursor = list.size();

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
        for (char ch : list) {
            sb.append(ch);
        }
        System.out.println(sb);
        br.close();
    }

    static void verifyCommand(char cmd, char str) {
        if (cmd == 'L') {
            if (cursor == 0) {
                return;
            }
            cursor--;
        } else if (cmd == 'D') {
            if (cursor == list.size()) {
                return;
            }
            cursor++;
        } else if (cmd == 'B') {
            if (cursor == 0) {
                return;
            }
            list.remove(cursor - 1);
            cursor--;
        } else if (cmd == 'P') {
            list.add(cursor == 0 ? 0 : cursor, str);
            cursor++;
        }
        return;
    }
}
