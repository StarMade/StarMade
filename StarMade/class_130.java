import javax.vecmath.Vector3f;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_130
  extends class_1363
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private class_972 field_92;
  private class_972 field_93;
  private class_1433 jdField_field_89_of_type_Class_1433 = new class_1433();
  private float jdField_field_89_of_type_Float = 32.0F;
  private boolean jdField_field_89_of_type_Boolean;
  private class_972 field_94;
  private class_972 field_95;
  
  public class_130(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = "powerbar-1x4-gui-";
    this.field_90 = new class_972(class_969.a2().a5(paramclass_371), a24());
    this.field_90.a_2(0);
    this.field_90.a161(66.0F, -11.0F, 0.0F);
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5(paramclass_371), a24());
    this.jdField_field_89_of_type_Class_972.a_2(2);
    this.jdField_field_89_of_type_Class_972.a161(66.0F, -11.0F, 0.0F);
    this.field_94 = new class_972(class_969.a2().a5(paramclass_371), a24());
    this.field_94.a_2(2);
    this.field_94.a161(66.0F, -11.0F, 0.0F);
    this.field_95 = new class_972(class_969.a2().a5(paramclass_371), a24());
    this.field_95.a_2(2);
    this.field_95.a161(66.0F, -11.0F, 0.0F);
    this.field_93 = new class_972(class_969.a2().a5(paramclass_371), a24());
    this.field_93.a_2(1);
    this.field_93.a161(549.0F, -11.0F, 0.0F);
    this.field_92 = new class_972(class_969.a2().a5(paramclass_371), a24());
    this.field_92.a_2(3);
    this.field_92.a161(549.0F, -11.0F, 0.0F);
    if (class_969.a2().a5(paramclass_371).a63() == null) {
      class_969.a2().a5(paramclass_371).c6(new javax.vecmath.Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
    }
  }
  
  public final void a2() {}
  
  public final void b()
  {
    GlUtil.d1();
    r();
    Object localObject = (class_371)a24();
    this.jdField_field_89_of_type_Class_972.a147().a63().field_599 = 0.0F;
    this.field_92.a147().a63().field_599 = 0.0F;
    this.jdField_field_89_of_type_Class_972.b();
    this.field_90.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
    float f1 = 1.0F - ((class_371)localObject).a20().a14() / 300.0F;
    org.lwjgl.util.vector.Vector4f localVector4f = new org.lwjgl.util.vector.Vector4f(this.field_90.a83().field_615 + f1 * 402.0F, this.field_90.a83().field_615 + 402.0F, this.field_90.a83().field_616, this.field_90.a83().field_616 + this.field_90.a3());
    this.field_90.a168(localVector4f);
    if (((class_371)localObject).a25() != null)
    {
      class_747 localclass_747 = ((class_371)localObject).a25();
      this.field_94.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
      float f3 = (float)(1.0D - localclass_747.a112().getShieldManager().getShields() / localclass_747.a112().getShieldManager().getShieldCapabilityHP());
      localObject = new org.lwjgl.util.vector.Vector4f(this.field_94.a83().field_615 + f3 * 402.0F, this.field_94.a83().field_615 + 402.0F, this.field_94.a83().field_616, this.field_94.a83().field_616 + this.field_94.a3());
      this.field_94.a168((org.lwjgl.util.vector.Vector4f)localObject);
      this.field_92.a147().a63().field_599 = 0.0F;
      if (this.jdField_field_89_of_type_Boolean != localclass_747.a112().getPowerAddOn().isInRecovery()) {
        this.jdField_field_89_of_type_Boolean = localclass_747.a112().getPowerAddOn().isInRecovery();
      }
      if ((this.jdField_field_89_of_type_Boolean) || (localclass_747.a112().getPowerAddOn().getPower() <= 0.0D))
      {
        this.field_92.a147().a63().field_596 = 1.0F;
        this.field_92.a147().a63().field_597 = 0.0F;
        this.field_92.a147().a63().field_598 = 0.0F;
        this.field_92.a147().a63().field_599 = (this.jdField_field_89_of_type_Class_1433.a1() * 0.3F);
      }
      this.field_92.b();
      this.field_93.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
      f3 = (float)localclass_747.a112().getPowerAddOn().getMaxPower();
      float f2 = (float)localclass_747.a112().getPowerAddOn().getPower() * 0.785F;
      localObject = this.field_93;
      f2 /= f3;
      ((class_1363)localObject).field_89.set(((class_1363)localObject).a83().field_615, ((class_1363)localObject).a83().field_615 + f2 * ((class_1363)localObject).b1(), ((class_1363)localObject).a83().field_616, ((class_1363)localObject).a83().field_616 + 1.0F * ((class_1363)localObject).a3());
      ((class_1363)localObject).a168(((class_1363)localObject).field_89);
    }
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1() * 2.0F + this.jdField_field_89_of_type_Float;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.field_90.c();
    this.field_92.c();
    this.field_93.c();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_1433.a(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_130
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */