package FightOrDie.states;

import java.awt.Graphics;

import FightOrDie.FilePathLoader;
import FightOrDie.Handler;
import FightOrDie.graphics.Assets;
import FightOrDie.tilegame.ui.ClickListener;
import FightOrDie.tilegame.ui.UIImageButton;
import FightOrDie.tilegame.ui.UIManager;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MenuState extends State
    {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final UIManager uiManager;

    public MenuState(Handler handler)
        {
		super(handler);
		this.uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
        
		uiManager.addObject(new UIImageButton(screenSize.width / 2 - 100, 100, 200, 125, Assets.startGame, new ClickListener()
            {
			@Override
			public void onClick()
                {
				handler.getGame().setGameState(new GameState(handler,"demoWorld.wld"));
				State.setState(handler.getGame().getGameState());
                }
            }));
		
		uiManager.addObject(new UIImageButton(screenSize.width / 2 - 100, 275, 200, 125, Assets.loadGame, new ClickListener()
            {
			@Override
			public void onClick()
                {
				handler.getGame().setGameState(new GameState(handler, new FilePathLoader().chooseFile(handler)));
				State.setState(handler.getGame().getGameState());
                }
            }));
        }

	@Override
	public void update()
        {
		uiManager.update();
        }

	@Override
	public void render(Graphics g)
        {
		g.drawImage(Assets.backgroundImg, 0, 0, null);
		uiManager.render(g);
        }
    }