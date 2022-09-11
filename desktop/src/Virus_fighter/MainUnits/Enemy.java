package Virus_fighter.MainUnits;

import Virus_fighter.Tools.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import Virus_fighter.Tools.Enums.*;
import com.badlogic.gdx.utils.TimeUtils;

//abstraction was used here
abstract public class Enemy {
    
    protected Platform platform;
    protected Vector2 position;
    
    // the Direction
    protected Facing direction;
    protected long startTime;
    
    protected int Health;
    
    // the enemy constructor
    public Enemy(Platform platform,int Health){
        this.platform = platform;
        direction = Facing.RIGHT;
        position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
        startTime = TimeUtils.nanoTime();
        this.Health=Health;
    };
    
    public abstract void update(float delta);
    
    public abstract void render(SpriteBatch batch);
    
    public int getHealth() {
        return Health;
    }
    
    public Vector2 getPosition() {
        return position;
    }
}