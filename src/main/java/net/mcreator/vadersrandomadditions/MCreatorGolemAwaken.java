package net.mcreator.vadersrandomadditions;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

@Elementsvadersrandomadditions.ModElement.Tag
public class MCreatorGolemAwaken extends Elementsvadersrandomadditions.ModElement {
	public MCreatorGolemAwaken(Elementsvadersrandomadditions instance) {
		super(instance, 6);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorGolemAwaken!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorGolemAwaken!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorGolemAwaken!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorGolemAwaken!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorGolemAwaken!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			Entity entityToSpawn = new MCreatorAncientGolem.CustomEntity(MCreatorAncientGolem.entity, world);
			entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360F, 0);
			world.addEntity(entityToSpawn);
		}
		entity.remove();
	}
}
