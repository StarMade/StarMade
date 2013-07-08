import com.bulletphysics.linearmath.AabbUtil2;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import javax.vecmath.Vector3f;

public abstract class class_1068
{
  protected final class_48 field_240;
  protected final class_48 field_244;
  public final int field_240;
  private class_1068[] field_240;
  private final class_1068[] jdField_field_244_of_type_ArrayOfClass_1068;
  private final int jdField_field_244_of_type_Int;
  
  public class_1068(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482, int paramInt1, int paramInt2)
  {
    this.jdField_field_244_of_type_ArrayOfClass_1068 = paramArrayOfclass_1068;
    this.jdField_field_240_of_type_Int = paramInt1;
    this.jdField_field_244_of_type_Int = paramInt2;
    if (paramInt2 % 2 != 0)
    {
      paramArrayOfclass_1068 = paramclass_481.field_475;
      paramclass_481.field_475 = paramclass_481.field_477;
      paramclass_481.field_477 = paramArrayOfclass_1068;
      paramArrayOfclass_1068 = paramclass_482.field_475;
      paramclass_482.field_475 = paramclass_482.field_477;
      paramclass_482.field_477 = paramArrayOfclass_1068;
    }
    a9(paramclass_481, paramclass_482);
    this.jdField_field_240_of_type_Class_48 = paramclass_481;
    this.jdField_field_244_of_type_Class_48 = paramclass_482;
  }
  
  public final void a7()
  {
    ObjectArrayList localObjectArrayList = new ObjectArrayList();
    Vector3f localVector3f1 = new Vector3f(this.jdField_field_240_of_type_Class_48.field_475, this.jdField_field_240_of_type_Class_48.field_476, this.jdField_field_240_of_type_Class_48.field_477);
    Vector3f localVector3f2 = new Vector3f(this.jdField_field_244_of_type_Class_48.field_475, this.jdField_field_244_of_type_Class_48.field_476, this.jdField_field_244_of_type_Class_48.field_477);
    Vector3f localVector3f3 = new Vector3f();
    Vector3f localVector3f4 = new Vector3f();
    for (int i = 0; i < this.jdField_field_244_of_type_ArrayOfClass_1068.length; i++)
    {
      class_1068 localclass_1068 = this.jdField_field_244_of_type_ArrayOfClass_1068[i];
      localVector3f3.set(localclass_1068.jdField_field_240_of_type_Class_48.field_475, localclass_1068.jdField_field_240_of_type_Class_48.field_476, localclass_1068.jdField_field_240_of_type_Class_48.field_477);
      localVector3f4.set(localclass_1068.jdField_field_244_of_type_Class_48.field_475, localclass_1068.jdField_field_244_of_type_Class_48.field_476, localclass_1068.jdField_field_244_of_type_Class_48.field_477);
      if ((localclass_1068 != this) && (AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4))) {
        localObjectArrayList.add(localclass_1068);
      }
    }
    this.jdField_field_240_of_type_ArrayOfClass_1068 = new class_1068[localObjectArrayList.size()];
    for (i = 0; i < localObjectArrayList.size(); i++) {
      this.jdField_field_240_of_type_ArrayOfClass_1068[i] = ((class_1068)localObjectArrayList.get(i));
    }
  }
  
  public boolean a3(class_48 paramclass_48)
  {
    return (paramclass_48.field_475 < this.jdField_field_244_of_type_Class_48.field_475) && (paramclass_48.field_475 >= this.jdField_field_240_of_type_Class_48.field_475) && (paramclass_48.field_476 < this.jdField_field_244_of_type_Class_48.field_476) && (paramclass_48.field_476 >= this.jdField_field_240_of_type_Class_48.field_476) && (paramclass_48.field_477 < this.jdField_field_244_of_type_Class_48.field_477) && (paramclass_48.field_477 >= this.jdField_field_240_of_type_Class_48.field_477);
  }
  
  public final short b1(class_48 paramclass_48)
  {
    for (int i = 0; i < this.jdField_field_240_of_type_ArrayOfClass_1068.length; i++) {
      if ((this.jdField_field_240_of_type_ArrayOfClass_1068[i].jdField_field_240_of_type_Int > this.jdField_field_240_of_type_Int) && (this.jdField_field_240_of_type_ArrayOfClass_1068[i].a3(paramclass_48)) && ((j = this.jdField_field_240_of_type_ArrayOfClass_1068[i].b1(paramclass_48)) != 32767)) {
        return j;
      }
    }
    i = a(paramclass_48);
    for (int j = 0; (j < this.jdField_field_240_of_type_ArrayOfClass_1068.length) && ((i == 0) || (i == 32767)); j++) {
      if ((this.jdField_field_240_of_type_ArrayOfClass_1068[j].jdField_field_240_of_type_Int == this.jdField_field_240_of_type_Int) && (this.jdField_field_240_of_type_ArrayOfClass_1068[j].a3(paramclass_48))) {
        i = this.jdField_field_240_of_type_ArrayOfClass_1068[j].a(paramclass_48);
      }
    }
    return i;
  }
  
  protected final class_48 a8(class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_482.b1(paramclass_481);
    paramclass_482.c1(this.jdField_field_240_of_type_Class_48);
    switch (this.jdField_field_244_of_type_Int)
    {
    case 0: 
      break;
    case 1: 
      paramclass_481 = paramclass_482.field_475;
      paramclass_482.field_475 = paramclass_482.field_477;
      paramclass_482.field_477 = (paramclass_481 - 1);
      break;
    case 2: 
      paramclass_482.field_477 = (this.jdField_field_244_of_type_Class_48.field_477 - this.jdField_field_240_of_type_Class_48.field_477 - paramclass_482.field_477);
      break;
    case 3: 
      paramclass_481 = paramclass_482.field_475;
      paramclass_482.field_475 = paramclass_482.field_477;
      paramclass_482.field_477 = paramclass_481;
      paramclass_482.field_477 = (this.jdField_field_244_of_type_Class_48.field_477 - this.jdField_field_240_of_type_Class_48.field_477 - paramclass_482.field_477);
    }
    return paramclass_482;
  }
  
  protected abstract short a(class_48 paramclass_48);
  
  public static void a9(class_48 paramclass_481, class_48 paramclass_482)
  {
    int i;
    if (paramclass_482.field_475 < paramclass_481.field_475)
    {
      i = paramclass_482.field_475 + 1;
      paramclass_481.field_475 += 1;
      paramclass_481.field_475 = i;
    }
    if (paramclass_482.field_476 < paramclass_481.field_476)
    {
      i = paramclass_482.field_476 + 1;
      paramclass_481.field_476 += 1;
      paramclass_481.field_476 = i;
    }
    if (paramclass_482.field_477 < paramclass_481.field_477)
    {
      i = paramclass_482.field_477 + 1;
      paramclass_481.field_477 += 1;
      paramclass_481.field_477 = i;
    }
  }
  
  public byte a6(class_48 paramclass_48)
  {
    return -1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1068
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */