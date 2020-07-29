package FightOrDie.entities.statics;

import java.awt.Graphics;

import FightOrDie.Handler;
import FightOrDie.graphics.Assets;
import FightOrDie.tiles.Tile;
import java.awt.Color;

public class Tree extends StaticEntity
    {
	public Tree(Handler handler, float x, float y)
        {
        super(handler, x, y, (int)(Tile.TILEWIDTH * 1.5), Tile.TILEHEIGHT * 2);	
        bounds.x = 32;
        bounds.y = 106;
        bounds.width = 23;
        bounds.height = 20;
        }

	@Override
	public void update()
        {
		
        }

	@Override
	public void render(Graphics g)
        {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
        //           (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        }

	@Override
	public void die()
        {
		//handler.getWorld().getItemManager().addItem(Item.rock.createNew((int)x, (int)y));
        }
    }