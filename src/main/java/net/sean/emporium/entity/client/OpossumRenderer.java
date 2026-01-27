package net.sean.emporium.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.custom.OpossumEntity;

public class OpossumRenderer extends MobEntityRenderer<OpossumEntity, OpossumRenderState, OpossumModel> {

    private static final Identifier TEXTURE = Identifier.of(AnimalEmporium.MOD_ID, "textures/entity/opossum.png");

    public OpossumRenderer(EntityRendererFactory.Context context) {
        super(context, new OpossumModel(context.getPart(ModModelLayers.OPOSSUM)), 0.425f);
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
            matrixStack.scale(0.8f,0.8f,0.8f);
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
}
