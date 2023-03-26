package beakjoon.gold.g21608;

import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] answerArray;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int result = 0;

        // 입력단 - 순서를 지켜서 배열을 돌리기 위해 큐 사용
        Queue<Student> studentQueue = new LinkedList<>();
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < N * N; i++) {
            int target = sc.nextInt();
            List<Integer> loves = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                loves.add(sc.nextInt());
            }
            studentQueue.add(new Student(target, loves));
            resultMap.put(target, loves);
        }

        // 순서대로 queue를 pop 하면서 알고리즘 동작
        answerArray = new int[N][N];
        while (!studentQueue.isEmpty()) {
            int[][] loveArray = new int[N][N];
            int[][] blankArray = new int[N][N];

            Student student = studentQueue.poll();

            Info max = new Info(0, 0, N, N);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (answerArray[i][j] != 0) {
                        continue;
                    }

                    int loveCnt = 0;
                    int blankCnt = 0;

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = j + dx[dir];
                        int ny = i + dy[dir];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                        if (answerArray[ny][nx] == 0) {
                            blankCnt++;
                        } else if (student.loves.contains(answerArray[ny][nx])) {
                            loveCnt++;
                        }
                    }

                    if (loveCnt > max.loveCnt) {
                        max = new Info(blankCnt, loveCnt, j, i);
                    } else if (loveCnt == max.loveCnt) {
                        if (blankCnt > max.blank) {
                            max = new Info(blankCnt, loveCnt, j, i);
                        } else if (blankCnt == max.blank) {
                            if (max.y > i) {
                                max = new Info(blankCnt, loveCnt, j, i);
                            } else if (max.y == i) {
                                if (max.x > j) {
                                    max = new Info(blankCnt, loveCnt, j, i);
                                }
                            }
                        }
                    }
                }
            }
            answerArray[max.y][max.x] = student.myNum;
        }

//        System.out.println(Arrays.deepToString(answerArray));



        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int key = answerArray[i][j];
                List<Integer> loves = resultMap.get(key);

                // 상하좌우를 돌면서 주변에 좋아하는 학생의 갯수 카운트
                int loveCnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = j + dx[dir];
                    int ny = i + dy[dir];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if (loves.contains(answerArray[ny][nx])) {
                        loveCnt++;
                    }
                }
                if (loveCnt == 1) {
                    result += 1;
                } else if (loveCnt == 2) {
                    result += 10;
                } else if (loveCnt == 3) {
                    result += 100;
                } else if (loveCnt == 4) {
                    result += 1000;
                }

            }
        }
        System.out.println(result);
    }

    static class Info{
        //blank : 인접 빈 칸, favoriteCount : 인접 좋아하는 친구
        int blank, loveCnt, x, y;
        //생성자
        public Info(int blank, int loveCnt, int x, int y){
            this.blank = blank;
            this.loveCnt = loveCnt;
            this.x = x;
            this.y = y;
        }
    }

    static class Student {
        int myNum;
        List<Integer> loves;

        public Student(int myNum, List<Integer> loves) {
            this.myNum = myNum;
            this.loves = loves;
        }
    }
}
