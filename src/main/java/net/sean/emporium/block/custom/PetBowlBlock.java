package net.sean.emporium.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sean.emporium.block.entity.ModBlockEntities;
import net.sean.emporium.block.entity.PetBowlBlockEntity;
import net.sean.emporium.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class PetBowlBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final BooleanProperty HAS_FOOD = BooleanProperty.of("has_food");

    public PetBowlBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(HAS_FOOD, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(3, 0, 3, 13, 3, 13);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(3, 0, 3, 13, 3, 13);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PetBowlBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_FOOD);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getStackInHand(hand);
        boolean hasFood = state.get(HAS_FOOD);

        // Add worm to bowl
        if (!hasFood && heldStack.getItem() == ModItems.WORM) {

            if (!player.getAbilities().creativeMode) {
                heldStack.decrement(1);
            }


            world.setBlockState(pos, state.with(PetBowlBlock.HAS_FOOD, true), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }

        // Take worm from bowl
        if (heldStack.isEmpty() && hasFood) {
            world.setBlockState(pos, state.with(HAS_FOOD, false), Block.NOTIFY_ALL);
            ItemStack worm = new ItemStack(ModItems.WORM);

            if (!player.getInventory().insertStack(worm)) {
                // Lets worm drop on ground if inventory is full, others can also pick up (no ownership)
                player.dropItem(worm, false);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.PET_BOWL_BE,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
    }
}