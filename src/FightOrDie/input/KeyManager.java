package FightOrDie.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyManager implements KeyListener
    {
	private static final Logger LOGGER = Logger.getLogger(KeyManager.class.getName());

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right;
    
    public KeyManager()
        {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
        }
    
    public void update()
        {
    	for(int i = 0; i < keys.length; ++i)
            {
    		if(cantPress[i] && !keys[i])
                {
    			cantPress[i] = false;
                }
    		else if (justPressed[i])
                {
    			cantPress[i] = true;
    			justPressed[i] = false;
                }
    		if(!cantPress[i] && keys[i])
                {
    			justPressed[i] = true;
                }
            }
    	
    	if(keyJustPressed(KeyEvent.VK_ESCAPE))
            {
    		System.exit(0);
            }
        
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        }
    
    @Override
    public void keyTyped(KeyEvent ke)
        {

        }

    public boolean keyJustPressed(int keyCode)
        {
    	if(!(keyCode < 0 || keyCode > keys.length))
            {
            return justPressed[keyCode];
            }
        return true;
        }
    
    @Override
    public void keyPressed(KeyEvent ke)
        {
    	if(!(ke.getKeyCode() < 0 || ke.getKeyCode() > keys.length))
            {
            keys[ke.getKeyCode()] = true;
            }
        }

    @Override
    public void keyReleased(KeyEvent ke)
        {
    	if(!(ke.getKeyCode()<0 || ke.getKeyCode()>keys.length))
            {
            keys[ke.getKeyCode()] = false;
            }
        }
    }