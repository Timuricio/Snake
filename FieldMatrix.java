/**
 * Created by Timur on 01.07.2016.
 */
public class FieldMatrix
{
    private final int x,y;
    private int [][] matrix;
    private Pixel[][] field;

    public FieldMatrix(int x, int y)
    {
        this.x = x;
        this.y = y;
        matrix = new int[x][y];
        field = new Pixel[x][y];

    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
    }

    public Pixel[][] getField()
    {
        return field;
    }

    public void setField(Pixel[][] field)
    {
        this.field = field;
    }
}
