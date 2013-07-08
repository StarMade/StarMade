import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.common.controller.SegmentBufferManager;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_219
  extends Thread
{
  private Vector3f jdField_field_640_of_type_JavaxVecmathVector3f = new Vector3f();
  protected ArrayList field_640;
  private ArrayList jdField_field_641_of_type_JavaUtilArrayList;
  private class_355 jdField_field_640_of_type_Class_355;
  private class_309 jdField_field_640_of_type_Class_309;
  private class_223 jdField_field_640_of_type_Class_223;
  private class_217 jdField_field_640_of_type_Class_217;
  public Object field_640;
  private HashSet jdField_field_640_of_type_JavaUtilHashSet = new HashSet();
  private final Vector3f jdField_field_641_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_642 = new Vector3f();
  
  public class_219(SegmentDrawer paramSegmentDrawer)
  {
    super("SegentSorter");
    this.jdField_field_640_of_type_JavaLangObject = new Object();
    setPriority(1);
    this.jdField_field_640_of_type_Class_355 = new class_355();
    this.jdField_field_640_of_type_Class_309 = new class_309();
    this.jdField_field_640_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_641_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_640_of_type_Class_223 = new class_223(this, (byte)0);
    this.jdField_field_640_of_type_Class_217 = new class_217(this, (byte)0);
  }
  
  public final void run()
  {
    while (!class_933.a1())
    {
      try
      {
        this.jdField_field_641_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
        class_355.a2(this.jdField_field_640_of_type_Class_355).set(this.jdField_field_641_of_type_JavaxVecmathVector3f);
        class_309.a2(this.jdField_field_640_of_type_Class_309).set(this.jdField_field_641_of_type_JavaxVecmathVector3f);
        this.jdField_field_640_of_type_Class_223.field_105.set(this.jdField_field_641_of_type_JavaxVecmathVector3f);
        System.currentTimeMillis();
        synchronized (SegmentDrawer.a68(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer))
        {
          ArrayList localArrayList = SegmentDrawer.a68(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer);
          localObject7 = this;
          for (int k = 0; k < localArrayList.size(); localObject6++) {
            synchronized (((SegmentBufferManager)(localObject8 = (SegmentController)localArrayList.get(k)).getSegmentBuffer()).a26())
            {
              Object localObject8;
              localObject10 = ((SegmentBufferManager)((SegmentController)localObject8).getSegmentBuffer()).a26().values().iterator();
              while (((Iterator)localObject10).hasNext())
              {
                localObject8 = (class_886)((Iterator)localObject10).next();
                ((class_219)localObject7).jdField_field_641_of_type_JavaUtilArrayList.add((class_884)localObject8);
              }
            }
          }
        }
        try
        {
          Collections.sort(this.jdField_field_641_of_type_JavaUtilArrayList, this.jdField_field_640_of_type_Class_309);
        }
        catch (Exception localException1)
        {
          localException1;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        Object localObject7;
        Object localObject10;
        Object localObject5;
        int m;
        int n;
        int i1;
        int i;
        int j;
        Iterator localIterator;
        Object localObject9;
        localInterruptedException;
      }
      catch (Exception localException3)
      {
        localException3;
      }
      this.jdField_field_640_of_type_JavaUtilArrayList.clear();
      localObject5 = this.jdField_field_641_of_type_JavaUtilArrayList;
      localObject7 = this;
      this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_Class_353.field_696 = 0L;
      ((class_219)localObject7).jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_108_of_type_Int = 0;
      ((class_219)localObject7).jdField_field_640_of_type_JavaUtilHashSet.clear();
      m = SegmentDrawer.jdField_field_98_of_type_Class_394.field_787 << 3;
      n = 0;
      for (i1 = 0; i1 < ((List)localObject5).size(); i1++)
      {
        localObject10 = (class_884)((List)localObject5).get(i1);
        if (n + ((class_884)localObject10).c() < m)
        {
          if ((!(((class_884)localObject10).a12() instanceof class_747)) || (!((class_747)((class_884)localObject10).a12()).a7()) || (((class_747)((class_884)localObject10).a12()).a75().contains(SegmentDrawer.a71(((class_219)localObject7).jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer).a20())))
          {
            n += ((class_884)localObject10).c();
            ((class_884)localObject10).b3(((class_219)localObject7).jdField_field_640_of_type_Class_223, true);
          }
        }
        else if (((class_884)localObject10).a16())
        {
          System.err.println("DEACTIVATING REGION: " + ((class_884)localObject10).a11() + " of " + ((class_884)localObject10).a12() + "; fill: " + n);
          ((class_884)localObject10).b3(((class_219)localObject7).jdField_field_640_of_type_Class_217, true);
        }
      }
      System.currentTimeMillis();
      this.jdField_field_641_of_type_JavaUtilArrayList.clear();
      System.currentTimeMillis();
      for (i = 0; i < this.jdField_field_640_of_type_JavaUtilArrayList.size(); i++)
      {
        (localObject5 = (class_657)this.jdField_field_640_of_type_JavaUtilArrayList.get(i)).a15().getAbsoluteSegmentWorldPositionClient((Segment)localObject5, this.field_642);
        this.field_642.sub(this.jdField_field_641_of_type_JavaxVecmathVector3f);
        ((class_657)localObject5).jdField_field_34_of_type_Float = this.field_642.lengthSquared();
      }
      try
      {
        Collections.sort(this.jdField_field_640_of_type_JavaUtilArrayList, this.jdField_field_640_of_type_Class_355);
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        System.err.println("[Exception] Catched: Resorting triggered by exception");
      }
      continue;
      i = 0;
      j = 0;
      if ((this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657.length != SegmentDrawer.jdField_field_98_of_type_Class_394.field_787) && (!jdField_field_640_of_type_Boolean)) {
        throw new AssertionError();
      }
      localIterator = this.jdField_field_640_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext())
      {
        localObject9 = (class_657)localIterator.next();
        if (j < SegmentDrawer.jdField_field_98_of_type_Class_394.field_787 - 100)
        {
          localObject7 = this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657[i];
          this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.field_109[i] = localObject7;
          this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657[i] = localObject9;
          this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657[i].a5(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_Int);
          localObject7 = localObject9;
          if ((((class_657)localObject7).jdField_field_34_of_type_Class_663 == class_663.field_936) || (((class_657)localObject7).jdField_field_34_of_type_Class_663 == class_663.field_937))
          {
            ((class_657)localObject7).jdField_field_34_of_type_Class_663 = class_663.field_934;
            ((class_657)localObject7).jdField_field_34_of_type_Int = 0;
            ((class_20)((class_657)localObject7).a15().getSegmentProvider()).a13((class_657)localObject7);
          }
          i++;
        }
        else if (((class_657)localObject9).b6())
        {
          this.jdField_field_640_of_type_JavaUtilHashSet.add(localObject9);
          ((class_657)localObject9).e(true);
        }
        j++;
      }
      synchronized (this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ArrayOfClass_657)
      {
        localObject7 = this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ArrayOfClass_657;
        localObject9 = this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_108_of_type_ArrayOfClass_657;
        SegmentDrawer.a69(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer, i);
        this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ArrayOfClass_657 = this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657;
        this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_108_of_type_ArrayOfClass_657 = this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.field_109;
        this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657 = ((class_657[])localObject7);
        this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.field_109 = ((class_657[])localObject9);
        if ((!jdField_field_640_of_type_Boolean) && (this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_ArrayOfClass_657 == this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_98_of_type_ArrayOfClass_657)) {
          throw new AssertionError("Pointers equal...");
        }
      }
      synchronized (SegmentDrawer.a65(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer))
      {
        SegmentDrawer.a65(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer).addAll(this.jdField_field_640_of_type_JavaUtilHashSet);
      }
      synchronized (this.jdField_field_640_of_type_JavaLangObject)
      {
        SegmentDrawer.a70(this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer);
        this.jdField_field_640_of_type_JavaLangObject.wait();
      }
      Thread.sleep(500L);
      this.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_field_106_of_type_Int += 1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_219
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */