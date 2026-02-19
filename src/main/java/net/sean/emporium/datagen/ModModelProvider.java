package net.sean.emporium.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
       blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WORM_BLOCK);
       blockStateModelGenerator.registerParentedItemModel(ModBlocks.PET_BOWL, Identifier.of(AnimalEmporium.MOD_ID, "block/pet_bowl_empty"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WORM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM_STICK, Models.HANDHELD_ROD);
        itemModelGenerator.register(ModItems.SLOP_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.OPOSSUM_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_WORM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE_SLICE, Models.GENERATED);
    }

    @Override
    public String getName() {
        return "AnimalEmporium ModModelProvider";
    }
}
