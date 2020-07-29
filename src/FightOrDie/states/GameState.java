package FightOrDie.states;

import FightOrDie.Handler;
import FightOrDie.worlds.World;
import java.awt.Graphics;

public class GameState extends State
    {
	String filename;
	private World world;
    
    public GameState(Handler handler, String filename)
        {
        super(handler);
        this.filename = filename;
        world = new World(handler, "res/worlds/" + filename);
        handler.setWorld(world);
        }
    
    @Override
    public void update()
        {
        world.update();
        handler.getGameCamera().move(1, 1);
        }

    @Override
    public void render(Graphics g)
        {
        world.render(g);
        }
    
    public void setFilename(String name)
        {
    	this.filename = name;
        }
    }