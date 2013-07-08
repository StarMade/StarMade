import javax.vecmath.Vector3f;
import org.schema.game.network.objects.NetworkShip;
import org.schema.schine.network.objects.remote.RemoteVector3f;

public final class class_1139
  extends class_1260
{
  private long jdField_field_223_of_type_Long;
  private Vector3f jdField_field_223_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_1139(class_747 paramclass_747, boolean paramBoolean)
  {
    super("Turret_Ent", paramclass_747);
    if (paramclass_747.isOnServer()) {
      this.field_223 = new class_1141(this, paramBoolean);
    }
  }
  
  public final void a24(class_809 paramclass_809)
  {
    if (System.currentTimeMillis() - this.jdField_field_223_of_type_Long < 5000L) {
      return;
    }
    if ((paramclass_809 instanceof class_747))
    {
      class_747 localclass_747 = (class_747)paramclass_809;
      if (((paramclass_809 = ((class_1039)a6()).a().a166(localclass_747, a26())) == class_762.field_1023) || (paramclass_809 == class_762.field_1022))
      {
        this.jdField_field_223_of_type_Long = System.currentTimeMillis();
        ((class_1256)this.field_223).a8(localclass_747);
      }
    }
  }
  
  public final void b1(class_941 paramclass_941)
  {
    super.b1(paramclass_941);
    if ((a5() instanceof class_1135))
    {
      a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetVelocity.set(new Vector3f(0.0F, 0.0F, 0.0F));
      return;
    }
    if ((a5() instanceof class_1137))
    {
      a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetVelocity.set(new Vector3f(0.0F, 0.0F, 0.0F));
      this.jdField_field_223_of_type_JavaxVecmathVector3f.set(((class_1137)a5()).a4());
      a26().a113().orientationDir.set(this.jdField_field_223_of_type_JavaxVecmathVector3f);
      a29(paramclass_941, this.jdField_field_223_of_type_JavaxVecmathVector3f);
      return;
    }
    if ((a5() instanceof class_1149))
    {
      a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a29(paramclass_941, this.jdField_field_223_of_type_JavaxVecmathVector3f);
      paramclass_941 = new Vector3f();
      Vector3f localVector3f = new Vector3f();
      paramclass_941.set(((class_1149)a5()).a4());
      localVector3f.set(((class_1149)a5()).b2());
      if (paramclass_941.length() > 0.0F)
      {
        a26().a113().targetPosition.set(paramclass_941);
        a26().a113().targetVelocity.set(localVector3f);
        a30(paramclass_941, localVector3f);
        a31(paramclass_941);
        b3(paramclass_941);
        ((class_1149)a5()).a4().set(0.0F, 0.0F, 0.0F);
        a5().a12(new class_1117());
      }
      return;
    }
    a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
    a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1139
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */