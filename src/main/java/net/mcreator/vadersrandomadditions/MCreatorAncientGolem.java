package net.mcreator.vadersrandomadditions;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import java.util.Random;

@Elementsvadersrandomadditions.ModElement.Tag
public class MCreatorAncientGolem extends Elementsvadersrandomadditions.ModElement {
	public static EntityType entity = null;

	public MCreatorAncientGolem(Elementsvadersrandomadditions instance) {
		super(instance, 1);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity> create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f)).build(
				"ancientgolem").setRegistryName("ancientgolem");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelIronGolem(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("vadersrandomadditions:textures/ancient_golem.png");
				}
			};
		});
	}

	public static class CustomEntity extends IronGolemEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 50;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, true));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(MCreatorVaultKey.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.portal.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(
					"vadersrandomadditions:ancientgolemhurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void baseTick() {
			super.baseTick();
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				MCreatorAncientGolemTick.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(175);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS);

		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		public void livingTick() {
			super.livingTick();
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Random random = this.rand;
			if (true)
				for (int l = 0; l < 10; ++l) {
					double d0 = (i + random.nextFloat());
					double d1 = (j + random.nextFloat());
					double d2 = (k + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 1.9D;
					double d4 = (random.nextFloat() - 0.5D) * 1.9D;
					double d5 = (random.nextFloat() - 0.5D) * 1.9D;
					world.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
				}
		}
	}

	/**
	 * ModelIronGolem - Either Mojang or a mod author Created using Tabula 7.1.0
	 */
	public static class ModelIronGolem extends EntityModel<Entity> {
		public RendererModel field_78176_b0;
		public RendererModel field_78176_b1;
		public RendererModel field_78174_d;
		public RendererModel field_78177_c;
		public RendererModel field_78175_e;
		public RendererModel field_78178_a0;
		public RendererModel field_78178_a1;
		public RendererModel field_78173_f;

		public ModelIronGolem() {
			this.textureWidth = 128;
			this.textureHeight = 128;
			this.field_78178_a0 = new RendererModel(this, 0, 0);
			this.field_78178_a0.setRotationPoint(0.0F, -7.0F, -2.0F);
			this.field_78178_a0.addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, 0.0F);
			this.field_78174_d = new RendererModel(this, 60, 58);
			this.field_78174_d.setRotationPoint(0.0F, -7.0F, 0.0F);
			this.field_78174_d.addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, 0.0F);
			this.field_78175_e = new RendererModel(this, 37, 0);
			this.field_78175_e.setRotationPoint(-4.0F, 11.0F, 0.0F);
			this.field_78175_e.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
			this.field_78173_f = new RendererModel(this, 60, 0);
			this.field_78173_f.mirror = true;
			this.field_78173_f.setRotationPoint(5.0F, 11.0F, 0.0F);
			this.field_78173_f.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
			this.field_78176_b0 = new RendererModel(this, 0, 40);
			this.field_78176_b0.setRotationPoint(0.0F, -7.0F, 0.0F);
			this.field_78176_b0.addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, 0.0F);
			this.field_78176_b1 = new RendererModel(this, 0, 70);
			this.field_78176_b1.setRotationPoint(0.0F, -7.0F, 0.0F);
			this.field_78176_b1.addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, 0.5F);
			this.field_78177_c = new RendererModel(this, 60, 21);
			this.field_78177_c.setRotationPoint(0.0F, -7.0F, 0.0F);
			this.field_78177_c.addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, 0.0F);
			this.field_78178_a1 = new RendererModel(this, 24, 0);
			this.field_78178_a1.setRotationPoint(0.0F, -7.0F, -2.0F);
			this.field_78178_a1.addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, 0.0F);
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.field_78178_a0.render(f5);
			this.field_78174_d.render(f5);
			this.field_78175_e.render(f5);
			this.field_78173_f.render(f5);
			this.field_78176_b0.render(f5);
			this.field_78176_b1.render(f5);
			this.field_78177_c.render(f5);
			this.field_78178_a1.render(f5);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model
		 * parts
		 */
		public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.field_78178_a0.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.field_78178_a0.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.field_78178_a1.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.field_78178_a1.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.field_78174_d.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.field_78177_c.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.field_78175_e.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.field_78173_f.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
