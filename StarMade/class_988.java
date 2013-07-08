import com.bulletphysics.linearmath.AabbUtil2;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;

public class class_988
{
  public final Vector3f field_1273;
  public final Vector3f field_1274;
  
  public static boolean a(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    return (paramVector3f1.field_615 >= paramVector3f2.field_615) && (paramVector3f1.field_615 <= paramVector3f3.field_615) && (paramVector3f1.field_616 >= paramVector3f2.field_616) && (paramVector3f1.field_616 <= paramVector3f3.field_616) && (paramVector3f1.field_617 >= paramVector3f2.field_617) && (paramVector3f1.field_617 <= paramVector3f3.field_617);
  }
  
  public class_988()
  {
    this.jdField_field_1273_of_type_JavaxVecmathVector3f = new Vector3f((1.0F / 1.0F), (1.0F / 1.0F), (1.0F / 1.0F));
    this.field_1274 = new Vector3f((1.0F / -1.0F), (1.0F / -1.0F), (1.0F / -1.0F));
  }
  
  public class_988(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    if ((!jdField_field_1273_of_type_Boolean) && ((paramVector3f2 == null) || (paramVector3f1 == null))) {
      throw new AssertionError();
    }
    this.field_1274 = paramVector3f2;
    this.jdField_field_1273_of_type_JavaxVecmathVector3f = paramVector3f1;
  }
  
  public final Vector3f a1(Vector3f paramVector3f)
  {
    paramVector3f.set(FastMath.a((this.field_1274.field_615 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615) / 2.0F), FastMath.a((this.field_1274.field_616 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616) / 2.0F), FastMath.a((this.field_1274.field_617 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617) / 2.0F));
    return paramVector3f;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof class_988))) {
      return false;
    }
    return (this.jdField_field_1273_of_type_JavaxVecmathVector3f.equals(((class_988)paramObject).jdField_field_1273_of_type_JavaxVecmathVector3f)) && (this.field_1274.equals(((class_988)paramObject).field_1274));
  }
  
  public final void a2(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615 = Math.min(paramFloat1, this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615);
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616 = Math.min(paramFloat2, this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616);
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617 = Math.min(paramFloat3, this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617);
    this.field_1274.field_615 = Math.max(paramFloat4, this.field_1274.field_615);
    this.field_1274.field_616 = Math.max(paramFloat5, this.field_1274.field_616);
    this.field_1274.field_617 = Math.max(paramFloat6, this.field_1274.field_617);
  }
  
  public final void a3(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615 = Math.min(paramVector3f1.field_615, this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615);
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616 = Math.min(paramVector3f1.field_616, this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616);
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617 = Math.min(paramVector3f1.field_617, this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617);
    this.field_1274.field_615 = Math.max(paramVector3f2.field_615, this.field_1274.field_615);
    this.field_1274.field_616 = Math.max(paramVector3f2.field_616, this.field_1274.field_616);
    this.field_1274.field_617 = Math.max(paramVector3f2.field_617, this.field_1274.field_617);
  }
  
  public final Vector3f b(Vector3f paramVector3f)
  {
    a1(paramVector3f);
    paramVector3f.add(this.jdField_field_1273_of_type_JavaxVecmathVector3f);
    return paramVector3f;
  }
  
  public final Vector3f a4(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    paramVector3f2.set(paramVector3f1);
    class_49.a1(paramVector3f2, this.jdField_field_1273_of_type_JavaxVecmathVector3f, this.field_1274);
    return paramVector3f2;
  }
  
  public final class_988 a5(class_988 paramclass_9881, class_988 paramclass_9882)
  {
    class_988 localclass_9882 = paramclass_9881;
    class_988 localclass_9881 = this;
    if (!AabbUtil2.testAabbAgainstAabb2(this.jdField_field_1273_of_type_JavaxVecmathVector3f, localclass_9881.field_1274, localclass_9882.jdField_field_1273_of_type_JavaxVecmathVector3f, localclass_9882.field_1274)) {
      return null;
    }
    paramclass_9882.jdField_field_1273_of_type_JavaxVecmathVector3f.set(this.jdField_field_1273_of_type_JavaxVecmathVector3f);
    paramclass_9882.field_1274.set(this.field_1274);
    class_49.a4(paramclass_9882.jdField_field_1273_of_type_JavaxVecmathVector3f, paramclass_9881.jdField_field_1273_of_type_JavaxVecmathVector3f);
    class_49.b(paramclass_9882.field_1274, paramclass_9881.field_1274);
    return paramclass_9882;
  }
  
  public final boolean a6()
  {
    return (this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615 != (1.0F / 1.0F)) && (this.field_1274.field_615 != (1.0F / -1.0F));
  }
  
  public final boolean a7(Vector3f paramVector3f)
  {
    return (paramVector3f.field_615 >= this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615) && (paramVector3f.field_615 <= this.field_1274.field_615) && (paramVector3f.field_616 >= this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616) && (paramVector3f.field_616 <= this.field_1274.field_616) && (paramVector3f.field_617 >= this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617) && (paramVector3f.field_617 <= this.field_1274.field_617);
  }
  
  public final boolean b1()
  {
    return (this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615 <= this.field_1274.field_615) && (this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616 <= this.field_1274.field_616) && (this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617 <= this.field_1274.field_617);
  }
  
  public final void a8()
  {
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.set((1.0F / 1.0F), (1.0F / 1.0F), (1.0F / 1.0F));
    this.field_1274.set((1.0F / -1.0F), (1.0F / -1.0F), (1.0F / -1.0F));
  }
  
  public final void a9(class_988 paramclass_988)
  {
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.set(paramclass_988.jdField_field_1273_of_type_JavaxVecmathVector3f);
    this.field_1274.set(paramclass_988.field_1274);
  }
  
  public final void b2(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.jdField_field_1273_of_type_JavaxVecmathVector3f.set(paramVector3f1);
    this.field_1274.set(paramVector3f2);
  }
  
  public String toString()
  {
    return "[ " + this.jdField_field_1273_of_type_JavaxVecmathVector3f + " | " + this.field_1274 + " ]";
  }
  
  public final boolean c()
  {
    return (b1()) && (this.field_1274.field_615 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615 >= 1.0F) && (this.field_1274.field_616 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616 >= 1.0F) && (this.field_1274.field_617 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617 >= 1.0F);
  }
  
  public final float a10()
  {
    return class_49.a6(this.field_1274.field_615 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_615, this.field_1274.field_616 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_616, this.field_1274.field_617 - this.jdField_field_1273_of_type_JavaxVecmathVector3f.field_617);
  }
  
  static
  {
    jdField_field_1273_of_type_Boolean = !xO.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_988
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */