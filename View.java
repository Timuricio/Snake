import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final static String FILE = "src/score.txt";
    private static FieldMatrix fieldMatrix = new FieldMatrix(61, 61);
    private static Snake snake;
    private static boolean replay = false;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        View view = new View();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE));
        List<Player> playerList = new ArrayList<>();

        playerList = view.addPlayers(inputStream);

        Player currentPlayer;
        JFrame frame = new JFrame("Snake");
        JLabel score = new JLabel();
        JLabel timer = new JLabel();
        JLabel playserSnake = new JLabel("#########");
        JFrame scores;

        view.init(frame, score, timer, playserSnake);
        String name = JOptionPane.showInputDialog(frame, "Введи свое имя:", "Эй, ты!", JOptionPane.QUESTION_MESSAGE);

        if (name == null||name.isEmpty())
            name = "NoName";

        playserSnake.setText(name);

        for (; ; ) {
            currentPlayer = new Player(name);
            scores = new JFrame("Scores");
            snake = new Snake(fieldMatrix.getMatrix(), 3);
            while (true) {
                snake.move(fieldMatrix.getMatrix());
                view.repaint(frame.getContentPane(), fieldMatrix.getMatrix());
                fieldMatrix.setMatrix(new int[61][61]);
                score.setText(String.format("%s - %d","Score",snake.score));
                timer.setText(String.format("%s - %d","Time",snake.time));
                view.sleep(100);

                if (!snake.isAlive)
                    break;
            }

            currentPlayer.setScore(snake.score);

            playerList = view.checkTheHeroes(playerList, currentPlayer);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
            view.sendScores(playerList, out);
            view.initScores(scores, playerList);

            System.out.println("Game Over, MotherFucker!!!");
            while (!replay)
                Thread.currentThread().sleep(100);

            replay = false;
        }
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
                return Integer.compare(o2.getScore(),o1.getScore());
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

    private void sleep(int it)
    {
        try
        {
            Thread.sleep(it);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void init(JFrame frame, JLabel score, JLabel timer,JLabel playserSnake)
    {
        frame.setPreferredSize(new Dimension(760, 640));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(null);

        paint(frame.getContentPane(), fieldMatrix.getMatrix());



        playserSnake.setBounds(610,10,90,20);
        frame.getContentPane().add(playserSnake);

        score.setText(String.format("%s - %d", "Score",0));
        score.setBounds(610,25,90,20);
        frame.getContentPane().add(score);

        timer.setText(String.format("%s - %d", "Time",0));
        timer.setBounds(610,40,90,20);
        frame.getContentPane().add(timer);

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
                if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.Ar[0][0] - snake.Ar[1][0] != 1 && snake.Ar[0][0] - snake.Ar[1][0] != -60)
                    snake.setDirection(Direction.LEFT);
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.Ar[0][0] - snake.Ar[1][0] != -1&& snake.Ar[0][0] - snake.Ar[1][0] != 60)
                    snake.setDirection(Direction.RIGHT);
                    //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (e.getKeyCode() == KeyEvent.VK_UP && snake.Ar[0][1] - snake.Ar[1][1] != 1&& snake.Ar[0][1] - snake.Ar[1][1] != -60)
                    snake.setDirection(Direction.UP);
                    //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.Ar[0][1] - snake.Ar[1][1] != -1&& snake.Ar[0][1] - snake.Ar[1][1] != 60)
                    snake.setDirection(Direction.DOWN);
                else if (e.getKeyChar() == 'q') snake.setDirection(Direction.BOT);
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });

    }

    private void initScores(JFrame frame, List<Player> list)
    {
        frame.setPreferredSize(new Dimension(250, 300));
        frame.setResizable(false);
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

        JButton buttonAgain = new JButton("Again");
        c.weighty = 0.1;
        c.weightx= 0.1;
        c.gridx = 0;
        c.gridy = 10;
        frame.getContentPane().add(buttonAgain,c);

        JButton buttonClose = new JButton("Close");
        c.weighty = 0.1;
        c.weightx= 0.1;
        c.gridx = 1;
        c.gridy = 10;
        frame.getContentPane().add(buttonClose,c);


        frame.pack();
        frame.setVisible(true);

        buttonAgain.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                replay = true;
                frame.setVisible(false);
            }
        });

        buttonClose.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    private void paint(Container container, int[][] matrix)
    {
        for (int y = 0; y < 61; y++)
        {
            for (int x = 0; x < 61; x++)
            {
                if (matrix[y][x] == 1)
                {
                    fieldMatrix.getField()[y][x] = new Pixel(Color.BLACK);

                } else
                {
                    fieldMatrix.getField()[y][x] = new Pixel(Color.decode("#D0D8F6"));
                }

                fieldMatrix.getField()[y][x].setBounds(0, 0, x * Pixel.W + 10, y * Pixel.H + 10);
                container.add(fieldMatrix.getField()[y][x]);
            }
        }
    }

    private void repaint(Container container, int[][] matrix)
    {
        for (int y = 0; y < 61; y++)
        {
            for (int x = 0; x < 61; x++)
            {
                if (matrix[y][x] == 1)
                {
                    fieldMatrix.getField()[y][x].setBackground(Color.BLACK);
                } else
                {
                    fieldMatrix.getField()[y][x].setBackground(Color.decode("#D0D8F6"));
                }

                container.repaint();
            }
        }
    }
}
