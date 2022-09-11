package Virus_fighter.Tools;

import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static final float WORLD_SIZE = 1200;
    
    public static final float KILL_PLANE = -500;
    //Path where the texture atlas is stored
    public static final String TEXTURE_ATLAS = "images/VirusFighter.pack.atlas";
    
    //Naming convention : state_weapon type_fighter number
    public static final String STANDING_GUN_1 = "1_Idle_gun";
    public static final String STANDING_MGUN_1 = "1_Idle_Mgun";
    public static final String STANDING_GUN_2 = "2_Idle_gun";
    public static final String STANDING_MGUN_2 = "2_Idle_Mgun";
    public static final String STANDING_GUN_3 = "3_Idle_gun";
    public static final String STANDING_MGUN_3 = "3_Idle_Mgun";
    
    //Naming convention : state_fighter number
    public static final String JUMPING_1 = "1_Jump";
    public static final String JUMPING_2 = "2_Jump";
    public static final String JUMPING_3 = "3_Jump";
    
    //Naming convention : state_Animation frame number_fighter number
    public static final String WALKING_A1_1 = "1_Run1";
    public static final String WALKING_A2_1 = "1_Run2";
    public static final String WALKING_A3_1 = "1_Run3";
    public static final String WALKING_A4_1 = "1_Run4";
    public static final String WALKING_A5_1 = "1_Run5";
    public static final String WALKING_A6_1 = "1_Run6";
    public static final String WALKING_A1_2 = "2_Run1";
    public static final String WALKING_A2_2 = "2_Run2";
    public static final String WALKING_A3_2 = "2_Run3";
    public static final String WALKING_A4_2 = "2_Run4";
    public static final String WALKING_A5_2 = "2_Run5";
    public static final String WALKING_A6_2 = "2_Run6";
    public static final String WALKING_A1_3 = "3_Run1";
    public static final String WALKING_A2_3 = "3_Run2";
    public static final String WALKING_A3_3 = "3_Run3";
    public static final String WALKING_A4_3 = "3_Run4";
    public static final String WALKING_A5_3 = "3_Run5";
    public static final String WALKING_A6_3 = "3_Run6";
    
    //time period to complete an animation loop, higher number = slower animations
    public static final float WALK_LOOP_DURATION = 0.1f;
    public static final float DEATH_LOOP_DURATION = 0.13f;
   
    
    public static final String PLATFORM_EDGE_RIGHT = "Edge_right";
    public static final String PLATFORM_EDGE_LEFT = "Edge_left";
    public static final String PLATFORM_FLOATING_RIGHT = "Floating_right";
    public static final String PLATFORM_FLOATING_LEFT = "Floating_left";
    public static final String PLATFORM_MAIN = "Main";
    public static final int PLATFORM_EDGE = 15;
    
    //Naming convention : state_Animation frame number_fighter number
    public static final String DEATH_A1_1 ="1_Hurt1";
    public static final String DEATH_A2_1 ="1_Hurt2";
    public static final String DEATH_A3_1 ="1_Hurt3";
    public static final String DEATH_A1_2 ="2_Hurt1";
    public static final String DEATH_A2_2 ="2_Hurt2";
    public static final String DEATH_A3_2 ="2_Hurt3";
    public static final String DEATH_A1_3 ="3_Hurt1";
    public static final String DEATH_A2_3 ="3_Hurt2";
    public static final String DEATH_A3_3 ="3_Hurt3";
    
    //Fighter attributes and characteristics
    public static final Vector2 FIGHTER_EYE_POSITION = new Vector2(71, 125);
    public static final float FIGHTER_EYE_HEIGHT = 125f;
    public static final float FIGHTER_STANCE_WIDTH = 60f;
    public static final float FIGHTER_HEIGHT = 170f;
    public static final float FIGHTER_WIDTH = 94f;
    public static final Vector2 FIGHTER_GUN_OFFSET = new Vector2(20, 47);
    public static final Vector2 FIGHTER_MGUN_OFFSET = new Vector2(8, 49);
    
    public static final Vector2 KNOCKBACK_VELOCITY = new Vector2(800, 800);
    public static final float JUMP_SPEED = 1.5f * WORLD_SIZE;
    //How long will the character remain in the air after jumping
    public static final float MAX_JUMP_DURATION = 0.1f;
    public static final float GRAVITY = WORLD_SIZE / 10;
    public static final float CHASE_CAM_MOVE_SPEED =WORLD_SIZE / 2;
    
    // Enemy 1 stats
    public static final String ENEMY_SPRITE = "Enemy1";
    public static final Vector2 ENEMY_CENTER = new Vector2(79.87f, 75.9f);
    public static final int ENEMY_HEALTH_1 = 3;
    public static final float ENEMY_SHOT_RADIUS1 = 39.93f;
    public static final float ENEMY_MOVEMENT_SPEED = 50;
    
    // Enemy 2 stats
    public static final String ENEMY_SPRITE2 = "Enemy2";
    public static final Vector2 ENEMY_CENTER2 = new Vector2(54.5f, 38.5f);
    public static final float ENEMY_MOVEMENT_SPEED2 = 40;
    public static final float ENEMY_BOB_AMPLITUDE2 = 4;
    public static final float ENEMY_BOB_PERIOD2 = 3.0f;
    public static final float ENEMY_COLLISION_RADIUS2 = 27.25f;
    public static final int ENEMY_HEALTH_2 = 5;
    public static final float ENEMY_SHOT_RADIUS2 = 27.25f;
    
    public static final float ENEMY_BOB_AMPLITUDE = 2;
    public static final float ENEMY_BOB_PERIOD = 3.0f;
    public static final float ENEMY_COLLISION_RADIUS1 = 39.93f;
    
    //Keywords used by the LevelLoader class
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_ERROR_MESSAGE = "There was a problem loading the level.";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";
    public static final String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    
    // HUD
    public static final float HUD_VIEWPORT_SIZE = 480;
    public static final float HUD_MARGIN = 20;
    public static final String FONT_FILE = "desktop/font/header.fnt";
    
    // Score based on enemy type
    public static final int ENEMY_KILL_SCORE1 = 50;
    public static final int ENEMY_KILL_SCORE2 = 75;
    
    // Explosion
    public static final String EXPLOSION_LARGE = "explosion-large";
    public static final String EXPLOSION_MEDIUM = "explosion-medium";
    public static final String EXPLOSION_SMALL = "explosion-small";
    public static final Vector2 EXPLOSION_CENTER = new Vector2(25, 25);
    public static final float EXPLOSION_DURATION = 0.5f;
    
    // Bullet
    public static final String GUN_BULLET = "GunBullet";
    public static final String MACHINE_GUN_BULLET= "MGunBullet";
    public static final float BULLET_MOVE_SPEED = 500;
}
