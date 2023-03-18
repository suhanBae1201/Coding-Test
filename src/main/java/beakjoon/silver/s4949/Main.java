package beakjoon.silver.s4949;

import java.util.*;
import java.io.*;

public class Main {
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] agrs) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String text = br.readLine();
            if (text.equals(".")) {
                break;
            }
            boolean isSuccess = true;
            for (char c : text.toCharArray()) {
                try {
                    verifyBlank(c);
                } catch (CustomException ex) {
                    System.out.println("no");
                    isSuccess = false;
                    stack.clear();
                    break;
                }
            }

            if(!stack.isEmpty()){
                System.out.println("no");
                isSuccess = false;
                stack.clear();
            }

            if (isSuccess) {
                System.out.println("yes");
            }

        }
    }

    static void verifyBlank(char c) {
        if(c =='.'){
            return;
        }

        if (c == '(' || c == '[') {
            stack.add(c);
            return;
        }

        if (c == ')') {
            if(stack.isEmpty()){
                throw new CustomException();
            }

            if (stack.peek().equals('(')) {
                stack.pop();
            } else {
                throw new CustomException();
            }
        } else if (c == ']') {
            if(stack.isEmpty()){
                throw new CustomException();
            }

            if (stack.peek().equals('[')) {
                stack.pop();
            } else {
                throw new CustomException();
            }
        }
    }

    static class CustomException extends RuntimeException {
        public CustomException() {
            super();
        }
    }
}
