package Virus_fighter.Tools;
//Contains enumerations used to determine the state and the direction the character is facing
public class Enums {
    public enum Facing {
        LEFT, RIGHT
    }
    
    public enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED,
        RECOILING
    }
    
    public enum WalkState {
        STANDING,
        WALKING
    }
   
    
}
