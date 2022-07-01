package com.flashfyre.ffenchantments.enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EndCurseEnchantment extends FFEnchantment {
	
	public EndCurseEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... slots) {
		super(rarity, type, slots, 
				() -> false, 
				() -> false, 
				() -> true, 
				() -> true);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 25;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 50;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public boolean isCurse() {
		return true;
	}
	
	public static boolean attemptTeleport(LivingEntity livingEntity) {
		
		if (!livingEntity.level.isClientSide() && livingEntity.isAlive()) {
	         double x = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5D) * 48.0D;
	         double y = livingEntity.getY() + (double)(livingEntity.getRandom().nextInt(48) - 24);
	         double z = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5D) * 48.0D;
	         
	         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(x, y, z);

	 		while(blockpos$mutableblockpos.getY() > livingEntity.level.getMinBuildHeight() && !livingEntity.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion()) {
	 			blockpos$mutableblockpos.move(Direction.DOWN);
	 		}

	 		BlockState blockstate = livingEntity.level.getBlockState(blockpos$mutableblockpos);
	 		boolean flag = blockstate.getMaterial().blocksMotion();
	 		boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
	 		if (flag && !flag1) {
	 			net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(livingEntity, x, y, z);
	 			if (event.isCanceled()) return false;
	 			Vec3 vec3 = livingEntity.position();
	 			boolean teleportSuccess = livingEntity.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
	 			if (teleportSuccess) {
	 				livingEntity.level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(livingEntity));
	 				if (!livingEntity.isSilent()) {
	 					livingEntity.level.playSound((Player)null, livingEntity.xo, livingEntity.yo, livingEntity.zo, SoundEvents.ENDERMAN_TELEPORT, livingEntity.getSoundSource(), 1.0F, 1.0F);
	 	            	livingEntity.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
	 				}
	 				return true;
	 			}	 			
	 		} 
		} 		
		
		return false;
	}
}
