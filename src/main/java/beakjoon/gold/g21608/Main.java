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

                    }


                }
            }

            // 해당 학생이 좋아하는 학생이 주변에 최대 몇명 있는지 찾기
            int loveMax = getLoveMax(N, loveArray, student);

            // loveMax가 0이면 blank 찾기로 이동, 0이 아니면 loveMax에 맞는 값 찾기
            if (loveMax == 0) {
                // blank 찾기
                findBlank(N, blankArray, student, new Stack<>());

            } else {
                // max에 맞는 값 찾아서 stack에 넣기
                Stack<Point> pointStack = new Stack<>();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (loveMax == loveArray[i][j]) {
                            pointStack.add(new Point(i, j));
                        }
                    }
                }
                if (pointStack.size() == 1) {
                    Point point = pointStack.pop();
                    answerArray[point.x][point.y] = student.myNum;
                    continue;
                } else {
                    // blank 찾기
                    findBlank(N, blankArray, student, pointStack);
                }
            }
        }

        System.out.println(Arrays.deepToString(answerArray));



        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int key = answerArray[i][j];
                int[] loves = resultMap.get(key);

                // 상하좌우를 돌면서 주변에 좋아하는 학생의 갯수 카운트
                int loveCnt = 0;
                for (int next = 0; next < 4; next++) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = j + dx[dir];
                        int ny = i + dy[dir];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                        if (loves[next] == answerArray[ny][nx]) {
                            loveCnt++;
                            break;
                        }
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

    static void findBlank(int N, int[][] blankArray, Student student, Stack<Point> pointStack) {
        int blankMax = -1;

        // 가장 처음 아무것도 없을때 동작
        if (pointStack.isEmpty()) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 상하좌우를 돌면서 주변에 비어있는 칸 갯수 카운트
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = j + dx[dir];
                        int ny = i + dy[dir];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }

                        if (answerArray[i][j] != 0) {
                            continue;
                        }

                        if (answerArray[ny][nx] == 0) {
                            blankArray[i][j]++;
                            blankMax = Math.max(blankArray[i][j], blankMax);
                        }
                    }
                }
            }

            findSeat(N, blankArray, student, blankMax);
        } else {
            Point maxPoint = null;
            while (!pointStack.isEmpty()) {
                Point poll = pointStack.pop();

                int i = poll.x;
                int j = poll.y;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = j + dx[dir];
                    int ny = i + dy[dir];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }

                    if (answerArray[i][j] != 0) {
                        continue;
                    }

                    if (answerArray[ny][nx] == 0) {
                        blankArray[i][j]++;
                        if (blankArray[i][j] >= blankMax) {
                            blankMax = blankArray[i][j];
                            maxPoint = poll;
                        }
                    }
                }
            }
            if (maxPoint != null) {
                answerArray[maxPoint.x][maxPoint.y] = student.myNum;
            } else {
                findSeat(N, blankArray, student, blankMax);
            }
            return;
        }
    }

    static void findSeat(int N, int[][] blankArray, Student student, int blankMax) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blankMax == blankArray[i][j] || (blankMax == -1 && answerArray[i][j] == 0)) {
                    answerArray[i][j] = student.myNum;
                    return;
                }
            }
        }
    }

    static int getLoveMax(int N, int[][] loveArray, Student student) {
        int loveMax = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 정답 배열에 어떠한 값이 차있으면 확인할 필요 없음
                if (answerArray[i][j] != 0) {
                    continue;
                }

                // 상하좌우를 돌면서 주변에 좋아하는 학생의 갯수 카운트
                int[] loves = student.loves;
                for (int next = 0; next < 4; next++) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = j + dx[dir];
                        int ny = i + dy[dir];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                        if (loves[next] == answerArray[ny][nx]) {
                            loveArray[i][j]++;
                            loveMax = Math.max(loveArray[i][j], loveMax);
                            break;
                        }
                    }
                }
            }
        }
        return loveMax;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
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
