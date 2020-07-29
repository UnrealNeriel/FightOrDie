 package FightOrDie.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import FightOrDie.Handler;
import FightOrDie.Item.Item;
import FightOrDie.Item.Key;
import FightOrDie.states.FightState;
import FightOrDie.states.State;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Entity
    {
    private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean active = true;
    protected boolean fightable = false;
    protected boolean isPlayer = false;
    protected boolean activable = false;

	public Entity(Handler handler, float x, float y, int width, int height)
        {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        bounds = new Rectangle(0, 0, width, height);
        }
	
	//method for after - death actions, dropping an item etc.
	public abstract void die();

	public abstract void update();
    
    public abstract void render(Graphics g);
    
    public boolean checkEntityCollision(float xOffset, float yOffset)
        {
    	for(Entity e: handler.getWorld().getEntityManager().getEntities())
            {
    		if(e.equals(this))
                {
                continue;
                }

            if (e.activable && e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && handler.getKeyManager().keyJustPressed(KeyEvent.VK_O))
                {
    			for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems())
                    {
    				if (i instanceof Key)
                        {
    					e.die();
    					e.setActive(false);
    					i.setCount(i.getCount() - 1);
    					if (i.getCount() == 0)
    						{
    						handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(i);
    						}
    					return true;
                        }
                    }
                }
    		
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.fightable && isPlayer)
	    		{
    			State.setState(new FightState(handler, handler.getWorld().getEntityManager().getPlayer(), e));
    			LOGGER.setLevel(Level.INFO);
                LOGGER.info("Fight started with " + e.toString());
    			return true;
	    		}
            
    		if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
    			{
    			return true;
    			}
            }
        
    	return false;
        }
    
	public Rectangle getCollisionBounds(float xOffset, float yOffset)
        {
    	return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
        }
    
    public void setX(float x)
        {
        this.x = x;
        }

    public void setY(float y)
        {
        this.y = y;
        }

    public void setWidth(int width)
        {
        this.width = width;
        }

    public void setHeight(int height)
        {
        this.height = height;
        }

    public float getX()
        {
        return x;
        }

    public float getY()
        {
        return y;
        }

    public int getWidth()
        {
        return width;
        }

    public int getHeight()
        {
        return height;
        }
    public boolean isActive() {
		return active;	
		}

	public void setActive(boolean active)
        {
		this.active = active;
		}

	public boolean isActivable()
        {
		return activable;
        }
	
	public boolean isFightable()
        {
		return fightable;
        }
	
	public boolean isPlayer()
        {
		return isPlayer;
        }
    }