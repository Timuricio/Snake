/**
 * Created by Иван on 02.07.2016.
 */
public class Rabbit extends Apple {
    public static final int Rpoint = 2000;
    public int go = 0;

    Rabbit(int[][] matrix) {
        x = random.nextInt(60);
        y = random.nextInt(60);

        while (matrix[x][y] == 1) {
            x = random.nextInt(60);
            y = random.nextInt(60);
        }
        paintApple(matrix);

    }

    @Override
    public void paintApple(int[][] matrix) {
        int x2 = x, y2 = y;
        if (++go == 1) {

            do {
                x = x2;
                y = y2;
                switch (random.nextInt(3)) {
                    case 1:
                        switch (random.nextInt(3) + 1) {
                            case 1:
                                if(++x == 61) x = 0 ;
                                break;
                            case 2:
                                if(--x == -1) x = 60;
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        switch (random.nextInt(3) + 1) {
                            case 1:
                                if(++y == 61) y = 0;
                                break;
                            case 2:
                                if(--y == -1) y = 60;
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            } while (matrix[y][x] == 1);
            go = 0;
        }
        matrix[y][x] = 1;
    }
}
