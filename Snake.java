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


    private Direction direction;

    public Snake(int[][] matrix, int size) {
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
        int t;
        if (direction.equals(Direction.LEFT)) {
            for (int i = size - 1; i > 0; i--) {
                t = Ar[i - 1][0];
                Ar[i][0] = t;
                t = Ar[i - 1][1];
                Ar[i][1] = t;
            }

            if (--headY == -1) {
                headY = 59;
            }

            t = headY;

            Ar[0][0] = t;


            for (int i = 1; i < size; i++) {
                if ((Ar[0][0] == Ar[i][0]) && (Ar[0][1] == Ar[i][1]))
                    isAlive = false;
            }

            for (int i = 0; i < size; i++) {
                matrix[Ar[i][0]][Ar[i][1]] = 1;
            }

            apple.paintApple(matrix);
            if ((Ar[0][0] == apple.x) && (Ar[0][1] == apple.y)) {
                score += Apple.point;
                apple.generateApple(matrix);
                size++;
                System.out.println(score); // на время
            }
            if(apple.S == true) {
                if ((Ar[0][0] == apple.S2.x) && (Ar[0][1] == apple.S2.y) || (Ar[0][0] == apple.S2.x2) && (Ar[0][1] == apple.S2.y2) || (Ar[0][0] == apple.S2.x3) && (Ar[0][1] == apple.S2.y3)) {
                    score += apple.S2.point;
                    size++;
                    apple.S = false;
                    System.out.println(score); // на время
                }
            }



            //return matrix;

        } else if (direction.equals(Direction.RIGHT)) {
            for (int i = size - 1; i > 0; i--) {
                t = Ar[i - 1][0];
                Ar[i][0] = t;
                t = Ar[i - 1][1];
                Ar[i][1] = t;
            }
            if (++headY == 60) {
                headY = 0;
            }
            t = headY;
            Ar[0][0] = t;

            for (int i = 1; i < size; i++) {
                if ((Ar[0][0] == Ar[i][0]) && (Ar[0][1] == Ar[i][1]))
                    isAlive = false;
            }

            for (int i = 0; i < size; i++) {
                matrix[Ar[i][0]][Ar[i][1]] = 1;
            }
            apple.paintApple(matrix);
            if ((Ar[0][0] == apple.x) && (Ar[0][1] == apple.y)) {
                score += Apple.point;
                apple.generateApple(matrix);
                size++;
                System.out.println(score); // на время
            }
            if(apple.S == true) {
                if ((Ar[0][0] == apple.S2.x) && (Ar[0][1] == apple.S2.y) || (Ar[0][0] == apple.S2.x2) && (Ar[0][1] == apple.S2.y2) || (Ar[0][0] == apple.S2.x3) && (Ar[0][1] == apple.S2.y3)) {
                    score += apple.S2.point;
                    size++;
                    apple.S = false;
                    System.out.println(score); // на время
                }
            }


        } else if (direction.equals(Direction.UP)) {
            for (int i = size - 1; i > 0; i--) {
                t = Ar[i - 1][0];
                Ar[i][0] = t;
                t = Ar[i - 1][1];
                Ar[i][1] = t;
            }
            if (--headX == -1) {
                headX = 59;
            }
            t = headX;
            Ar[0][1] = t;

            for (int i = 1; i < size; i++) {
                if ((Ar[0][0] == Ar[i][0]) && (Ar[0][1] == Ar[i][1]))
                    isAlive = false;
            }

            for (int i = 0; i < size; i++) {
                matrix[Ar[i][0]][Ar[i][1]] = 1;
            }
            apple.paintApple(matrix);
            if ((Ar[0][0] == apple.x) && (Ar[0][1] == apple.y)) {
                score += Apple.point;
                apple.generateApple(matrix);
                size++;
                System.out.println(score); // на время
            }
            if(apple.S == true) {
                if ((Ar[0][0] == apple.S2.x) && (Ar[0][1] == apple.S2.y) || (Ar[0][0] == apple.S2.x2) && (Ar[0][1] == apple.S2.y2) || (Ar[0][0] == apple.S2.x3) && (Ar[0][1] == apple.S2.y3)) {
                    score += apple.S2.point;
                    size++;
                    apple.S = false;
                    System.out.println(score); // на время
                }
            }


        } else {
            for (int i = size - 1; i > 0; i--) {
                t = Ar[i - 1][0];
                Ar[i][0] = t;
                t = Ar[i - 1][1];
                Ar[i][1] = t;
            }
            if (++headX == 60) {
                headX = 0;
            }
            t = headX;
            Ar[0][1] = t;

            for (int i = 1; i < size; i++) {
                if ((Ar[0][0] == Ar[i][0]) && (Ar[0][1] == Ar[i][1]))
                    isAlive = false;
            }

            for (int i = 0; i < size; i++) {
                matrix[Ar[i][0]][Ar[i][1]] = 1;
            }
            apple.paintApple(matrix);
            if ((Ar[0][0] == apple.x) && (Ar[0][1] == apple.y)) {
                score += Apple.point;
                apple.generateApple(matrix);
                size++;
                System.out.println(score); // на время
            }
            if(apple.S == true) {
                if ((Ar[0][0] == apple.S2.x) && (Ar[0][1] == apple.S2.y) || (Ar[0][0] == apple.S2.x2) && (Ar[0][1] == apple.S2.y2) || (Ar[0][0] == apple.S2.x3) && (Ar[0][1] == apple.S2.y3)) {
                    score += apple.S2.point;
                    size++;
                    apple.S = false;
                    System.out.println(score); // на время
                }
            }


        }
    }

    public static void gouon(int[][] matrix, int headX, int headY, int size, int[][] Ar) {
        for (int i = 0; i < size; i++, headX++) {
            matrix[headX][headY] = 1;
            Ar[i][0] = headX;
            Ar[i][1] = headY;
        }
    }


}
