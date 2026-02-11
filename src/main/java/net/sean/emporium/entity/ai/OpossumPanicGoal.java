package net.sean.emporium.entity.ai;

import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.sean.emporium.entity.custom.OpossumEntity;

public class OpossumPanicGoal extends EscapeDangerGoal {
    private final OpossumEntity opossum;


    public OpossumPanicGoal(OpossumEntity opossum, double speed) {
        super(opossum, speed);
        this.opossum = opossum;
    }

    @Override
    public boolean canStart() {
        if (this.opossum.isTamed()) return false;
        return super.canStart();
    }
}
