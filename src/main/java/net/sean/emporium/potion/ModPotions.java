package net.sean.emporium.potion;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.item.ModItems;

public class ModPotions {
    /*public static Potion HASTE_POTION;
    public static Potion LONG_HASTE_POTION;
    public static Potion STRONG_HASTE_POTION;*/

    public static final RegistryEntry<Potion> HASTE_POTION = register("haste_potion",
            new Potion("haste_potion", new StatusEffectInstance(StatusEffects.HASTE,3600)));
    public static final RegistryEntry<Potion> LONG_HASTE_POTION = register("long_haste_potion",
            new Potion("long_haste_potion", new StatusEffectInstance(StatusEffects.HASTE,9600)));
    public static final RegistryEntry<Potion> STRONG_HASTE_POTION = register("strong_haste_potion",
            new Potion("strong_haste_potion", new StatusEffectInstance(StatusEffects.HASTE,1800, 1)));


    private static RegistryEntry<Potion> register(String name, Potion potion){
        return Registry.registerReference(Registries.POTION, Identifier.of(AnimalEmporium.MOD_ID, name), potion);
    }

    public static void registerPotions(){
        AnimalEmporium.LOGGER.info("Registering Mod Potions for " + AnimalEmporium.MOD_ID);
    }
}