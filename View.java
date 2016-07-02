import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by Timur on 01.07.2016.
 */
public class View
{
    private static FieldMatrix fieldMatrix = new FieldMatrix(60, 60);
    //private static int[][] matrix = new int[60][60];
    private static Pixel[][] field = new Pixel[60][60];
    private static Snake snake = new Snake(fieldMatrix.getMatrix(), 3);

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("Snake");

        View view = new View();
        view.init(frame);

        for (; ; )
        {
            snake.move(fieldMatrix.getMatrix());
            view.repaint(frame.getContentPane(), fieldMatrix.getMatrix());
            fieldMatrix.setMatrix(new int[60][60]);
            view.sleep();

            if (!snake.isAlive)
                break;
        }

        System.out.println("Game Over, MotherFucker!!!");
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

        paint(frame.getContentPane(), fieldMatrix.getMatrix());

        JLabel score = new JLabel("000000");

        score.setBounds(620,620,80,20);
        frame.getContentPane().add(score);

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


    private void paint(Container container, int[][] matrix)
    {
        for (int x = 0; x < 60; x++)
        {
            for (int y = 0; y < 60; y++)
            {
                if (matrix[x][y] == 1)
                {
                    field[x][y] = new Pixel(Color.BLACK);

                } else
                {
                    field[x][y] = new Pixel(Color.decode("#D0D8F6"));
                }

                field[x][y].setBounds(0, 0, x * Pixel.W, y * Pixel.H + 10);
                container.add(field[x][y]);
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
                    field[x][y].setBackground(Color.BLACK);
                } else
                {
                    field[x][y].setBackground(Color.decode("#D0D8F6"));
                }

                container.repaint();
            }
        }
    }
}
