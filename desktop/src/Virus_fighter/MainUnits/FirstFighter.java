package Virus_fighter.MainUnits;

import Virus_fighter.Level;
import Virus_fighter.Tools.Assets;
import Virus_fighter.Tools.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import Virus_fighter.Tools.Enums.*;


public class FirstFighter extends Fighter {
    
    public FirstFighter(Vector2 Spawnlocation,Level level) {
        super(Spawnlocation, level, 120,Constants.WORLD_SIZE / 4);
    }
    //renders the appropriate animation/asset according to current state of the fighter
    //this is the same for other fighters
    // with the difference and usage of the loaded assets and animation
    @Override
    public void render(SpriteBatch batch) {
    
    
        TextureRegion region;
        //load appropriate asset according to selected gun type
        if (isGun)
            region = Assets.instance.fighter_1_assets.idle_gun;
        else
            region = Assets.instance.fighter_1_assets.idle_mgun;
    
        
        //used to determine which direction character is facing
        //all assets are facing rightwards
        //so instead of mirroring all assets to have a copy facing in each direction
        //we used the flipx argument in draw function to mirror images instead
        boolean right = false;
        boolean left = false;
    
        //first check if the character is dead or not
        //and which position was he facing when he died
        // to render the animation in the correct direction and time
        if (HEALTH <= 0 && facing == Facing.RIGHT) {
            float DeathTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - DeathStartTime);
            region = Assets.instance.fighter_1_assets.Death_Animation.getKeyFrame(DeathTimeSeconds);
            right = true;
            left = false;
        } else if (HEALTH <= 0 && facing == Facing.LEFT) {
            float DeathTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - DeathStartTime);
            region = Assets.instance.fighter_1_assets.Death_Animation.getKeyFrame(DeathTimeSeconds);
            right = false;
            left = true;
            
            //character is not dead, check his state and direction
            // to draw the correct animation/asset
        } else {
            if (facing == Facing.RIGHT && jumpState != JumpState.GROUNDED) {
                region = Assets.instance.fighter_1_assets.jumping;
                right = true;
                left = false;
            } else if (facing == Facing.RIGHT && walkState == WalkState.STANDING) {
    
                if (isGun)
                    region = Assets.instance.fighter_1_assets.idle_gun;
                else
                    region = Assets.instance.fighter_1_assets.idle_mgun;
    
                right = true;
                left = false;
            } else if (facing == Facing.RIGHT && walkState == WalkState.WALKING) {
                float RunTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - RunStartTime);
                region = Assets.instance.fighter_1_assets.run_Animation.getKeyFrame(RunTimeSeconds);
                right = true;
                left = false;
            } else if (facing == Facing.LEFT && jumpState != JumpState.GROUNDED) {
                region = Assets.instance.fighter_1_assets.jumping;
                left = true;
                right = false;
            } else if (facing == Facing.LEFT && walkState == WalkState.STANDING) {
                if (isGun)
                    region = Assets.instance.fighter_1_assets.idle_gun;
                else
                    region = Assets.instance.fighter_1_assets.idle_mgun;
                left = true;
                right = false;
            } else if (facing == Facing.LEFT && walkState == WalkState.WALKING) {
                float RunTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - RunStartTime);
                region = Assets.instance.fighter_1_assets.run_Animation.getKeyFrame(RunTimeSeconds);
                left = true;
                right = false;
            }
        }
    
        if (right) {
            //draw the selected region specified by the above conditions to draw it
            batch.draw(
                    region.getTexture(),
                    position.x - Constants.FIGHTER_EYE_POSITION.x,
                    position.y - Constants.FIGHTER_EYE_POSITION.y,
                    0,
                    0,
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    1,
                    1,
                    0,
                    region.getRegionX(),
                    region.getRegionY(),
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    false,
                    false);
        
        } else if (left) {
            batch.draw(
                    region.getTexture(),
                    position.x - Constants.FIGHTER_EYE_POSITION.x,
                    position.y - Constants.FIGHTER_EYE_POSITION.y,
                    0,
                    0,
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    1,
                    1,
                    0,
                    region.getRegionX(),
                    region.getRegionY(),
                    region.getRegionWidth(),
                    region.getRegionHeight(),
                    true,
                    false);
        
        }
    }
    
}