package Virus_fighter.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

//Class Assets iis responsible for managing,loading and storing all the assets
//that will be used during the gameplay.
//Contains other public classes responsible for a group of related assets
public class Assets implements Disposable, AssetErrorListener {
    
    public static final Assets instance = new Assets();
    public Fighter_1_Assets fighter_1_assets;
    public Fighter_2_Assets fighter_2_assets;
    public Fighter_3_Assets fighter_3_assets;
    public EnemyAssets enemyAssets;
    public EnemyAssets2 enemyAssets2;
    public BulletAssets1 bullet_asset1;
    public BulletAssets2 bullet_assets2;
    public ExplosionAssets explosionAssets;
    public PlatformAssets platformAssets;
    private AssetManager assetManager;
    
    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        //All the assets are loaded in "one pack" as a single image
        //Each asset is a "region" inside that image.
        //the pack has an "atlas" that can be described to be a map that holds
        //The location"(regions)" of each asset in the pack
        //The asset manager loads the atlas, so the pack can be navigated
        //To retrieve the needed asset
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        
        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        fighter_1_assets = new Fighter_1_Assets(atlas);
        fighter_2_assets = new Fighter_2_Assets(atlas);
        fighter_3_assets = new Fighter_3_Assets(atlas);
        platformAssets = new PlatformAssets(atlas);
        enemyAssets = new EnemyAssets(atlas);
        enemyAssets2 = new EnemyAssets2(atlas);
        bullet_asset1 = new BulletAssets1(atlas);
        bullet_assets2 = new BulletAssets2(atlas);
        explosionAssets = new ExplosionAssets(atlas);
    }
    
    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error("Class Assets", "Couldn't load asset: " + asset.fileName, throwable);
    }
    
    @Override
    public void dispose() {
        assetManager.dispose();
    }
    
    //Class that holds All Fighter 1 assets
    //this is repeated 2 more times for each fighter as each has different assets.
    public class Fighter_1_Assets
    {
        public final TextureAtlas.AtlasRegion idle_gun;
        public final TextureAtlas.AtlasRegion idle_mgun;
    
        public final TextureAtlas.AtlasRegion jumping;
    
        public final Animation Death_Animation;
        public final Animation run_Animation;
        
        public Fighter_1_Assets(TextureAtlas atlas)
        {
            //The atlas here is used to find a certain asset
           idle_gun=atlas.findRegion(Constants.STANDING_GUN_1);
           idle_mgun=atlas.findRegion(Constants.STANDING_MGUN_1);
           jumping=atlas.findRegion(Constants.JUMPING_1);
           
            //Animations are created by loading all the frames in an array,
            Array<TextureAtlas.AtlasRegion> Run_Frames=new Array<TextureAtlas.AtlasRegion>();
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A1_1));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A2_1));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A3_1));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A4_1));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A5_1));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A6_1));
            //The array of frames is used to create the animation
            run_Animation= new Animation(Constants.WALK_LOOP_DURATION,Run_Frames, Animation.PlayMode.LOOP);
    
            Array<TextureAtlas.AtlasRegion> Death_Frames=new Array<TextureAtlas.AtlasRegion>();
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A1_1));
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A2_1));
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A3_1));
            Death_Animation = new Animation(Constants.DEATH_LOOP_DURATION,Death_Frames, Animation.PlayMode.NORMAL);
            
        }
    }
    public class Fighter_2_Assets
    {
        public final TextureAtlas.AtlasRegion idle_gun;
        public final TextureAtlas.AtlasRegion idle_mgun;
    
        public final TextureAtlas.AtlasRegion jumping;
    
        public final Animation Death_Animation;
        public final Animation run_Animation;
        
        public Fighter_2_Assets(TextureAtlas atlas)
        {
            idle_gun=atlas.findRegion(Constants.STANDING_GUN_2);
            idle_mgun=atlas.findRegion(Constants.STANDING_MGUN_2);
            jumping=atlas.findRegion(Constants.JUMPING_2);
    
            Array<TextureAtlas.AtlasRegion> Run_Frames=new Array<TextureAtlas.AtlasRegion>();
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A1_2));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A2_2));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A3_2));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A4_2));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A5_2));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A6_2));
            run_Animation= new Animation(Constants.WALK_LOOP_DURATION,Run_Frames, Animation.PlayMode.LOOP);
    
            Array<TextureAtlas.AtlasRegion> Death_Frames=new Array<TextureAtlas.AtlasRegion>();
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A1_2));
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A2_2));
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A3_2));
            Death_Animation = new Animation(Constants.DEATH_LOOP_DURATION,Death_Frames, Animation.PlayMode.NORMAL);
        }
    }
    public class Fighter_3_Assets
    {
        public final TextureAtlas.AtlasRegion idle_gun;
        public final TextureAtlas.AtlasRegion idle_mgun;
    
        public final TextureAtlas.AtlasRegion jumping;
    
        public final Animation Death_Animation;
        public final Animation run_Animation;
        
        public Fighter_3_Assets(TextureAtlas atlas)
        {
            idle_gun=atlas.findRegion(Constants.STANDING_GUN_3);
            idle_mgun=atlas.findRegion(Constants.STANDING_MGUN_3);
            jumping=atlas.findRegion(Constants.JUMPING_3);
    
            Array<TextureAtlas.AtlasRegion> Run_Frames=new Array<TextureAtlas.AtlasRegion>();
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A1_3));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A2_3));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A3_3));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A4_3));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A5_3));
            Run_Frames.add(atlas.findRegion(Constants.WALKING_A6_3));
            run_Animation= new Animation(Constants.WALK_LOOP_DURATION,Run_Frames, Animation.PlayMode.LOOP);
    
            Array<TextureAtlas.AtlasRegion> Death_Frames=new Array<TextureAtlas.AtlasRegion>();
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A1_3));
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A2_3));
            Death_Frames.add(atlas.findRegion(Constants.DEATH_A3_3));
            Death_Animation = new Animation(Constants.DEATH_LOOP_DURATION,Death_Frames, Animation.PlayMode.NORMAL);
            
        }
    }
    //Class that holds All Platform assets
    public class PlatformAssets {
        
        public NinePatch platformNinePatch;
        private TextureAtlas atlas;
        public PlatformAssets(TextureAtlas atlas) {
            this.atlas = atlas;
            
        }
        //The following methods used to draw different types of platforms
         public NinePatch drawMain() {
             TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_MAIN);
             int edge = Constants.PLATFORM_EDGE;
             platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
             return platformNinePatch;
         }
         public NinePatch drawRightEdge() {
             TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_EDGE_RIGHT);
             int edge = Constants.PLATFORM_EDGE;
             platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
             return platformNinePatch;
         }
         public NinePatch drawLeftEdge() {
            TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_EDGE_LEFT);
            int edge = Constants.PLATFORM_EDGE;
            platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
             return platformNinePatch;
        }
        public NinePatch drawFloatingLeft() {
            TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_FLOATING_LEFT);
            int edge = Constants.PLATFORM_EDGE;
            platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
            return platformNinePatch;
        }
        public NinePatch drawFloatingRight() {
            TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_FLOATING_RIGHT);
            int edge = Constants.PLATFORM_EDGE;
            platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
            return platformNinePatch;
        }
    }
    //Class that holds All the first enemy type assets
    public class EnemyAssets
    {
        // Add AtlasRegion for the enemy
        public final TextureAtlas.AtlasRegion enemy;
 
        public EnemyAssets(TextureAtlas atlas)
        {
            // Save the enemy sprite
            enemy = atlas.findRegion(Constants.ENEMY_SPRITE);
        }
    }
    public class EnemyAssets2 {

        public final TextureAtlas.AtlasRegion enemy2;

        public EnemyAssets2(TextureAtlas atlas)
        {
            enemy2 = atlas.findRegion(Constants.ENEMY_SPRITE2);
        }
    }
    
    // BulletAssets class
    public class BulletAssets1 {
        public final TextureAtlas.AtlasRegion GunBullet;
        
        public BulletAssets1(TextureAtlas atlas)
        {
            GunBullet = atlas.findRegion(Constants.GUN_BULLET);
        }
    }
    public class BulletAssets2
    {
        public final TextureAtlas.AtlasRegion MGunBullet;
        
        public BulletAssets2(TextureAtlas atlas) {
            MGunBullet = atlas.findRegion(Constants.MACHINE_GUN_BULLET);
        }
        
    }
    
    // Explosion asset class
    public class ExplosionAssets {
        
        public final Animation explosion;
        
        public ExplosionAssets(TextureAtlas atlas) {
            
            Array<TextureAtlas.AtlasRegion> explosionRegions = new Array<TextureAtlas.AtlasRegion>();
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_LARGE));
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_MEDIUM));
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_SMALL));
            
            explosion = new Animation(Constants.EXPLOSION_DURATION / explosionRegions.size,
                    explosionRegions, Animation.PlayMode.NORMAL);
        }
    }
}