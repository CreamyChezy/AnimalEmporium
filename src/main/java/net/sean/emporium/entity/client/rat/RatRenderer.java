package net.sean.emporium.entity.client.rat;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.custom.RatEntity;

public class RatRenderer extends MobEntityRenderer<RatEntity, RatRenderState, RatModel> {

    public RatRenderer(EntityRendererFactory.Context context) {
        super(context, new RatModel(context.getPart(RatModel.RAT)), 0.2f);
    }

    @Override
    public Identifier getTexture(RatRenderState state) {
        return Identifier.of(AnimalEmporium.MOD_ID, "textures/entity/rat/" + state.variant.getColor() + ".png");
    }

    @Override
    public RatRenderState createRenderState() {
        return new RatRenderState();
    }

    @Override
    public void updateRenderState(RatEntity entity, RatRenderState state, float f) {
        super.updateRenderState(entity, state, f);
        state.variant = entity.getVariant();
    }

    @Override
    public void render(RatRenderState state, MatrixStack matrixStack,
                       OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {

        if(state.baby) {
            matrixStack.scale(0.5f,0.5f,0.5f);
        }
        else{
            matrixStack.scale(1f,1f,1f);
        }

        super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }
}
