import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.HashSet;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.common.data.world.SegmentData;

public final class class_215
  extends Thread
{
  private class_386 jdField_field_638_of_type_Class_386;
  private class_657 jdField_field_638_of_type_Class_657;
  private class_657 field_639;
  private class_213 jdField_field_638_of_type_Class_213;
  
  public class_215(SegmentDrawer paramSegmentDrawer, class_213 paramclass_213)
  {
    super("LightUpdate" + SegmentDrawer.jdField_field_98_of_type_Int++);
    setPriority(4);
    this.jdField_field_638_of_type_Class_386 = new class_386();
    this.jdField_field_638_of_type_Class_213 = paramclass_213;
  }
  
  public final void a(class_657 paramclass_657)
  {
    synchronized (this)
    {
      this.jdField_field_638_of_type_Class_657 = paramclass_657;
      notify();
      return;
    }
  }
  
  private void a1(class_657 paramclass_657, CubeMeshBufferContainer paramCubeMeshBufferContainer, class_400 paramclass_400)
  {
    for (;;)
    {
      try
      {
        if ((!paramclass_657.g()) && (paramclass_657.a16() != null)) {
          synchronized (localSegmentData1 = paramclass_657.a16())
          {
            SegmentData localSegmentData1;
            CubeMeshBufferContainer localCubeMeshBufferContainer = paramCubeMeshBufferContainer;
            SegmentData localSegmentData2 = localSegmentData1;
            class_215 localclass_215 = this;
            if (localSegmentData2.getSize() > 0)
            {
              if ((!jdField_field_638_of_type_Boolean) && (localSegmentData2 == null)) {
                throw new AssertionError();
              }
              localclass_215.jdField_field_638_of_type_Class_386.a7(localCubeMeshBufferContainer);
            }
            localCubeMeshBufferContainer = paramCubeMeshBufferContainer;
            localSegmentData2 = localSegmentData1;
            localclass_215 = this;
            if ((!jdField_field_638_of_type_Boolean) && (localSegmentData2 == null)) {
              throw new AssertionError();
            }
            localclass_215.jdField_field_638_of_type_Class_386.a1(localSegmentData2, localCubeMeshBufferContainer);
            SegmentDrawer.a64(paramclass_400, localSegmentData1, paramCubeMeshBufferContainer);
            return;
          }
        }
        return;
      }
      catch (Exception localException2)
      {
        Exception localException1;
        (localException1 = localException2).printStackTrace();
        System.err.println("[CLIENT] Exception: " + localException1.getClass().getSimpleName() + " in computing Lighting. retrying");
      }
    }
  }
  
  public final void run()
  {
    try
    {
      while (!class_933.a1())
      {
        synchronized (this)
        {
          while ((this.jdField_field_638_of_type_Class_657 == null) && (this.field_639 == null)) {
            wait();
          }
          this.field_639 = this.jdField_field_638_of_type_Class_657;
          this.jdField_field_638_of_type_Class_657 = null;
        }
        class_657 localclass_657 = this.field_639;
        ??? = this;
        if ((!jdField_field_638_of_type_Boolean) && (localclass_657.b5() != null)) {
          throw new AssertionError();
        }
        synchronized (localclass_657.field_34)
        {
          class_400 localclass_400 = SegmentDrawer.jdField_field_98_of_type_Class_394.a3(localclass_657);
          localclass_657.a33(localclass_400);
          CubeMeshBufferContainer localCubeMeshBufferContainer = localclass_657.a31();
          ((class_215)???).a1(localclass_657, localCubeMeshBufferContainer, localclass_400);
          synchronized (((class_215)???).jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
          {
            int i;
            if ((i = ((class_215)???).jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.indexOf(localclass_657)) >= 0)
            {
              if ((!jdField_field_638_of_type_Boolean) && (localclass_657.getId() == ((class_657)((class_215)???).jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).getId())) {
                throw new AssertionError();
              }
              synchronized (SegmentDrawer.a65(((class_215)???).jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer))
              {
                SegmentDrawer.a65(((class_215)???).jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer).add(((class_215)???).jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i));
              }
              localObject1.jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(i);
            }
            localObject1.jdField_field_638_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localclass_657);
          }
        }
        localObject3.jdField_field_638_of_type_Class_213.field_637 += 1;
        Thread.sleep(3L);
        this.jdField_field_638_of_type_Class_213.a(this, this.field_639, true);
        this.field_639 = null;
      }
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_215
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */