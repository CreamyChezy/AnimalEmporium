package net.sean.emporium.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.block.custom.PetBowlBlock;

public class ModBlocks {

    public static final Block PET_BOWL = registerBlock("pet_bowl",
            new PetBowlBlock(Block.Settings.create()
                    .mapColor(MapColor.WHITE_GRAY)
                    .sounds(BlockSoundGroup.METAL)
                    .strength(2.0F,6.0F)
                    .requiresTool()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AnimalEmporium.MOD_ID,"pet_bowl")))));

    public static final Block WORM_BLOCK = registerBlock("worm_block",
            new Block(Block.Settings.copy(Blocks.HAY_BLOCK).sounds(BlockSoundGroup.SLIME)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AnimalEmporium.MOD_ID,"worm_block")))));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(AnimalEmporium.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(AnimalEmporium.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        AnimalEmporium.LOGGER.info("Registering ModBlocks for " + AnimalEmporium.MOD_ID);
    }

}
