import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class class_1384
{
  private class_986 jdField_field_1580_of_type_Class_986;
  private final Int2ObjectOpenHashMap jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
  private final Int2ObjectOpenHashMap jdField_field_1581_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
  private boolean jdField_field_1580_of_type_Boolean;
  private Transform[] jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform;
  private final HashMap jdField_field_1580_of_type_JavaUtilHashMap = new HashMap();
  
  public final Transform[] a()
  {
    if (this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform == null)
    {
      this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform = new Transform[this.jdField_field_1581_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size()];
      for (int i = 0; i < this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++)
      {
        this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform[i] = new Transform();
        this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform[i].setIdentity();
      }
      if ((!jdField_field_1581_of_type_Boolean) && (this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform.length != 29)) {
        throw new AssertionError(this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform.length);
      }
    }
    Iterator localIterator = this.jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Transform localTransform = (Transform)this.jdField_field_1581_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get((Integer)localEntry.getKey());
      ((class_986)localEntry.getValue()).a14(localTransform);
      this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform[((class_986)localEntry.getValue()).jdField_field_98_of_type_Int].set(localTransform);
    }
    return this.jdField_field_1580_of_type_ArrayOfComBulletphysicsLinearmathTransform;
  }
  
  public final class_1034 a1(String paramString)
  {
    class_1034 localclass_1034;
    if ((localclass_1034 = (class_1034)this.jdField_field_1580_of_type_JavaUtilHashMap.get(paramString)) == null) {
      throw new IllegalArgumentException("animation '" + paramString + "' not found in " + this.jdField_field_1580_of_type_JavaUtilHashMap);
    }
    return localclass_1034;
  }
  
  public final Collection a2()
  {
    return this.jdField_field_1580_of_type_JavaUtilHashMap.keySet();
  }
  
  public final class_986 a3(String paramString)
  {
    return (class_986)this.jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(a5(paramString));
  }
  
  public final int a4()
  {
    return this.jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size();
  }
  
  public final int a5(String paramString)
  {
    Iterator localIterator = this.jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator();
    while (localIterator.hasNext())
    {
      class_986 localclass_986;
      if ((localclass_986 = (class_986)localIterator.next()).jdField_field_98_of_type_JavaLangString.equals(paramString)) {
        return localclass_986.jdField_field_98_of_type_Int;
      }
    }
    System.err.println("WARNING: bone not found in skeleton: " + paramString);
    if (!jdField_field_1581_of_type_Boolean) {
      throw new AssertionError();
    }
    return -1;
  }
  
  public final Int2ObjectOpenHashMap a6()
  {
    return this.jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap;
  }
  
  public final class_986 a7()
  {
    return this.jdField_field_1580_of_type_Class_986;
  }
  
  public final void a8(class_941 paramclass_941)
  {
    class_1384 localclass_1384 = this;
    Iterator localIterator = this.jdField_field_1580_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      localclass_1384.jdField_field_1581_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(localInteger, localTransform);
    }
    this.jdField_field_1580_of_type_Class_986.a1(paramclass_941);
    this.jdField_field_1580_of_type_Class_986.e();
    this.jdField_field_1580_of_type_Boolean = true;
  }
  
  public final boolean a9()
  {
    return this.jdField_field_1580_of_type_Boolean;
  }
  
  public final void a10()
  {
    this.jdField_field_1580_of_type_Class_986.d();
  }
  
  public final void a11(List paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      class_1034 localclass_1034 = (class_1034)paramList.next();
      this.jdField_field_1580_of_type_JavaUtilHashMap.put(localclass_1034.field_1303, localclass_1034);
    }
  }
  
  public final void a12(class_986 paramclass_986)
  {
    this.jdField_field_1580_of_type_Class_986 = paramclass_986;
  }
  
  public final void b(class_941 paramclass_941)
  {
    if (!this.jdField_field_1580_of_type_Boolean) {
      a8(paramclass_941);
    }
    this.jdField_field_1580_of_type_Class_986.a1(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1384
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */