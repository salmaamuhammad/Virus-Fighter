package Virus_fighter.MainUnits;


import Virus_fighter.Tools.Assets;
import Virus_fighter.Tools.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.utils.TimeUtils;
import Virus_fighter.Tools.Enums.*;

/**
 * The inherted class of the enemy
 */
public class Virus1 extends Enemy {
    
    
    // the enemy constructor
    public Virus1(Platform platform)
    {
        super(platform,Constants.ENEMY_HEALTH_1);
        
    }
    
    
    @Override
    public void update(float delta)
    {
        
        // Move the enemy left/right
        // Using the delta time and the newly created enemy movement speed constant
        
        switch (direction)
        {
            case LEFT:
                position.x -= Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
            case RIGHT:
                position.x += Constants.ENEMY_MOVEMENT_SPEED * delta;
        }
        
        // If the enemy is off the left side of the platform, the enemy move back to the right
        
        
        //If the enemy if off the right side of the platform,the enemy move back to the left
        if (position.x < platform.left)
        {
            position.x = platform.left;
            direction = Facing.RIGHT;
        } else if (position.x > platform.right)
        {
            position.x = platform.right;
            direction = Facing.LEFT;
        }
        
        // Figure out where in the bob cycle we're at
        // bobMultiplier = 1 + sin(2 PI elapsedTime / period)
        final float elapsedTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTime);
        final float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * elapsedTime / Constants.ENEMY_BOB_PERIOD);
        
        // Set the enemy vertical position
        // y = platformTop + enemyCenter + bobAmplitude * bobMultiplier
        
        position.y = platform.top + Constants.ENEMY_CENTER.y + Constants.ENEMY_BOB_AMPLITUDE * bobMultiplier;
        
    }
    @Override
    public void render(SpriteBatch batch)
    {
        final TextureRegion region = Assets.instance.enemyAssets.enemy;
        batch.draw(
                region.getTexture(),
                position.x - Constants.ENEMY_CENTER.x,
                position.y - Constants.ENEMY_CENTER.y ,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1.5f,
                1.5f,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }
}