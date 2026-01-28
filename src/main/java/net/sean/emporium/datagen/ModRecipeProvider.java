package net.sean.emporium.datagen;

import net.minecraft.data.DataOutput;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeGenerator.RecipeProvider {

    public ModRecipeProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter) {
        return new RecipeGenerator(wrapperLookup, exporter) {
            @Override
            public void generate() {
                // SLOP BUCKET SHAPELESS
                createShapeless(RecipeCategory.MISC, ModItems.SLOP_BUCKET, 1)
                        .input(Items.WATER_BUCKET)
                        .input(Items.MUD)
                        .criterion(hasItem(Items.WATER_BUCKET),conditionsFromItem(Items.WATER_BUCKET))
                        .criterion(hasItem(Items.MUD),conditionsFromItem(Items.MUD))
                        .offerTo(exporter);
                // WORM STICK SHAPELESS
                createShapeless(RecipeCategory.TOOLS, ModItems.WORM_STICK, 1)
                        .input(Items.FISHING_ROD)
                        .input(ModItems.WORM)
                        .criterion(hasItem(Items.FISHING_ROD), conditionsFromItem(Items.FISHING_ROD))
                        .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                        .offerTo(exporter);
                // GOLDEN WORM SHAPED
                createShaped(RecipeCategory.FOOD, ModItems.GOLDEN_WORM, 1)
                        .pattern("nnn")
                        .pattern("nwn")
                        .pattern("nnn")
                        .input('n', Items.GOLD_NUGGET)
                        .input('w', ModItems.WORM)
                        .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                        .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                        .offerTo(exporter);
                // WORM SOUP w/ RED MUSHROOM SHAPELESS
                createShapeless(RecipeCategory.FOOD, ModItems.WORM_SOUP, 1)
                        .input(Items.BOWL)
                        .input(ModItems.WORM)
                        .input(Items.RED_MUSHROOM)
                        .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                        .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                        .offerTo(exporter);
                // WORM SOUP w/ BROWN MUSHROOM SHAPELESS
                createShapeless(RecipeCategory.FOOD, ModItems.WORM_SOUP, 1)
                        .input(Items.BOWL)
                        .input(ModItems.WORM)
                        .input(Items.BROWN_MUSHROOM)
                        .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                        .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                        .offerTo(exporter);
                // WORM BLOCK SHAPED
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WORM_BLOCK, 1)
                        .pattern("www")
                        .pattern("www")
                        .pattern("www")
                        .input('w', ModItems.WORM)
                        .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                        .offerTo(exporter);
                // WORMS FROM WORM BLOCK SHAPELESS
                createShapeless(RecipeCategory.FOOD, ModItems.WORM, 9)
                        .input(ModBlocks.WORM_BLOCK)
                        .criterion(hasItem(ModBlocks.WORM_BLOCK), conditionsFromItem(ModBlocks.WORM_BLOCK))
                        .offerTo(exporter);
                // PET BOWL SHAPED
                createShaped(RecipeCategory.DECORATIONS, ModBlocks.PET_BOWL, 1)
                        .pattern("ibi")
                        .pattern(" i ")
                        .input('i', Items.IRON_INGOT)
                        .input('b', Items.BOWL)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "AnimalEmporium ModRecipeProvider";
    }
}
