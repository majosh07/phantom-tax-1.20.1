package net.jporh.phantomtax.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net.minecraft.entity.mob.PhantomEntity$SwoopMovementGoal")
public abstract class PhantomMixin {
	@ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PhantomEntity;tryAttack(Lnet/minecraft/entity/Entity;)Z"))
	private Entity doHunger(Entity entity) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entity;

			playerEntity.getHungerManager().setFoodLevel((playerEntity.getHungerManager().getFoodLevel() >= 6) ? playerEntity.getHungerManager().getFoodLevel() - 6 : 0);
			playerEntity.getHungerManager().setSaturationLevel(0);
		}
		return entity;
	}
}


