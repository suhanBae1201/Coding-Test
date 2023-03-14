package programmers.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 배수한
 * @since 1.0
 */
public class BufferedTest {

    public static void main(String[] args) throws IOException {

        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

            while (tokenizer.hasMoreTokens()) {
                System.out.println("tokenizer.nextToken() = " + tokenizer.nextToken());
            }
        }

    }
}
