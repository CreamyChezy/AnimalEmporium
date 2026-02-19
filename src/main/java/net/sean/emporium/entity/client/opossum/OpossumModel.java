package net.sean.emporium.entity.client.opossum;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.animation.OpossumAnimations;
import net.sean.emporium.entity.client.EmporiumModel;

public class OpossumModel extends EntityModel<OpossumRenderState> implements EmporiumModel {
    public static final EntityModelLayer OPOSSUM = new EntityModelLayer(Identifier.of(AnimalEmporium.MOD_ID,"opossum"),"main");
    private final ModelPart opossum;
    private final ModelPart head;

    private final Animation walkingAnimation;
    //private final Animation idlingAnimation;


    public OpossumModel(ModelPart root) {
       super(root);
       this.opossum = root;
       this.head = opossum.getChild("head");

       this.walkingAnimation = OpossumAnimations.OPOSSUM_WALK.createAnimation(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 5.0F, new Dilation(0.0F)), EmporiumModel.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create().uv(21, 2).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(4.0F, 0.0F, -2.0F));

        ModelPartData bone2 = body.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(4.0F, 0.0F, 1.0F));

        ModelPartData backlegleft = body.addChild("backlegleft", ModelPartBuilder.create().uv(6, 18).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(6, 21).cuboid(-1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(-3.0F, 0.0F, -2.0F));

        ModelPartData backlegright = body.addChild("backlegright", ModelPartBuilder.create().uv(0, 3).cuboid(-1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(17, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), EmporiumModel.pivot(-3.0F, 0.0F, 1.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 10).cuboid(0.0F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(12, 19).cuboid(1.0F, -3.0F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(1.0F, -3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(19, 10).cuboid(3.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(4.0F, 20.0F, -1.0F));

        ModelPartData muzzelr_r1 = head.addChild("muzzelr_r1", ModelPartBuilder.create().uv(0, 16).cuboid(5.5835F, -3.0F, 1.7064F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 2.0F, 1.0F, 0.0F, 0.3054F, 0.0F));

        ModelPartData muzzlel_r1 = head.addChild("muzzlel_r1", ModelPartBuilder.create().uv(11, 15).cuboid(5.2827F, -3.0F, -3.6601F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 2.0F, 1.0F, 0.0F, -0.3054F, 0.0F));

        ModelPartData muzzleup_r1 = head.addChild("muzzleup_r1", ModelPartBuilder.create().uv(12, 12).cuboid(4.6813F, -5.5675F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.3054F));

        ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(16, 19).cuboid(-3.0F, 1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(21, 0).cuboid(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(9, 10).cuboid(-6.0F, 2.0F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(19, 14).cuboid(-7.0F, 1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), EmporiumModel.pivot(-4.0F, 19.0F, -1.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(OpossumRenderState state) {
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