package FightOrDie.tiles;

import FightOrDie.graphics.Assets;

public class DirtTile extends Tile
    {
    public DirtTile(int id)
        {
        super(Assets.dirt, id);
        }
    
    @Override
    public boolean isSlowing()
        {
        return true;
        }
    }