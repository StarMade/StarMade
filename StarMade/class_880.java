import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.server.ServerState;

public abstract class class_880
{
  protected class_720 field_14;
  public SegmentController field_14;
  protected Collection field_14;
  public ArrayList field_14;
  protected HashSet field_14;
  private class_48 jdField_field_14_of_type_Class_48;
  private class_876 jdField_field_14_of_type_Class_876;
  public long field_14;
  private long jdField_field_15_of_type_Long;
  private class_48 jdField_field_15_of_type_Class_48;
  
  public class_880(SegmentController paramSegmentController)
  {
    this.jdField_field_14_of_type_JavaUtilHashSet = new HashSet();
    new Object();
    this.jdField_field_14_of_type_Class_48 = new class_48();
    this.jdField_field_15_of_type_Long = 0L;
    this.jdField_field_15_of_type_Class_48 = new class_48();
    this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_14_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_14_of_type_Class_720 = new class_720(paramSegmentController, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState() instanceof ServerState);
    this.jdField_field_14_of_type_Class_876 = ((class_361)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a();
    this.jdField_field_14_of_type_JavaUtilCollection = a15();
  }
  
  private void g()
  {
    while (!this.jdField_field_14_of_type_JavaUtilArrayList.isEmpty())
    {
      Segment localSegment = (Segment)this.jdField_field_14_of_type_JavaUtilArrayList.remove(0);
      d2((class_672)localSegment);
    }
  }
  
  protected final void d2(class_672 paramclass_672)
  {
    if ((!jdField_field_14_of_type_Boolean) && (paramclass_672 == null)) {
      throw new AssertionError();
    }
    if (paramclass_672 == null) {
      try
      {
        throw new IllegalArgumentException("SEGMENT IS NULL");
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    }
    paramclass_672.f1(true);
    if ((!jdField_field_14_of_type_Boolean) && (!paramclass_672.g()) && (paramclass_672.a16() == null)) {
      throw new AssertionError();
    }
    if (paramclass_672.a16() != null) {
      synchronized (paramclass_672.a16())
      {
        if (paramclass_672.a16().needsRevalidate()) {
          paramclass_672.a16().revalidateData();
        }
      }
    }
    paramclass_672.a11(true);
    paramclass_672.f1(false);
    this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a(paramclass_672);
    synchronized (this.jdField_field_14_of_type_JavaUtilCollection)
    {
      this.jdField_field_14_of_type_JavaUtilHashSet.remove(paramclass_672.field_34);
    }
    a8(paramclass_672);
  }
  
  public final void a20(SegmentData paramSegmentData)
  {
    synchronized (this.jdField_field_14_of_type_Class_876)
    {
      this.jdField_field_14_of_type_Class_876.a1(paramSegmentData, false);
      return;
    }
  }
  
  public abstract void a();
  
  protected final boolean a21(class_48 paramclass_48)
  {
    return this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.isInboundAbs(Segment.a9(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477, this.jdField_field_15_of_type_Class_48));
  }
  
  public final void a22(Collection paramCollection)
  {
    long l1 = System.currentTimeMillis();
    int i;
    if ((i = paramCollection.size()) > 0)
    {
      long l3;
      long l2;
      synchronized (this.jdField_field_14_of_type_JavaUtilCollection)
      {
        l3 = System.currentTimeMillis() - l1;
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext())
        {
          class_48 localclass_48 = (class_48)localIterator.next();
          if (!this.jdField_field_14_of_type_JavaUtilCollection.contains(localclass_48)) {
            if (!this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a3(localclass_48))
            {
              this.jdField_field_14_of_type_JavaUtilCollection.add(localclass_48);
            }
            else
            {
              class_672 localclass_672;
              if ((localclass_672 = (class_672)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a5(localclass_48)).a44() < 0L)
              {
                localclass_672.field_36 = true;
                this.jdField_field_14_of_type_JavaUtilCollection.add(localclass_48);
              }
            }
          }
        }
        paramCollection.clear();
        l2 = System.currentTimeMillis() - l1 - l3;
        paramCollection = null;
        ((class_715)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getController()).a().a2(true, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
      }
      long l4 = System.currentTimeMillis() - l1 - l2 - l3;
      long l5;
      if ((l5 = System.currentTimeMillis() - l1) > 10L) {
        System.err.println("[SEGMENTPROVIDER] WARNING: ANALYZING RETURN REQ: Size: " + i + "; Total: " + l5 + "; Sync: " + l3 + "; loop: " + l2 + "; notify: " + l4);
      }
    }
  }
  
  public class_672 a1(class_48 paramclass_48, boolean paramBoolean)
  {
    if ((!jdField_field_14_of_type_Boolean) && (paramclass_48.field_475 % 16 != 0)) {
      throw new AssertionError("Invalid request: " + paramclass_48);
    }
    if ((!jdField_field_14_of_type_Boolean) && (paramclass_48.field_476 % 16 != 0)) {
      throw new AssertionError("Invalid request: " + paramclass_48);
    }
    if ((!jdField_field_14_of_type_Boolean) && (paramclass_48.field_477 % 16 != 0)) {
      throw new AssertionError("Invalid request: " + paramclass_48);
    }
    Object localObject;
    if ((localObject = (class_672)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a5(paramclass_48)) != null)
    {
      a2((class_672)localObject);
      if ((!jdField_field_14_of_type_Boolean) && (!((class_672)localObject).field_34.equals(paramclass_48))) {
        throw new AssertionError();
      }
    }
    else if (this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      (localObject = new class_672(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController)).a24(paramclass_48);
    }
    else
    {
      (localObject = new class_657(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController)).a24(paramclass_48);
    }
    if (paramBoolean) {
      b3((class_672)localObject);
    } else {
      c1((class_672)localObject);
    }
    return localObject;
  }
  
  public final void e1(class_672 paramclass_672)
  {
    c();
    synchronized (this.jdField_field_14_of_type_Class_720)
    {
      synchronized (paramclass_672.field_35)
      {
        this.jdField_field_14_of_type_Class_720.a9(paramclass_672);
      }
    }
    a();
  }
  
  private void a23(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    synchronized (this.jdField_field_14_of_type_JavaUtilCollection)
    {
      this.jdField_field_14_of_type_Class_48.b(paramInt1, paramInt2, paramInt3);
      if ((!this.jdField_field_14_of_type_JavaUtilCollection.contains(this.jdField_field_14_of_type_Class_48)) || (paramBoolean))
      {
        this.jdField_field_14_of_type_JavaUtilCollection.add(new class_48(paramInt1, paramInt2, paramInt3));
        ((class_715)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getController()).a().a2(true, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
      }
      return;
    }
  }
  
  protected abstract void a2(class_672 paramclass_672);
  
  public final int a24()
  {
    return this.jdField_field_14_of_type_Class_876.a3();
  }
  
  public final SegmentData a25()
  {
    synchronized (this.jdField_field_14_of_type_Class_876)
    {
      this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer();
      return this.jdField_field_14_of_type_Class_876.a2();
    }
  }
  
  public final SegmentController a26()
  {
    return this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
  
  public final boolean c3()
  {
    if (a10())
    {
      class_48 localclass_481 = null;
      int i = 0;
      synchronized (this.jdField_field_14_of_type_JavaUtilCollection)
      {
        if (!this.jdField_field_14_of_type_JavaUtilCollection.isEmpty()) {
          do
          {
            localclass_481 = a11();
          } while ((this.jdField_field_14_of_type_JavaUtilHashSet.contains(localclass_481)) && (!this.jdField_field_14_of_type_JavaUtilCollection.isEmpty()));
        }
        if ((localclass_481 != null) && (!this.jdField_field_14_of_type_JavaUtilHashSet.contains(localclass_481)))
        {
          this.jdField_field_14_of_type_JavaUtilHashSet.add(localclass_481);
          i = 1;
        }
        if (this.jdField_field_14_of_type_JavaUtilCollection.isEmpty()) {
          ((class_715)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getController()).a().a2(false, this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
        }
      }
      if (i != 0)
      {
        a1(localclass_482, false);
        return true;
      }
    }
    return false;
  }
  
  public void b1() {}
  
  public abstract void c();
  
  public abstract void a8(Segment paramSegment);
  
  public final void e2()
  {
    System.err.println(this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " Purging DB of " + this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController);
    this.jdField_field_14_of_type_Class_720.a7();
  }
  
  protected final void a27(Segment paramSegment, SegmentData paramSegmentData, boolean paramBoolean)
  {
    if ((!jdField_field_14_of_type_Boolean) && (paramSegmentData == null)) {
      throw new AssertionError();
    }
    this.jdField_field_14_of_type_Class_876.a1(paramSegmentData, paramBoolean);
    paramSegment.a22(null);
  }
  
  protected abstract boolean a10();
  
  protected abstract class_48 a11();
  
  public void a18(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a2(paramInt1, paramInt2, paramInt3))
    {
      a23(paramInt1, paramInt2, paramInt3, false);
      return;
    }
    class_672 localclass_672;
    if ((localclass_672 = (class_672)this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a4(paramInt1, paramInt2, paramInt3)).a44() < 0L)
    {
      localclass_672.field_36 = true;
      a23(paramInt1, paramInt2, paramInt3, true);
    }
  }
  
  public void a19(class_48 paramclass_48)
  {
    a18(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public abstract Segment a12(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract boolean b2();
  
  public abstract void b3(class_672 paramclass_672);
  
  public abstract void c1(class_672 paramclass_672);
  
  protected abstract Collection a15();
  
  public void d1()
  {
    if (this.jdField_field_15_of_type_Long > 10000L)
    {
      if ((!jdField_field_14_of_type_Boolean) && (!this.jdField_field_14_of_type_OrgSchemaGameCommonControllerSegmentController.getCreatorThread().field_1138)) {
        throw new AssertionError();
      }
      this.jdField_field_15_of_type_Long = 0L;
    }
    else
    {
      this.jdField_field_15_of_type_Long += 1L;
    }
    g();
  }
  
  public final void f1()
  {
    this.jdField_field_14_of_type_Class_720.b();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_880
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */