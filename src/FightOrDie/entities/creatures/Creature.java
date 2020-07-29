package FightOrDie.entities.creatures;

import FightOrDie.entities.*;
import FightOrDie.tiles.Tile;
import FightOrDie.Handler;

public abstract class Creature extends Entity
    {
    public int DEFAULT_HEALTH = 20;
    public int health;
    public static final float DEFAULT_SPEED = 3;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFALUT_CREATE_HEIGHT  = 64;
    
    protected float speed;
    protected float xMove, yMove;
    
    public Creature(Handler handler, float x, float y, int width, int height)
        {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        }

    public void move()
        {
    	if(!checkEntityCollision(xMove, 0f))
            {
            checkSlowX();
    		moveX();
            }
    	if(!checkEntityCollision(0f, yMove))
            {
            checkSlowY();
    		moveY();
            }
        }
    
    /* COLLISION DETECTION */

    public void moveX()
        {
    	if (xMove > 0)
            { // moving right
    		int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
    		
    		if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
    			!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                {
    			x += xMove;
                }
            else
                {
    			x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                }
        	}
        
    	else if (xMove < 0)
            {// moving left
    		int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
    		
    		if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
    			!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                {
    			x += xMove;
                }
            else
                {
    			x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                }
            }
        }
    
    public void moveY()
        {
    	if (yMove < 0)
            { // up
    		int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
    		
    		if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
    		   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
                {
    			y += yMove;
                }
            else
                {
    			y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                }
            }
        
    	else if (yMove > 0)
            { // down
    		int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
    		
    		if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
    			!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
                {
    			y += yMove;
                }
            else 
                {
    			y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                }
            }
        }
    
    public void checkSlowX()
        {
        if (xMove > 0)
            {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            
            if (slowingTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
    			slowingTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                {
                x -= xMove / 2;
                }
            }
        else if (xMove < 0)
            {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            
            if (slowingTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
    			slowingTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                {
                x -= xMove / 2;
                }
            }
        }
    
    public void checkSlowY()
        {
        if (yMove < 0)
            {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(slowingTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
    		   slowingTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
                {
                y -= yMove / 2.5;
                }
            }
        
        else if (yMove > 0)
            {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(slowingTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
    		   slowingTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
                {
                y -= yMove / 2.5;
                }
            }
        }
    
    protected boolean collisionWithTile (int x, int y)
        {
    	return handler.getWorld().getTile(x, y).isSolid();
        }
    
    protected boolean slowingTile (int x, int y)
        {
        return handler.getWorld().getTile(x, y).isSlowing();
        }
    
    /* GETTERS AND SETTERS */
    
    public float getxMove()
        {
        return xMove;
        }

    public void setxMove(float xMove)
        {
        this.xMove = xMove;
        }

    public float getyMove()
        {
        return yMove;
        }

    public void setyMove(float yMove)
        {
        this.yMove = yMove;
        }

    public int getHealth()
        {
        return health;
        }

    public void setHealth(int health)
        {
        this.health = health;
        }
    
    public void damageHealth(int damage)
    	{
    	setHealth(handler.getWorld().getEntityManager().getPlayer().getHealth() - damage);
    	}

    public float getSpeed()
        {
        return speed;
        }

    public void setSpeed(float speed)
        {
        this.speed = speed;
        }
    }