import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.abilities.effects.EffectApplication;
import de.gurkenlabs.litiengine.abilities.effects.EffectTarget;
import de.gurkenlabs.litiengine.abilities.effects.ForceEffect;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityPivotType;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.physics.Force;
import de.gurkenlabs.litiengine.physics.GravityForce;

import java.util.Optional;

@AbilityInfo(cooldown = 250, origin = EntityPivotType.COLLISIONBOX_CENTER, duration = 350, value = 175)
public class Jump extends Ability {
    
    public Jump(Creature executor) {
        super(executor);
        this.addEffect(new JumpEffect(this));
    }
    
    private class JumpEffect extends ForceEffect {
        
        protected JumpEffect(Ability ability) {
            super(ability, ability.getAttributes().value().get(), EffectTarget.EXECUTINGENTITY);
        }
        
        @Override
        protected Force applyForce(IMobileEntity affectedEntity) {
            GravityForce force = new GravityForce(affectedEntity, this.getStrength(), Direction.UP);
            affectedEntity.movement().apply(force);
            return force;
        }
        
        @Override
        protected boolean hasEnded(final EffectApplication appliance) {
            return super.hasEnded(appliance) || this.isTouchingCeiling();
        }
        
        // If is touching ceiling return true
        private boolean isTouchingCeiling() {
            Optional<CollisionBox> opt = Game.world().environment().getCollisionBoxes().stream().filter(x -> x.getBoundingBox().intersects(this.getAbility().getExecutor().getBoundingBox())).findFirst();
            if(opt.isEmpty()) return false;

            CollisionBox box = opt.get();
            return box.getCollisionBox().getMaxY() <= this.getAbility().getExecutor().getCollisionBox().getMinY();
        }
    }
}
