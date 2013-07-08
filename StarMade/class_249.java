import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.nio.ShortBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public final class class_249
{
  private static ObjectArrayList jdField_field_653_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
  private final Map jdField_field_653_of_type_JavaUtilMap = new HashMap();
  private final class_291[] jdField_field_653_of_type_ArrayOfClass_291 = new class_291[64];
  private int jdField_field_653_of_type_Int;
  private boolean jdField_field_653_of_type_Boolean;
  
  public final void a(class_798 paramclass_798)
  {
    class_291 localclass_291 = null;
    paramclass_798 = paramclass_798;
    (localclass_291 = (class_291)jdField_field_653_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(0)).jdField_field_98_of_type_Class_798 = paramclass_798;
    paramclass_798 = jdField_field_653_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty() ? new class_291(paramclass_798) : localclass_291;
    this.jdField_field_653_of_type_JavaUtilMap.put(paramclass_798.jdField_field_98_of_type_Class_798, paramclass_798);
  }
  
  public final void a1(Int2ObjectOpenHashMap paramInt2ObjectOpenHashMap)
  {
    Iterator localIterator1 = this.jdField_field_653_of_type_JavaUtilMap.values().iterator();
    while (localIterator1.hasNext())
    {
      localObject = (class_291)localIterator1.next();
      if (!paramInt2ObjectOpenHashMap.containsKey(((class_291)localObject).jdField_field_98_of_type_Class_798.a1().getId()))
      {
        (localObject = localObject).jdField_field_98_of_type_Class_798 = null;
        jdField_field_653_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localObject);
        localIterator1.remove();
      }
    }
    Object localObject = paramInt2ObjectOpenHashMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((((paramInt2ObjectOpenHashMap = (class_797)((Iterator)localObject).next()) instanceof class_798)) && (!this.jdField_field_653_of_type_JavaUtilMap.containsKey(paramInt2ObjectOpenHashMap))) {
        a((class_798)paramInt2ObjectOpenHashMap);
      }
    }
    for (int i = 0; i < this.jdField_field_653_of_type_Int; i++) {
      this.jdField_field_653_of_type_ArrayOfClass_291[i] = null;
    }
    this.jdField_field_653_of_type_Int = 0;
    Iterator localIterator2 = this.jdField_field_653_of_type_JavaUtilMap.values().iterator();
    while (localIterator2.hasNext()) {
      if ((paramInt2ObjectOpenHashMap = (class_291)localIterator2.next()).a5()) {
        a3(paramInt2ObjectOpenHashMap, true);
      }
    }
  }
  
  public final void a2()
  {
    if (this.jdField_field_653_of_type_Boolean) {
      for (int i = 0; i < this.jdField_field_653_of_type_Int; i++) {
        this.jdField_field_653_of_type_ArrayOfClass_291[i].b();
      }
    }
  }
  
  public static void b() {}
  
  private void a3(class_291 paramclass_291, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (this.jdField_field_653_of_type_Int < this.jdField_field_653_of_type_ArrayOfClass_291.length)
      {
        this.jdField_field_653_of_type_ArrayOfClass_291[this.jdField_field_653_of_type_Int] = paramclass_291;
        this.jdField_field_653_of_type_Int += 1;
      }
    }
    else if (this.jdField_field_653_of_type_Int < this.jdField_field_653_of_type_ArrayOfClass_291.length) {
      for (paramBoolean = false; paramBoolean < this.jdField_field_653_of_type_ArrayOfClass_291.length; paramBoolean++) {
        if (this.jdField_field_653_of_type_ArrayOfClass_291[paramBoolean] == paramclass_291)
        {
          this.jdField_field_653_of_type_ArrayOfClass_291[paramBoolean] = this.jdField_field_653_of_type_ArrayOfClass_291[(this.jdField_field_653_of_type_Int - 1)];
          this.jdField_field_653_of_type_Int -= 1;
          break;
        }
      }
    }
    this.jdField_field_653_of_type_Boolean = (this.jdField_field_653_of_type_Int > 0);
  }
  
  public final void a4(class_657 paramclass_657)
  {
    ShortBuffer localShortBuffer;
    int i = (localShortBuffer = paramclass_657.b4().field_1665).position();
    localShortBuffer.rewind();
    class_35 localclass_35 = new class_35();
    new class_48();
    class_796 localclass_796 = new class_796();
    class_291 localclass_291;
    if ((localclass_291 = (class_291)this.jdField_field_653_of_type_JavaUtilMap.get(paramclass_657.a15())) != null)
    {
      boolean bool1 = localclass_291.a5();
      Object localObject2 = paramclass_657;
      Object localObject1 = localclass_291;
      for (int m = 0; m < ((class_291)localObject1).jdField_field_98_of_type_Class_226.b1(); m++)
      {
        ((class_291)localObject1).jdField_field_98_of_type_Class_226.field_9.a4(m, ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f);
        if ((((Segment)localObject2).field_34.field_475 <= ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.field_615 + 8.0F) && (((Segment)localObject2).field_34.field_476 <= ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.field_616 + 8.0F) && (((Segment)localObject2).field_34.field_477 <= ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.field_617 + 8.0F) && (((Segment)localObject2).field_34.field_475 + 16 > ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.field_615 + 8.0F) && (((Segment)localObject2).field_34.field_476 + 16 > ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.field_616 + 8.0F) && (((Segment)localObject2).field_34.field_477 + 16 > ((class_291)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.field_617 + 8.0F))
        {
          ((class_291)localObject1).jdField_field_98_of_type_Class_226.b(m);
          m--;
        }
      }
      for (int j = 0; j < i; j++)
      {
        SegmentData.getPositionFromIndex(localShortBuffer.get(), localclass_35);
        localclass_796.a(paramclass_657, localclass_35);
        localObject2 = localclass_796;
        (localObject1 = localclass_291).jdField_field_98_of_type_Class_226.b1();
        localObject1 = ((class_796)localObject2).a2(((class_291)localObject1).jdField_field_98_of_type_Class_48);
        class_226 localclass_226;
        (localclass_226 = ((class_291)localObject1).jdField_field_98_of_type_Class_226).field_9.set(0.0F, 0.0F, 0.0F);
        int k = localclass_226.a14(localclass_226.field_9, localclass_226.field_9);
        localclass_226.field_9.a7(k, ((class_48)localObject1).field_475 - class_747.field_136.field_475, ((class_48)localObject1).field_476 - class_747.field_136.field_476, ((class_48)localObject1).field_477 - class_747.field_136.field_477);
        localclass_226.field_9.b1(k, ((class_48)localObject1).field_475, ((class_48)localObject1).field_476, ((class_48)localObject1).field_477);
      }
      boolean bool2 = localclass_291.a5();
      if ((bool1) && (!bool2)) {
        a3(localclass_291, false);
      } else if ((!bool1) && (bool2)) {
        a3(localclass_291, true);
      }
    }
    localShortBuffer.rewind();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_249
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */