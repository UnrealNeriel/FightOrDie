package FightOrDie.entities.creatures;

import FightOrDie.graphics.Animation;
import FightOrDie.graphics.Assets;
import FightOrDie.inventory.Inventory;
import FightOrDie.states.MenuState;
import FightOrDie.states.State;
import FightOrDie.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature
    {
	private Inventory inventory;
	/* ANIMATIONS */
	private final Animation animeDown, animeUp, animeLeft, animeRight, animeUpRight, animeUpLeft, animeDownRight, animeDownLeft;
	private BufferedImage correctFrame;
	
    public Player(Handler handler, float x, float y)
        {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFALUT_CREATE_HEIGHT);
        this.speed = 3.2f;
        this.isPlayer = true;
        this.health = 20;
        /* COLLISION BOX */
        bounds.x = 18;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
        
        /* ANIMATIONS */
        animeDown = new Animation(250, Assets.player_down);
        animeUp = new Animation(250, Assets.player_up);
        animeLeft = new Animation(250, Assets.player_left);
        animeRight = new Animation(250, Assets.player_right);
        animeUpRight = new Animation(250, Assets.player_UpRight);
        animeUpLeft = new Animation(250, Assets.player_UpLeft);
        animeDownRight = new Animation(250, Assets.player_DownRight);
        animeDownLeft = new Animation(250, Assets.player_DownLeft);
        
        correctFrame = Assets.player_stand;
        
        inventory = new Inventory(handler);
        }

    @Override
    public void update()
        {
    	/* ANIMATIONS */
    	animeDown.update();
    	animeUp.update();
    	animeLeft.update();
    	animeRight.update();
    	animeUpRight.update();
    	animeUpLeft.update();
    	animeDownRight.update();
    	animeDownLeft.update();
    	/* END OF ANIMATIONS */
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        inventory.update();
        }

    private void getInput()
        {
    	xMove = 0;
        yMove = 0;
    	if(inventory.isActive())
    		{
    		return;
    		}
        
        if (handler.getKeyManager().up) {yMove = -speed;}
        if (handler.getKeyManager().down) {yMove = speed;}
        if (handler.getKeyManager().left) {xMove = -speed;}
        if (handler.getKeyManager().right) {xMove = speed;}
        }
    
    @Override
    public void die()
        {
		handler.getGame().setFighting(false);
		handler.getGame().setMenuState(null);
		handler.getGame().nullEnemyDamage();
		State.setState(new MenuState(handler));
        }
    
    @Override
    public void render(Graphics g)
        {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
        //           (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        }
    
    public void postRender(Graphics g)
        {
    	inventory.render(g);
        }
    
    private BufferedImage getCurrentAnimationFrame()
        {
    	if (xMove > 0 && yMove < 0) 
            { // right and up
    		correctFrame = animeUpRight.getCurrentFrame();
            }
        else if (xMove < 0 && yMove < 0) 
            { // left and up
    		correctFrame = animeUpLeft.getCurrentFrame();
            } 
        else if (xMove > 0 && yMove > 0) 
            { // right and down
    		correctFrame = animeDownRight.getCurrentFrame();
            } 
        else if (xMove < 0 && yMove > 0) 
            { // left and down
    		correctFrame = animeDownLeft.getCurrentFrame();
            } 
        else if (xMove < 0) 
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
    		correctFrame = Assets.player_stand;
            }
    	
        return correctFrame;
        }

	public Inventory getInventory()
        {
		return inventory;
        }

	public void setInventory(Inventory inventory)
        {
		this.inventory = inventory;
        }
    }