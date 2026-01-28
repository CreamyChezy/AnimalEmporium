package net.sean.emporium.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import net.sean.emporium.entity.ModEntities;
import net.sean.emporium.item.ModItems;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.UUID;

public class OpossumEntity extends TameableEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public OpossumEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        }
        else {
            --this.idleAnimationTimeout;
        }

    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 0.6f, 1.0f) : 0.0f;

        // not sure what any of the floats in this line actually do, so these must be experimented with later
        this.limbAnimator.updateLimbs(f, 1,0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getEntityWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, Ingredient.ofItems(ModItems.WORM_STICK), false));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.0));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createOpossumAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.FOLLOW_RANGE, 15.0)
                .add(EntityAttributes.TEMPT_RANGE,10.0);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.OPOSSUM.create(world, SpawnReason.BREEDING);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.WORM);
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }

    @Override
    public void setTamed(boolean tamed, boolean updateAttributes) {
        super.setTamed(tamed, updateAttributes);
        if (tamed) {
            this.setHealth(20.0F);
        }
    }

    @Override
    public boolean isTamed() {
        return super.isTamed();
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (!this.isTamed()) {
            return false;
        } else if (!(other instanceof OpossumEntity)) {
            return false;
        } else {
            OpossumEntity opossumEntity = (OpossumEntity) other;
            return opossumEntity.isTamed() && super.canBreedWith(other);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (!this.isTamed() && item == ModItems.WORM_STICK) {
            if (!player.getAbilities().creativeMode) {
                // make sure this still works without the lambda
                player.getStackInHand(hand).damage(1, player);
            }
            if (this.random.nextInt(Math.max(1, 6)) == 0) {
                this.setOwner(player);
                this.navigation.stop();
                this.setSitting(false);
                this.setTarget(null);
                this.getEntityWorld().sendEntityStatus(this, (byte) 7);
                return ActionResult.SUCCESS;
            } else {
                for (int i = 0; i < 7; ++i) {
                    this.getEntityWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
                }
            }
        }
            if (this.isOwner(player) && (item != ModItems.WORM_STICK && item != ModItems.WORM)) {
                this.setSitting(!this.isSitting());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget(null);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.FAIL;
            }
    }
}
