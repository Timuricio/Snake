/**
 * Created by Иван on 02.07.2016.
 */
public class SuperApple extends Apple {

    public int Xscore = 7;
    public int point = Apple.point*Xscore;
    public int x2, y2, x3, y3;

    public SuperApple(int[][] matrix) {
        x = super.random.nextInt(59);
        y = super.random.nextInt(59);
        x2 = x + 1;
        y2 = y;
        x3 = x;
        y3 = y + 1;

        while ( ( matrix[x][y] == 1 ) || (matrix[x2][y2] == 1) || ( matrix[x3][y3] == 1 ) || (x == 59) || (y == 59)) {
            x = random.nextInt(59);
            y = random.nextInt(59);
        }
        paintApple(matrix);
    }

    @Override
    public void paintApple(int[][] matrix) {

        {
            matrix[x][y] = 1;
            matrix[x2][y2] = 1;
            matrix[x3][y3] = 1;
        }
    }
}
