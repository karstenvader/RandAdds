package net.mcreator.vadersrandomadditions;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

@Elementsvadersrandomadditions.ModElement.Tag
public class MCreatorAncientGolemTick extends Elementsvadersrandomadditions.ModElement {
	public MCreatorAncientGolemTick(Elementsvadersrandomadditions instance) {
		super(instance, 2);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorAncientGolemTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorAncientGolemTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorAncientGolemTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorAncientGolemTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorAncientGolemTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double XToWarp = 0;
		double ZToWarp = 0;
		double Count = 0;
		Count = (double) ((Count) + 1);
		if ((((Count) % 10) == 0)) {
			if ((Math.random() >= 0.98)) {
				if ((Math.random() >= 0.5)) {
					XToWarp = (double) (x + ((Math.random() - 0.5) * 10));
					ZToWarp = (double) (z + ((Math.random() - 0.5) * 10));
					if (((world.getBlockState(new BlockPos((int) (XToWarp), (int) y, (int) (ZToWarp)))).getBlock() == Blocks.AIR.getDefaultState()
							.getBlock())) {
						if ((!((world.getBlockState(new BlockPos((int) (XToWarp), (int) (y - 1), (int) (ZToWarp)))).getBlock() == Blocks.AIR
								.getDefaultState().getBlock()))) {
							if (entity instanceof LivingEntity)
								((LivingEntity) entity).setPositionAndUpdate((XToWarp), y, (ZToWarp));
							world.playSound((PlayerEntity) null, x, y, z, (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("entity.enderman.teleport")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
						}
					}
				} else {
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 2, (int) 10));
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.enderman.teleport")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				}
			}
		}
	}
}
