import java.util.Random;

/**
 * Created by Timur on 01.07.2016.
 */
public class Apple
        {
            public static final int point = 200;
            public int x, y;
            private Random random = new Random();

    public Apple()
    {
        x = random.nextInt(59);
        y = random.nextInt(59);

        while (x != 30 && x != 31 && x != 32 && y != 30)
        {
            x = random.nextInt(59);
            y = random.nextInt(59);
        }
    }

    public void generateApple(int[][] matrix)
    {
        x = random.nextInt(59);
        y = random.nextInt(59);

        while (matrix[x][y] == 1)
        {
            x = random.nextInt(59);
            y = random.nextInt(59);
        }

    }

    public void paintApple(int[][] matrix)
    {
        matrix[x][y] = 1;
    }
}
