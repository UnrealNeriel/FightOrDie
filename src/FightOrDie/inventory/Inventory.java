package FightOrDie.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import FightOrDie.Handler;
import FightOrDie.Item.Item;
import FightOrDie.Item.Key;
import FightOrDie.Item.Potion;
import FightOrDie.graphics.Assets;
import FightOrDie.graphics.Text;
import FightOrDie.states.FightState;
import java.util.logging.Logger;

public class Inventory
    {
	private static final Logger LOGGER = Logger.getLogger(FightState.class.getName());
	
	final Potion potion = new Potion(Assets.potionImg, "health pot", 5);
	final Key key = new Key(Assets.keyImg, "key chest", 9);
	
    private Handler handler;
    private boolean active = false;
    private boolean activated = false;
    private ArrayList<Item> inventoryItems;
	
    private final int invImageWidth = 50, invImageHeight = 50;
	
    private int selectedItem = 0;
	
    public Inventory(Handler handler)
        {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
        }
    
    public void update()
        {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
            {
            if (!activated)
                {
				initializeInventory();
				activated = true;
				}
            active = !active;
            }
        if(!active)
            {
            return;
            }
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			{
			selectedItem--;
			}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			{
			selectedItem++;
			}
		
		if(selectedItem < 0)
			{
			selectedItem = inventoryItems.size() - 1;
			}
		else if(selectedItem >= inventoryItems.size())
			{
			selectedItem = 0;
			}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && !getInventoryItems().isEmpty())
			{
			if(inventoryItems.get(selectedItem) instanceof Potion && handler.getWorld().getEntityManager().getPlayer().getHealth() != 20)
				{
				potion.actionKey(handler);
				}
			}
		

        /*for(Item i : inventoryItems)
            {
            if (i instanceof Potion)
				{
				if (inventoryItems.indexOf(i) != 0)
                    {
                    Collections.reverse(inventoryItems);
                    }
				}
            }*/
		}
	
	public void render(Graphics g)
        {
        if(!active)
            {
			return;
            }
		
        g.drawImage(Assets.inventoryImg, (handler.getGame().getWidth() / 2 - handler.getGame().getWidth() / 8), 0, handler.getGame().getWidth() / 4, handler.getGame().getHeight() / 4, null);
		
        int invX = handler.getGame().getWidth() / 2 - handler.getGame().getWidth() / 8, invY = 0,
        invWidth = handler.getGame().getWidth() / 4,
        invHeight = handler.getGame().getHeight() / 4,
        invListCenterX = invX + invWidth / 4 + 35,
        invListCenterY = invY + invHeight / 2 + 44,
        invListSpacing = 21;
		
        if(inventoryItems.isEmpty())
            {
			return;
            }
            
        for(int i = -5; i < 6; ++i)
            {
			if(selectedItem + i < 0 || selectedItem + i >= inventoryItems.size())
                {
                continue;
                }
			if(i == 0)
                {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
                invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.itemInv);
                }
            else
                {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
                invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.itemInv);
                }
            }
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invX + (invWidth * 3 / 4) + 8 , 21, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invX + (invWidth * 3 / 4) + 33, 128, true, Color.WHITE, Assets.itemInv);
        }
	
	public void addItem(Item item)
        {
        for(Item i : inventoryItems)
            {
			if(i.getId() == item.getId())
                {
                i.setCount(i.getCount() + 1);
                return;
                }
            }
        inventoryItems.add(item);
		LOGGER.info(inventoryItems.toString());
        }
	
	public void removeItem(Item item)
        {
        inventoryItems.remove(item);
        }
	
	//Getters and Setters
	
	public boolean isActive()
        {
		return active;
        }
    
	public ArrayList<Item> getInventoryItems()
        {
		return inventoryItems;
        }
	
	public void initializeInventory()
        {
		for (int i = 0; i < handler.getWorld().getPotions(); ++i)
			{
			addItem(potion);
            }
		
		for (int i = 0; i < handler.getWorld().getKeys(); ++i)
            {
			addItem(key);
            }
        }
    }