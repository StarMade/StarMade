import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.SegmentOutOfBoundsException;
import org.schema.game.common.crashreporter.CrashReporter;
import org.schema.game.common.data.world.DeserializationException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public class class_1204
  extends class_880
{
  private static int jdField_field_14_of_type_Int;
  
  public class_1204(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    new HashMap();
  }
  
  public final void a()
  {
    jdField_field_14_of_type_Int -= 1;
  }
  
  protected final void a2(class_672 paramclass_672)
  {
    ((class_1041)paramclass_672.a15().getState()).a26().a(paramclass_672);
  }
  
  public final void c()
  {
    jdField_field_14_of_type_Int += 1;
  }
  
  public final void a8(Segment paramSegment) {}
  
  protected final boolean a10()
  {
    return true;
  }
  
  protected final class_48 a11()
  {
    return (class_48)((ArrayList)this.jdField_field_14_of_type_JavaUtilCollection).remove(0);
  }
  
  public final void a18(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      a12(paramInt1, paramInt2, paramInt3);
      return;
    }
    catch (IOException localIOException1)
    {
      (paramInt1 = localIOException1).printStackTrace();
      System.err.println("Exiting because of exception " + paramInt1);
      try
      {
        CrashReporter.a();
      }
      catch (IOException localIOException2)
      {
        localIOException2;
      }
      System.exit(0);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      (paramInt1 = localInterruptedException).printStackTrace();
      System.err.println("Exiting because of exception " + paramInt1);
      try
      {
        CrashReporter.a();
      }
      catch (IOException localIOException3)
      {
        localIOException3;
      }
      System.exit(0);
      return;
    }
    catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException)
    {
      localSegmentOutOfBoundsException;
    }
  }
  
  public final void a19(class_48 paramclass_48)
  {
    a18(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public final Segment a12(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!this.field_14.isInboundAbs(Segment.a9(paramInt1, paramInt2, paramInt3, new class_48()))) {
      throw new SegmentOutOfBoundsException(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3), this.field_14);
    }
    paramInt1 = new class_48(paramInt1, paramInt2, paramInt3);
    paramInt2 = a1(paramInt1, true);
    if ((!jdField_field_14_of_type_Boolean) && (!paramInt2.field_34.equals(paramInt1))) {
      throw new AssertionError();
    }
    d2(paramInt2);
    if ((!jdField_field_14_of_type_Boolean) && (!paramInt2.field_34.equals(paramInt1))) {
      throw new AssertionError();
    }
    return paramInt2;
  }
  
  public final boolean b2()
  {
    return false;
  }
  
  public final void b3(class_672 paramclass_672)
  {
    synchronized (this.field_14.getSegmentBuffer())
    {
      if (this.field_14.getSegmentBuffer().a3(paramclass_672.field_34)) {
        return;
      }
    }
    ??? = 0;
    synchronized (this.jdField_field_14_of_type_Class_720)
    {
      try
      {
        ??? = this.jdField_field_14_of_type_Class_720.a8(paramclass_672.field_34.field_475, paramclass_672.field_34.field_476, paramclass_672.field_34.field_477, paramclass_672);
      }
      catch (IOException localIOException)
      {
        (localObject2 = localIOException).printStackTrace();
        System.err.println(localObject2.getClass().getSimpleName() + " WARNING: COULD NOT READ " + paramclass_672.field_34 + ": " + paramclass_672 + ", " + paramclass_672.a15() + ":  RECREATING -- In case of EOF at timestamp: " + this.jdField_field_14_of_type_Class_720.a6());
        ??? = 2;
      }
      catch (DeserializationException localDeserializationException)
      {
        Object localObject2;
        (localObject2 = localDeserializationException).printStackTrace();
        ??? = 2;
        System.err.println(localObject2.getClass().getSimpleName() + " WARNING: COULD NOT READ " + paramclass_672.field_34 + ": " + paramclass_672 + ", " + paramclass_672.a15() + ":  RECREATING");
      }
    }
    if (??? == 2)
    {
      if (!this.field_14.getCreatorThread().a1())
      {
        if (paramclass_672.a16() == null) {
          this.field_14.getSegmentProvider().a25().assignData(paramclass_672);
        }
        paramclass_672.b2(0);
        paramclass_672.a16().setNeedsRevalidate(false);
        synchronized (paramclass_672.field_36)
        {
          synchronized (paramclass_672.a16())
          {
            this.field_14.getCreatorThread().a(paramclass_672);
          }
        }
        if (paramclass_672.g()) {
          a27(paramclass_672, paramclass_672.a16(), true);
        }
      }
      else
      {
        if (paramclass_672.a16() != null) {
          a27(paramclass_672, paramclass_672.a16(), false);
        }
        paramclass_672.b2(0);
      }
      paramclass_672.a46(System.currentTimeMillis());
      return;
    }
    if (??? == 1)
    {
      if (paramclass_672.a16() != null) {
        a27(paramclass_672, paramclass_672.a16(), false);
      }
      paramclass_672.b2(0);
    }
  }
  
  public final void c1(class_672 paramclass_672)
  {
    b3(paramclass_672);
  }
  
  protected final Collection a15()
  {
    return new ArrayList();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1204
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */