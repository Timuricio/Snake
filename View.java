import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Created by Timur on 01.07.2016.
 */
public class View
{
    private static FieldMatrix fieldMatrix = new FieldMatrix(60, 60);
    private static Snake snake = new Snake(fieldMatrix.getMatrix(), 30);

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        View view = new View();
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/score.txt"));
        List<Player> playerList = new ArrayList<>();

        playerList = view.addPlayers(inputStream);

        Player currentPlayer;
        JFrame frame = new JFrame("Snake");
        JFrame scores = new JFrame("Scores");

        view.init(frame);

        String name = JOptionPane.showInputDialog(frame, "Введи свое имя:", "Эй, ты!", JOptionPane.QUESTION_MESSAGE);
        currentPlayer = new Player(name);

        while (true)
        {
            snake.move(fieldMatrix.getMatrix());
            view.repaint(frame.getContentPane(), fieldMatrix.getMatrix());
            fieldMatrix.setMatrix(new int[60][60]);
            view.sleep();

            if (!snake.isAlive)
                break;
        }

        currentPlayer.setScore(snake.score);

        playerList = view.checkTheHeroes(playerList, currentPlayer);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/score.txt"));
        view.sendScores(playerList,out);
        view.initScores(scores,playerList);

        System.out.println("Game Over, MotherFucker!!!");
    }

    private void sendScores(List<Player> players, ObjectOutputStream out) throws IOException
    {
        for (Player player : players)
        {
            out.writeObject(player);
        }
        out.close();
    }

    private List<Player> checkTheHeroes(List<Player> playerList, Player player)
    {
        playerList.add(player);
        playerList.sort(new Comparator<Player>()
        {
            @Override
            public int compare(Player o1, Player o2)
            {
                return Integer.compare(o1.getScore(),o2.getScore());
            }
        });

        playerList.remove(10);
        return playerList;
    }


    private List<Player> addPlayers(ObjectInputStream inputStream) throws IOException, ClassNotFoundException
    {
        List<Player> list = new ArrayList<>();
        Player player;

        for (int i = 0; i < 10; i++)
        {
            player = (Player) inputStream.readObject();
            list.add(player);
        }

        inputStream.close();
        return list;
    }

    private void sleep()
    {
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void init(JFrame frame)
    {
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(null);

        paint(frame.getContentPane(), fieldMatrix.getMatrix());


        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.Ar[0][0] - snake.Ar[1][0] != 1)
                    snake.setDirection(Direction.LEFT);
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.Ar[0][0] - snake.Ar[1][0] != -1)
                    snake.setDirection(Direction.RIGHT);
                    //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (e.getKeyCode() == KeyEvent.VK_UP && snake.Ar[0][1] - snake.Ar[1][1] != 1)
                    snake.setDirection(Direction.UP);
                    //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.Ar[0][1] - snake.Ar[1][1] != -1)
                    snake.setDirection(Direction.DOWN);
                else if (e.getKeyChar() == 'q')
                    System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });

    }

    private void initScores(JFrame frame, List<Player> list)
    {
        frame.setPreferredSize(new Dimension(200, 300));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.setLocationByPlatform(true);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(2,2,5,5);
        c.weighty = 1;
        c.weightx= 1;
        c.gridx = 0;

        for (int i = 0; i < list.size(); i++)
        {
            JLabel label = new JLabel();
            label.setText(String.format("%s - %d", list.get(i).getName(), list.get(i).getScore()));
            c.gridy = i;
            frame.getContentPane().add(label,c);
        }


        frame.pack();
        frame.setVisible(true);
    }

    private void paint(Container container, int[][] matrix)
    {
        for (int x = 0; x < 60; x++)
        {
            for (int y = 0; y < 60; y++)
            {
                if (matrix[x][y] == 1)
                {
                    fieldMatrix.getField()[x][y] = new Pixel(Color.BLACK);

                } else
                {
                    fieldMatrix.getField()[x][y] = new Pixel(Color.decode("#D0D8F6"));
                }

                fieldMatrix.getField()[x][y].setBounds(0, 0, x * Pixel.W + 10, y * Pixel.H + 10);
                container.add(fieldMatrix.getField()[x][y]);
            }
        }
    }

    private void repaint(Container container, int[][] matrix)
    {
        for (int x = 0; x < 60; x++)
        {
            for (int y = 0; y < 60; y++)
            {
                if (matrix[x][y] == 1)
                {
                    fieldMatrix.getField()[x][y].setBackground(Color.BLACK);
                } else
                {
                    fieldMatrix.getField()[x][y].setBackground(Color.decode("#D0D8F6"));
                }

                container.repaint();
            }
        }
    }
}
