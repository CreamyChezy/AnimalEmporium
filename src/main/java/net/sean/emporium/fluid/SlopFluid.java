package net.sean.emporium.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.item.ModItems;

public abstract class SlopFluid extends FluidTemplate {
    @Override
    public Fluid getStill() {
        return ModFluids.STILL_SLOP;
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_SLOP;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.SLOP_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return ModFluids.SLOP.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 10;
    }

    @Override
    protected void onEntityCollision(World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        super.onEntityCollision(world, pos, entity, handler);

        if(entity instanceof LivingEntity living){
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1, true, true));
        }
    }

    public static class Flowing extends SlopFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        protected int getMaxFlowDistance(WorldView world) {
            return 0;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }


    }

    public abstract static class Still extends SlopFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 6;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
