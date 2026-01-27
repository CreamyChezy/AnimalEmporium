package net.sean.emporium.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;

public class ModModelLayers {
    public static final EntityModelLayer OPOSSUM =
            new EntityModelLayer(Identifier.of(AnimalEmporium.MOD_ID, "opossum"), "main");
}
