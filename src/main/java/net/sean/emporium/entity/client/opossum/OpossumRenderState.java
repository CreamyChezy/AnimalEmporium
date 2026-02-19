package net.sean.emporium.entity.client.opossum;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class OpossumRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public float deathProgress;
}
