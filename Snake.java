/**
 * Created by Timur on 01.07.2016.
 */
public class Snake {
    private Apple apple;
    boolean isAlive = true;
    int[][] Ar;
    int size;
    int headX = 30;
    int headY = 30;
    int score;
    int time;
    public static int t;
    public boolean BOT2Trigger = false;


    private Direction direction;

    public Snake(int[][] matrix, int size) {
        time = 30000;
        score = 0;
        direction = Direction.LEFT;
        this.size = size;
        Ar = new int[100][2];
        apple = new Apple();
        gouon(matrix, headX, headY, size, Ar);

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(int[][] matrix) {

        if (direction.equals(Direction.LEFT)) {

            bodyM();
            LEFT(matrix);

        } else if (direction.equals(Direction.RIGHT)) {

            bodyM();
            RIGHT(matrix);


        } else if (direction.equals(Direction.UP)) {

            bodyM();
            UP(matrix);


        } else if (direction.equals(Direction.DOWN)) {

            bodyM();
            DOWN(matrix);

        } else if (direction.equals(Direction.BOT)) BOT(matrix);

        if (time <= 0) isAlive = false;
    }

    public static void gouon(int[][] matrix, int headX, int headY, int size, int[][] Ar) {
        for (int i = 0; i < size; i++, headX++) {
            matrix[headY][headX] = 1;
            Ar[i][0] = headX;
            Ar[i][1] = headY;
        }
    }

    public int center(int K, int KK) {

        if (KK + (30 - K) > 60) {
            return (KK + (30 - K)) - 60;
        } else if (KK + (30 - K) < 0) {
            return (KK + (30 - K) + 60);
        } else {
            return (KK + (30 - K));
        }
    }

    public void bodyM() {
        for (int i = size - 1; i > 0; i--) {
            t = Ar[i - 1][0];
            Ar[i][0] = t;
            t = Ar[i - 1][1];
            Ar[i][1] = t;
        }
    }

    public boolean canGo(int y, int x) {
        for (int i = 1; i < size; i++) {
            if ((x == Ar[i][0]) && (y == Ar[i][1]))
                return false;
        }
        return true;
    }

    public void isAlive() {
        for (int i = 1; i < size; i++) {
            if ((Ar[0][0] == Ar[i][0]) && (Ar[0][1] == Ar[i][1]))
                isAlive = false;
        }
    }

    public void eatApple(int[][] matrix) {
        if ((Ar[0][0] == apple.x) && (Ar[0][1] == apple.y)) {
            score += Apple.point;
            apple.generateApple(matrix);
            time += 35;
            size++;
        }
    }

    public void eatSApple() {
        if (apple.S == true) {
            if ((Ar[0][0] == apple.S2.x) && (Ar[0][1] == apple.S2.y) || (Ar[0][0] == apple.S2.x2) && (Ar[0][1] == apple.S2.y2) || (Ar[0][0] == apple.S2.x3) && (Ar[0][1] == apple.S2.y3)) {
                score += apple.S2.point;
                time += 35;
                size++;
                apple.S = false;
            }
        }
    }

    public void eatRApple() {
        if (apple.R == true) {
            if ((Ar[0][0] == apple.R1.x) && (Ar[0][1] == apple.R1.y)) {
                score += apple.R1.point;
                time += 150;
                size++;
                apple.R = false;
            }

        }
    }

    public void matrixS(int[][] matrix) {
        for (int i = 0; i < size; i++) {
            matrix[Ar[i][1]][Ar[i][0]] = 1;
        }
    }

    public void BOT(int[][] matrix) {
        {
            bodyM();

            if (30 > center(apple.x, headX)) {
                if (canGo(headY, headX + 1)) {
                    RIGHT(matrix);
                } else if (canGo(headY, headX - 1)) {
                    LEFT(matrix);
                } else if (canGo(headY + 1, headX)) {
                    DOWN(matrix);
                } else if (canGo(headY - 1, headX)) {
                    UP(matrix);
                } else RIGHT(matrix);


            } else if (30 < center(apple.x, headX)) {

                if (canGo(headY, headX + 1)) {
                    LEFT(matrix);
                } else if (canGo(headY, headX - 1)) {
                    RIGHT(matrix);
                } else if (canGo(headY + 1, headX)) {
                    DOWN(matrix);
                } else if (canGo(headY - 1, headX)) {
                    UP(matrix);
                } else RIGHT(matrix);

            } else {

                if (30 > center(apple.y, headY)) {

                    if (canGo(headY, headX + 1)) {
                        DOWN(matrix);
                    } else if (canGo(headY, headX - 1)) {
                        UP(matrix);
                    } else if (canGo(headY + 1, headX)) {
                        RIGHT(matrix);
                    } else if (canGo(headY - 1, headX)) {
                        LEFT(matrix);
                    } else RIGHT(matrix);

                } else if (30 < center(apple.y, headY)) {

                    if (canGo(headY, headX + 1)) {
                        UP(matrix);
                    } else if (canGo(headY, headX - 1)) {
                        DOWN(matrix);
                    } else if (canGo(headY + 1, headX)) {
                        RIGHT(matrix);
                    } else if (canGo(headY - 1, headX)) {
                        LEFT(matrix);
                    } else RIGHT(matrix);
                } else {
                    System.out.println("Ошибка!!!!");
                }


            }
            met(matrix);


        }
    }

    public void UP(int[][] matrix) {

        if (--headY == -1) {
            headY = 60;
        }
        t = headY;
        Ar[0][1] = t;

        met(matrix);
    }

    public void RIGHT(int[][] matrix) {

        if (++headX == 61) {
            headX = 0;
        }
        t = headX;
        Ar[0][0] = t;

        met(matrix);
    }

    public void DOWN(int[][] matrix) {

        if (++headY == 61) {
            headY = 0;
        }
        t = headY;
        Ar[0][1] = t;

        met(matrix);
    }

    public void LEFT(int[][] matrix) {


        if (--headX == -1) {
            headX = 60;
        }

        t = headX;

        Ar[0][0] = t;


        met(matrix);
    }

    public void met(int[][] matrix) {
        isAlive();

        matrixS(matrix);

        eatApple(matrix);

        eatSApple();

        eatRApple();

        apple.paintApple(matrix);
        time--;
    }

    public void BOT2(int[][] matrix) {
        if (BOT2Trigger) {
            bodyM();
            LEFT(matrix);
            BOT2Trigger = false;
            return;
        }
        bodyM();
        UP(matrix);
        BOT2Trigger = true;

    }

    public void miniBOT(int[][] matrix) {
        if (apple.y > headY) {
            bodyM();
            DOWN(matrix);

        } else if (apple.y < headY) {
            bodyM();
            UP(matrix);
        } else if (apple.y == headY) {
            if (apple.x > headX) {
                bodyM();
                RIGHT(matrix);
            } else if (apple.x < headX) {
                bodyM();
                LEFT(matrix);
            } else {
                System.out.println("ПИЗДЕЦ!");
            }
        }

        System.out.println(headX + "\t" + headY + "\n");
        System.out.println(apple.x + "\t" + apple.y);

    }


}
