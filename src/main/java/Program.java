import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class Program {
    
    public static void main(String[] args) {
        Game.info().setName("Parkour Master");
        Game.info().setSubTitle("");
        Game.info().setVersion("1.0");
        
        Game.init(args);
        
        Game.graphics().setBaseRenderScale(4.001f);

        Resources.load("game.litidata");
        Game.world().loadEnvironment("level1");
        Game.screens().add(new IngameScreen());
        ParkourMasterLogic.init();
        
        Game.start();
    }
    
}
