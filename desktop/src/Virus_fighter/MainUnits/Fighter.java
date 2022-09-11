package Virus_fighter.MainUnits;

import Virus_fighter.Level;
import Virus_fighter.Tools.Constants;
import Virus_fighter.Tools.Enums.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

//parent Fighter class contains the shared attributes and methods of all 3 fighters
//abstraction was used here
public abstract class Fighter {
    
    protected Vector2 spawnLocation;
    public Vector2 position;
    protected Vector2 lastFramePosition;
    protected Vector2 velocity;
    
    protected Facing facing;
    protected JumpState jumpState;
    protected WalkState walkState;
    
    //these are used to determine when the animation started
    // so we can render the correct animation frame according
    // to time passed from start time
    protected long RunStartTime;
    protected long jumpStartTime;
    protected long DeathStartTime;
    
    private Level level;
    
    protected int HEALTH=0;
    protected float MOVE_SPEED=0;
    
    //boolean determines if the current selected is the gun or machine gun
    // by default gun is selected
    boolean isGun= true;
    
    public Fighter(Vector2 spawnLocation,Level level, int HEALTH, float MOVE_SPEED) {
        this.spawnLocation = spawnLocation;
        this.level=level;
        position = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();
        this.HEALTH=HEALTH;
        this.MOVE_SPEED=MOVE_SPEED;
        init();
    }
    
