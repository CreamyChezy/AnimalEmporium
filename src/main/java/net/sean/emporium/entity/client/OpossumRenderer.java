package net.sean.emporium.entity.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.custom.OpossumEntity;

public class OpossumRenderer extends MobEntityRenderer<OpossumEntity, OpossumRenderState, OpossumModel> {

    private static final Identifier TEXTURE = Identifier.of(AnimalEmporium.MOD_ID, "textures/entity/opossum.png");

    public OpossumRenderer(EntityRendererFactory.Context context) {
        super(context, new OpossumModel(context.getPart(OpossumModel.OPOSSUM)), 0.425f);
    }

    @Override
    public OpossumRenderState createRenderState() {
        return new OpossumRenderState();
    }

    @Override
    public void render(OpossumRenderState state, MatrixStack matrixStack,
                       OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {

        matrixStack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(90));

        if(state.baby) {
            matrixStack.scale(0.7f,0.7f,0.7f);
        }
        else{
            matrixStack.scale(1.5f,1.5f,1.5f);
        }

        super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    @Override
    public Identifier getTexture(OpossumRenderState state) {
        return TEXTURE;
    }

    @Override
    public void updateRenderState(OpossumEntity livingEntity, OpossumRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        //livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);

        // Tracks death animation progress
        livingEntityRenderState.deathProgress = livingEntity.deathTime > 0
                ? MathHelper.clamp(((float) livingEntity.deathTime + f - 1.0F) / 20.0F * 1.6F, 0.0F, 1.0F)
                : 0.0F;
    }

    @Override
    protected void setupTransforms(OpossumRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
        super.setupTransforms(state, matrices, bodyYaw, baseHeight);

        // Manual fix for death animation due to opossum model being rotated 90 degrees
        if (state.deathProgress > 0.0F) {
            float angle = MathHelper.sqrt(state.deathProgress) * 90.0F;
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-angle));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(angle));
        }
    }
}
