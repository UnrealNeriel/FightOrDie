package FightOrDie.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import FightOrDie.Handler;
import FightOrDie.Item.Key;
import FightOrDie.Item.Potion;
import FightOrDie.graphics.Animation;
import FightOrDie.graphics.Assets;
import java.awt.Color;

public class Zombie extends Creature
	{
	private final Animation animeDown, animeUp, animeLeft, animeRight;
	private BufferedImage correctFrame;
	int direction = 0;
	public Zombie (Handler handler, float x, float y)
		{
		super (handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, (int) (Creature.DEFALUT_CREATE_HEIGHT * 1.5));
		this.health = 20;
		this.fightable = true;

		/* COLLISION BOX */
		bounds.x = 10;
		bounds.y = 64;
		bounds.width = 50;
		bounds.height = 30;
		this.speed = 2;

		/* ANIMATIONS */
		animeDown = new Animation(150, Assets.zombie_down);
		animeUp = new Animation(150, Assets.zombie_up);
		animeLeft = new Animation(150, Assets.zombie_left);
		animeRight = new Animation(150, Assets.zombie_right);

		correctFrame = Assets.zombie_stand;
		}

	public void getMove()
		{
		xMove = 0;
		yMove = 0;
		int chance = (int)(Math.random() * 100);
		if (chance == 6)
			{
			direction = (int)(Math.random() * 5);
			int distance = (int)(Math.random() * 100);
			if (distance < 20)
				{
				distance = 20;
				}
			}
		switch (direction)
			{
			case 1:
				xMove = -speed;
				break;
			case 2:
				xMove = speed;
				break;
			case 3:
				yMove = -speed;
				break;
			case 4:
				yMove = speed;
				break;
			}
		}

	@Override
	public void die()
		{
		int chance = (int)(Math.random() * 3);
		if (chance != 1)
			{
			final Potion healthPot = new Potion(Assets.potionImg, "health pot", 5);
			handler.getWorld().getItemManager().addItem(healthPot.createNew((int)this.x, (int)this.y));
			}
		else
			{
			final Key keyChest = new Key(Assets.keyImg, "key chest", 9);
			handler.getWorld().getItemManager().addItem(keyChest.createNew((int)this.x, (int)this.y));
			}
		this.active = false;
		}

	@Override
	public void update()
		{
		/* ANIMATIONS */
		animeDown.update();
		animeUp.update();
		animeLeft.update();
		animeRight.update();
		/* END OF ANIMATIONS */
		getMove();
		move();
		}

	@Override
	public void render(Graphics g)
		{
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

			//g.setColor(Color.red);
			//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			//           (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		}

	private BufferedImage getCurrentAnimationFrame()
		{
		if (xMove < 0)
			{ // left
			correctFrame = animeLeft.getCurrentFrame();
			}
		else if (xMove > 0)
			{ // right
			correctFrame = animeRight.getCurrentFrame();
			} 
		else if (yMove < 0) 
			{ // up
			correctFrame = animeUp.getCurrentFrame();
			} 
		else if (yMove > 0) 
			{ // down
			correctFrame = animeDown.getCurrentFrame();
			} 
		else 
			{ //standing still
			correctFrame = Assets.zombie_stand;
			}
		return correctFrame;
		}
	}