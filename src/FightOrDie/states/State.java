package FightOrDie.states;

import FightOrDie.Handler;

import java.awt.Graphics;

public abstract class State
    {
    private static State currentState = null;
    
    public static void setState(State state)
        {
        currentState = state;
        }
    
    public static State getState()
        {
        return currentState;
        }
    
    protected Handler handler;
    
    public State(Handler handler)
        {
        this.handler = handler;
        }
    
    /* SEPARATOR :D */
    public abstract void update();
    public abstract void render(Graphics g);
    }