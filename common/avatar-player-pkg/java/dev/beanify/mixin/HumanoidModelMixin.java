package dev.beanify.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Armor models are separate per-slot HumanoidModel instances posed with setupAnim(state), so
 * hooking the base class reaches player armor (arms hidden, legs frozen). The
 * AvatarRenderState check keeps zombies, skeletons and armor stands untouched.
 *
 * Variant "avatar-player-pkg": Minecraft 1.21.11 and the 26.x line.
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
        if (!(state instanceof AvatarRenderState)) {
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
