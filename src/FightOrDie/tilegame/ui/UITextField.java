package FightOrDie.tilegame.ui;

import java.awt.Color;
import java.awt.Graphics;

import FightOrDie.graphics.Assets;
import FightOrDie.graphics.Text;

public class UITextField extends UIObject
    {
	String text;
	
	public UITextField(float x, float y, int width, int height, String text)
        {
		super(x, y, width, height);
		this.text = text;
    	}

	public void update(String message)
        {
		this.text = message;
    	}

	@Override
	public void render(Graphics g)
        {
		Text.drawString(g, text, (int)x, (int) y, true, Color.BLACK, Assets.itemInv);
    	}

	@Override
	public void onClick()
        {
		
        }

	@Override
	public void update()
        {
		
        }
    }
