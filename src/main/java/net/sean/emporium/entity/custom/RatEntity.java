package net.sean.emporium.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.sean.emporium.entity.ModEntities;
import net.sean.emporium.entity.ai.animal.TamedPanicGoal;
import net.sean.emporium.item.ModItems;
import org.jspecify.annotations.Nullable;

public class RatEntity extends TameableShoulderEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(RatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int idleAnimationTimeout = 0;


    public RatEntity(EntityType<? extends TameableShoulderEntity> entityType, World world) {
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
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.CHEESE_SLICE);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        RatEntity baby = ModEntities.RAT.create(world, SpawnReason.BREEDING);
        if (baby != null && entity instanceof RatEntity parent) {
            baby.setVariant(this.random.nextBoolean() ? this.getVariant() : parent.getVariant());
        }
        return baby;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new TamedPanicGoal(this, 1.5D));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(4, new TemptGoal(this, 1.0D, Ingredient.ofItems(ModItems.CHEESE_SLICE), false));
        this.goalSelector.add(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.add(6, new SitOnOwnerShoulderGoal(this));
        this.goalSelector.add(7, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createRatAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.FOLLOW_RANGE, 15.0)
                .add(EntityAttributes.TEMPT_RANGE,10.0);
    }

    @Override
    public void setTamed(boolean tamed, boolean updateAttributes) {
        super.setTamed(tamed, updateAttributes);
        if (tamed) {
            this.setHealth(10.0F);
        }
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (!this.isTamed()) {
            return false;
        } else if (!(other instanceof RatEntity)) {
            return false;
        } else {
            RatEntity ratEntity = (RatEntity) other;
            return ratEntity.isTamed() && super.canBreedWith(other);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (!this.isTamed() && item == ModItems.CHEESE_SLICE) {
            if (!player.getAbilities().creativeMode) {
                player.getStackInHand(hand).decrement(1);
            }
            if (this.random.nextInt(6) == 0) {
                this.setOwner(player);
                this.navigation.stop();
                this.setSitting(false);
                this.setTarget(null);
                this.getEntityWorld().sendEntityStatus(this, (byte) 7);
                this.setTamed(true,true);
            } else {
                for (int i = 0; i < 7; ++i) {
                    this.getEntityWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
                }
            }
            return ActionResult.SUCCESS;
        }
        else if (this.isOwner(player) && item != ModItems.CHEESE_SLICE) {
            this.setSitting(!this.isSitting());
            this.jumping = false;
            this.navigation.stop();
            this.setTarget(null);
            return ActionResult.SUCCESS;
        }
        else return super.interactMob(player, hand);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    public RatVariants getVariant() {
        return RatVariants.byId(this.dataTracker.get(VARIANT));
    }

    public void setVariant(RatVariants variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData) {
        this.setVariant(RatVariants.byId(this.random.nextInt(RatVariants.values().length)));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        if (view.contains("Variant")) {
            this.setVariant(RatVariants.byId(view.getInt("Variant", 0)));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getEntityWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 0.6f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 1,0.2f);
    }
}
