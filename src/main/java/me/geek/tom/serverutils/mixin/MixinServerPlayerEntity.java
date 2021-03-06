package me.geek.tom.serverutils.mixin;

import me.geek.tom.serverutils.TomsServerUtils;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity {
    @Inject(method = "onDeath", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"))
    private void hook_deathMessage(DamageSource source, CallbackInfo ci, boolean bl, Text text) {
        TomsServerUtils.onPlayerAnnouncement((ServerPlayerEntity) (Object) this, text, 0xFF0000);
    }
}
