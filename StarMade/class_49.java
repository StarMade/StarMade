import java.text.DecimalFormat;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.common.FastMath;

public final class class_49
{
  public static Vector3f a(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3)
  {
    Vector3f localVector3f;
    (localVector3f = new Vector3f()).sub(paramVector3f1, paramVector3f3);
    if (paramVector3f2.lengthSquared() == 0.0F) {
      return localVector3f;
    }
    paramVector3f2.dot(paramVector3f2);
    double d1 = paramVector3f2.dot(paramVector3f2) - paramFloat * paramFloat;
    double d2 = 2.0F * localVector3f.dot(paramVector3f2);
    double d3 = localVector3f.dot(localVector3f);
    double d4 = (float)Math.sqrt(Math.abs(d2 * d2 - d1 * 4.0D * d3));
    double d5 = (-d2 - d4) / (d1 * 2.0D);
    double d6 = (-d2 + d4) / (d1 * 2.0D);
    double d7;
    if ((d5 > d6) && (d6 > 0.0D)) {
      d7 = d6;
    } else {
      d7 = d5;
    }
    paramFloat = new Vector3f();
    (paramVector3f2 = new Vector3f(paramVector3f2)).scale((float)d7);
    paramFloat.add(paramVector3f1, paramVector3f2);
    (paramVector3f1 = new Vector3f()).sub(paramFloat, paramVector3f3);
    return paramVector3f1;
  }
  
  public static void a1(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    paramVector3f1.field_615 = FastMath.a5(paramVector3f1.field_615, paramVector3f2.field_615, paramVector3f3.field_615);
    paramVector3f1.field_616 = FastMath.a5(paramVector3f1.field_616, paramVector3f2.field_616, paramVector3f3.field_616);
    paramVector3f1.field_617 = FastMath.a5(paramVector3f1.field_617, paramVector3f2.field_617, paramVector3f3.field_617);
  }
  
  public static float a2(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    float f = paramVector3f1.field_615 * paramVector3f2.field_615 + paramVector3f1.field_616 * paramVector3f2.field_616;
    return FastMath.a3(paramVector3f1.field_615 * paramVector3f2.field_616 - paramVector3f1.field_616 * paramVector3f2.field_615, f);
  }
  
  public static boolean a3(Vector3f paramVector3f)
  {
    return (Float.isNaN(paramVector3f.field_615)) || (Float.isNaN(paramVector3f.field_616)) || (Float.isNaN(paramVector3f.field_617));
  }
  
  public static void a4(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    if (paramVector3f2.field_615 > paramVector3f1.field_615) {
      paramVector3f1.field_615 = paramVector3f2.field_615;
    }
    if (paramVector3f2.field_616 > paramVector3f1.field_616) {
      paramVector3f1.field_616 = paramVector3f2.field_616;
    }
    if (paramVector3f2.field_617 > paramVector3f1.field_617) {
      paramVector3f1.field_617 = paramVector3f2.field_617;
    }
  }
  
  public static void b(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    if (paramVector3f2.field_615 < paramVector3f1.field_615) {
      paramVector3f1.field_615 = paramVector3f2.field_615;
    }
    if (paramVector3f2.field_616 < paramVector3f1.field_616) {
      paramVector3f1.field_616 = paramVector3f2.field_616;
    }
    if (paramVector3f2.field_617 < paramVector3f1.field_617) {
      paramVector3f1.field_617 = paramVector3f2.field_617;
    }
  }
  
  public static void a5(Vector4f paramVector4f)
  {
    if ((paramVector4f.field_596 == 0.0F) && (paramVector4f.field_597 == 0.0F) && (paramVector4f.field_598 == 0.0F))
    {
      paramVector4f.field_599 = 0.0F;
      return;
    }
    float f = FastMath.m(paramVector4f.field_596 * paramVector4f.field_596 + paramVector4f.field_597 * paramVector4f.field_597 + paramVector4f.field_598 * paramVector4f.field_598);
    paramVector4f.field_596 /= f;
    paramVector4f.field_597 /= f;
    paramVector4f.field_598 /= f;
    paramVector4f.field_599 = ((float)(2.0D * FastMath.a2(paramVector4f.field_599)));
  }
  
  public static float a6(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return FastMath.l(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 + paramFloat3 * paramFloat3);
  }
  
  static
  {
    new DecimalFormat("#,###,###,##0.00");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_49
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */