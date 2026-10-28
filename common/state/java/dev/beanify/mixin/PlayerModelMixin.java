package dev.beanify.mixin;

import net.minecraft.client.model.PlayerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Turns every player model into a "bean": no arms or sleeves, legs that never swing.
 *
 * Variant "entity": Minecraft 1.16.5 - 1.21.8 (Mojang mappings).
 *
 * The arm ROTATION is deliberately left alone. Held items are positioned from the arm part's
 * transform (ItemInHandLayer -> translateToHand), which does not look at ModelPart.visible, so
 * items still render exactly where a hand would be.
 */
@Mixin(PlayerModel.class)
@SuppressWarnings("rawtypes")
public abstract class PlayerModelMixin {

    // Name-only target: the descriptor differs across this range (entity+floats before 1.21.2,
    // a render state from 1.21.2). The handler only needs CallbackInfo, which Mixin allows.
    // Everything here is idempotent, so it does not matter if a bridge method is hit as well.
    @Inject(method = "setupAnim", at = @At("TAIL"), require = 0)
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

        // PlayerModel copies leg poses to the pants overlay before this point, so redo it.
        model.leftPants.copyFrom(model.leftLeg);
        model.rightPants.copyFrom(model.rightLeg);
    }
}
