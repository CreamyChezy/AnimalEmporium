package net.sean.emporium.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.sean.emporium.entity.custom.OpossumEntity;

import java.util.EnumSet;
import java.util.List;

public class MountParentGoal extends Goal {
    private final OpossumEntity baby;
    private OpossumEntity parent;
    private final double speed;

    public MountParentGoal(OpossumEntity baby, double speed) {
        this.baby = baby;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!this.baby.isBaby() || this.baby.hasVehicle() || this.baby.getMountCooldown() > 0) return false;
        if (this.baby.getRandom().nextInt(1200) != 0) return false;

        List<OpossumEntity> list = this.baby.getEntityWorld().getEntitiesByClass(OpossumEntity.class,
                this.baby.getBoundingBox().expand(3.0D),
                adult -> !adult.isBaby() && adult.getPassengerList().size() < 3);

        if (!list.isEmpty()) {
            this.parent = list.getFirst();
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        if (this.parent == null) return;

        this.baby.getLookControl().lookAt(this.parent,30.0F,30.0F);

        if (this.baby.squaredDistanceTo(this.parent) < 2.0D) {
            this.baby.startRiding(this.parent);
            this.stop();
        }
        else {
            this.baby.getNavigation().startMovingTo(this.parent, this.speed);
        }
    }

    @Override
    public boolean shouldContinue() {
        return this.parent != null && this.parent.isAlive() && !this.baby.hasVehicle() && this.parent.getPassengerList().size() < 3;
    }

    @Override
    public void stop() {
        super.stop();
        this.parent = null;
        this.baby.setMountCooldown(this.baby.getRandom().nextInt(40) + 60);
    }
}
