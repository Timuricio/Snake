
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Timur on 02.07.2016.
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("score.txt"));
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Nagibator8000");
        player1.setScore(1000000);
        Player player2 = new Player("MiLaShKa");
        player2.setScore(9000);
        Player player3 = new Player("Sir Lonsevrot");
        player3.setScore(8000);
        Player player4 = new Player("Bloody Eddy");
        player4.setScore(7000);
        Player player5 = new Player("John");
        player5.setScore(6000);
        Player player6 = new Player("Kavai ^_^");
        player6.setScore(5000);
        Player player7 = new Player("Plague1619");
        player7.setScore(4000);
        Player player8 = new Player("Love69");
        player8.setScore(3000);
        Player player9 = new Player("Fedja30");
        player9.setScore(2000);
        Player player10 = new Player("Novichok");
        player10.setScore(1000);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);

        for (Player player : players)
        {
            out.writeObject(player);
        }
        out.close();
    }
}
