package net.sean.emporium.entity.client.rat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.animation.RatAnimations;
import net.sean.emporium.entity.client.EmporiumModel;

public class RatModel extends EntityModel<RatRenderState> implements EmporiumModel {
    public static final EntityModelLayer RAT = new EntityModelLayer(Identifier.of(AnimalEmporium.MOD_ID, "rat"), "main");
    private final ModelPart rat;
    private final ModelPart head;
    private final Animation walkingAnimation;

    public RatModel(ModelPart root) {
        super(root);
        this.rat = root.getChild("Rat");
        this.head = rat.getChild("BodyBottom").getChild("Chest").getChild("Head");

        this.walkingAnimation = RatAnimations.RAT_WALK.createAnimation(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Rat = modelPartData.addChild("Rat", ModelPartBuilder.create(), EmporiumModel.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData BodyBottom = Rat.addChild("BodyBottom", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -4.0F, 6.0F, 4.0F, 5.0F, new Dilation(0.0F))
                .uv(6, 21).cuboid(-3.0F, -4.0F, -4.0F, 6.0F, 4.0F, 7.0F, new Dilation(0.25F))
                .uv(18, 9).cuboid(-2.5F, -3.5F, 1.0F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, -2.0F, 2.0F));

        ModelPartData Chest = BodyBottom.addChild("Chest", ModelPartBuilder.create().uv(0, 9).cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 13).cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.25F)), EmporiumModel.pivot(0.0F, -4.0F, -4.0F));

        ModelPartData Head = Chest.addChild("Head", ModelPartBuilder.create().uv(0, 17).cuboid(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.25F)), EmporiumModel.pivot(0.0F, 2.0F, -4.0F));

        ModelPartData Nose = Head.addChild("Nose", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hat = Head.addChild("hat", ModelPartBuilder.create().uv(-6, 21).cuboid(-3.0F, -1.5F, -5.0F, 6.0F, 0.0F, 6.0F, new Dilation(0.0F))
                .uv(16, 6).cuboid(-2.0F, -3.5F, -4.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 0).cuboid(-2.0F, -5.5F, -4.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-1.0F, -4.5F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(-1.0F, -5.5F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 4).cuboid(-1.0F, -6.5F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 0).cuboid(-0.5F, -8.5F, -2.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Ears = Head.addChild("Ears", ModelPartBuilder.create(), EmporiumModel.pivot(0.0F, -1.5F, -1.0F));

        ModelPartData LeftEar = Ears.addChild("LeftEar", ModelPartBuilder.create(), EmporiumModel.pivot(-1.5F, 0.0F, 0.0F));

        ModelPartData LeftEar_r1 = LeftEar.addChild("LeftEar_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData RightEar = Ears.addChild("RightEar", ModelPartBuilder.create(), EmporiumModel.pivot(-1.5F, 0.0F, 0.0F));

        ModelPartData RightEar_r1 = RightEar.addChild("RightEar_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData whiskers = Head.addChild("whiskers", ModelPartBuilder.create().uv(17, 0).cuboid(-3.5F, -1.5F, 0.0F, 7.0F, 3.0F, 0.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 0.0F, -4.0F));

        ModelPartData FrontLegs = Chest.addChild("FrontLegs", ModelPartBuilder.create(), EmporiumModel.pivot(0.0F, 3.5F, -1.5F));

        ModelPartData FrontLeftLeg = FrontLegs.addChild("FrontLeftLeg", ModelPartBuilder.create().uv(22, 3).cuboid(0.0F, -0.5F, -1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), EmporiumModel.pivot(2.0F, 0.0F, 0.0F));

        ModelPartData FrontLeftFoot = FrontLeftLeg.addChild("FrontLeftFoot", ModelPartBuilder.create().uv(10, 18).cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.5F, 1.5F, -1.0F));

        ModelPartData FrontRightLeg = FrontLegs.addChild("FrontRightLeg", ModelPartBuilder.create().uv(22, 3).cuboid(-1.0F, -0.5F, -1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), EmporiumModel.pivot(-2.0F, 0.0F, 0.0F));

        ModelPartData FrontRightFoot = FrontRightLeg.addChild("FrontRightFoot", ModelPartBuilder.create().uv(10, 18).cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), EmporiumModel.pivot(-0.5F, 1.5F, -1.0F));

        ModelPartData Tail = BodyBottom.addChild("Tail", ModelPartBuilder.create().uv(21, 17).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, -2.0F, 2.0F));

        ModelPartData tail2 = Tail.addChild("tail2", ModelPartBuilder.create().uv(10, 11).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 0.0F, 3.0F));

        ModelPartData BackLegs = BodyBottom.addChild("BackLegs", ModelPartBuilder.create(), EmporiumModel.pivot(0.0F, -1.0F, -1.0F));

        ModelPartData BackLeftLeg = BackLegs.addChild("BackLeftLeg", ModelPartBuilder.create().uv(14, 18).cuboid(3.0F, -0.5F, -1.5F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, -0.5F, 0.5F));

        ModelPartData BackLeftFoot = BackLeftLeg.addChild("BackLeftFoot", ModelPartBuilder.create().uv(18, 13).cuboid(3.0F, -2.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 4.5F, -1.5F));

        ModelPartData BackRightLeg = BackLegs.addChild("BackRightLeg", ModelPartBuilder.create().uv(14, 18).cuboid(-4.0F, -5.0F, 0.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 4.0F, -1.0F));

        ModelPartData BackRightFoot = BackRightLeg.addChild("BackRightFoot", ModelPartBuilder.create().uv(18, 13).cuboid(-4.0F, -2.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(RatRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.relativeHeadYaw, state.pitch);

        this.walkingAnimation.applyWalking(state.limbSwingAnimationProgress, state.limbSwingAmplitude,2f,3f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

}
