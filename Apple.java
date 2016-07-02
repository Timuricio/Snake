import java.util.Random;

/**
 * Created by Timur on 01.07.2016.
 */
public class Apple {
    public static int maxAppleCount = 3;
    public static int rabbitCounter = 20;
    public static final int point = 200;
    public int x, y;
    public boolean S = false;
    public boolean R = false;
    private int count = 0;
    private int Rcount = 0;
    protected Random random = new Random();
    SuperApple S2;
    Rabbit R1;

    public Apple() {

        x = random.nextInt(59);
        y = random.nextInt(59);

        while (x != 30 && x != 31 && x != 32 && y != 30) {
            x = random.nextInt(59);
            y = random.nextInt(59);
        }
        count++;

    }

    public void generateApple(int[][] matrix) {
        x = random.nextInt(59);
        y = random.nextInt(59);

        while (matrix[x][y] == 1) {
            x = random.nextInt(59);
            y = random.nextInt(59);
        }
        if (++count == maxAppleCount) {
            S = true;
            count = 0;
            S2 = new SuperApple(matrix);
        }
        if (++Rcount == rabbitCounter) {
            R = true;
            Rcount = 0;
            R1 = new Rabbit(matrix);
        }

    }

    public void paintApple(int[][] matrix) {
        matrix[x][y] = 1;
        if(S) {
            if(S2.point > 200) {
                S2.point -= 10;
            }
            S2.paintApple(matrix);
        }
        if(R) {
            S2.paintApple(matrix);
        }
    }
}
