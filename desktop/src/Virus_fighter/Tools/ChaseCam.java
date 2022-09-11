package Virus_fighter.Tools;

import Virus_fighter.MainUnits.Fighter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
    
    //The camera that focuses on a part of the world
    //The world is rendered in the area focused on by the camera
    //The cam has 2 modes, either follow the player, or move freely
    //To switch between modes press "Space bar key" once
    public class ChaseCam {
        public Camera camera;
        public Fighter target;
        private Boolean following;
        
        public ChaseCam(Camera camera, Fighter target) {
            following = true;
            this.camera=camera;
            this.target=target;
        }
        
        
        public void update(float delta) {
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                following = !following;
            }
            
            if (following) {
                camera.position.x = target.getPosition().x + 390;
                camera.position.y = target.getPosition().y + 160;
            } else {
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    camera.position.x -= delta * Constants.CHASE_CAM_MOVE_SPEED;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    camera.position.x += delta * Constants.CHASE_CAM_MOVE_SPEED;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    camera.position.y += delta * Constants.CHASE_CAM_MOVE_SPEED;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    camera.position.y -= delta * Constants.CHASE_CAM_MOVE_SPEED;
                }
            }
        }
    }
