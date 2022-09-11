package Virus_fighter.MainUnits;

import Virus_fighter.Tools.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform {
    public final float top;
    public final float bottom;
    public final float left;
    public final float right;
    private String type;
    
    public Platform(float left, float top, float width, float height,String type) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.type=type;
    }
    
    public void render(SpriteBatch batch) {
        float width = right - left;
        float height = top - bottom;
        
        if (type.equals("EdgeRight"))
            Assets.instance.platformAssets.drawRightEdge().draw(batch, left , bottom , width , height);
        else if(type.equals("EdgeLeft"))
            Assets.instance.platformAssets.drawLeftEdge().draw(batch, left , bottom , width , height );
        else if (type.equals("Main"))
            Assets.instance.platformAssets.drawMain().draw(batch, left , bottom , width, height);
        else if(type.equals("FloatingRight"))
            Assets.instance.platformAssets.drawFloatingRight().draw(batch, left , bottom , width, height);
        else if(type.equals("FloatingLeft"))
            Assets.instance.platformAssets.drawFloatingLeft().draw(batch, left, bottom, width, height);
    }
    
}
