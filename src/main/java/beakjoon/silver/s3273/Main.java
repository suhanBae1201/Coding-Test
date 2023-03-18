package beakjoon.silver.s3273;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer.parseInt(br.readLine());

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        Set<Integer> ints = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();
        while (tokenizer.hasMoreTokens()) {
            ints.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int finalNum = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i : ints) {
            if (finalNum <= i || resultSet.contains(i)) {
                continue;
            }

            int result = finalNum - i;

            if (ints.contains(result) && result != i) {
                cnt++;
                resultSet.add(result);
            }
        }
        System.out.println(cnt);
    }
}
