import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.Collision;

import java.awt.geom.Rectangle2D;

@EntityInfo(width = 15, height = 22)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 7, collisionBoxHeight = 20, collision = true)
public class Player extends Creature implements IUpdateable {
    
    private static Player instance;
    private final Jump jump;
    private int consecutiveJumps = 0;
    
    public static Player instance() {
        if(instance == null) {
            instance = new Player();
        }
        
        return instance;
    }
    
    private Player() {
        super("agentPurple");
        this.addController(new PlatformingMovementController<>(this));
        this.jump = new Jump(this);
    }
    
    @Override
    public void update() {
        if(this.isTouchingGround()) {
            this.consecutiveJumps = 0;
        }
    }
    
    private boolean isTouchingGround() {
        Rectangle2D groundCheck = new Rectangle2D.Double(this.getCollisionBox().getX(), this.getCollisionBox().getY(), this.getCollisionBoxWidth(), this.getCollisionBoxHeight() + 1);
        if(groundCheck.getMaxY() > Game.physics().getBounds().getMaxY()) {
            return true;
        }
        return Game.physics().collides(groundCheck, Collision.STATIC);
    }
    
    @Action(description = "Jump")
    public void jump() {
        System.out.println(consecutiveJumps);
        if(this.consecutiveJumps > 0 || !this.jump.canCast()) {
            return;
        } 
        System.out.println("Jumped");
        this.consecutiveJumps++;
        this.jump.cast();
    }
}