    public void init(){
        position.set(spawnLocation);
        lastFramePosition.set(spawnLocation);
        velocity.setZero();
        jumpState = JumpState.FALLING;
        facing = Facing.RIGHT;
        walkState = WalkState.STANDING;
    }
    
    
    //Method used to update Fighter position,state,health,velocity,etc... every frame
    public void update(float delta, Array<Platform> platforms) {
        
        lastFramePosition.set(position);
        velocity.y -= Constants.GRAVITY;
        position.mulAdd(velocity, delta);
        
        //The kill plane is the area below all platforms where if not specified
        // the character will be stuck in falling state forever
        //the condition checks if he falls below the kill plane, to reduce his health
        // and re initialize the character at the spawn location(starting position)
        if (position.y < Constants.KILL_PLANE) {
            HEALTH-=10;
            if (HEALTH>0)
            {
               init();
            }
        }
        
        // Land on/fall off platforms
        if (jumpState != JumpState.JUMPING) {
            if (jumpState != JumpState.RECOILING) {
                jumpState = JumpState.FALLING;
            }
            for (Platform platform : platforms) {
                if (landedOnPlatform(platform)) {
                    jumpState = JumpState.GROUNDED;
                    velocity.y = 0;
                    velocity.x = 0;
                    position.y = platform.top + Constants.FIGHTER_EYE_HEIGHT;
                }
            }
        }
        
        // Collide with enemies
        Rectangle FighetrBounds = new Rectangle(
                position.x - Constants.FIGHTER_STANCE_WIDTH / 2,
                position.y - Constants.FIGHTER_EYE_HEIGHT,
                Constants.FIGHTER_STANCE_WIDTH,
                Constants.FIGHTER_HEIGHT);
        
        for (Enemy enemy : level.getEnemies()) {
            Rectangle enemyBounds;
            if (enemy instanceof Virus1)
            {
                enemyBounds = new Rectangle(
                        enemy.position.x - Constants.ENEMY_COLLISION_RADIUS1,
                        enemy.position.y - Constants.ENEMY_COLLISION_RADIUS1,
                        2 * Constants.ENEMY_COLLISION_RADIUS1,
                        1.5f * Constants.ENEMY_COLLISION_RADIUS1
                );
            }else
            {
                enemyBounds = new Rectangle(
                        enemy.position.x - Constants.ENEMY_COLLISION_RADIUS2,
                        enemy.position.y - Constants.ENEMY_COLLISION_RADIUS2,
                        2 * Constants.ENEMY_COLLISION_RADIUS2,
                        2 * Constants.ENEMY_COLLISION_RADIUS2);
            }
            
            if (FighetrBounds.overlaps(enemyBounds)) {
                
                if (position.x < enemy.position.x) {
                    recoilFromEnemy(Facing.LEFT);
                    if (enemy  instanceof Virus1)
                       HEALTH-=10;
                    else
                        HEALTH-=15;
                    if (HEALTH<=0)
                    {
                        //record time of death
                        DeathStartTime=TimeUtils.nanoTime();
                    }
                } else {
                    recoilFromEnemy(Facing.RIGHT);
                    if (enemy  instanceof Virus1)
                        HEALTH-=10;
                    else
                        HEALTH-=15;
                    if (HEALTH<=0)
                    {
                        //record time of death
                        DeathStartTime=TimeUtils.nanoTime();
                    }
                }
            }
        }
        
        // Move left/right
        if (jumpState != JumpState.RECOILING) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveLeft(delta,MOVE_SPEED);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveRight(delta,MOVE_SPEED);
            } else {
                walkState = WalkState.STANDING;
            }
        }
        
        // Jump
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
            }
        } else {
            endJump();
        }
        //Change weapon
        if(Gdx.input.isKeyJustPressed(Input.Keys.C))
        {
            isGun = !isGun;
        }
        //shoots Gun
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            
            Vector2 bulletPosition ;
            if (isGun)
            {
                if (facing == Facing.RIGHT
                        && jumpState == JumpState.GROUNDED
                        && walkState == WalkState.STANDING) {
                            bulletPosition = new Vector2(
                                 position.x + Constants.FIGHTER_GUN_OFFSET.x ,
                               position.y - Constants.FIGHTER_GUN_OFFSET.y
                            );
                          level.spawnBullet1(bulletPosition, facing);
                    } else if(facing == Facing.LEFT
                        && jumpState == JumpState.GROUNDED
                        && walkState == WalkState.STANDING){
                    
                            bulletPosition = new Vector2(
                                position.x - (Constants.FIGHTER_WIDTH-Constants.FIGHTER_GUN_OFFSET.x) ,
                                position.y - Constants.FIGHTER_GUN_OFFSET.y
                            );
                           level.spawnBullet1(bulletPosition, facing);
                    }
                }else {
                    if (facing == Facing.RIGHT
                            && jumpState == JumpState.GROUNDED
                            && walkState == WalkState.STANDING) {
                       bulletPosition = new Vector2(
                            position.x + Constants.FIGHTER_MGUN_OFFSET.x ,
                            position.y - Constants.FIGHTER_MGUN_OFFSET.y
                       );
                        level.spawnBullet2(bulletPosition, facing);
                } else if(facing == Facing.LEFT
                            && jumpState == JumpState.GROUNDED
                            && walkState == WalkState.STANDING) {
        
                    bulletPosition = new Vector2(
                            position.x - (Constants.FIGHTER_WIDTH-Constants.FIGHTER_MGUN_OFFSET.x ),
                            position.y - Constants.FIGHTER_MGUN_OFFSET.y
                    );
                        level.spawnBullet2(bulletPosition, facing);
                }
            }
        }
    }
    //Check if character fell on given platform or not
    protected boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;
        
        if (lastFramePosition.y - Constants.FIGHTER_EYE_HEIGHT >= platform.top &&
                position.y - Constants.FIGHTER_EYE_HEIGHT < platform.top) {
            
            float leftFoot = position.x - Constants.FIGHTER_STANCE_WIDTH / 2;
            float rightFoot = position.x + Constants.FIGHTER_STANCE_WIDTH / 2;
            
            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);
            straddle = (platform.left > leftFoot && platform.right < rightFoot);
        }
        return leftFootIn || rightFootIn || straddle;
    }
    protected void moveLeft(float delta,float moveSpeed) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            RunStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.LEFT;
        position.x -= delta * moveSpeed;
    }
    protected void moveRight(float delta,float moveSpeed) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            RunStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.RIGHT;
        position.x += delta * moveSpeed;
    }
    protected void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }
    protected void continueJump() {
        if (jumpState == JumpState.JUMPING) {
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);
            if (jumpDuration < Constants.MAX_JUMP_DURATION) {
                velocity.y = Constants.JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }
    protected void endJump() {
        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }
    }
    protected void recoilFromEnemy(Facing direction) {
        
        jumpState = JumpState.RECOILING;
        velocity.y = Constants.KNOCKBACK_VELOCITY.y;
        
        if (direction == Facing.LEFT) {
            velocity.x = -Constants.KNOCKBACK_VELOCITY.x;
        } else {
            velocity.x = Constants.KNOCKBACK_VELOCITY.x;
        }
    }
    //abstract method as each character has different assets
    public abstract void render(SpriteBatch batch);
    
    //encapsulation was used here
    public int getHEALTH() {
        return HEALTH;
    }
    public Vector2 getPosition() {
        return position;
    }
}
