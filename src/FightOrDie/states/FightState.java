package FightOrDie.states;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import FightOrDie.Handler;
import FightOrDie.entities.Entity;
import FightOrDie.entities.creatures.Player;
import FightOrDie.graphics.Assets;
import FightOrDie.tilegame.ui.ClickListener;
import FightOrDie.tilegame.ui.UIImageButton;
import FightOrDie.tilegame.ui.UIManager;
import FightOrDie.tilegame.ui.UITextField;

public class FightState extends State
	{
	private static final Logger LOGGER = Logger.getLogger(FightState.class.getName());

	private UIManager uiManager;
	private UIManager texts;
	private Entity enemy;
	private Player player;
    private int fightHealth, commonHealthP;
	private int hit_player = 0, def_player = 0;
	UITextField currentStats, currentRoundE, currentRoundP;
	private State newMenuState;
	
	public FightState(final Handler handler, final Player player, final Entity e)
		{
		super(handler);
		this.player = player;
		this.enemy = e;
		this.fightHealth = 20;
		handler.getGame().nullPlayerDamage();
		this.uiManager = new UIManager(handler);
		this.texts = new UIManager(handler);
		handler.getGame().setFighting(true);
		handler.getMouseManager().setUIManager(uiManager);
		currentStats = new UITextField(500,300,200,300, "YOUR HEALTH: " + player.health + "     ENEMY HEALTH: " + fightHealth);
		texts.addObject(currentStats);
		init();
		}
	
	private void init()
		{
		uiManager.addObject(new UIImageButton(425,125,150,100,Assets.btn_start, new ClickListener()
			{
			@Override
			public void onClick()
				{
				if (hit_player != 0 && def_player != 0)
					{
					texts.removeObject(currentStats);
					texts.removeObject(currentRoundE);
					texts.removeObject(currentRoundP);
					removeHighlight();
					String text[] = new String[2];
					text = checkFight(generateEnemyTurn(), hit_player, def_player);
					if (player.health > 0 && fightHealth < 1)
						{
						try
							{
							texts.addObject(new UITextField(500,300,200,300,"you won"));
							Thread.sleep(3000);
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						handler.getGame().setFighting(false);
						enemy.die();
						State.setState(handler.getGame().gameState);
						}
					else if (player.health < 1 && fightHealth > 0)
						{
						try
							{
							texts.addObject(new UITextField(500,300,200,300,"you lost"));
							Thread.sleep(3000);
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						player.die();
						
						}
					else if (player.health < 1 && fightHealth < 1)
						{
						try
							{
							uiManager.addObject(new UITextField(500,300,200,300,"you killed each other"));
							Thread.sleep(3000);
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						player.die();
						}
					
					String stats = "YOUR HEALTH: " + player.health + "     ENEMY HEALTH: " + fightHealth;
					currentStats = new UITextField(500,300,200,300, stats);
					texts.addObject(currentStats);
					currentRoundE = new UITextField(500,400,200,300, text[0]);
					texts.addObject(currentRoundE);
					currentRoundP = new UITextField(500,425,200,300, text[1]);
					texts.addObject(currentRoundP);
					hit_player = 0;
					def_player = 0;
					}
				else
					{
					LOGGER.setLevel(Level.INFO);
			    	LOGGER.info("Round");
					}
				}
			}));
		
		//BUTTONS FOR HIT AND DEF
		
		uiManager.addObject(new UIImageButton(725,50,75,75,Assets.enemy_head, new ClickListener()
			{
			@Override
			public void onClick()
				{
				hit_player = 1;
				highlightEnemy(725, 50, 75, 75);
				}
			}));
		
		uiManager.addObject(new UIImageButton(200,50,75,75,Assets.player_head, new ClickListener()
			{
			@Override
			public void onClick()
				{
				def_player = 1;
				highlightPlayer(200,50,75,75);
				}
			}));
		
		//TORSO
		uiManager.addObject(new UIImageButton(725,150,75,75,Assets.enemy_torso, new ClickListener()
			{
			@Override
			public void onClick()
				{
				hit_player = 2;
				highlightEnemy(725, 150, 75, 75);
				}
			}));
		
		uiManager.addObject(new UIImageButton(200,150,75,75,Assets.player_torso, new ClickListener()
			{
			@Override
			public void onClick()
				{
				def_player = 2;
				highlightPlayer(200,150,75,75);
				}
			}));
		
		//LEGS
		uiManager.addObject(new UIImageButton(725,250,75,75,Assets.enemy_legs, new ClickListener()
			{
			@Override
			public void onClick()
				{
				hit_player = 3;
				highlightEnemy(725, 250, 75, 75);
				}
			}));
		
		uiManager.addObject(new UIImageButton(200,250,75,75,Assets.player_legs, new ClickListener()
			{
			@Override
			public void onClick()
				{
				def_player = 3;
				highlightPlayer(200,250,75,75);
				}
			}));
		}
	
	private String[] checkFight(int[] enemyTurn, int hitPlayer, int defPlayer)
		{
		String round[] = new String[2];
		String part = "";
		round[0] = "";
		round[1] = "";
		int attack = 0;
		
		if (hitPlayer == 1) part = "HEAD";
		else if (hitPlayer == 2) part = "TORSO";
		else part = "LEGS";
		
		if(hitPlayer == enemyTurn[1])
			{
			round[0] += "Your attack to " + part + " was blocked!";
			}
		else
			{
			attack = 20/*(int)(Math.random() * 10)*/;
			if (attack < 1) attack = 1;
			fightHealth -= attack;
			handler.getGame().addPlayerDamage(attack);
			round[0] += "You dealt " + attack + " damage to " + part;
			}
		////////////////////////////////////////////
		handler.getGame().addEnemyDamage(1);
		///////////////////////////////////////////
		
		if (enemyTurn[0] == 1) part = "HEAD";
		else if (enemyTurn[0] == 2) part = "TORSO";
		else part = "LEGS";
		
		if(enemyTurn[0] == defPlayer)
			{
			round[1] += "You blocked attack to " + part;
			LOGGER.setLevel(Level.INFO);
	    	LOGGER.info("You blocked the attack");
			}
		else
			{
			attack = (int)(Math.random() * 2);
			if (attack < 1) attack = 1;
			player.health -= attack;
			handler.getGame().addEnemyDamage(attack);
			round[1] += "You received " + attack + " damage to " + part;
			LOGGER.setLevel(Level.INFO);
	    	LOGGER.info("You attacked enemy:" + (commonHealthP - this.player.health));
			}
		return round;
		}
	
	private int[] generateEnemyTurn()
		{
		int[] enemyTurn = new int[2];
		for(int i = 0; i < 2; ++i)
			{
			int tmp = 1 + (int)(Math.random() * 3); //1 is min, 3 is max
			if(tmp > 3)
				{
				tmp = 3;
				}
			enemyTurn[i] = tmp; //enemyTurn[0] = attack, enemyTurn[1] = defence
			}
		LOGGER.setLevel(Level.INFO);
    	LOGGER.info(enemyTurn[0] + " " + enemyTurn[1]);
    	return enemyTurn;	
		}

	@Override
	public void update()
		{
		uiManager.update();
		texts.update();
		}

	@Override
	public void render(Graphics g)
		{
		uiManager.render(g);
		texts.render(g);
		}
	
	public void highlightPlayer(int i, int j, int k, int l)
		{
		handler.getGame().setDrawPlayer(true);
		handler.getGame().setDrawXplayer(i);
		handler.getGame().setDrawYplayer(j);
		handler.getGame().setDrawWidthPlayer(k);
		handler.getGame().setDrawHeightPlayer(l);
		}
	
	public void highlightEnemy(int i, int j, int k, int l)
		{
		handler.getGame().setDrawEnemy(true);
		handler.getGame().setDrawXenemy(i);
		handler.getGame().setDrawYenemy(j);
		handler.getGame().setDrawWidthEnemy(k);
		handler.getGame().setDrawHeightEnemy(l);
		}
	
	public void removeHighlight()
		{
		handler.getGame().setDrawEnemy(false);
		handler.getGame().setDrawPlayer(false);
		}
	}