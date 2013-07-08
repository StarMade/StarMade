import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_402
  implements class_965
{
  private class_371 jdField_field_98_of_type_Class_371;
  public ArrayList field_98;
  private boolean jdField_field_98_of_type_Boolean = true;
  
  public class_402(class_371 paramclass_371)
  {
    this.jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    for (int i = 0; i < this.jdField_field_98_of_type_JavaUtilArrayList.size(); i++) {
      ((class_404)this.jdField_field_98_of_type_JavaUtilArrayList.get(i)).b();
    }
  }
  
  public final void c()
  {
    ((Mesh)class_969.a2().a4("Konata").a152().get(0)).a177().a9(class_333.field_135.field_1592);
    this.jdField_field_98_of_type_Boolean = false;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    for (int i = 0; i < this.jdField_field_98_of_type_JavaUtilArrayList.size(); i++)
    {
      class_941 localclass_941 = paramclass_941;
      class_404 localclass_404 = (class_404)this.jdField_field_98_of_type_JavaUtilArrayList.get(i);
      try
      {
        if (localclass_404.jdField_field_98_of_type_Class_748 == null) {
          localclass_404.d();
        }
        if (localclass_404.jdField_field_98_of_type_Class_748 != null) {
          try
          {
            boolean bool;
            if (((bool = localclass_404.jdField_field_98_of_type_Class_748.a132(class_367.field_713) | localclass_404.jdField_field_98_of_type_Class_748.a132(class_367.field_714) | localclass_404.jdField_field_98_of_type_Class_748.a132(class_367.field_711) | localclass_404.jdField_field_98_of_type_Class_748.a132(class_367.field_712) | localclass_404.jdField_field_98_of_type_Class_748.a132(class_367.field_715) | localclass_404.jdField_field_98_of_type_Class_748.a132(class_367.field_716)) != localclass_404.jdField_field_98_of_type_Boolean) && (localclass_404.jdField_field_98_of_type_Class_1028 != null))
            {
              if (bool) {
                localclass_404.jdField_field_98_of_type_Class_1028.a2("flying", 0.5F);
              } else {
                localclass_404.jdField_field_98_of_type_Class_1028.a2("default", 0.5F);
              }
              localclass_404.jdField_field_98_of_type_Boolean = bool;
            }
            localclass_404.jdField_field_98_of_type_Class_941 = localclass_941;
            localclass_404.jdField_field_98_of_type_Class_297.field_104 = (localclass_404.a40() + localclass_404.jdField_field_98_of_type_Class_748.a141().b());
          }
          catch (Exception localException)
          {
            System.err.println("DRAWABLE CHARACTER UPDATE FAILED: " + localException.getClass().getSimpleName() + ": " + localException.getMessage() + "; channel " + localclass_404.jdField_field_98_of_type_Class_1028 + ", PlayerState: " + localclass_404.jdField_field_98_of_type_Class_748);
          }
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        System.err.println(localIllegalArgumentException.getMessage());
      }
    }
  }
  
  public final void d()
  {
    while (this.jdField_field_98_of_type_JavaUtilArrayList.size() > 0) {
      ((class_404)this.jdField_field_98_of_type_JavaUtilArrayList.remove(0)).e();
    }
    Iterator localIterator = this.jdField_field_98_of_type_Class_371.a7().values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject;
      if (((localObject = (class_797)localIterator.next()) instanceof class_750))
      {
        localObject = new class_404((class_750)localObject);
        this.jdField_field_98_of_type_JavaUtilArrayList.add(localObject);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_402
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */