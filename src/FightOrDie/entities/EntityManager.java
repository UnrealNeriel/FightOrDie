package FightOrDie.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import FightOrDie.Handler;
import FightOrDie.entities.creatures.Player;

public class EntityManager
	{
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>()
		{
		public int compare(Entity arg0, Entity arg1)
			{
			if(arg0.getY() + arg0.getHeight() < arg1.getY() + arg1.getHeight())
				{
				return -1;
				}
			return 1;
			}
		};
	
	public EntityManager(Handler handler, Player player)
		{
		this.setHandler(handler);
		this.setPlayer(player);
		setEntities(new ArrayList<Entity>());
		addEntity(player);
		}
	
	public void update()
		{
		Iterator<Entity> it = entities.iterator();
		
		while(it.hasNext())
			{
			Entity e = it.next();
			e.update();
			if(!e.isActive())
				{
				it.remove();
				}
			}
		entities.sort(renderSorter);
		}
	
	public void render(Graphics g)
		{
		for (Entity e : entities)
			{
			e.render(g);
			}
		player.postRender(g);
		}

	public void addEntity(Entity e)
		{
		entities.add(e);
		}
	
	/* GETTERS AND SETTERS */
	
	public Handler getHandler()
		{
		return handler;
		}

	public void setHandler(Handler handler)
		{
		this.handler = handler;
		}

	public Player getPlayer()
		{
		return player;
		}

	public void setPlayer(Player player)
		{
		this.player = player;
		}

	public ArrayList<Entity> getEntities()
		{
		return entities;
		}

	public void setEntities(ArrayList<Entity> entities)
		{
		this.entities = entities;
		}
	}