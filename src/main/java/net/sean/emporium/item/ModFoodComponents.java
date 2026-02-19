package net.sean.emporium.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;


public class ModFoodComponents {
    // WORM
    public static final FoodComponent WORM = new FoodComponent.Builder().nutrition(2).saturationModifier(.15f).build();
    public static final ConsumableComponent WORM_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100), .15f)).build();

    // GOLDEN WORM
    public static final FoodComponent GOLDEN_WORM = new FoodComponent.Builder().nutrition(5).saturationModifier(2f).build();
    public static final ConsumableComponent GOLDEN_WORM_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HASTE, 300), 1f)).build();

    // WORM SOUP
    public static final FoodComponent WORM_SOUP = new FoodComponent.Builder().nutrition(6).saturationModifier(.5f).build();
    public static final ConsumableComponent WORM_SOUP_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HASTE, 400), 1f)).build();

    // CHEESE SLICE
    public static final FoodComponent CHEESE_SLICE = new FoodComponent.Builder().nutrition(4).saturationModifier(.2f).build();
}
