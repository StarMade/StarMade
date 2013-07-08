import com.bulletphysics.dynamics.constraintsolver.HingeConstraint;

public final class class_1425
  extends class_1429
{
  private float field_276;
  private float field_277;
  
  public final class_29[] a()
  {
    null.a14(Float.valueOf(this.field_276));
    null.a14(Float.valueOf(this.field_277));
    if ((Float.valueOf(null.field_19).floatValue() > null.getHingeAngle() + 0.01F) || (Float.valueOf(null.field_19).floatValue() < null.getHingeAngle() - 0.01F))
    {
      null.a14(Float.valueOf(null.getHingeAngle()));
      class_1040.a2();
    }
    return null;
  }
  
  public final void a1()
  {
    null.field_276 = Float.valueOf(null.field_19).floatValue();
    null.field_277 = Float.valueOf(null.field_19).floatValue();
    System.currentTimeMillis();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1425
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */