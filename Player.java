import java.io.Serializable;

/**
 * Created by Timur on 02.07.2016.
 */
public class Player implements Serializable
{
    private String name;
    private int score;

    public Player(String name)
    {
        this.name = name;
        score = 0;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
