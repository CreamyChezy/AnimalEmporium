package net.sean.emporium.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.sean.emporium.entity.animation.ModAnimations;
import net.sean.emporium.entity.custom.OpossumEntity;

public class OpossumModel<T extends OpossumEntity> extends SinglePartEntityModel<T> {
    private final ModelPart opossum;
    private final ModelPart head;

    public OpossumModel(ModelPart root) {
       this.opossum = root;
       this.head = opossum.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData bone = body.addChild("bone", ModelPartBuilder.create().uv(21, 2).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, -2.0F));

        ModelPartData bone2 = body.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, 1.0F));

        ModelPartData backlegleft = body.addChild("backlegleft", ModelPartBuilder.create().uv(6, 18).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(6, 21).cuboid(-1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, -2.0F));

        ModelPartData backlegright = body.addChild("backlegright", ModelPartBuilder.create().uv(0, 3).cuboid(-1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(17, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 1.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 10).cuboid(0.0F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(12, 19).cuboid(1.0F, -3.0F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(1.0F, -3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(19, 10).cuboid(3.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 20.0F, -1.0F));

        ModelPartData muzzelr_r1 = head.addChild("muzzelr_r1", ModelPartBuilder.create().uv(0, 16).cuboid(5.5835F, -3.0F, 1.7064F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 2.0F, 1.0F, 0.0F, 0.3054F, 0.0F));

        ModelPartData muzzlel_r1 = head.addChild("muzzlel_r1", ModelPartBuilder.create().uv(11, 15).cuboid(5.2827F, -3.0F, -3.6601F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 2.0F, 1.0F, 0.0F, -0.3054F, 0.0F));

        ModelPartData muzzleup_r1 = head.addChild("muzzleup_r1", ModelPartBuilder.create().uv(12, 12).cuboid(4.6813F, -5.5675F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.3054F));

        ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(16, 19).cuboid(-3.0F, 1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(21, 0).cuboid(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(9, 10).cuboid(-6.0F, 2.0F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(19, 14).cuboid(-7.0F, 1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 19.0F, -1.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(OpossumEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.OPOSSUM_WALK, limbSwing, limbSwingAmount, 2f, 3f);
        //this.updateAnimation(entity.idleAnimationState, ModAnimations.OPOSSUM_IDLE, ageInTicks, 1f);

    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        opossum.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return opossum;
    }
}