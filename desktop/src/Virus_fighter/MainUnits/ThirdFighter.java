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

public class ThirdFighter extends Fighter{
    
  
    public ThirdFighter(Vector2 Spawnlocation, Level level) {
        super(Spawnlocation,level,80,Constants.WORLD_SIZE / 2);
    }
    
    @Override
    public void render(SpriteBatch batch) {
    
        TextureRegion region;
    
        if (isGun)
            region = Assets.instance.fighter_3_assets.idle_gun;
        else
            region = Assets.instance.fighter_3_assets.idle_mgun;
        
        boolean right = false;
        boolean left = false;
    
        if (HEALTH<=0 && facing == Facing.RIGHT ) {
            float DeathTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - DeathStartTime);
            region = Assets.instance.fighter_3_assets.Death_Animation.getKeyFrame(DeathTimeSeconds);
            right = true;
            left = false;
        }else if(HEALTH<=0 && facing == Facing.LEFT){
           float DeathTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - DeathStartTime);
            region = Assets.instance.fighter_3_assets.Death_Animation.getKeyFrame(DeathTimeSeconds);
            right = false;
            left = true;
        }else {
            if (facing == Facing.RIGHT && jumpState != JumpState.GROUNDED) {
                region = Assets.instance.fighter_3_assets.jumping;
                right = true;
                left = false;
            } else if (facing == Facing.RIGHT && walkState == WalkState.STANDING) {
                if (isGun)
                    region = Assets.instance.fighter_3_assets.idle_gun;
                else
                    region = Assets.instance.fighter_3_assets.idle_mgun;
                right = true;
                left = false;
            } else if (facing == Facing.RIGHT && walkState == WalkState.WALKING) {
                float RunTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - RunStartTime);
                region = Assets.instance.fighter_3_assets.run_Animation.getKeyFrame(RunTimeSeconds);
                right = true;
                left = false;
            } else if (facing == Facing.LEFT && jumpState != JumpState.GROUNDED) {
                region = Assets.instance.fighter_3_assets.jumping;
                left = true;
                right = false;
            } else if (facing == Facing.LEFT && walkState == WalkState.STANDING) {
                if (isGun)
                    region = Assets.instance.fighter_3_assets.idle_gun;
                else
                    region = Assets.instance.fighter_3_assets.idle_mgun;
                left = true;
                right = false;
            } else if (facing == Facing.LEFT && walkState == WalkState.WALKING) {
                float RunTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - RunStartTime);
                region = Assets.instance.fighter_3_assets.run_Animation.getKeyFrame(RunTimeSeconds);
                left = true;
                right = false;
            }
        }
        if (right) {
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
        }else if(left)
        {
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
