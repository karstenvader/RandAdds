/**
 * ModelIronGolem - Either Mojang or a mod author Created using Tabula 7.1.0
 */
public static class ModelIronGolem extends ModelBase {
	public ModelRenderer field_78176_b0;
	public ModelRenderer field_78176_b1;
	public ModelRenderer field_78174_d;
	public ModelRenderer field_78177_c;
	public ModelRenderer field_78175_e;
	public ModelRenderer field_78178_a0;
	public ModelRenderer field_78178_a1;
	public ModelRenderer field_78173_f;

	public ModelIronGolem() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.field_78178_a0 = new ModelRenderer(this, 0, 0);
		this.field_78178_a0.setRotationPoint(0.0F, -7.0F, -2.0F);
		this.field_78178_a0.addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, 0.0F);
		this.field_78174_d = new ModelRenderer(this, 60, 58);
		this.field_78174_d.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.field_78174_d.addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, 0.0F);
		this.field_78175_e = new ModelRenderer(this, 37, 0);
		this.field_78175_e.setRotationPoint(-4.0F, 11.0F, 0.0F);
		this.field_78175_e.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
		this.field_78173_f = new ModelRenderer(this, 60, 0);
		this.field_78173_f.mirror = true;
		this.field_78173_f.setRotationPoint(5.0F, 11.0F, 0.0F);
		this.field_78173_f.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
		this.field_78176_b0 = new ModelRenderer(this, 0, 40);
		this.field_78176_b0.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.field_78176_b0.addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, 0.0F);
		this.field_78176_b1 = new ModelRenderer(this, 0, 70);
		this.field_78176_b1.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.field_78176_b1.addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, 0.5F);
		this.field_78177_c = new ModelRenderer(this, 60, 21);
		this.field_78177_c.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.field_78177_c.addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, 0.0F);
		this.field_78178_a1 = new ModelRenderer(this, 24, 0);
		this.field_78178_a1.setRotationPoint(0.0F, -7.0F, -2.0F);
		this.field_78178_a1.addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
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
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.field_78178_a0.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.field_78178_a0.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.field_78178_a1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.field_78178_a1.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.field_78174_d.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.field_78177_c.rotateAngleX = MathHelper.cos(f * 0.6662F
				+ (float) Math.PI)
				* f1;
		this.field_78175_e.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.field_78173_f.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}
