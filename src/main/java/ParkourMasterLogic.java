import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;

public final class ParkourMasterLogic {
    
    private ParkourMasterLogic() {
    }
    
    public static void init() {
        System.out.println("Initializing...");
        Camera camera = new PositionLockCamera(Player.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
        
        Game.world().setGravity(100);
      
        Game.world().onLoaded(e -> {
            Spawnpoint spawn = e.getSpawnpoint("spawn");
            System.out.println("Loading...");
            if(spawn != null) {
                System.out.println("Spawn detected");
                spawn.spawn(Player.instance());
            }
        });
    }
}
