import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public final class class_1054
  implements Comparable
{
  private final Vector3f jdField_field_1316_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Quat4f jdField_field_1316_of_type_JavaxVecmathQuat4f = new Quat4f();
  private final Vector3f field_1317 = new Vector3f(1.0F, 1.0F, 1.0F);
  private final float jdField_field_1316_of_type_Float;
  
  public class_1054(float paramFloat, Vector3f paramVector3f1, Quat4f paramQuat4f, Vector3f paramVector3f2)
  {
    this.jdField_field_1316_of_type_Float = paramFloat;
    this.jdField_field_1316_of_type_JavaxVecmathVector3f.set(paramVector3f1);
    this.jdField_field_1316_of_type_JavaxVecmathQuat4f.set(paramQuat4f);
    if ((paramVector3f2 != null) && (paramVector3f2.field_615 != 0.0F) && (paramVector3f2.field_616 != 0.0F) && (paramVector3f2.field_617 != 0.0F)) {
      this.field_1317.set(paramVector3f2);
    }
  }
  
  public final Quat4f a()
  {
    return this.jdField_field_1316_of_type_JavaxVecmathQuat4f;
  }
  
  public final float a1()
  {
    return this.jdField_field_1316_of_type_Float;
  }
  
  public final Vector3f a2()
  {
    return this.jdField_field_1316_of_type_JavaxVecmathVector3f;
  }
  
  public final String toString()
  {
    return "KeyFrame [translate=" + this.jdField_field_1316_of_type_JavaxVecmathVector3f + ", rotation=" + this.jdField_field_1316_of_type_JavaxVecmathQuat4f + ", scale=" + this.field_1317 + ", time=" + this.jdField_field_1316_of_type_Float + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1054
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */