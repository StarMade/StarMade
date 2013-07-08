import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_213
  extends Thread
{
  private ArrayList jdField_field_637_of_type_JavaUtilArrayList;
  public Object field_637;
  public int field_637;
  public long field_637;
  
  public class_213(SegmentDrawer paramSegmentDrawer)
  {
    super("SegmentLightingUpdateThreadManager");
    SegmentDrawer.a66(paramSegmentDrawer, new HashSet(100));
    SegmentDrawer.b9(paramSegmentDrawer, new HashSet(100));
    this.jdField_field_637_of_type_JavaUtilArrayList = new ArrayList(2);
    for (int i = 0; i < 2; i++)
    {
      class_215 localclass_215;
      (localclass_215 = new class_215(paramSegmentDrawer, this)).start();
      this.jdField_field_637_of_type_JavaUtilArrayList.add(localclass_215);
    }
  }
  
  public final void a(class_215 paramclass_215, class_657 paramclass_657, boolean arg3)
  {
    if (??? != 0) {
      synchronized (SegmentDrawer.b10(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer))
      {
        SegmentDrawer.b10(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).remove(paramclass_657);
      }
    }
    synchronized (this.jdField_field_637_of_type_JavaUtilArrayList)
    {
      this.jdField_field_637_of_type_JavaUtilArrayList.add(paramclass_215);
      this.jdField_field_637_of_type_JavaUtilArrayList.notify();
      return;
    }
  }
  
  public final void a1(class_657 paramclass_657)
  {
    if (paramclass_657.g())
    {
      paramclass_657.e(false);
      return;
    }
    if (!paramclass_657.field_35)
    {
      paramclass_657.field_35 = true;
      SegmentDrawer.c4(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).add(paramclass_657);
    }
  }
  
  public final void a2()
  {
    if (!SegmentDrawer.c4(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).isEmpty())
    {
      synchronized (SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer))
      {
        SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).addAll(SegmentDrawer.c4(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer));
        SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).notify();
      }
      SegmentDrawer.c4(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).clear();
    }
  }
  
  public final void run()
  {
    try
    {
      while (class_969.a1() == null) {}
      while (!class_933.a1())
      {
        class_215 localclass_215 = null;
        synchronized (this.jdField_field_637_of_type_JavaUtilArrayList)
        {
          while (this.jdField_field_637_of_type_JavaUtilArrayList.isEmpty()) {
            this.jdField_field_637_of_type_JavaUtilArrayList.wait();
          }
          localclass_215 = (class_215)this.jdField_field_637_of_type_JavaUtilArrayList.remove(0);
        }
        synchronized (SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer))
        {
          while (SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).isEmpty()) {
            SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).wait();
          }
          class_357 localclass_357;
          class_357.a1(localclass_357 = new class_357()).set(class_969.a1().a83());
          Object localObject7 = null;
          Object localObject8 = SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).iterator();
          while (((Iterator)localObject8).hasNext())
          {
            class_657 localclass_657 = (class_657)((Iterator)localObject8).next();
            if ((localObject7 == null) || (localclass_357.a((class_657)localObject7, localclass_657) > 0)) {
              localObject7 = localclass_657;
            }
          }
          localObject8 = localObject7;
          SegmentDrawer.d2(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).remove(localObject8);
          ((class_657)localObject8).field_35 = false;
          synchronized (SegmentDrawer.b10(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer))
          {
            synchronized (SegmentDrawer.a65(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer))
            {
              if ((((class_657)localObject8).c()) || (SegmentDrawer.b10(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).contains(localObject8)))
              {
                a(localclass_215, (class_657)localObject8, false);
                continue;
              }
              SegmentDrawer.b10(this.jdField_field_637_of_type_OrgSchemaGameClientViewSegmentDrawer).add(localObject8);
            }
          }
          synchronized (((class_657)localObject8).field_34)
          {
            ((class_657)localObject8).d2(true);
            ((class_657)localObject8).field_36 = System.currentTimeMillis();
            ((class_657)localObject8).e(false);
          }
          localObject3.a((class_657)localObject8);
        }
      }
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      Object localObject5 = null;
      localInterruptedException.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_213
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */