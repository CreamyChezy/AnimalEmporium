package net.sean.emporium.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.sean.emporium.entity.custom.OpossumEntity;

import java.util.EnumSet;

public class DismountParentGoal extends Goal {
    private final OpossumEntity baby;

    public DismountParentGoal(OpossumEntity baby) {
        this.baby = baby;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.baby.isBaby() || !this.baby.hasVehicle()) return false;
        if (this.baby.getRandom().nextInt(1200) != 0) return false;

        Entity vehicle = baby.getVehicle();
        return vehicle instanceof OpossumEntity;
    }

    @Override
    public void start() {
        this.baby.stopRiding();
        this.baby.addVelocity(
                (this.baby.getRandom().nextDouble() - 0.5) * 0.2,
                0.2,
                (this.baby.getRandom().nextDouble() - 0.5) * 0.2
        );

        this.baby.velocityDirty = true;
        this.baby.setMountCooldown(60);
    }
}
