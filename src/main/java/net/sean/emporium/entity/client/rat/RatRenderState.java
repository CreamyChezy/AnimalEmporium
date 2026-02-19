package net.sean.emporium.entity.client.rat;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.sean.emporium.entity.custom.RatVariants;

public class RatRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public RatVariants variant = RatVariants.GRAY;
}
