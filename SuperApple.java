/**
 * Created by Иван on 02.07.2016.
 */
public class SuperApple extends Apple {

    public int Xscore = 7;
    public int point = Apple.point*Xscore;
    public int x2, y2, x3, y3;

    public SuperApple(int[][] matrix) {
        x = super.random.nextInt(58) + 1;
        y = super.random.nextInt(58) + 1;
        x2 = x + 1;
        y2 = y;
        x3 = x;
        y3 = y + 1;

        while ( ( matrix[y][x] == 1 ) || (matrix[y2][x2] == 1) || ( matrix[y3][x3] == 1 ) || (y == 59) || (x == 59)) {
            x = random.nextInt(58) + 1;
            y = random.nextInt(58) + 1;
        }
        paintApple(matrix);
    }

    @Override
    public void paintApple(int[][] matrix) {

        {
            matrix[y][x] = 1;
            matrix[y2][x2] = 1;
            matrix[y3][x3] = 1;
        }
    }
}
