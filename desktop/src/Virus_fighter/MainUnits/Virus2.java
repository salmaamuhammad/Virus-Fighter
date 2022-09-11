package Virus_fighter.MainUnits;

import Virus_fighter.Tools.Assets;
import Virus_fighter.Tools.Constants;
import Virus_fighter.Tools.Enums;
import Virus_fighter.Tools.Utils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;


public class Virus2 extends Enemy {
    // the enemy constructor
    public Virus2(Platform platform)
    {
        super(platform,Constants.ENEMY_HEALTH_2);
    }
    
    public void update(float delta)
    {
        
        // Move the enemy left/right
        // Using the delta time and the newly created enemy movement speed constant
        
        switch (direction)
        {
            case LEFT:
                position.x -= Constants.ENEMY_MOVEMENT_SPEED2* delta;
                break;
            case RIGHT:
                position.x += Constants.ENEMY_MOVEMENT_SPEED2 * delta;
        }
        
        // If the enemy is off the left side of the platform, the enemy move back to the right
        
        
        //If the enemy if off the right side of the platform,the enemy move back to the left
        if (position.x < platform.left)
        {
            position.x = platform.left;
            direction = Enums.Facing.RIGHT;
        } else if (position.x > platform.right)
        {
            position.x = platform.right;
            direction = Enums.Facing.LEFT;
        }
        
        // Figure out where in the bob cycle we're at
        // bobMultiplier = 1 + sin(2 PI elapsedTime / period)
        final float elapsedTime = MathUtils.nanoToSec*(TimeUtils.nanoTime() - startTime);
        final float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * elapsedTime / Constants.ENEMY_BOB_PERIOD2);
        
        // Set the enemy vertical position
        // y = platformTop + enemyCenter + bobAmplitude * bobMultiplier
        
        position.y = platform.top + Constants.ENEMY_CENTER2.y+ Constants.ENEMY_BOB_AMPLITUDE2 * bobMultiplier;
        
    }
    @Override
    public void render(SpriteBatch batch)
    {
         TextureRegion region= Assets.instance.enemyAssets2.enemy2;
         Utils.drawTextureRegion(batch, region, position, Constants.ENEMY_CENTER2);
    }
}
