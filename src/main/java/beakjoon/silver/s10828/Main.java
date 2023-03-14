package beakjoon.silver.s10828;

import java.util.*;
import java.io.*;

public class Main {

    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < count; i++) {
            int num = myStack(br.readLine());
            if (num == Integer.MAX_VALUE) {
                continue;
            }
            System.out.println(num);
        }

        br.close();
    }

    static int myStack(String cmd) {
        String[] split = cmd.split(" ");
        int num = -1;
        if (split.length == 2) {
            num = Integer.parseInt(split[1]);
        }

        if (split[0].equals("push")) {
            stack.push(num);
            return Integer.MAX_VALUE;
        } else if (split[0].equals("top")) {
            if (stack.isEmpty()) {
                return -1;
            }
            return stack.peek();
        } else if (split[0].equals("size")) {
            return stack.size();
        } else if (split[0].equals("empty")) {
            return stack.isEmpty() ? 1 : 0;
        } else if (split[0].equals("pop")) {
            if (stack.isEmpty()) {
                return -1;
            }
            return stack.pop();
        }

        return -1;
    }

}
