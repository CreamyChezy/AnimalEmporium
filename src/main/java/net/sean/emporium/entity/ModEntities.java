package net.sean.emporium.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sean.emporium.AnimalEmporium;
import net.sean.emporium.entity.custom.OpossumEntity;

public class ModEntities {
    public static final EntityType<OpossumEntity> OPOSSUM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(AnimalEmporium.MOD_ID, "opossum"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,
                    OpossumEntity::new).dimensions(EntityDimensions.fixed(0.5f,0.5f)).build());
}
