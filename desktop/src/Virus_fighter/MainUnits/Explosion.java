package Virus_fighter.MainUnits;

import Virus_fighter.Tools.Assets;
import Virus_fighter.Tools.Constants;
import Virus_fighter.Tools.Utils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Explosion {
    private final Vector2 position;
    private final long startTime;
    
    public Explosion(Vector2 position) {
        // Saving the spawn position
        this.position = position;
        
        // Saving off the start time
        startTime = TimeUtils.nanoTime();
    }
    
    public void render(SpriteBatch batch) {
        // Select and draw the appropriate frame of the explosion animation
        batch.draw(
                Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(startTime)).getTexture(),
                position.x - Constants.EXPLOSION_CENTER.x,
                position.y - Constants.EXPLOSION_CENTER.y,
                0,
                0,
                70,
                70,
                1,
                1,
                0,
                Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(startTime)).getRegionX(),
                Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(startTime)).getRegionY(),
                Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(startTime)).getRegionWidth(),
                Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(startTime)).getRegionHeight(),
                false,
                false);
    }
    
    public boolean isFinished() {
        final float elapsedTime = Utils.secondsSince(startTime);
        return Assets.instance.explosionAssets.explosion.isAnimationFinished(elapsedTime);
    }
}
