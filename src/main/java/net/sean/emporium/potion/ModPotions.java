package net.sean.emporium.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.item.ModItems;

public class ModPotions {
    public static Potion HASTE_POTION;
    public static Potion LONG_HASTE_POTION;
    public static Potion STRONG_HASTE_POTION;

    private static Potion register(String name, Potion potion){
        return Registry.register(Registries.POTION, new Identifier(AnimalEmporium.MOD_ID, name), potion);
    }

    public static void registerPotions(){
        HASTE_POTION = register("haste_potion", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 3600)));
        LONG_HASTE_POTION = register("long_haste_potion", new Potion(new StatusEffectInstance(StatusEffects.HASTE,9600)));
        STRONG_HASTE_POTION = register("strong_haste_potion", new Potion(new StatusEffectInstance(StatusEffects.HASTE,1800,1)));
    }
    public static void registerPotionsRecipes(){
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, ModItems.GOLDEN_WORM, HASTE_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(HASTE_POTION, Items.REDSTONE, LONG_HASTE_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(HASTE_POTION, Items.GLOWSTONE_DUST, STRONG_HASTE_POTION);
    }
}