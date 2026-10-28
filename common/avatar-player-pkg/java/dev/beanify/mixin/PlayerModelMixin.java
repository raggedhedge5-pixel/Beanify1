package dev.beanify.mixin;

import net.minecraft.client.model.player.PlayerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Turns every player model into a "bean": no arms or sleeves, legs that never swing.
 *
 * Variant "avatar-player-pkg": Minecraft 1.21.11 and the 26.x line (PlayerModel moved to
 * net.minecraft.client.model.player in 1.21.11).
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

        // ModelPart.copyFrom no longer exists from 1.21.9, so set the overlay rotations directly.
        // (Positions were already copied by vanilla and we never change them.)
        model.leftPants.xRot = 0.0F;
        model.leftPants.yRot = 0.0F;
        model.leftPants.zRot = 0.0F;
        model.rightPants.xRot = 0.0F;
        model.rightPants.yRot = 0.0F;
        model.rightPants.zRot = 0.0F;
    }
}
