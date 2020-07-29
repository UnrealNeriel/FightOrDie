package FightOrDie.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
    {
    /* STATIC SHIT */
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0); // 0 id = grass
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    /* CLASS */
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id)
        {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
        }
    
    public void update()
        {
        
        }
    
    public void render(Graphics g, int x, int y)
        {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
        }
    
    public boolean isSolid()
        {
        return false;
        }
    
    public boolean isSlowing()
        {
        return false;
        }
    
    public int getId()
        {
        return id;
        }
    }