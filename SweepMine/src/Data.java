import constant.DifficultModeEnum;
import constant.Status;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Data {
    public static Status status = Status.LOADING;
    public static int size = 16;
    public static int numOfMine = 0;

    // 表示是否有雷，1：有，0没有
    public static int[][] maps = null;
    // 是否被访问
    public static boolean[][] visited = null;
    // 周边雷的数量
    public static int[][] nums = null;
    // 是否被标记
    public static boolean[][] flags = null;

    public static Point lastVisitedPoint = null;

    private static DifficultModeEnum mode;

    public static void initData(DifficultModeEnum difficultModeEnum) {
        status = Status.LOADING;
        initMine(difficultModeEnum);
    }

    public static void initMine(DifficultModeEnum difficultModeEnum) {
        mode = difficultModeEnum;
        numOfMine = initMineNumByMode();
        maps = new int[size][size];
        visited = new boolean[size][size];
        nums = new int[size][size];
        flags = new boolean[size][size];
        int num = numOfMine;
        lastVisitedPoint = new Point(-1, -1);

        while (num > 0) {
            while (true) {
                int x = (int) (new Random().nextInt(size));
                int y = (int) (new Random().nextInt(size));
                if (maps[x][y] != 1) {
                    maps[x][y] = 1;
                    break;
                }
            }
            num--;
        }

        // 计算周边的雷
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nums[i][j] = count(i, j);
            }
        }
    }

    public static int initMineNumByMode() {
        int max = mode.getMax();
        int min = mode.getMin();
        Random random = new Random();

        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static int count(int i, int j) {
        int x = i - 1;
        int sum = 0;
        while (x <= i + 1) {
            if (x >= 0 && x < size) {
                int y = j - 1;
                while (y <= j + 1) {
                    if (y >= 0 && y < size) {
                        sum = sum + maps[x][y];
                    }
                    y++;
                }
            }
            x++;
        }
        return sum;
    }

    public static void printf(int[][] datas) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(datas[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void visit(int x, int y) {
        lastVisitedPoint.x = x;
        lastVisitedPoint.y = y;
        if (maps[x][y] == 1) {
            status = Status.FAILED;
            // 游戏结束，暴露所有的雷
        } else {
            // 点击的不是雷
            Queue<Point> points = new LinkedList<>();
            points.add(new Point(x, y));
            while (!points.isEmpty()) {
                Point point = points.poll();
                visited[point.x][point.y] = true;
                if (nums[point.x][point.y] == 0) {
                    addToVisited(points, point.x, point.y);
                }
            }
        }
    }

    public static void addToVisited(Queue<Point> points, int i, int j) {
        int x = i - 1;
        while (x <= i + 1) {
            if (x >= 0 && x < size) {
                int y = j - 1;
                while (y <= j + 1) {
                    if (y >= 0 && y < size) {
                        if (!(x == i && j == y)) {
                            // 没访问过且不是雷
                            if (!visited[x][y] && maps[x][y] == 0) {
                                points.add(new Point(x, y));
                            }
                        }
                    }
                    y++;
                }
            }
            x++;
        }
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
