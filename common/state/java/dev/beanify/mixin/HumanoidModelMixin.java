package dev.beanify.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * From 1.21.2 armor models are no longer copied from the player model; each armor model is posed
 * from the render state with its own setupAnim. Without this, a bean in armor would still swing
 * its armored legs. The PlayerRenderState check keeps zombies, skeletons and armor stands intact.
 *
 * Variant "state": Minecraft 1.21.2 - 1.21.8 (render state is PlayerRenderState).
 */
@Mixin(HumanoidModel.class)
@SuppressWarnings("rawtypes")
public abstract class HumanoidModelMixin {

    @Inject(
        method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V",
        at = @At("TAIL"),
        require = 0
    )
    private void beanify$makeBean(HumanoidRenderState state, CallbackInfo ci) {
        if (!(state instanceof PlayerRenderState)) {
            return;
        }
        HumanoidModel model = (HumanoidModel) (Object) this;

        model.leftArm.visible = false;
        model.rightArm.visible = false;

        model.leftLeg.xRot = 0.0F;
        model.leftLeg.yRot = 0.0F;
        model.leftLeg.zRot = 0.0F;
        model.rightLeg.xRot = 0.0F;
        model.rightLeg.yRot = 0.0F;
        model.rightLeg.zRot = 0.0F;
    }
}
