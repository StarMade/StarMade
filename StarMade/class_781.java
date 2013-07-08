import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import java.io.PrintStream;
import java.util.Iterator;

public class class_781
  implements class_80
{
  public boolean field_136;
  public String field_136;
  public String field_139;
  public String field_182;
  public float field_136;
  public int field_136;
  public long field_136;
  public int field_139;
  public int field_182;
  public static int field_183 = 10;
  
  public class_781(class_781 paramclass_781)
  {
    this.jdField_field_182_of_type_JavaLangString = "";
    this.jdField_field_182_of_type_Int = 10;
    this.jdField_field_136_of_type_Boolean = paramclass_781.jdField_field_136_of_type_Boolean;
    this.jdField_field_136_of_type_JavaLangString = new String(paramclass_781.jdField_field_136_of_type_JavaLangString);
    this.jdField_field_139_of_type_JavaLangString = new String(paramclass_781.jdField_field_139_of_type_JavaLangString);
    this.jdField_field_182_of_type_JavaLangString = new String(paramclass_781.jdField_field_182_of_type_JavaLangString);
    this.jdField_field_139_of_type_Int = paramclass_781.jdField_field_139_of_type_Int;
    this.jdField_field_182_of_type_Int = paramclass_781.jdField_field_182_of_type_Int;
  }
  
  public class_781()
  {
    this.jdField_field_182_of_type_JavaLangString = "";
    this.jdField_field_182_of_type_Int = 10;
  }
  
  public int hashCode()
  {
    return this.jdField_field_136_of_type_JavaLangString.hashCode();
  }
  
  public boolean equals(Object paramObject)
  {
    return this.jdField_field_136_of_type_JavaLangString.equals(((class_781)paramObject).jdField_field_136_of_type_JavaLangString);
  }
  
  public String toString()
  {
    return "CatalogPermission [catUID=" + this.jdField_field_136_of_type_JavaLangString + ", ownerUID=" + this.jdField_field_139_of_type_JavaLangString + ", description=" + this.jdField_field_182_of_type_JavaLangString + ", price=" + this.jdField_field_139_of_type_Int + ", permission=" + this.jdField_field_182_of_type_Int + "]";
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_JavaLangString = ((String)paramclass_69[0].a4());
    this.jdField_field_139_of_type_JavaLangString = ((String)paramclass_69[1].a4());
    this.jdField_field_182_of_type_Int = ((Integer)paramclass_69[2].a4()).intValue();
    this.jdField_field_139_of_type_Int = ((Integer)paramclass_69[3].a4()).intValue();
    this.jdField_field_182_of_type_JavaLangString = ((String)paramclass_69[4].a4());
    this.jdField_field_136_of_type_Long = ((Long)paramclass_69[5].a4()).longValue();
    this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[6].a4()).intValue();
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_556, null, this.jdField_field_136_of_type_JavaLangString);
    class_69 localclass_692 = new class_69(class_79.field_556, null, this.jdField_field_139_of_type_JavaLangString);
    class_69 localclass_693 = new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_182_of_type_Int));
    class_69 localclass_694 = new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_139_of_type_Int));
    class_69 localclass_695 = new class_69(class_79.field_556, null, this.jdField_field_182_of_type_JavaLangString);
    class_69 localclass_696 = new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_Long));
    class_69 localclass_697 = new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int));
    return new class_69(class_79.field_561, null, new class_69[] { localclass_691, localclass_692, localclass_693, localclass_694, localclass_695, localclass_696, localclass_697, new class_69(class_79.field_548, null, null) });
  }
  
  public final boolean a7()
  {
    return (this.jdField_field_182_of_type_Int & 0x2) == 2;
  }
  
  public final boolean b2()
  {
    return (this.jdField_field_182_of_type_Int & 0x1) == 1;
  }
  
  public final boolean c3()
  {
    return (this.jdField_field_182_of_type_Int & 0x4) == 4;
  }
  
  public final boolean d10()
  {
    return (this.jdField_field_182_of_type_Int & 0x10) == 16;
  }
  
  public final void a189(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean) {
      this.jdField_field_182_of_type_Int |= paramInt;
    } else {
      this.jdField_field_182_of_type_Int &= (paramInt ^ 0xFFFFFFFF);
    }
    System.err.println("SET PERMISSIONS: " + paramInt + ": " + paramBoolean + ": " + this.jdField_field_182_of_type_Int + " fac: " + b2() + "; others: " + a7() + "; home " + c3() + "; enemy " + d10());
  }
  
  public final void a190(Object2IntArrayMap paramObject2IntArrayMap)
  {
    if (paramObject2IntArrayMap.isEmpty())
    {
      this.jdField_field_136_of_type_Float = -1.0F;
      return;
    }
    float f1 = paramObject2IntArrayMap.size();
    float f2 = 0.0F;
    paramObject2IntArrayMap = paramObject2IntArrayMap.values().iterator();
    while (paramObject2IntArrayMap.hasNext())
    {
      int i = ((Integer)paramObject2IntArrayMap.next()).intValue();
      f2 += i;
    }
    this.jdField_field_136_of_type_Float = (f2 / f1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_781
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */