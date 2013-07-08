import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.FactoryAddOnInterface;
import org.schema.game.common.controller.elements.ManagerContainerFactoryAddOn;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_283
  implements class_965
{
  private final HashMap jdField_field_98_of_type_JavaUtilHashMap = new HashMap();
  private final class_371 jdField_field_98_of_type_Class_371;
  
  public class_283(class_371 paramclass_371)
  {
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
  }
  
  public final void a() {}
  
  public final void d()
  {
    Object localObject1 = this.jdField_field_98_of_type_Class_371.a7().values().iterator();
    Object localObject3;
    while (((Iterator)localObject1).hasNext()) {
      if (((((localObject2 = (class_797)((Iterator)localObject1).next()) instanceof class_864)) || ((localObject2 instanceof class_737))) && (!this.jdField_field_98_of_type_JavaUtilHashMap.containsKey(localObject2)))
      {
        localObject3 = new class_285((SegmentController)localObject2);
        this.jdField_field_98_of_type_JavaUtilHashMap.put((SegmentController)localObject2, localObject3);
      }
    }
    localObject1 = new ArrayList();
    Object localObject2 = this.jdField_field_98_of_type_JavaUtilHashMap.keySet().iterator();
    while (((Iterator)localObject2).hasNext()) {
      if ((localObject3 = (SegmentController)((Iterator)localObject2).next()).getSectorId() != this.jdField_field_98_of_type_Class_371.a8()) {
        ((ArrayList)localObject1).add(localObject3);
      }
    }
    localObject2 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (SegmentController)((Iterator)localObject2).next();
      if ((localObject1 = (class_285)this.jdField_field_98_of_type_JavaUtilHashMap.remove(localObject3)) != null) {
        ((class_285)localObject1).a();
      }
    }
  }
  
  public final void b()
  {
    class_1376.field_1577.c();
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilHashMap.values().iterator();
    while (localIterator.hasNext())
    {
      class_285 localclass_285;
      float f = ((FactoryAddOnInterface)((class_798)(localclass_285 = (class_285)localIterator.next()).a21()).a()).getFactory().getAccumulated() / 5.0F;
      GlUtil.a33(class_1376.field_1577, "time", f);
      localclass_285.b();
    }
    class_1377.e();
  }
  
  public final void c() {}
  
  public final void a19(class_753 paramclass_753)
  {
    class_285 localclass_285;
    if ((localclass_285 = (class_285)this.jdField_field_98_of_type_JavaUtilHashMap.get(paramclass_753)) != null)
    {
      localclass_285.d();
      return;
    }
    System.err.println("[CLIENT][ConnectionDrawer] WARNING segController to update not found!!!!!!!!!!! searching " + paramclass_753);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_283
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */