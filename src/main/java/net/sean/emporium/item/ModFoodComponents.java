package net.sean.emporium.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent WORM = new FoodComponent.Builder().hunger(2).saturationModifier(.15f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100), .15f).build();
    public static final FoodComponent GOLDEN_WORM = new FoodComponent.Builder().hunger(5).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 300), 1f).build();
    public static final FoodComponent WORM_SOUP = new FoodComponent.Builder().hunger(6).saturationModifier(.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE,400),1f).build();

}
