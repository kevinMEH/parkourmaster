import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

@EntityInfo(width = 15, height = 22)
@MovementInfo(velocity = 60)
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
    
    Point2D previousLocation = this.getLocation();
    
    @Override
    public void update() {
        if(this.isTouchingGround()) {
            this.consecutiveJumps = 0;
        }
//        if(this.getLocation().equals(previousLocation)) animations().
        if(Math.abs(this.getVelocity().get() - 0.0f) < 0.01) System.out.println("Idle");
    }
    
    private boolean isTouchingGround() {
        Rectangle2D groundCheck = new Rectangle2D.Double(this.getCollisionBox().getX(), this.getCollisionBox().getY(), this.getCollisionBoxWidth(), this.getCollisionBoxHeight() + 1);
        Optional<CollisionBox> boxesIntersect = Game.world().environment().getCollisionBoxes().stream().filter(x -> x.getBoundingBox().intersects(groundCheck)).findFirst();
        return boxesIntersect.isPresent();
    }
    
    @Action(description = "Jump")
    public void jump() {
        if(this.consecutiveJumps > 0 || !this.jump.canCast()) {
            return;
        } 
        this.consecutiveJumps++;
        this.jump.cast();
    }
}
