import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.Iterator;
import org.schema.game.common.controller.SegmentBufferManager;
import org.schema.game.common.controller.SegmentController;

public final class class_890
  implements Runnable
{
  public class_890(SegmentBufferManager paramSegmentBufferManager, Long2ObjectRBTreeMap paramLong2ObjectRBTreeMap) {}
  
  public final void run()
  {
    Iterator localIterator = this.jdField_field_1121_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator();
    while (localIterator.hasNext())
    {
      class_886 localclass_886 = (class_886)localIterator.next();
      long l1 = System.currentTimeMillis();
      ((class_884)localclass_886).b();
      long l2;
      if ((l2 = System.currentTimeMillis() - l1) > 20L) {
        System.err.println("[" + SegmentBufferManager.a32(this.jdField_field_1121_of_type_OrgSchemaGameCommonControllerSegmentBufferManager).getState() + "] WARNING: Segment Clear of " + localclass_886 + "(" + this.jdField_field_1121_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.a12() + ": " + this.jdField_field_1121_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.a12().getState() + ") took " + l2 + " ms");
      }
    }
    this.jdField_field_1121_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.a12().getElementClassCountMap().a2();
    this.jdField_field_1121_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.a12().resetTotalElements();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_890
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */