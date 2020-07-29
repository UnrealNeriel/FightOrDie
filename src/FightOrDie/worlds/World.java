package FightOrDie.worlds;

import FightOrDie.Handler;
import FightOrDie.Item.ItemManager;
import FightOrDie.entities.EntityManager;
import FightOrDie.entities.creatures.Skeleton;
import FightOrDie.entities.creatures.Player;
import FightOrDie.entities.creatures.Zombie;
import FightOrDie.entities.statics.Chest;
import FightOrDie.entities.statics.Tree;
import FightOrDie.tiles.Tile;
import FightOrDie.utils.Utils;
import java.awt.Graphics;

public class World
    {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int [][]tiles;
    private int id;
    private int potions = 0;
    private int keys = 0;
    
    private int spawnXskeleton, spawnYskeleton, skeletonCount;
    private int spawnXzombie, spawnYzombie, zombieCount;
    private int spawnXstatic, spawnYstatic, staticCount;
    /* ENTITIES */
    private EntityManager entityManager;
    private ItemManager itemManager;
    
    public World(Handler handler, String path)
        {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        
        loadWorld(path);
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        }
    
    public void update()
        {
    	itemManager.update();
        entityManager.update();
        }

    public void render (Graphics g)
        {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        
        for (int y = yStart; y < yEnd; ++y)
            {
            for (int x = xStart; x < xEnd; ++x)
                {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                }
            }
        
        itemManager.render(g);
        /* ENTITIES */
        entityManager.render(g);
        }
    
    public Tile getTile(int x, int y)
        {
    	if (x < 0 || y < 0 || x >= width || y >= height)
            {
    		return Tile.grassTile;
            }
    	
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            {
            return Tile.dirtTile;
            }
        return t;
        }
    
    private void loadWorld(String path)
        {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        spawnXskeleton = Utils.parseInt(tokens[4]);
        spawnYskeleton = Utils.parseInt(tokens[5]);
        spawnXzombie = Utils.parseInt(tokens[6]);
        spawnYzombie = Utils.parseInt(tokens[7]);
        
        tiles = new int[width][height];
        
        for (int y = 0; y < height; ++y)
            {
            for (int x = 0; x < width; ++x)
                {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                }
            }
        int jump = 4;
        
        staticCount = Utils.parseInt(tokens[height * width + jump]);
        ++jump;
        for (int i = 0; i < staticCount; ++i, jump += 3)
        	{
        	spawnXstatic = Utils.parseInt(tokens[height * width + jump]);
        	spawnYstatic = Utils.parseInt(tokens[height * width + jump + 1]);
        	id = Utils.parseInt(tokens[height * width + jump + 2]);
        	if (id == 100)
	        	{
	        	entityManager.addEntity(new Tree(handler, spawnXstatic, spawnYstatic));
	        	}
        	else if (id == 101)
        		{
        		entityManager.addEntity(new Chest(handler, spawnXstatic, spawnYstatic));
        		}
        	}
        
        skeletonCount = Utils.parseInt(tokens[height * width + jump]);
        ++jump;
        for (int i = 0; i < skeletonCount; ++i, jump += 2)
        	{
        	spawnXskeleton = Utils.parseInt(tokens[height * width + jump]);
        	spawnYskeleton = Utils.parseInt(tokens[height * width + jump + 1]);
        	entityManager.addEntity(new Skeleton(handler, spawnXskeleton, spawnYskeleton));
        	}
        
        zombieCount = Utils.parseInt(tokens[height * width + jump]);
        ++jump;
        for (int i = 0; i < zombieCount; ++i, jump += 2)
        	{
        	spawnXzombie = Utils.parseInt(tokens[height * width + jump]);
        	spawnYzombie = Utils.parseInt(tokens[height * width + jump + 1]);
        	entityManager.addEntity(new Zombie(handler, spawnXzombie, spawnYzombie));
        	}
        
        this.potions = Utils.parseInt(tokens[height * width + jump]);
        ++jump;
        this.keys = Utils.parseInt(tokens[height * width + jump]);
        }
    
    public int getWidth()
        {
    	return width;
        }
    
    public int getHeight()
        {
    	return height;
        }

	public ItemManager getItemManager()
        {
		return itemManager;
        }

	public void setItemManager(ItemManager itemManager)
        {
		this.itemManager = itemManager;
        }

	public Handler getHandler()
        {
		return handler;
        }

	public void setHandler(Handler handler)
        {
		this.handler = handler;
        }

	public int getSpawnYskeleton()
        {
		return spawnYskeleton;
        }

	public void setSpawnYskeleton(int spawnYskeleton)
        {
		this.spawnYskeleton = spawnYskeleton;
        }

	public int getSpawnXskeleton()
        {
		return spawnXskeleton;
        }

	public void setSpawnXskeleton(int spawnXskeleton)
        {
		this.spawnXskeleton = spawnXskeleton;
        }
	
	public int getPotions()
        {
		return this.potions;
        }
	
	public int getKeys()
        {
		return this.keys;
        }
	
	public int[][] getTiles()
        {
		return tiles;
        }
    
    public EntityManager getEntityManager()
        {
		return entityManager;
        }
    }