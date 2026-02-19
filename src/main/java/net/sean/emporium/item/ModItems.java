package net.sean.emporium.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.block.ModBlocks;
import net.sean.emporium.entity.ModEntities;
import net.sean.emporium.fluid.ModFluids;
import net.sean.emporium.item.custom.WormSoupItem;

public class ModItems {

    // FOOD ITEMS
    public static final Item WORM = registerItem("worm", new Item(new Item.Settings().food(ModFoodComponents.WORM,
            ModFoodComponents.WORM_EFFECT).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "worm")))));
    public static final Item GOLDEN_WORM = registerItem("golden_worm", new Item(new Item.Settings().food(ModFoodComponents.GOLDEN_WORM,
            ModFoodComponents.GOLDEN_WORM_EFFECT).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "golden_worm")))));
    public static final Item WORM_SOUP = registerItem("worm_soup", new WormSoupItem(new Item.Settings().food(ModFoodComponents.WORM_SOUP,
            ModFoodComponents.WORM_SOUP_EFFECT).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "worm_soup")))));
    public static final Item CHEESE_SLICE = registerItem("cheese_slice", new Item(new Item.Settings().food(ModFoodComponents.CHEESE_SLICE)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "cheese_slice")))));

    // FUNCTIONAL ITEMS
    public static final Item WORM_STICK = registerItem("worm_stick", new Item(new Item.Settings().maxCount(1).maxDamage(75)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "worm_stick")))));
    public static final Item SLOP_BUCKET = registerItem("slop_bucket", new BucketItem(ModFluids.STILL_SLOP, new Item.Settings().maxCount(1)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "slop_bucket")))));

    // SPAWN EGGS
    public static final Item OPOSSUM_SPAWN_EGG = registerItem("opossum_spawn_egg",
            new SpawnEggItem(new Item.Settings().spawnEgg(ModEntities.OPOSSUM)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnimalEmporium.MOD_ID, "opossum_spawn_egg")))));

    private static void addItemsToFoodTab(FabricItemGroupEntries entries){
        entries.add(WORM);
        entries.add(GOLDEN_WORM);
        entries.add(WORM_SOUP);
        entries.add(CHEESE_SLICE);
    }
    private static void addItemsToToolsTab(FabricItemGroupEntries entries){
        entries.add(WORM_STICK);
        entries.add(SLOP_BUCKET);
    }
    private static void addItemsToSpawnEggsTab(FabricItemGroupEntries entries){
        entries.add(OPOSSUM_SPAWN_EGG);
    }
    private static void addItemsToBuildingBlocksTab(FabricItemGroupEntries entries){
        entries.add(ModBlocks.WORM_BLOCK);
    }
    private static void addItemsToFunctionalTab(FabricItemGroupEntries entries){
        entries.add(ModBlocks.PET_BOWL);
    }
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(AnimalEmporium.MOD_ID, name), item);
    }
    public static void registerModItems() {
        AnimalEmporium.LOGGER.info("Registering ModItems for " + AnimalEmporium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggsTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingBlocksTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItems::addItemsToFunctionalTab);
    }
}
