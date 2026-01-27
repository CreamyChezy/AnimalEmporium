package net.sean.emporium.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
       blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WORM_BLOCK);

       Identifier bowlID = Identifier.of(AnimalEmporium.MOD_ID, "block/pet_bowl_empty");
       blockStateModelGenerator.registerParentedItemModel(ModBlocks.PET_BOWL, bowlID);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WORM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM_STICK, Models.HANDHELD_ROD);
        itemModelGenerator.register(ModItems.SLOP_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.OPOSSUM_SPAWN_EGG, new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.GOLDEN_WORM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM_SOUP, Models.GENERATED);
    }
}
