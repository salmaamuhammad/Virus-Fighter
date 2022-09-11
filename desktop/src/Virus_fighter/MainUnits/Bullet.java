package Virus_fighter.MainUnits;

import Virus_fighter.Level;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import Virus_fighter.Tools.Enums.Facing;
//abstraction was used here
public abstract class Bullet {
    // Add a Direction
    protected Facing direction;
    
    protected Level level;
    
    // Add a position
    protected Vector2 position;
    
    public boolean active;
    
    public Bullet(Level level, Vector2 position, Facing direction) {
        this.level = level;
        this.position = position;
        this.direction = direction;
        
        active = true;
    }
    
    public abstract void update(float delta);
    
    
    
    public abstract void render(SpriteBatch batch);
}
