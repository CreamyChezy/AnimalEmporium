package net.sean.emporium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.util.Identifier;
import net.sean.emporium.entity.ModEntities;
import net.sean.emporium.entity.client.opossum.OpossumModel;
import net.sean.emporium.entity.client.opossum.OpossumRenderer;
import net.sean.emporium.entity.client.rat.RatModel;
import net.sean.emporium.entity.client.rat.RatRenderer;
import net.sean.emporium.fluid.ModFluids;

@Environment(EnvType.CLIENT)
public class AnimalEmporiumClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SLOP, ModFluids.FLOWING_SLOP, new SimpleFluidRenderHandler(
                Identifier.of("minecraft", "block/water_still"),
                Identifier.of("minecraft","block/water_flow"),
                0x612B20
        ));
        BlockRenderLayerMap.putFluids(BlockRenderLayer.SOLID, ModFluids.STILL_SLOP, ModFluids.FLOWING_SLOP);

        EntityRendererRegistry.register(ModEntities.OPOSSUM, OpossumRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(OpossumModel.OPOSSUM, OpossumModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.RAT, RatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(RatModel.RAT, RatModel::getTexturedModelData);
    }
}