import javax.vecmath.Vector3f;
import org.schema.common.FastMath;

public class class_48
  implements Comparable
{
  public int field_475;
  public int field_476;
  public int field_477;
  
  public class_48() {}
  
  public class_48(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.jdField_field_475_of_type_Int = ((int)paramFloat1);
    this.field_476 = ((int)paramFloat2);
    this.field_477 = ((int)paramFloat3);
  }
  
  public class_48(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jdField_field_475_of_type_Int = paramInt1;
    this.field_476 = paramInt2;
    this.field_477 = paramInt3;
  }
  
  public class_48(Vector3f paramVector3f)
  {
    this.jdField_field_475_of_type_Int = ((int)paramVector3f.field_615);
    this.field_476 = ((int)paramVector3f.field_616);
    this.field_477 = ((int)paramVector3f.field_617);
  }
  
  public class_48(class_48 paramclass_48)
  {
    this.jdField_field_475_of_type_Int = paramclass_48.jdField_field_475_of_type_Int;
    this.field_476 = paramclass_48.field_476;
    this.field_477 = paramclass_48.field_477;
  }
  
  public final void a(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jdField_field_475_of_type_Int += paramInt1;
    this.field_476 += paramInt2;
    this.field_477 += paramInt3;
  }
  
  public final void a1(class_48 paramclass_48)
  {
    this.jdField_field_475_of_type_Int += paramclass_48.jdField_field_475_of_type_Int;
    this.field_476 += paramclass_48.field_476;
    this.field_477 += paramclass_48.field_477;
  }
  
  public final void a2()
  {
    this.jdField_field_475_of_type_Int /= 2;
    this.field_476 /= 2;
    this.field_477 /= 2;
  }
  
  public final boolean a3(int paramInt1, int paramInt2, int paramInt3)
  {
    return (this.jdField_field_475_of_type_Int == paramInt1) && (this.field_476 == paramInt2) && (this.field_477 == paramInt3);
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (class_48)paramObject;
      return (this.jdField_field_475_of_type_Int == paramObject.jdField_field_475_of_type_Int) && (this.field_476 == paramObject.field_476) && (this.field_477 == paramObject.field_477);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public int hashCode()
  {
    return ((this.jdField_field_475_of_type_Int ^ this.jdField_field_475_of_type_Int >>> 16) * 15 + (this.field_476 ^ this.field_476 >>> 16)) * 15 + (this.field_477 ^ this.field_477 >>> 16);
  }
  
  public final float a4()
  {
    return FastMath.l(this.jdField_field_475_of_type_Int * this.jdField_field_475_of_type_Int + this.field_476 * this.field_476 + this.field_477 * this.field_477);
  }
  
  public final void a5(int paramInt)
  {
    this.jdField_field_475_of_type_Int *= paramInt;
    this.field_476 *= paramInt;
    this.field_477 *= paramInt;
  }
  
  public final void b(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jdField_field_475_of_type_Int = paramInt1;
    this.field_476 = paramInt2;
    this.field_477 = paramInt3;
  }
  
  public final void b1(class_48 paramclass_48)
  {
    b(paramclass_48.jdField_field_475_of_type_Int, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public final void c(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jdField_field_475_of_type_Int -= paramInt1;
    this.field_476 -= paramInt2;
    this.field_477 -= paramInt3;
  }
  
  public final void c1(class_48 paramclass_48)
  {
    this.jdField_field_475_of_type_Int -= paramclass_48.jdField_field_475_of_type_Int;
    this.field_476 -= paramclass_48.field_476;
    this.field_477 -= paramclass_48.field_477;
  }
  
  public final void a6(class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_481.jdField_field_475_of_type_Int -= paramclass_482.jdField_field_475_of_type_Int;
    paramclass_481.field_476 -= paramclass_482.field_476;
    paramclass_481.field_477 -= paramclass_482.field_477;
  }
  
  public final void b2(class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_481.jdField_field_475_of_type_Int += paramclass_482.jdField_field_475_of_type_Int;
    paramclass_481.field_476 += paramclass_482.field_476;
    paramclass_481.field_477 += paramclass_482.field_477;
  }
  
  public String toString()
  {
    return "(" + this.jdField_field_475_of_type_Int + ", " + this.field_476 + ", " + this.field_477 + ")";
  }
  
  public final int a7(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return this.jdField_field_475_of_type_Int;
    case 1: 
      return this.field_476;
    case 2: 
      return this.field_477;
    }
    if (!jdField_field_475_of_type_Boolean) {
      throw new AssertionError(paramInt);
    }
    throw new NullPointerException(paramInt + " coord");
  }
  
  public static class_48 a8(String paramString)
  {
    if ((paramString = paramString.split(",")).length != 3) {
      throw new NumberFormatException("Wrong number of arguments");
    }
    return new class_48(Integer.parseInt(paramString[0].trim()), Integer.parseInt(paramString[1].trim()), Integer.parseInt(paramString[2].trim()));
  }
  
  public final void b3()
  {
    this.jdField_field_475_of_type_Int = (-this.jdField_field_475_of_type_Int);
    this.field_476 = (-this.field_476);
    this.field_477 = (-this.field_477);
  }
  
  public final void c2()
  {
    this.jdField_field_475_of_type_Int = Math.abs(this.jdField_field_475_of_type_Int);
    this.field_476 = Math.abs(this.field_476);
    this.field_477 = Math.abs(this.field_477);
  }
  
  static
  {
    jdField_field_475_of_type_Boolean = !q.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_48
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */