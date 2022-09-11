package Virus_fighter.Tools;

import Virus_fighter.Level;
import Virus_fighter.MainUnits.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Comparator;

//The LevelLoader class loads data of the level designed used Overlaped 2d
//the designed level is read as a JSON type file
public class LevelLoader {
    
    public static Level load(String Levelname, Viewport viewport, String selectedFighter) {
        //creates a new Level that will be filled with data loaded by LevelLoader
        Level level = new Level(viewport);
        //represents a file
        FileHandle file = Gdx.files.internal("core/assets/levels/"+Levelname+".dt");
        //Parser for JSON text
        JSONParser parser = new JSONParser();
        
        JSONObject rootJsonObject;
        
        try {
            //load the level file as a JSON object which is similar to a map
            rootJsonObject = (JSONObject) parser.parse(file.reader());
            
            //load from the root object the composition of the level
            JSONObject composite = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);
            
            //load from the composite the data of all entites and units used to build the level
            //Such as (Character ,platforms ,enemies ,etc...)
            JSONArray allEntites = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);
            
            //create two arrays to separate platforms from non platforms(Character, enemies)
            JSONArray nonPlatformObjects = new JSONArray();
            JSONArray platforms =  new JSONArray();
            
            //Loop used to separate platforms from non platforms
            for (Object item : allEntites)
            {
                JSONObject Entity = (JSONObject) item;
                
                if (Entity.get(Constants.LEVEL_IMAGENAME_KEY).equals("Edgeright")
                        || Entity.get(Constants.LEVEL_IMAGENAME_KEY).equals("Edgeleft" )
                        || Entity.get(Constants.LEVEL_IMAGENAME_KEY).equals("Main")
                        || Entity.get(Constants.LEVEL_IMAGENAME_KEY).equals("Floatingright")
                        || Entity.get(Constants.LEVEL_IMAGENAME_KEY).equals("Floatingleft")
                )
                    platforms.add(Entity);
                else
                    nonPlatformObjects.add(Entity);
            }
            //load the platforms and entites to the level object created above
            loadPlatforms(platforms, level);
            loadNonPlatformEntities(level, nonPlatformObjects,selectedFighter);
            
        } catch (Exception ex) {
            Gdx.app.log("Class LevelLoader", ex.getMessage());
            Gdx.app.log("Class LevelLoader", Constants.LEVEL_ERROR_MESSAGE);
        }
        
        return level;
    }
    //This function is used to safely extract the x and y coordinates of the objects
    //From the level design file
    //To find out where they are positioned in the level
    //This function also solves a problem that may cause the app
    //To throw a Null Pointer exception
    //if an object has coordinates of (0,39), it is stored in the level file as
    // x: Null , y: 39
    //this would cause the exception so null is replaced by zero in the return statement
    private static Vector2 extractXY(JSONObject object) {
        
        Number x = (Number) object.get(Constants.LEVEL_X_KEY);
        Number y = (Number) object.get(Constants.LEVEL_Y_KEY);
        
        return new Vector2(
                (x == null) ? 0 : x.floatValue(),
                (y == null) ? 0 : y.floatValue()
        );
    }
    //Function used to load the chosen character at the location
    //Specified in the level design.
    //Enemies are not loaded in this function as their creation is based on the platforms
    //I.e, to render an enemy, we check if the platform has an enemy above it
    //the create an enemy on that platform, this is done in Method loadPlatforms
    private static void loadNonPlatformEntities(Level level, JSONArray nonPlatformObjects,String selectedFighter) {
        // a loop is used as the array contains both enemies and the character
        //We want to load the character only
        for (Object o : nonPlatformObjects) {
            JSONObject item = (JSONObject) o;
            
            final Vector2 imagePosition = extractXY(item);
            
            if (item.get(Constants.LEVEL_IMAGENAME_KEY).equals("1Idlegun")) {
                
                final Vector2 fighterPosition = imagePosition.add(Constants.FIGHTER_EYE_POSITION);
                //polymorphism was used here
                if (selectedFighter.equals("character1")) {
                    level.setFighter(new FirstFighter(fighterPosition, level));
                }
                else if(selectedFighter.equals("character2")) {
                    level.setFighter(new SecondFighter(fighterPosition, level));
                }
                else {
                    level.setFighter(new ThirdFighter(fighterPosition, level));
                }
            }
        }
    }
    
    //Used to create platforms according to their type and coordinates,
    //Specified by the level design
    private static void loadPlatforms(JSONArray array, Level level) {
        //Array stores all the platforms
        Array<Platform> platformArray = new Array<Platform>();
    
        for (Object object : array) {
            final JSONObject platformObject = (JSONObject) object;
            final Platform platform;
        
            Vector2 bottomLeft = extractXY(platformObject);
        
            final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();
            
            //platforms are created using the extracted data above and their type
            if (platformObject.get(Constants.LEVEL_IMAGENAME_KEY).equals("Edgeleft")) {
                platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height, "EdgeLeft");
                platformArray.add(platform);
            } else if (platformObject.get(Constants.LEVEL_IMAGENAME_KEY).equals("Edgeright")) {
                platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height, "EdgeRight");
                platformArray.add(platform);
            } else if (platformObject.get(Constants.LEVEL_IMAGENAME_KEY).equals("Main")) {
                platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height, "Main");
                platformArray.add(platform);
            } else if (platformObject.get(Constants.LEVEL_IMAGENAME_KEY).equals("Floatingright")) {
                platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height, "FloatingRight");
                platformArray.add(platform);
            } else {
                platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height, "FloatingLeft");
                platformArray.add(platform);
            }
            //The identifier are special tags used to identify certain objects
            //and is set by the level designer if needed
            //In this case, the identifier is used to identify
            // if the platform has an enemy on top of it, to render an enemy at these coordinates
            final String identifier = (String) platformObject.get(Constants.LEVEL_IDENTIFIER_KEY);
            
            //Identifier is used to determine which type of enemy is standing
            //on the current platform, the add an enemy to the array of enemies in class Level
            //That holds all eneimes
            //polymorphism was used here
            if (identifier != null && identifier.equals("Enemy1")) {
                final Enemy enemy = new Virus1(platform);
                level.getEnemies().add(enemy);
            } else if(identifier != null && identifier.equals("Enemy2")){
                final Enemy enemy = new Virus2(platform);
                level.getEnemies().add(enemy);
            }
            
        }
        //Libgdx renders objects from bottom to top
        //to avoid any errors or glitches, the platforms are arranged accordingly
        platformArray.sort(new Comparator<Platform>() {
            @Override
            public int compare(Platform o1, Platform o2) {
                if (o1.top < o2.top) {
                    return 1;
                } else if (o1.top > o2.top) {
                    return -1;
                }
                return 0;
            }
        });
        //After sorting, the platforms are sent to the platform array in Level Class
        level.getPlatforms().addAll(platformArray);
    }
}
