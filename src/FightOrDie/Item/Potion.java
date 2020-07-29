package FightOrDie.Item;

import FightOrDie.Handler;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Potion extends Item
    {
	public Potion(BufferedImage texture, String name, int id)
        {
		super(texture, name, id);
		this.activable = true;
        }
	
	@Override
	public Item createNew(int count)
        {
		Item i = new Potion(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
        }
	
	@Override
	public Item createNew(int x, int y)
        {
		Item i = new Potion(texture, name, id);
		i.setPosition(x + 20, y + 50);
		return i;
        }

	@Override
	public void actionKey(Handler handler)
        {
		//for (Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems())
		for(Iterator<Item> iterator = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().iterator(); iterator.hasNext(); )
			{
			Item i = iterator.next();
			if (i instanceof Potion)
				{
				handler.getWorld().getEntityManager().getPlayer().setHealth(20);
				handler.getGame().nullEnemyDamage();
				if (((Potion) i).count == 1)
					{
					iterator.remove();
					//handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(i);
					}
				else
					{
					i.setCount(i.getCount() - 1);
					}
				}
			}
        }
    }
/*
for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext();) {
    Integer integer = iterator.next();
    if(integer == 2) {
        iterator.remove();
    }
}
*/