import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.server.ServerStateInterface;

public final class class_717
  extends Thread
{
  private StateInterface jdField_field_983_of_type_OrgSchemaSchineNetworkStateInterface;
  private final boolean jdField_field_983_of_type_Boolean;
  private final ArrayList jdField_field_983_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_984_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList field_985 = new ArrayList();
  private final ArrayList field_986 = new ArrayList();
  private final Set jdField_field_983_of_type_JavaUtilSet = new HashSet();
  private boolean jdField_field_984_of_type_Boolean = true;
  
  public class_717(StateInterface paramStateInterface)
  {
    this.jdField_field_983_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_983_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
    setName((this.jdField_field_983_of_type_Boolean ? "[SERVER]" : "[CLIENT]") + "_CREATOR_THREAD");
  }
  
  public final void a(SegmentController paramSegmentController)
  {
    if (!this.jdField_field_983_of_type_Boolean) {
      synchronized (this.field_985)
      {
        this.field_985.add(paramSegmentController);
        return;
      }
    }
  }
  
  public final void b(SegmentController paramSegmentController)
  {
    if (!this.jdField_field_983_of_type_Boolean) {
      synchronized (this.field_986)
      {
        this.field_986.add(paramSegmentController);
        return;
      }
    }
  }
  
  private void a1()
  {
    System.currentTimeMillis();
    Object localObject4 = this;
    if (this.jdField_field_983_of_type_JavaUtilSet.isEmpty()) {
      synchronized (((class_717)localObject4).jdField_field_983_of_type_JavaUtilSet)
      {
        while (((class_717)localObject4).jdField_field_983_of_type_JavaUtilSet.isEmpty()) {
          ((class_717)localObject4).jdField_field_983_of_type_JavaUtilSet.wait(20000L);
        }
      }
    }
    if (((class_717)localObject4).jdField_field_984_of_type_Boolean)
    {
      ((class_717)localObject4).jdField_field_983_of_type_JavaUtilArrayList.clear();
      synchronized (((class_717)localObject4).jdField_field_983_of_type_JavaUtilSet)
      {
        ((class_717)localObject4).jdField_field_983_of_type_JavaUtilArrayList.addAll(((class_717)localObject4).jdField_field_983_of_type_JavaUtilSet);
        ((class_717)localObject4).jdField_field_984_of_type_Boolean = false;
      }
    }
    ??? = this.jdField_field_983_of_type_JavaUtilArrayList.size();
    try
    {
      for (Object localObject3 = 0; localObject3 < ???; localObject3++)
      {
        localObject4 = (SegmentController)this.jdField_field_983_of_type_JavaUtilArrayList.get(localObject3);
        int i = 0;
        if (((class_371)this.jdField_field_983_of_type_OrgSchemaSchineNetworkStateInterface).a7().containsKey(((SegmentController)localObject4).getId()))
        {
          if (((SegmentController)localObject4).getCreatorThread() != null) {
            i = (localObject4 = ((SegmentController)localObject4).getCreatorThread()).jdField_field_195_of_type_Boolean ? ((class_908)localObject4).jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().c3() : 0;
          }
          if (i != 0) {
            Thread.sleep(2L);
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      System.err.println("[CreatorThreadController] Exception handled " + localException);
    }
  }
  
  public final void a2(boolean paramBoolean, SegmentController paramSegmentController)
  {
    if (paramBoolean)
    {
      if (!this.jdField_field_983_of_type_JavaUtilSet.contains(paramSegmentController))
      {
        long l1 = System.currentTimeMillis();
        synchronized (this.jdField_field_983_of_type_JavaUtilSet)
        {
          this.jdField_field_983_of_type_JavaUtilSet.add(paramSegmentController);
          class_371.field_150 = this.jdField_field_983_of_type_JavaUtilSet.size();
          this.jdField_field_984_of_type_Boolean = true;
          this.jdField_field_983_of_type_JavaUtilSet.notify();
        }
        long l2;
        if ((l2 = System.currentTimeMillis() - l1) > 5L) {
          System.err.println("[CREATORTHREAD][CLIENT] WARNING: notify for " + paramSegmentController + " on queue " + this.jdField_field_983_of_type_JavaUtilSet.size() + " took " + l2 + " ms");
        }
      }
    }
    else if (this.jdField_field_983_of_type_JavaUtilSet.contains(paramSegmentController)) {
      synchronized (this.jdField_field_983_of_type_JavaUtilSet)
      {
        this.jdField_field_983_of_type_JavaUtilSet.remove(paramSegmentController);
        class_371.field_150 = this.jdField_field_983_of_type_JavaUtilSet.size();
        this.jdField_field_984_of_type_Boolean = true;
        return;
      }
    }
  }
  
  public final void run()
  {
    try
    {
      if (!this.jdField_field_983_of_type_Boolean)
      {
        while (((class_371)this.jdField_field_983_of_type_OrgSchemaSchineNetworkStateInterface).a20() == null) {
          Thread.sleep(70L);
        }
        localObject = this;
        localObject = new class_711((class_717)localObject);
        (localObject = new Thread((Runnable)localObject, "[CLIENT]RequestNewSegments")).setPriority(3);
        ((Thread)localObject).start();
        for (;;)
        {
          a1();
        }
      }
      return;
    }
    catch (Exception localException)
    {
      Object localObject;
      (localObject = localException).printStackTrace();
      System.err.println("Creator Thread DIED!!!");
      class_29.a12((Exception)localObject);
      System.exit(0);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_717
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */