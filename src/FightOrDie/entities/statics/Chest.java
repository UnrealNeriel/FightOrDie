package FightOrDie.entities.statics;

import java.awt.Graphics;

import FightOrDie.Handler;
import FightOrDie.graphics.Assets;
import FightOrDie.tiles.Tile;
import java.awt.Color;

public class Chest extends StaticEntity
    {
	public Chest(Handler handler, float x, float y)
        {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);	
		bounds.x = 4;
		bounds.y = 20;
		bounds.width = 47;
		bounds.height = 32;
		this.activable = true;
		}
	
	@Override
	public void update()
        {
		
        }

	@Override
	public void render(Graphics g)
        {
        g.drawImage(Assets.chestImg, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            
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