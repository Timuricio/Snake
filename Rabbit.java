/**
 * Created by Иван on 02.07.2016.
 */
public class Rabbit extends Apple {
    public static final int Rpoint = 2000;
    public int go = 0;

    Rabbit(int[][] matrix) {
        x = random.nextInt(59);
        y = random.nextInt(59);

        while (matrix[x][y] == 1) {
            x = random.nextInt(59);
            y = random.nextInt(59);
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
                                if(++x == 60) x = 0 ;
                                break;
                            case 2:
                                if(--x == -1) x = 59;
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        switch (random.nextInt(3) + 1) {
                            case 1:
                                if(++y == 60) y = 0;
                                break;
                            case 2:
                                if(--y == -1) y = 59;
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            } while (matrix[x][y] == 1);
            go = 0;
        }
        matrix[x][y] = 1;
    }
}
