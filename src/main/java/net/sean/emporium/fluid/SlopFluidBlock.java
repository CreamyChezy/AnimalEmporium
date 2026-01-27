package net.sean.emporium.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class SlopFluidBlock extends FluidBlock {
    public SlopFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    // not needed anymore since checking for entity in fluid moved to classes that extend FlowableFluid
    private boolean isEntityInFluid(LivingEntity entity, World world, BlockPos pos){
        VoxelShape blockShape = VoxelShapes.cuboid(
                pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0
        );
        return blockShape.getBoundingBox().intersects(entity.getBoundingBox());
    }
}
