package net.mcreator.vadersrandomadditions;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

@Elementsvadersrandomadditions.ModElement.Tag
public class MCreatorVaultOpen extends Elementsvadersrandomadditions.ModElement {
	public MCreatorVaultOpen(Elementsvadersrandomadditions instance) {
		super(instance, 7);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorVaultOpen!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorVaultOpen!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorVaultOpen!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorVaultOpen!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((!(((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == MCreatorTempleBrick.block.getDefaultState()
				.getBlock()) || ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == MCreatorTempleVaultDoor.block
				.getDefaultState().getBlock()))) || (!(((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == MCreatorTempleVaultDoor.block
				.getDefaultState().getBlock()) || ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == MCreatorTempleBrick.block
				.getDefaultState().getBlock())))) || ((!(((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == MCreatorTempleVaultDoor.block
				.getDefaultState().getBlock()) || ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == MCreatorTempleBrick.block
				.getDefaultState().getBlock()))) || (!(((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == MCreatorTempleBrick.block
				.getDefaultState().getBlock()) || ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == MCreatorTempleVaultDoor.block
				.getDefaultState().getBlock())))))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
