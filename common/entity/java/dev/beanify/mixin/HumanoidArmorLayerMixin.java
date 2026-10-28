package dev.beanify.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Chestplates carry their own arm geometry, so without this a bean would still show floating
 * armor arms. Only layers whose parent model is a PlayerModel are touched, so zombies,
 * skeletons and armor stands keep their arms.
 *
 * Variant "entity": Minecraft 1.16.5 - 1.21.8. (setPartVisibility was removed in 1.21.9.)
 * Leg poses reach the armor models through copyPropertiesTo, so they are already frozen.
 */
@Mixin(HumanoidArmorLayer.class)
@SuppressWarnings("rawtypes")
public abstract class HumanoidArmorLayerMixin {

    @Inject(method = "setPartVisibility", at = @At("TAIL"), require = 0)
    private void beanify$hideArmorArms(HumanoidModel armorModel, EquipmentSlot slot, CallbackInfo ci) {
        if (((RenderLayer) (Object) this).getParentModel() instanceof PlayerModel) {
            armorModel.leftArm.visible = false;
            armorModel.rightArm.visible = false;
        }
    }
}
