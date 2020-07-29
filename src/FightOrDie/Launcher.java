package FightOrDie;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher
    {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static void main(String[] args)
        {
        Game game = new Game("Fight or Die", (int) screenSize.getWidth(), (int) screenSize.getHeight());
		//Game game = new Game("Fight or Die", 1000, 600);
        game.start();
        }
    }