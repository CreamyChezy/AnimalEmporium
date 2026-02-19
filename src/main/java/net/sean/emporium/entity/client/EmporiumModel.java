package net.sean.emporium.entity.client;

import net.minecraft.client.model.ModelTransform;

public interface EmporiumModel {

    static ModelTransform pivot(float x, float y, float z) {
        return new ModelTransform(x, y, z, 0f, 0f, 0f,1f,1f,1f);
    }
}
