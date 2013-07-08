import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_220
  implements Observer, class_965
{
  private static ArrayList jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
  public static float field_98;
  private final Map jdField_field_98_of_type_JavaUtilMap = new HashMap();
  private int jdField_field_98_of_type_Int;
  private final ArrayList field_106 = new ArrayList();
  private class_222[] jdField_field_98_of_type_ArrayOfClass_222 = new class_222[''];
  
  public final void a2(class_798 paramclass_798)
  {
    class_222 localclass_222;
    (localclass_222 = (class_222)jdField_field_98_of_type_JavaUtilArrayList.remove(0)).a2(paramclass_798);
    this.field_106.add(jdField_field_98_of_type_JavaUtilArrayList.isEmpty() ? new class_222(paramclass_798) : localclass_222);
  }
  
  public final void a() {}
  
  public final void d()
  {
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilMap.values().iterator();
    while (localIterator.hasNext())
    {
      class_222 localclass_222;
      (localclass_222 = (class_222)localIterator.next()).deleteObserver(this);
      (localclass_222 = localclass_222).d();
      jdField_field_98_of_type_JavaUtilArrayList.add(localclass_222);
    }
    this.jdField_field_98_of_type_Int = 0;
    this.jdField_field_98_of_type_JavaUtilMap.clear();
  }
  
  public final void b()
  {
    if (!class_949.field_1195.b1()) {}
  }
  
  public final class_222 a3(SegmentController paramSegmentController)
  {
    return (class_222)this.jdField_field_98_of_type_JavaUtilMap.get(paramSegmentController);
  }
  
  public final void c()
  {
    class_969.a2().a4("Sphere").a152().get(0);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if (((Boolean)paramObject).booleanValue() == true)
    {
      paramObject = 1;
      for (int i = 0; (i < this.jdField_field_98_of_type_ArrayOfClass_222.length) && (i < this.jdField_field_98_of_type_Int); i++) {
        if (this.jdField_field_98_of_type_ArrayOfClass_222[i] == paramObservable)
        {
          paramObject = 0;
          break;
        }
      }
      if ((paramObject != 0) && (this.jdField_field_98_of_type_Int < this.jdField_field_98_of_type_ArrayOfClass_222.length))
      {
        this.jdField_field_98_of_type_ArrayOfClass_222[this.jdField_field_98_of_type_Int] = ((class_222)paramObservable);
        this.jdField_field_98_of_type_Int += 1;
      }
      return;
    }
    if (this.jdField_field_98_of_type_Int < this.jdField_field_98_of_type_ArrayOfClass_222.length) {
      for (paramObject = 0; paramObject < this.jdField_field_98_of_type_ArrayOfClass_222.length; paramObject++) {
        if (this.jdField_field_98_of_type_ArrayOfClass_222[paramObject] == (class_222)paramObservable)
        {
          this.jdField_field_98_of_type_ArrayOfClass_222[paramObject] = this.jdField_field_98_of_type_ArrayOfClass_222[(this.jdField_field_98_of_type_Int - 1)];
          this.jdField_field_98_of_type_Int -= 1;
          return;
        }
      }
    }
  }
  
  public final void a1(class_941 paramclass_941)
  {
    if ((class_220.jdField_field_98_of_type_Float = jdField_field_98_of_type_Float + paramclass_941.a()) > 1.0F) {
      jdField_field_98_of_type_Float -= (int)jdField_field_98_of_type_Float;
    }
    while (!this.field_106.isEmpty())
    {
      class_222 localclass_222;
      (localclass_222 = (class_222)this.field_106.remove(0)).addObserver(this);
      this.jdField_field_98_of_type_JavaUtilMap.put(localclass_222.field_98, localclass_222);
    }
    for (int i = 0; i < this.jdField_field_98_of_type_Int; i++) {
      this.jdField_field_98_of_type_ArrayOfClass_222[i].a1(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_220
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */