package Virus_fighter;


import Virus_fighter.MainUnits.*;
import Virus_fighter.MainUnits.Platform;
import Virus_fighter.Tools.Constants;
import Virus_fighter.Tools.Enums.Facing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

//The level class holds all the level data,
// and is responsible for rendering the level and updating it's elements
public class Level {
    public boolean gameOver;
    public boolean victory;
    
    public Viewport viewport;
    public int score;
    private Fighter fighter;
    private Array<Platform> platforms;
    //Delayed removal array is used to delay the removal of an object
    // until "end()" is called, this will allow the code to remove entities
    // without affecting the iterations
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Explosion> explosions;
    
    private Music sound;
    
    public Level(Viewport viewport) {
        this.viewport = viewport;
    
        //to add background music
        sound=Gdx.audio.newMusic(Gdx.files.internal("desktop/Music/music.mp3"));
        sound.play();
        //to replay the music when it ends
        sound.setOnCompletionListener(new Music.OnCompletionListener()
        {
            @Override
            public void onCompletion(Music music) {
                sound.play();
            }
        });
        
        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();
        bullets = new DelayedRemovalArray<Bullet>();
        explosions = new DelayedRemovalArray<Explosion>();
        gameOver = false;
        victory = false;
        score = 0;
        
    }
    //Method is called every frame, to update what we see on the screen
    public void update(float delta) {
        //this is used to determine if the level ended or not
        // and wether you win or lose
        if (fighter.getHEALTH() <= 0) {
            gameOver = true;
        } else if (score >= 1725) {
            victory = true;
        }
        
        if (!gameOver && !victory) {
            //updates fighter position,health,etc...
            fighter.update(delta, platforms);
        
            // Update Bullets
            //Bullets delayed removal array, which means that the bullet specified
            // after begin()
            //will be removed only after reacing end()
            bullets.begin();
            for (Bullet bullet : bullets) {
                bullet.update(delta);
                //Bullets are in active if the hit an enemy or go out of camera bounds
                //this is done in bullet class and it's children
                if (!bullet.active) {
                    bullets.removeValue(bullet, false);
                }
            }
            bullets.end();
        
            // Update Enemies health, determine if they are dead to remove them and change score
            enemies.begin();
            for (int i = 0; i < enemies.size; i++) {
                Enemy enemy = enemies.get(i);
                enemy.update(delta);
                if (enemy.getHealth() < 1) {
                    spawnExplosion(enemy.getPosition());
                    enemies.removeIndex(i);
                    if (enemy instanceof Virus1)
                       score += Constants.ENEMY_KILL_SCORE1;
                    else
                        score += Constants.ENEMY_KILL_SCORE2;
                }
            }
            enemies.end();
        
            // Update Explosions
            // determine if explosion animation ended to remvoe the explosions
            explosions.begin();
            for (int i = 0; i < explosions.size; i++) {
                if (explosions.get(i).isFinished()) {
                    explosions.removeIndex(i);
                }
            }
            explosions.end();
        }
    }
    
    public void render(SpriteBatch batch) {
        viewport.apply();
        //sets background color
        Gdx.gl.glClearColor(
                1,
                0.71f,
                0.65f,
                0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //setting up the batch that will draw all the entities
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for (Platform platform : platforms) {
            platform.render(batch);
        }
        
        for (Enemy enemy : enemies) {
                enemy.render(batch);
        }
        
        fighter.render(batch);
        
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
        
        for (Explosion explosion : explosions) {
            explosion.render(batch);
        }
        
    }
    //Methods used to spawn bullets or explosion at certain coordinates and direction
    public void spawnBullet1(Vector2 position, Facing direction) {
        bullets.add(new Gun(this, position, direction));
    }
    public void spawnBullet2(Vector2 position, Facing direction) {
        bullets.add(new MachineGun(this, position, direction));
    }
    public void spawnExplosion(Vector2 position) {
        explosions.add(new Explosion(position));
    }
    
    //encapsulation was used here
    public Array<Platform> getPlatforms() {
        return platforms;
    }
    
    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }
    
    public Fighter getFighter() {
        return fighter;
    }
    
    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
    
    public Viewport getViewport() {
        return viewport;
    }
    
    public int getScore() {
        return score;
    }
}
