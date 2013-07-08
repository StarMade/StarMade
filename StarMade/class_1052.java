import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.util.BitSet;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;

public final class class_1052
  extends class_1058
{
  private final String jdField_field_231_of_type_JavaLangString;
  private int jdField_field_231_of_type_Int = -1;
  private Quat4f jdField_field_231_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Vector3f jdField_field_231_of_type_JavaxVecmathVector3f = new Vector3f();
  private Quat4f jdField_field_232_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Vector3f jdField_field_232_of_type_JavaxVecmathVector3f = new Vector3f();
  private static Quat4f field_233 = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
  
  public class_1052(String paramString)
  {
    this.jdField_field_231_of_type_JavaLangString = paramString;
  }
  
  public final String a()
  {
    return this.jdField_field_231_of_type_JavaLangString;
  }
  
  private void a1(int paramInt, Quat4f paramQuat4f)
  {
    paramQuat4f.set(((class_1054)this.jdField_field_231_of_type_Class_27.get(paramInt)).a());
  }
  
  private float a2(int paramInt)
  {
    return ((class_1054)this.jdField_field_231_of_type_Class_27.get(paramInt)).a1();
  }
  
  private void a3(int paramInt, Vector3f paramVector3f)
  {
    paramVector3f.set(((class_1054)this.jdField_field_231_of_type_Class_27.get(paramInt)).a2());
  }
  
  public final void a4(float paramFloat1, float paramFloat2, class_1056 paramclass_1056, class_1028 paramclass_1028)
  {
    BitSet localBitSet = paramclass_1028.field_1293;
    paramclass_1028 = paramFloat2;
    paramclass_1056 = paramclass_1056.a3();
    paramFloat2 = paramFloat1;
    paramFloat1 = this;
    Object localObject = null;
    if (this.jdField_field_231_of_type_Int == -1) {
      paramFloat1.jdField_field_231_of_type_Int = paramclass_1056.a5(paramFloat1.jdField_field_231_of_type_JavaLangString);
    }
    if ((localBitSet != null) && (!localBitSet.get(paramFloat1.jdField_field_231_of_type_Int)))
    {
      System.err.println("NOT AFFECTED: " + ((class_986)paramclass_1056.a6().get(paramFloat1.jdField_field_231_of_type_Int)).field_98);
      return;
    }
    paramclass_1056 = (class_986)paramclass_1056.a6().get(paramFloat1.jdField_field_231_of_type_Int);
    int i = paramFloat1.jdField_field_231_of_type_Class_27.size() - 1;
    Quat4f localQuat4f1;
    float f1;
    Quat4f localQuat4f2;
    float f2;
    float f3;
    if ((paramFloat2 < 0.0F) || (paramFloat1.jdField_field_231_of_type_Class_27.size() == 1))
    {
      paramFloat1.a1(0, paramFloat1.jdField_field_231_of_type_JavaxVecmathQuat4f);
      paramFloat1.a3(0, paramFloat1.jdField_field_231_of_type_JavaxVecmathVector3f);
    }
    else if (paramFloat2 >= paramFloat1.a2(i))
    {
      paramFloat1.a1(i, paramFloat1.jdField_field_231_of_type_JavaxVecmathQuat4f);
      paramFloat1.a3(i, paramFloat1.jdField_field_231_of_type_JavaxVecmathVector3f);
    }
    else
    {
      int j = 0;
      int k = 1;
      for (int m = 0; (m < i) && (paramFloat1.a2(m) < paramFloat2); m++)
      {
        j = m;
        k = m + 1;
      }
      paramFloat2 = (paramFloat2 - paramFloat1.a2(j)) / (paramFloat1.a2(k) - paramFloat1.a2(j));
      paramFloat1.a1(j, paramFloat1.jdField_field_231_of_type_JavaxVecmathQuat4f);
      paramFloat1.a3(j, paramFloat1.jdField_field_231_of_type_JavaxVecmathVector3f);
      paramFloat1.a1(k, paramFloat1.jdField_field_232_of_type_JavaxVecmathQuat4f);
      paramFloat1.a3(k, paramFloat1.jdField_field_232_of_type_JavaxVecmathVector3f);
      localQuat4f1 = paramFloat1.jdField_field_231_of_type_JavaxVecmathQuat4f;
      f1 = paramFloat2;
      localQuat4f2 = paramFloat1.jdField_field_232_of_type_JavaxVecmathQuat4f;
      Quat4f localQuat4f3 = localQuat4f2;
      Quat4f localQuat4f4;
      f2 = (localQuat4f4 = localQuat4f1).field_599 * localQuat4f3.field_599 + localQuat4f4.field_596 * localQuat4f3.field_596 + localQuat4f4.field_597 * localQuat4f3.field_597 + localQuat4f4.field_598 * localQuat4f3.field_598;
      f3 = 1.0F - f1;
      if (f2 < 0.0F)
      {
        localQuat4f1.field_596 = (f3 * localQuat4f1.field_596 - f1 * localQuat4f2.field_596);
        localQuat4f1.field_597 = (f3 * localQuat4f1.field_597 - f1 * localQuat4f2.field_597);
        localQuat4f1.field_598 = (f3 * localQuat4f1.field_598 - f1 * localQuat4f2.field_598);
        localQuat4f1.field_599 = (f3 * localQuat4f1.field_599 - f1 * localQuat4f2.field_599);
      }
      else
      {
        localQuat4f1.field_596 = (f3 * localQuat4f1.field_596 + f1 * localQuat4f2.field_596);
        localQuat4f1.field_597 = (f3 * localQuat4f1.field_597 + f1 * localQuat4f2.field_597);
        localQuat4f1.field_598 = (f3 * localQuat4f1.field_598 + f1 * localQuat4f2.field_598);
        localQuat4f1.field_599 = (f3 * localQuat4f1.field_599 + f1 * localQuat4f2.field_599);
      }
      localQuat4f1.normalize();
      paramFloat1.jdField_field_231_of_type_JavaxVecmathVector3f.interpolate(paramFloat1.jdField_field_232_of_type_JavaxVecmathVector3f, paramFloat2);
    }
    if (paramclass_1028 != 1.0F)
    {
      field_233.set(0.0F, 0.0F, 0.0F, 1.0F);
      localQuat4f1 = paramFloat1.jdField_field_231_of_type_JavaxVecmathQuat4f;
      f1 = 1.0F - paramclass_1028;
      localQuat4f2 = field_233;
      if ((localQuat4f1.field_596 != localQuat4f2.field_596) || (localQuat4f1.field_597 != localQuat4f2.field_597) || (localQuat4f1.field_598 != localQuat4f2.field_598) || (localQuat4f1.field_599 != localQuat4f2.field_599))
      {
        if ((f2 = localQuat4f1.field_596 * localQuat4f2.field_596 + localQuat4f1.field_597 * localQuat4f2.field_597 + localQuat4f1.field_598 * localQuat4f2.field_598 + localQuat4f1.field_599 * localQuat4f2.field_599) < 0.0F)
        {
          localQuat4f2.field_596 = (-localQuat4f2.field_596);
          localQuat4f2.field_597 = (-localQuat4f2.field_597);
          localQuat4f2.field_598 = (-localQuat4f2.field_598);
          localQuat4f2.field_599 = (-localQuat4f2.field_599);
          f2 = -f2;
        }
        f3 = 1.0F - f1;
        float f4 = f1;
        if (1.0F - f2 > 0.1F)
        {
          f2 = FastMath.b(f2);
          paramFloat2 = 1.0F / FastMath.h(f2);
          f3 = FastMath.h(f3 * f2) * paramFloat2;
          f4 = FastMath.h(f1 * f2) * paramFloat2;
        }
        localQuat4f1.field_596 = (f3 * localQuat4f1.field_596 + f4 * localQuat4f2.field_596);
        localQuat4f1.field_597 = (f3 * localQuat4f1.field_597 + f4 * localQuat4f2.field_597);
        localQuat4f1.field_598 = (f3 * localQuat4f1.field_598 + f4 * localQuat4f2.field_598);
        localQuat4f1.field_599 = (f3 * localQuat4f1.field_599 + f4 * localQuat4f2.field_599);
      }
      paramFloat1.jdField_field_231_of_type_JavaxVecmathVector3f.scale(paramclass_1028);
    }
    paramclass_1056.a59(paramFloat1.jdField_field_231_of_type_JavaxVecmathVector3f, paramFloat1.jdField_field_231_of_type_JavaxVecmathQuat4f);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1052
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */