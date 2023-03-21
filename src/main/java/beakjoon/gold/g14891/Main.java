package beakjoon.gold.g14891;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] wheels = new boolean[4][8];
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                wheels[i][j] = chars[j] == '1';
            }
        }

        int k = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer;
        for (int i = 0; i < k; i++) {

            tokenizer = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(tokenizer.nextToken());
            boolean isForward = tokenizer.nextToken().equals("1");

            visited = new boolean[4];
            rotate(wheelNum - 1, isForward, wheels);

        }

        int point = 0;
        for (int j = 0; j < wheels.length; j++) {
            boolean[] wheel = wheels[j];
            if (wheel[0]) {
                point += (int) Math.pow(2, j);
            }
        }
        System.out.println(point);
    }

    static void rotate(int target, boolean isForward, boolean[][] wheels) {
        boolean[][] copyWheels = Arrays.copyOf(wheels, wheels.length);
        visited[target] = true;
        if (target < 3 && !visited[target + 1] && copyWheels[target][2] != copyWheels[target + 1][6]) {
            rotate(target + 1, !isForward, copyWheels);
        }
        if (target > 0 && !visited[target - 1] && copyWheels[target - 1][2] != copyWheels[target][6]) {
            rotate(target - 1, !isForward, copyWheels);
        }
        verifyDirection(target, isForward, copyWheels);
    }


    static void verifyDirection(int wheelNum, boolean isForward, boolean[][] copyWheels) {
        if (isForward) {
            forward(wheelNum, copyWheels);
        } else {
            reverse(wheelNum, copyWheels);
        }
    }


    static void forward(int wheelNum, boolean[][] copyWheels) {
        boolean[] wheel = wheels[wheelNum];
        boolean[] temp = copyWheels[wheelNum];
        for (int i = 0; i < wheel.length; i++) {
            wheel[(i + 1) % 8] = temp[i];
        }
    }

    static void reverse(int wheelNum, boolean[][] copyWheels) {
        boolean[] wheel = wheels[wheelNum];
        boolean[] temp = copyWheels[wheelNum];
        for (int i = 0; i < wheel.length; i++) {
            wheel[(i - 1) < 0 ? 7 : (i - 1)] = temp[i];
        }
    }

}
