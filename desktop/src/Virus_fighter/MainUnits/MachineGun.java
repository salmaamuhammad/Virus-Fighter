package Virus_fighter.MainUnits;

import Virus_fighter.Level;
import Virus_fighter.Tools.Assets;
import Virus_fighter.Tools.Constants;
import Virus_fighter.Tools.Enums.Facing;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MachineGun extends Bullet {
    public MachineGun(Level level, Vector2 position, Facing direction) {
        super(level, position, direction);
        
        active = true;
    }
    @Override
    public void update(float delta) {
        
        // Move the bullet the correct amount in the correction direction
        
        switch (direction) {
            case LEFT:
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
                break;
            case RIGHT:
                position.x += delta * Constants.BULLET_MOVE_SPEED;
                break;
        }
        
        //For every enemy, check if the bullet is within the enemy's hit detection radius
        //if so, set the boolean "active" to false and decrement the enemy health   S
        for (Enemy enemy : level.getEnemies())
        {
            if (enemy instanceof Virus1)
            {
                if (position.dst(enemy.position) < Constants.ENEMY_SHOT_RADIUS1 * 2 ) {
                    // Spawn an explosion
                    level.spawnExplosion(position);
                    active = false;
                    enemy.Health -= 3;
                }
            }else
            {
                if (position.dst(enemy.position) < Constants.ENEMY_SHOT_RADIUS2 * 2) {
                    // Spawn an explosion
                    level.spawnExplosion(position);
                    active = false;
                    enemy.Health -= 3;
                }
            }
        }
        // Getting the world width from the level's viewport
        final float worldWidth = level.getViewport().getWorldWidth();
        // Getting the level's viewport's camera's horizontal position
        final float cam_X = level.getViewport().getCamera().position.x;
        // If the bullet is offscreen, set active = false
        if (position.x < cam_X - worldWidth / 2 || position.x > cam_X + worldWidth / 2) {
            active = false;
        }
    }
    
    @Override
    public void render(SpriteBatch batch) {
        
        final TextureRegion region = Assets.instance.bullet_assets2.MGunBullet;
        if (direction==Facing.RIGHT)
            batch.draw(region.getTexture(),position.x,position.y
                    ,0,0,region.getRegionWidth(),
                    region.getRegionHeight(),0.25f,0.25f,
                    0,region.getRegionX(),region.getRegionY(),
                    region.getRegionWidth(),region.getRegionHeight(),
                    false,false);
        else
            batch.draw(region.getTexture(),position.x,position.y
                    ,0,0,region.getRegionWidth(),
                    region.getRegionHeight(),0.25f,0.25f,
                    0,region.getRegionX(),region.getRegionY(),
                    region.getRegionWidth(),region.getRegionHeight(),
                    true,false);
        
    }
}
