package FightOrDie.Item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import FightOrDie.Handler;

public abstract class Item
    {
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;
	public static Item[] items = new Item[256];
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	protected boolean activable = false;
	
	public Item(BufferedImage texture, String name, int id)
        {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		items[id] = this;
        }
	
	public void update()
        {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds))
            {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
            }
        }
	
	public void render(Graphics g)
        {
		if(handler != null)
            {
            render(g,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()));
            }
        }
	
	public void render(Graphics g, int x, int y)
        {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
        }
	
	public void setPosition(int x, int y)
        {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
        }
	
	public abstract Item createNew(int x, int y);
	
	public abstract Item createNew(int count);
	
	public abstract void actionKey(Handler handler);
	
	//GETTERS AND SETTERS
	public Handler getHandler()
        {
		return handler;
        }
	
	public void setHandler(Handler handler)
        {
		this.handler = handler;
        }
	
	public BufferedImage getTexture()
        {
		return texture;
        }
	
	public void setTexture(BufferedImage texture)
        {
		this.texture = texture;
        }
	
	public String getName()
        {
		return name;
        }
	
	public void setName(String name)
        {
		this.name = name;
        }
	
	public int getX()
        {
		return x;
        }
	
	public void setX(int x)
        {
		this.x = x;
        }
	
	public int getY()
        {
		return y;
        }
	
    public void setY(int y)
        {
        this.y = y;
        }
	
	public int getCount()
        {
		return count;
        }
	
	public void setCount(int count)
        {
		this.count = count;
        }
	
	public int getId()
        {
		return id;
        }
	
	public boolean isPickedUp()
        {
		return pickedUp;
        }
	
	public void setPickedUp(boolean pickedUp)
        {
		this.pickedUp = pickedUp;
        }
	
	public boolean isActivable()
        {
		return activable;
        }
    }