package FightOrDie;

import FightOrDie.states.State;
import FightOrDie.states.MenuState;
import FightOrDie.input.KeyManager;
import FightOrDie.input.MouseManager;
import FightOrDie.Item.Item;
import FightOrDie.Item.Key;
import FightOrDie.Item.Potion;
import FightOrDie.display.Display;
import FightOrDie.entities.Entity;
import FightOrDie.entities.statics.StaticEntity;
import FightOrDie.graphics.Assets;
import FightOrDie.graphics.GameCamera;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game implements Runnable
    {
    private Display display;
    public String title;
    private int width, height;
    
    private boolean draw_player = false;
    private int drawXplayer, drawYplayer, drawWidthPlayer, drawHeightPlayer;
    
    private boolean draw_enemy = false;
    private int drawXenemy , drawYenemy , drawWidthEnemy , drawHeightEnemy;
    
    private boolean fighting = false;
    private int playerDamage = 0, enemyDamage = 0;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    static final int GAME_WIDTH = 1000, GAME_HEIGHT = 600;
    
    private BufferedWriter out;

    SimpleDateFormat dateFormat;
    Date date;
    String frmtdDate;
    
    /* STATES */
    public State gameState;
    public State menuState;
    public State fightState;
    
    /* INPUT */
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    /* CAMERA */
    private GameCamera gameCamera;
    
    /* HANDLER */
    private Handler handler = new Handler(this);
    
    public Game(String title, int width, int height)//constructor
        {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        }
    
    private void init()
        {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        
        menuState = new MenuState(handler);
        State.setState(menuState);
        }
    
    private void update()
        {
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F6))
            {
            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
            date = new Date();
            frmtdDate = dateFormat.format(date);
            frmtdDate += ".wld";
		
            try  
				{
						FileWriter fstream = new FileWriter("C:/Users/Neriel/eclipse/FightOrDie/res/worlds/" + frmtdDate, true); //true tells to append data.
                out = new BufferedWriter(fstream);
                out.write(Integer.toString(handler.getWorld().getWidth()) + " ");
                out.write(Integer.toString(handler.getWorld().getHeight())); out.newLine();
                out.write(Integer.toString((int)handler.getWorld().getEntityManager().getPlayer().getX()) + " ");
                out.write(Integer.toString((int)handler.getWorld().getEntityManager().getPlayer().getY()));
                out.newLine();
                for (int i = 0; i < handler.getWorld().getHeight(); ++i)
                    {
					for (int j = 0; j < handler.getWorld().getWidth(); ++j)
						{
						out.write(handler.getWorld().getTiles()[j][i] + " ");
						}
					out.newLine();
					}
				int tmp = 0;
				for (Entity e : handler.getWorld().getEntityManager().getEntities())
                    {
					if (e instanceof StaticEntity)
						{
						++tmp;
						}
					}
				out.write(Integer.toString(tmp)); out.newLine();
				for (Entity e : handler.getWorld().getEntityManager().getEntities())
                    {
					if (e instanceof StaticEntity)
						{
						out.write(Integer.toString((int)e.getX()) + " ");
						out.write(Integer.toString((int)e.getY()) + " ");
						String id = "";
						if (e.isActivable())
                            {
                            id = "101";
                            }
						else
                            {
                            id = "100";
                            }
						out.write(id);
						out.newLine();
						}
					}
				tmp = 0;
				for (Entity e : handler.getWorld().getEntityManager().getEntities())
					{
					if (e.isFightable() && !e.isPlayer() && e.isActive())
					{
						++tmp;
						}
					}
				out.write(Integer.toString(tmp)); out.newLine();
				for (Entity e : handler.getWorld().getEntityManager().getEntities())
					{
					if (e.isFightable() && !e.isPlayer() && e.isActive())
						{
						out.write(Integer.toString((int)e.getX()) + " ");
						out.write(Integer.toString((int)e.getY()));
						out.newLine();
						}
					}
				tmp = 0;
				for(Item potion : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems())
                    {
					if (potion instanceof Potion)
						{
						++tmp;
						}
					}
				out.write(Integer.toString(tmp));
				tmp = 0;
				out.newLine();
				for(Item key : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems())
                    {
					if (key instanceof Key)
						{
						++tmp;
						}
					}
				out.write(Integer.toString(tmp));
				}
			
            catch (IOException e)
				{
                System.err.println("Error: " + e.getMessage());
				}
            finally
				{
                if(out != null)
                    {
					try
                        {
						out.close();
						try
                            {
                            Thread.sleep(1);
                            }
                        catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						}
					catch (IOException e)
						{
						e.printStackTrace();
						}
					}
				}
			}    	
        keyManager.update();
        
        if (State.getState() != null)
            {
            State.getState().update();
            }
        }
    
    private void render()
        {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) //if no buffer strategy
            {
            display.getCanvas().createBufferStrategy(3);
            return;
            }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        
        // start draw
        
        if (State.getState() != null)
            {
            State.getState().render(g);
            }
        
        // START OF FIGHT DRAW
        if (draw_player)
			{
			g.setColor(Color.green);
			g.fillRect (drawXplayer - 10, drawYplayer - 10, drawWidthPlayer + 20, 10); //up
			g.fillRect (drawXplayer - 10, drawYplayer + drawHeightPlayer, drawWidthPlayer + 20, 10); //bottom
			g.fillRect (drawXplayer - 10, drawYplayer, 10, drawHeightPlayer); //left
			g.fillRect (drawXplayer + drawWidthPlayer, drawYplayer, 10, drawHeightPlayer); //right
			}
        
        if (draw_enemy)
			{
			g.setColor(Color.red);
			g.fillRect (drawXenemy - 10, drawYenemy  - 10, drawWidthEnemy + 20, 10); //up
			g.fillRect (drawXenemy  - 10, drawYenemy  + drawHeightEnemy, drawWidthEnemy + 20, 10); //bottom
			g.fillRect (drawXenemy  - 10, drawYenemy, 10, drawHeightEnemy); //left
			g.fillRect (drawXenemy  + drawWidthEnemy, drawYenemy, 10, drawHeightEnemy); //right
			}
        
        if (fighting)
			{
			g.setColor(Color.red);
			g.fillRect(50, 100, 5, 400);	g.fillRect(900, 100, 5, 400);
			g.fillRect(55, 100, 40, 5); 	g.fillRect(905, 100, 40, 5);
			g.fillRect(95, 100, 5, 400); 	g.fillRect(945, 100, 5, 400);
			g.fillRect(55, 495, 40, 5); 	g.fillRect(905, 495, 40, 5);
			g.setColor(Color.ORANGE);
			g.fillRect(55, 105 + 20 * enemyDamage, 40, 390 - 20 * enemyDamage); 	g.fillRect(905, 105 + 20 * playerDamage, 40, 390 - 20 * playerDamage);
			}
        //END OF FIGHT DRAW
        /*
         * drawing rectangles while fighting to see actual health either player and enemy
         */
        
        // end draw
        bs.show();
        g.dispose();
        }        
    
    /*
     * limits framerate to the fps variable
     */
    public void run()
        {
        init();

        int fps = 60; //update and render called per second
        double timePerUpdate = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int updates = 0;
        
        while (running) //game loop
            {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            timer += now - lastTime;
            lastTime = now;
            
            if (delta >= 1)
                {
                update();
                render();
                ++updates;
                --delta;
                }
            if (timer >= 1000000000)
                {
                //System.out.println("Updates and Frames " + updates);
                updates = 0;
                timer = 0;
                }
            }
        stop();
        }
    /*
     * starting Thread-1, GUI of the game
     */
    public synchronized void start()
        {
        if (running)
            {
            return; //avoid mess
            }
        running = true;
        thread = new Thread(this);
        thread.start();
        }
    
    /*
     * stops the game
     */
    public synchronized void stop()
        {
        if (!running) // avoid mess
            {
            return;
            }
        running = false;
        try
            {
            thread.join();
            }
        catch (InterruptedException e)
            {
            e.printStackTrace();
            }
        }
    
    /*GETTERS*/
    
    public KeyManager getKeyManager()
		{
		return keyManager;
		}
    
    public MouseManager getMouseManager()
		{
    	return mouseManager;
		}
    	
    public GameCamera getGameCamera()
		{
		return gameCamera;
		}

    public int getWidth()
		{
		return width;
		}

    public int getHeight()
		{
		return height;
		}

    public Handler getHandler()
		{
    	return handler;
		}
    
    public int getDrawXPlayer()
		{
    	return drawXplayer;
		}
    
    /* SETTERS */
    
    public void setDrawPlayer(boolean draw)
		{
		this.draw_player = draw;
		}

	public void setDrawHeightPlayer(int drawHeight)
		{
		this.drawHeightPlayer = drawHeight;
		}

	public void setDrawYplayer(int drawY)
		{
		this.drawYplayer = drawY;
		}

	public void setDrawXplayer(int drawX)
		{
		this.drawXplayer = drawX;
		}

	public void setDrawWidthPlayer(int drawWidth)
		{
		this.drawWidthPlayer = drawWidth;
		}
    
	public void setDrawEnemy(boolean draw)
		{
		this.draw_enemy = draw;
		}

	public void setDrawHeightEnemy(int drawHeight)
		{
		this.drawHeightEnemy = drawHeight;
		}

	public void setDrawYenemy(int drawY)
		{
		this.drawYenemy = drawY;
		}

	public void setDrawXenemy(int drawX)
		{
		this.drawXenemy = drawX;
		}

	public void setDrawWidthEnemy(int drawWidth)
		{
		this.drawWidthEnemy = drawWidth;
		}
	
	public void setFighting(boolean f)
		{
		this.fighting = f;
		}
	
	public void addEnemyDamage(int enemyDamage)
		{
		this.enemyDamage += enemyDamage;
		if (this.enemyDamage > 20) this.enemyDamage = 20;
		}
	
	public void addPlayerDamage(int playerDamage)
		{
		this.playerDamage += playerDamage;
		if (this.playerDamage > 20) this.playerDamage = 20;
		}
	
	public void nullPlayerDamage()
		{
		this.playerDamage = 0;
		}
	
	public void nullEnemyDamage()
		{
		this.enemyDamage = 0;
		}

	public static int getGameWidth()
		{
		return GAME_WIDTH;
		}

	public static int getGameHeight()
		{
		return GAME_HEIGHT;
		}
	
	public int getEnemyDamage()
		{
		return enemyDamage;
		}
	
	public void setGameState(State state)
		{
		this.gameState = state;
		}
	
	public void setMenuState(State state)
		{
		this.menuState = state;
		}
	
	public State getGameState()
		{
		return this.gameState;
		}
	
	public State getMenuState()
		{
		return this.menuState;
		}
    }