package net.sean.emporium.entity.ai.animal;

import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.passive.TameableEntity;

public class TamedPanicGoal extends EscapeDangerGoal {
    private final TameableEntity entity;


    public TamedPanicGoal(TameableEntity entity, double speed) {
        super(entity, speed);
        this.entity = entity;
    }

    @Override
    public boolean canStart() {
        if (this.entity.isTamed()) return false;
        return super.canStart();
    }
}
