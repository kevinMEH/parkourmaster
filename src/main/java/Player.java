import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

@EntityInfo(width = 15, height = 22)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 7, collisionBoxHeight = 20, collision = true)
public class Player extends Creature implements IUpdateable {
    private static Player instance;
    
    public static Player instance() {
        if(instance == null) {
            instance = new Player();
        }
        
        return instance;
    }
    
    private Player() {
        super("agentPurple");
        this.addController(new PlatformingMovementController<>(this));
    }
    
    @Override
    public void update() {
        
    }
}
