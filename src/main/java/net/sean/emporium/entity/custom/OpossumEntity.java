package net.sean.emporium.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.sean.emporium.entity.ModEntities;
import net.sean.emporium.entity.ai.animal.opossum.DismountParentGoal;
import net.sean.emporium.entity.ai.animal.EatFromBowlGoal;
import net.sean.emporium.entity.ai.animal.opossum.MountParentGoal;
import net.sean.emporium.entity.ai.animal.TamedPanicGoal;
import net.sean.emporium.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OpossumEntity extends TameableEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private boolean hasAttemptedInitialMount = false;
    private int mountCooldown = 0;

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

    public void setMountCooldown(int ticks){
        this.mountCooldown = ticks;
    }

    public int getMountCooldown() {
        return this.mountCooldown;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData);

        if(spawnReason == SpawnReason.NATURAL || spawnReason == SpawnReason.SPAWN_ITEM_USE || spawnReason == SpawnReason.CHUNK_GENERATION) {
            float rand = world.getRandom().nextFloat();
            // Creates 1-3 passenger babies based on rand value
            if(rand < 0.4f) {
                this.spawnRider(world);
                if(rand < 0.2f) {
                    this.spawnRider(world);
                    if (rand < 0.1f) {
                        this.spawnRider(world);
                    }
                }
            }
        }

        return entityData;
    }

    private void spawnRider(ServerWorldAccess world) {
        // Spawns baby opossum to be a passenger
        OpossumEntity rider = ModEntities.OPOSSUM.create(this.getEntityWorld(), SpawnReason.JOCKEY);
        if (rider != null && !this.isBaby()) {
            rider.setBaby(true);
            rider.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
            rider.initialize(world, world.getLocalDifficulty(this.getBlockPos()), SpawnReason.LOAD, null);
            // MUST set rider to start riding BEFORE spawning
            rider.startRiding(this);
            world.spawnEntityAndPassengers(rider);
        }
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        List<Entity> passengers = this.getPassengerList();
        int index = passengers.indexOf(passenger);
        // Sets -1 index (from chunk gen errors) back to next open index
        if (index == -1) index = passengers.size() - 1;

        double sideOffset = 0.0;
        double heightOffset = this.getHeight();

        // Changes the riding position based on if baby is passenger 1, 2, or 3
        double forwardOffset = 0;
        if (index == 1) forwardOffset = -0.35;
        else if (index == 2) forwardOffset = 0.35;

        Vec3d offsetVec = new Vec3d(forwardOffset, heightOffset, sideOffset)
                .rotateY(-this.getYaw() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));

        positionUpdater.accept(passenger, this.getX() + offsetVec.x, this.getY() + offsetVec.y, this.getZ() + offsetVec.z);
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        // Makes sure no more than 3 babies can ride one adult
        return this.getPassengerList().size() < 3;
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        // Fixes baby passengers suffocating in walls when adult jumps up blocks
        // This does make all baby passengers invincible to all suffocation, though (but not regular babies)
        if (this.isBaby() && this.getVehicle() instanceof OpossumEntity && source.isOf(DamageTypes.IN_WALL)) {
            return true;
        }
        return super.isInvulnerableTo(world, source);
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

        // Makes sure baby becomes passenger on chunk generation
        if(!this.getEntityWorld().isClient() && !hasAttemptedInitialMount && this.age == 1 && !this.isBaby()) {
            if (this.getPassengerList().isEmpty()) {
                List<OpossumEntity> notRiding = this.getEntityWorld().getEntitiesByClass(OpossumEntity.class, this.getBoundingBox().expand(1),
                        baby -> baby.isBaby() && !baby.hasVehicle());
                for (OpossumEntity baby : notRiding) {
                    baby.startRiding(this);
                    break;
                }
            }
            hasAttemptedInitialMount = true;
        }

        if (this.mountCooldown > 0) {
            this.mountCooldown--;
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new TamedPanicGoal(this, 1.5D));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(4, new TemptGoal(this, 1.0D, Ingredient.ofItems(ModItems.WORM_STICK), false));
        this.goalSelector.add(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.add(6, new EatFromBowlGoal(this, 1.0D, 10));
        this.goalSelector.add(7, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(7, new MountParentGoal(this, 1.0D));
        this.goalSelector.add(7, new DismountParentGoal(this));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
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

    @Override
    public void setTamed(boolean tamed, boolean updateAttributes) {
        super.setTamed(tamed, updateAttributes);
        if (tamed) {
            this.setHealth(20.0F);
        }
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
            if (!player.getAbilities().creativeMode) player.getStackInHand(hand).damage(1, player);
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
        else if (this.isOwner(player) && (item != ModItems.WORM_STICK && item != ModItems.WORM)) {
            this.setSitting(!this.isSitting());
            this.jumping = false;
            this.navigation.stop();
            this.setTarget(null);
            return ActionResult.SUCCESS;
        }
        else return super.interactMob(player, hand);
    }

    @Override
    protected void onGrowUp() {
        if (!this.isBaby() && this.hasVehicle()) {
            Entity vehicle = this.getVehicle();
            if (vehicle instanceof AbstractBoatEntity) {
                AbstractBoatEntity abstractBoatEntity = (AbstractBoatEntity) vehicle;
                if (!abstractBoatEntity.isSmallerThanBoat(this)) {
                    this.stopRiding();
                }
            }
            // Passenger babies fall off adult when growing up
            if (vehicle instanceof OpossumEntity) {
                this.stopRiding();
            }
        }
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        boolean damaged = super.damage(world, source, amount);

        if (damaged && !world.isClient() && this.hasPassengers()) {
            List<Entity> passengers = this.getPassengerList();

            for (Entity passenger : passengers) {
                if (passenger instanceof OpossumEntity baby && baby.isBaby()) {
                    baby.stopRiding();
                    double xDir = (this.random.nextDouble() - 0.5) * 0.4;
                    double zDir = (this.random.nextDouble() - 0.5) * 0.4;
                    baby.setVelocity(xDir, 0.1, zDir);
                    baby.velocityDirty = true;
                    baby.setMountCooldown(100);
                }
            }
        }
        return damaged;
    }
}
