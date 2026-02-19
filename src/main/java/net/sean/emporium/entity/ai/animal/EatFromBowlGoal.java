package net.sean.emporium.entity.ai.animal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.block.custom.PetBowlBlock;

public class EatFromBowlGoal extends MoveToTargetPosGoal {
    private final AnimalEntity entity;

    public EatFromBowlGoal(AnimalEntity entity, double speed, int range) {
        super(entity, speed, range);
        this.entity = entity;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isOf(ModBlocks.PET_BOWL) && state.get(PetBowlBlock.HAS_FOOD);
    }

    @Override
    public boolean canStart() {
        if (this.entity.hasVehicle() || this.entity.getHealth() >= this.entity.getMaxHealth()) return false;
        return super.canStart();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.hasReached()) {
            World world = this.entity.getEntityWorld();
            BlockPos targetPos = this.targetPos;
            BlockState state = world.getBlockState(targetPos);

            if (state.isOf(ModBlocks.PET_BOWL) && this.entity.getHealth() < this.entity.getMaxHealth()) {
                world.setBlockState(targetPos, state.with(PetBowlBlock.HAS_FOOD, false));
                this.entity.setHealth(this.entity.getMaxHealth());
                world.playSound(null, targetPos.getX(), targetPos.getY(), targetPos.getZ(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.MASTER, 1,1);
                this.stop();
            }
        }
    }

    @Override
    public double getDesiredDistanceToTarget() {
        return 2.0D;
    }
}
