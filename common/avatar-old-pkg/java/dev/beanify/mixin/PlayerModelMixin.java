package dev.beanify.mixin;

import net.minecraft.client.model.PlayerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Turns every player model into a "bean": no arms or sleeves, legs that never swing.
 *
 * Variant "avatar-old-pkg": Minecraft 1.21.9 - 1.21.10 (render state is AvatarRenderState,
 * PlayerModel still lives in net.minecraft.client.model).
 *
 * Arm ROTATION is left alone so held items stay where a hand would be.
 */
@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin {

    @Inject(
        method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V",
        at = @At("TAIL"),
        require = 0
    )
    private void beanify$makeBean(CallbackInfo ci) {
        PlayerModel model = (PlayerModel) (Object) this;

        model.leftArm.visible = false;
        model.rightArm.visible = false;
        model.leftSleeve.visible = false;
        model.rightSleeve.visible = false;

        model.leftLeg.xRot = 0.0F;
        model.leftLeg.yRot = 0.0F;
        model.leftLeg.zRot = 0.0F;
        model.rightLeg.xRot = 0.0F;
        model.rightLeg.yRot = 0.0F;
        model.rightLeg.zRot = 0.0F;

        model.leftPants.copyFrom(model.leftLeg);
        model.rightPants.copyFrom(model.rightLeg);
    }
}
