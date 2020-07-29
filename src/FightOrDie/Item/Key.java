package FightOrDie.Item;

import FightOrDie.Handler;
import java.awt.image.BufferedImage;

public class Key extends Item
    {
	public Key(BufferedImage texture, String name, int id)
        {
		super(texture, name, id);
        }

	@Override
	public Item createNew(int count)
        {
		Item i = new Key(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
        }
	
	@Override
	public Item createNew(int x, int y)
        {
		Item i = new Key(texture, name, id);
		i.setPosition(x + 20, y + 50);
		return i;
        }

	@Override
	public void actionKey(Handler handler)
        {

        }
    }