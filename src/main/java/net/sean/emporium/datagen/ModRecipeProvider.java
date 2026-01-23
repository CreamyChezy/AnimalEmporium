package net.sean.emporium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.item.ModItems;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
       // SLOP BUCKET SHAPELESS
       ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SLOP_BUCKET, 1)
                .input(Items.WATER_BUCKET)
                .input(Items.MUD)
                .criterion(hasItem(Items.WATER_BUCKET),conditionsFromItem(Items.WATER_BUCKET))
                .criterion(hasItem(Items.MUD),conditionsFromItem(Items.MUD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SLOP_BUCKET) + "shapeless"));
       // WORM STICK SHAPELESS
       ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.WORM_STICK, 1)
                .input(Items.FISHING_ROD)
                .input(ModItems.WORM)
                .criterion(hasItem(Items.FISHING_ROD), conditionsFromItem(Items.FISHING_ROD))
                .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WORM_STICK) + "shapeless"));
       // GOLDEN WORM SHAPED
       ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GOLDEN_WORM, 1)
               .pattern("nnn")
               .pattern("nwn")
               .pattern("nnn")
               .input('n', Items.GOLD_NUGGET)
               .input('w', ModItems.WORM)
               .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
               .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
               .offerTo(exporter, new Identifier(getRecipeName(ModItems.GOLDEN_WORM) + "shaped"));
       // WORM SOUP w/ RED MUSHROOM SHAPELESS
       ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.WORM_SOUP, 1)
               .input(Items.BOWL)
               .input(ModItems.WORM)
               .input(Items.RED_MUSHROOM)
               .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
               .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
               .offerTo(exporter, new Identifier(getRecipeName(ModItems.WORM_SOUP) + "shapeless_red"));
       // WORM SOUP w/ BROWN MUSHROOM SHAPELESS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.WORM_SOUP, 1)
                .input(Items.BOWL)
                .input(ModItems.WORM)
                .input(Items.BROWN_MUSHROOM)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WORM_SOUP) + "shapeless_brown"));
       // WORM BLOCK SHAPED
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WORM_BLOCK, 1)
                .pattern("www")
                .pattern("www")
                .pattern("www")
                .input('w', ModItems.WORM)
                .criterion(hasItem(ModItems.WORM), conditionsFromItem(ModItems.WORM))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WORM_BLOCK) + "shaped"));
       // WORMS FROM WORM BLOCK SHAPELESS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.WORM, 9)
                .input(ModBlocks.WORM_BLOCK)
                .criterion(hasItem(ModBlocks.WORM_BLOCK), conditionsFromItem(ModBlocks.WORM_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WORM) + "shapeless"));
       // PET BOWL SHAPED
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PET_BOWL, 1)
                .pattern("ibi")
                .pattern(" i ")
                .input('i', Items.IRON_INGOT)
                .input('b', Items.BOWL)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PET_BOWL) + "shaped"));



    }
}
